package uts.isd.controller.reporting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.ReportingDAO;
import uts.isd.model.reporting.Report;

import java.io.IOException;
import java.util.ArrayList;

public class ReportingServlet extends HttpServlet {
    private static final ReportingDAO DAO = new ReportingDAO();
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        try {
            ArrayList<String> reportNames = DAO.getReportNames();
            HttpSession session = request.getSession(true);
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
        try {
            Report r = DAO.get(request.getParameter("selectedReport"));
            HttpSession session = request.getSession(true);
            session.setAttribute("report", r);
            response.sendRedirect("reporting/reportView1.jsp");          
        } 
        
        catch (Throwable exception) {
            //TODO: handle exception
            System.out.println(exception);
        }
    }
}