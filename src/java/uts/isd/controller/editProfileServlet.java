package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import uts.isd.model.Log;
import uts.isd.model.Account;
import uts.isd.model.dao.StaffDAO;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.UserAccessDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class editProfileServlet extends HttpServlet {
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("user");
    
    }
    
}
