package uts.isd.controller.reporting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.ReportingDAO;
import uts.isd.model.reporting.Report;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class ReportingServlet extends HttpServlet {   
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        // Implements logic for retrieving a list of report names and displaying them in the view
        try {
            HttpSession session = request.getSession();
            ArrayList<String> reportNames = ReportingDAO.getReportNames();
            session.setAttribute("reportNames", reportNames);
            response.sendRedirect("reporting.jsp");
        } 
        
        catch (Throwable exception) {
            //TODO: handle exception
            System.out.println(exception);
        }
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        HttpSession session = request.getSession();
        
        // Implements logic for creating a new report
        if (request.getParameter("newReportCreated") != null && request.getParameter("newReportCreated").equals("yes")) {
            // validate (check the dates)
            // create new report
            String reportName = (String)request.getParameter("reportName");
            String description = (String)request.getParameter("reportDescription");
            String startDate = (String)request.getParameter("startDate");
            String endDate = (String)request.getParameter("endDate");
            // save the report
            try {
                // Create a skeleton report (temp)
                Report rInitial = new Report(reportName, description, startDate, endDate);

                // Create the record in the database
                ReportingDAO.save(rInitial);

                // Retrieve the report from the database and instantiate the full report object
                Report r = ReportingDAO.get(reportName);

                // Save the report to the session and redirect to the report view page
                session.setAttribute("report", r);
                response.sendRedirect("reporting/reportView.jsp");
            }

            catch (Throwable exception) {
                //TODO: handle exception
                System.out.println(exception);
            }
            
        }

        // Implements logic for exiting a report view and cleaning the session correctly
        else if (request.getParameter("reportExit") != null && request.getParameter("reportExit").equals("yes")) {
            // Remove all reporting functionality related attributes from the session
            session.removeAttribute("report");
            session.removeAttribute("editReport");
            session.removeAttribute("modifyingReport");

            try {
                // Retrieve a fresh list of report names
                ArrayList<String> reportNames = ReportingDAO.getReportNames();
                
                // Save them to the session
                session.setAttribute("reportNames", reportNames);

                // Redirect back to the home page
                response.sendRedirect("reporting.jsp");            
            }

            catch (Exception e) {
                // handle
            }
            
        }

        // Implements logic for opening the view for an existing report
        else {
            if (!request.getParameter("selectedReport").equals("Stock Report")) {
                try {
                    // Retrieve the selected report name, and instantiate a new report object from data in the db
                    Report r = ReportingDAO.get(request.getParameter("selectedReport"));
    
                    // Save the report to the session
                    session.setAttribute("report", r);
    
                    // Redirect to the report view
                    response.sendRedirect("reporting/reportView.jsp");          
                } 
                
                catch (Throwable exception) {
                    //TODO: handle exception
                    System.out.println(exception);
                }
            }

            else {
                try {
                    Report r = new Report(ReportingDAO.getProductStock(), ReportingDAO.getProductCategories());

                    session.setAttribute("report", r);

                    response.sendRedirect("reporting/stockReport.jsp");
                }

                catch (Exception e) {
                    // handle exception
                }
                
            }
            
        }
    }
}