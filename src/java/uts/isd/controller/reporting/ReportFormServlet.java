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
        
        // Logic for when a report deletion is selected
        if (request.getParameter("deleteReport") != null && request.getParameter("deleteReport").equals("yes")) {
            Report r = (Report)session.getAttribute("report");
            try {
                ReportingDAO.delete(r);
                session.setAttribute("editReport", "null");
                session.setAttribute("modifyingReport", "false");
                session.setAttribute("deleteReport", "");
                session.setAttribute("report", null);
                response.sendRedirect("ReportingServlet");
            }

            catch (Throwable exception) {
                // handle
            }
        }

        // Logic for when a report edit is desired
        else if (request.getParameter("updateReport") != null && request.getParameter("updateReport").equals("yes")) {
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
                session.setAttribute("editReport", null);
                response.sendRedirect("reporting/reportView.jsp");
            }

            catch (Throwable exception) {
                //TODO: handle exception
                System.out.println(exception);
            }
            
        }
    }
}