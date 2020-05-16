/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import uts.isd.model.dao.DB;
import java.sql.*;
public class DBConnector extends DB{
    public DBConnector() throws ClassNotFoundException, SQLException {

    Class.forName(driver);

    conn = DriverManager.getConnection(URL, dbuser, dbpass);

    }

    public Connection openConnection(){

    return this.conn;

    }

    public void closeConnection() throws SQLException {

    this.conn.close();

    }
}