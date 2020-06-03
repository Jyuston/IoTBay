package uts.isd.controller.reporting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.ReportingDAO;
import uts.isd.model.reporting.Report;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReportFormServlet extends HttpServlet {   
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        // Implements redirection to the edit form logic
        try {
            ArrayList<String> reportNames = ReportingDAO.getReportNames();
            request.setAttribute("editReport", "yes");
        } 
        
        catch (SQLException e) {
            request.setAttribute("error", e.getMessage());
            //request.getRequestDispatcher("reporting/errorPage.jsp").include(request, response);
        }

        finally {
            request.getRequestDispatcher("reportForm.jsp").include(request, response);
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        HttpSession session = request.getSession();
        
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

                // Update the report, then retrieve it with latest data and save it to the session
                // Note this is neccessary for data refresh
                session.setAttribute("report", ReportingDAO.update(originalName, createParamsArray(request))); 
                                              
                session.removeAttribute("editReport");
                response.sendRedirect("reporting/reportView.jsp");
            }

            // Catches an exception that may occur in the model when working with DAO objects
            catch (DAOException | SQLException | ParseException  e) {
                request.setAttribute("error", e.getMessage());

                // Clean up the session
                session.removeAttribute("editReport");
                session.removeAttribute("report");

                // Redirect back to error page
                request.getRequestDispatcher("reporting.jsp").include(request, response);
            }
        }
        
        // Logic for creating a new report
        else {  
            try {
                Report r = ReportingDAO.save(createParamsArray(request));
                session.setAttribute("report", r);
                response.sendRedirect("reporting/reportView.jsp");
            }          

            catch (DAOException | SQLException | ParseException e) {
                request.setAttribute("error", e.getMessage());
                // Redirects back to the ORIGINAL page
                request.getRequestDispatcher("reporting.jsp").include(request, response);
            }
        }
    }

    private String[] createParamsArray(HttpServletRequest request) throws ServletException {
        String params[] = {
            (String)request.getParameter("reportName"),
            (String)request.getParameter("reportDescription"),
            (String)request.getParameter("startDate"),
            (String)request.getParameter("endDate")
        };

        return params;
    }
}