/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import model.Account;

/**
 *
 * @author truongmanhtuan
 */
import java.sql.*;
import model.MySQLConnector;

public class AdminDAO {
    public Account readAdmin(String username) throws SQLException {
        String sql = "SELECT userName, passWord FROM Admin WHERE userName = ?";
        try (Connection connection = MySQLConnector.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Account(rs.getString("userName"), rs.getString("passWord"));
                }
            }
        }
        return null; 
    }
}
