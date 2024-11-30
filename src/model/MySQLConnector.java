/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author truongmanhtuan
 */
public class MySQLConnector {
    static final String dataBaseURL = "jdbc:mysql://localhost:3306/Shop_online";
    static final String username = "root";
    static final String password = "0966325274";
//    static final String dataBaseURL = "jdbc:mysql://172.206.126.247:22/Shop_online";
//    static final String username = "tuan";
//    static final String password = "Truongmanhtuan04@";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dataBaseURL, username, password);
    }
}
