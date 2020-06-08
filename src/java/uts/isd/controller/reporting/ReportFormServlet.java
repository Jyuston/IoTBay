package uts.isd.controller.reporting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.ReportingDAO;
import uts.isd.model.reporting.Report;
import uts.isd.model.Account;
import uts.isd.controller.Validator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReportFormServlet extends HttpServlet {   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        HttpSession session = request.getSession();

        if (accessAllowed(request, session)) {
            // Implements redirection to the edit form logic
            try {
                ArrayList<String> reportNames = ReportingDAO.getReportNames();
                request.setAttribute("editReport", "true");
            } 
            
            catch (SQLException e) {
                request.setAttribute("error", e.getMessage());
            }

            finally {
                request.getRequestDispatcher("reportForm.jsp").include(request, response);
            }
        }

        else {
            request.setAttribute("error", "Unauthorised Access to Reporting!");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator(request);
        
        // Logic for when a report deletion is selected
        if (request.getParameter("deleteReport") != null && request.getParameter("deleteReport").equals("yes")) {
            Report r = (Report)session.getAttribute("report");
            try {
                ReportingDAO.delete(r);
                
                // Remove all report functionality related attributes from the session
                session.removeAttribute("report");

                // Redirect to the home page
                response.sendRedirect("ReportingServlet");
            }

            // Catches a DAO Exception
            catch (DAOException | SQLException e) {
                request.setAttribute("error", e.getMessage());

                // Clear the report from the session if still exists
                session.removeAttribute("report");

                // Redirects to error page to display error message to user
                request.getRequestDispatcher("reporting.jsp").include(request, response);
            }
        }

        // Logic for when a report edit is desired
        else if (request.getParameter("updateReport") != null && request.getParameter("updateReport").equals("yes")) {
            try {
                // Get the report from the session
                Report report = (Report)session.getAttribute("report");
                
                // Get its original name
                String originalName = report.getName();

                // Construct Parameters
                String[] params = createParamsArray(request);

                // Run validation check
                validator.validateReportDates(params[2], params[3])
                        .validateReportName(params[0])
                        .validateReportDescription(params[1]);

                // Check if validation failed
                if (validator.failed()) {
                    request.getRequestDispatcher("reportForm.jsp").include(request, response);
                    return;
                }

                // Update the report, then retrieve it with latest data and save it to the session
                // Note this is neccessary for data refresh
                session.setAttribute("report", ReportingDAO.update(originalName, params, isNameChanged(params, report))); 
                
                // Clean session
                session.removeAttribute("editReport");
                response.sendRedirect("reportView.jsp");
            }

            // Catches an exception that may occur in the model when working with DAO objects
            catch (Exception e) {
                request.setAttribute("error", e.getMessage());

                // Redirect back
                request.getRequestDispatcher("reportForm.jsp").include(request, response);
            }
        }
        
        // Logic for creating a new report
        else {  
            try {
                // Construct Parameters
                String[] params = createParamsArray(request);

                // Run validation check
                validator.validateReportDates(params[2], params[3])
                        .validateReportName(params[0])
                        .validateReportDescription(params[1]);


                // Check if validation failed
                if (validator.failed()) {
                    request.getRequestDispatcher("reporting.jsp").include(request, response);
                    return;
                }
                
                Report r = ReportingDAO.save(createParamsArray(request));
                session.setAttribute("report", r);
                response.sendRedirect("reportView.jsp");
            }          

            catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                
                // Redirects back to the ORIGINAL page
                request.getRequestDispatcher("reporting.jsp").include(request, response);
            }
        }
    }

    private String[] createParamsArray(HttpServletRequest request) throws ServletException {
        String[] params = {
            (String)request.getParameter("reportName"),
            (String)request.getParameter("reportDescription"),
            (String)request.getParameter("startDate"),
            (String)request.getParameter("endDate")
        };

        return params;
    }

    private boolean isNameChanged(String[] params, Report r) throws ServletException {
        // Checks to see if name was changed
        return !params[0].equals(r.getName());
    }

    // Checks if the user is allowed to access the page (i.e. is a staff member)
    private boolean accessAllowed(HttpServletRequest r, HttpSession s) throws DAOException {
        try {
            Account a = (Account) s.getAttribute("user");
            if (a.isStaff()) {
                return true;
            }
        }

        catch (Exception e) {
            r.setAttribute("error", e);
        }

        return false;
    }
}