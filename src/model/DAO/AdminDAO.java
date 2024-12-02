package model.DAO;

import model.Account;
import java.sql.*;
import model.MySQLConnector;

public class AdminDAO {
    //Đọc
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
    //Sửa
    public boolean updatePassword(String username, String Password) throws SQLException {
        String sqlUpdate = "UPDATE Admin SET passWord = ? WHERE userName = ?";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sqlUpdate)) {
            stmt.setString(1, Password); 
            stmt.setString(2, username); 

            int rowsAffected = stmt.executeUpdate(); 
            return rowsAffected > 0;
        }
    }
}
