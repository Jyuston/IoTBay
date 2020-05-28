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

public class ReportFormServlet extends HttpServlet {   
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        try {
            HttpSession session = request.getSession();
            // Connection dbConnection = (Connection) session.getAttribute("dbConnection");
            // ReportingDAO DAO = new ReportingDAO(dbConnection);
            ArrayList<String> reportNames = ReportingDAO.getReportNames();
            session.setAttribute("editReport", "yes");
            response.sendRedirect("reportForm.jsp");
        } 
        
        catch (Throwable exception) {
            //TODO: handle exception
            System.out.println(exception);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        HttpSession session = request.getSession();
        //Connection dbConnection = (Connection) session.getAttribute("dbConnection");
        //ReportingDAO DAO = new ReportingDAO(dbConnection);

        if (request.getParameter("updateReport") != null && request.getParameter("updateReport").equals("yes")) {
            // validate (check the dates)
            // create new report
            String reportName = (String)request.getParameter("reportName");
            String description = (String)request.getParameter("reportDescription");
            String startDate = (String)request.getParameter("startDate");
            String endDate = (String)request.getParameter("endDate");
            // save the report
            try {
                // Create a skeleton report (temp)
                Report report = (Report)session.getAttribute("report");
                String originalName = report.getName();
                // Create the record in the database
                report.setReportName(reportName);
                report.setDescription(description);
                report.setReportStartDate(startDate);
                report.setReportEndDate(endDate);
                // Update the report
                ReportingDAO.update(report, originalName);
                // Retrieve an updated report (necessary for data refresh)
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

        else if (request.getParameter("newReport") != null && request.getParameter("newReport").equals("yes")) {
            // do stuff
            String reportName = (String)request.getParameter("reportName");
            String description = (String)request.getParameter("reportDescription");
            String startDate = (String)request.getParameter("startDate");
            String endDate = (String)request.getParameter("endDate");

            try {
                // Instantiate a temp report object
                Report tempReport = new Report(reportName, description, startDate, endDate);
                // Save the 'temp' report
                ReportingDAO.save(tempReport);

                // Retrieve the report + data from the db via the DAO
                Report r = ReportingDAO.get(reportName);
                session.setAttribute("report", r);
                response.sendRedirect("reporting/reportView.jsp");
            }

            catch (Throwable exception) {
                // handle exception
            }
        }

        else {
            Report r = (Report)session.getAttribute("report");
            try {
                ReportingDAO.delete(r);
                session.setAttribute("report", null);
                response.sendRedirect("ReportingServlet");
            }

            catch (Throwable exception) {
                // handle
            }
            
            
        }
    }
}