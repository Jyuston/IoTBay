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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReportFormServlet extends HttpServlet {   
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        // Implements redirection to the edit form logic
        try {
            ArrayList<String> reportNames = ReportingDAO.getReportNames();
            request.setAttribute("editReport", "yes");
            request.getRequestDispatcher("reportForm.jsp").include(request, response);
        } 
        
        catch (SQLException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("reporting/errorPage.jsp").include(request, response);
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
            catch (DAOException e) {
                request.setAttribute("error", e.getMessage());

                // Clear the report from the session if still exists
                session.removeAttribute("report");

                // Redirects to error page to display error message to user
                request.getRequestDispatcher("reporting/errorPage.jsp").include(request, response);
            }

            catch (SQLException e) {
                request.setAttribute("error", e.getMessage());

                // Clear the report from the session if still exists
                session.removeAttribute("report");

                // Redirects to error page to display error message to user
                request.getRequestDispatcher("reporting/errorPage.jsp").include(request, response);
            }
        }

        // Logic for when a report edit is desired
        else if (request.getParameter("updateReport") != null && request.getParameter("updateReport").equals("yes")) {
            // create new report
            String reportName = (String)request.getParameter("reportName");

            // save the report
            try {
                // Create a skeleton report (temp)
                Report report = (Report)session.getAttribute("report");
                String originalName = report.getName();

                // Create the record in the database
                report.setReportName(reportName);
                report.setDescription((String)request.getParameter("reportDescription"));
                report.setReportStartDate((String)request.getParameter("startDate"));
                report.setReportEndDate((String)request.getParameter("endDate"));

                // Update the report
                ReportingDAO.update(report, originalName);
                
                // Retrieve an updated report (necessary for data refresh)
                Report r = ReportingDAO.get(reportName);

                // Save the report to the session and redirect to the report view page
                session.setAttribute("report", r);                                
                session.removeAttribute("editReport");
                response.sendRedirect("reporting/reportView.jsp");
            }

            // Catches a DAO Exception
            catch (DAOException e) {
                request.setAttribute("error", e.getMessage());
                session.removeAttribute("editReport");
                session.removeAttribute("report");
                request.getRequestDispatcher("reporting/errorPage.jsp").include(request, response);
            }
            
            // Catches an SQL Exception
            catch (SQLException e) {
                // Clean up the session
                request.setAttribute("error", e.getMessage());
                session.removeAttribute("editReport");
                session.removeAttribute("report");

                // Redirect to error page and display message
                request.getRequestDispatcher("reporting/errorPage.jsp").include(request, response);
            }
        }
    }
}