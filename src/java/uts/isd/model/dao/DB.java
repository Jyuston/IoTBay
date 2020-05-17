package uts.isd.model.dao;

import java.sql.Connection;

/**
 * Super class of DAO classes that stores the database information
 */

abstract class DB {
    String URL = "jdbc:derby://localhost:1527/testdb"; //replace this string with your jdbc:derby local host url
    String db = "TST";//name of the database
    String dbuser = "tst";//db root user
    String dbpass = "admin"; //db root password
    String driver = "org.apache.derby.jdbc.ClientDriver"; //jdbc client driver - built in with NetBeans
    Connection conn; //connection null-instance to be initialized in sub-classes
}