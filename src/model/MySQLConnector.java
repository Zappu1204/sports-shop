package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    //local
    static final String dataBaseURL = "jdbc:mysql://localhost:3306/Shop_online";
    static final String username = "root";
    static final String password = "0966325274";
    //server
//    static final String dataBaseURL = "jdbc:mysql://172.206.126.247:22/Shop_online";
//    static final String username = "tuan";
//    static final String password = "Truongmanhtuan04@";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dataBaseURL, username, password);
    }
}
