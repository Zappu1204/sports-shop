package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Cart;

import model.MySQLConnector;
import model.User;

public class UserDAO {
    private final Connection connection;
    private String query;
    private PreparedStatement statement;
    private ResultSet result, resultCountRows;
    private int insertedLines = 0;
    
    public UserDAO() throws SQLException {
        connection = MySQLConnector.getConnection();
    }
    //Thêm
    public boolean addUser(User user) throws SQLException {
        // Lấy tất cả các userId hiện có từ cơ sở dữ liệu
        String getAllIdsQuery = "SELECT CAST(SUBSTRING(userId, 3, 8) AS INT) AS numericId FROM Account";
        List<Integer> existingIds = new ArrayList<>();
        try (PreparedStatement getAllIdsStatement = connection.prepareStatement(getAllIdsQuery);
             ResultSet rs = getAllIdsStatement.executeQuery()) {
            while (rs.next()) {
                existingIds.add(rs.getInt("numericId"));
            }
        }
        // Xác định min và max ID
        int minId = 1; // ID bắt đầu từ 1
        int maxId = existingIds.isEmpty() ? 0 : Collections.max(existingIds);
        // Tìm ID còn thiếu trong khoảng min-max
        int newId = -1; // -1 để nhận biết không tìm thấy ID còn thiếu
        for (int i = minId; i <= maxId; i++) {
            if (!existingIds.contains(i)) {
                newId = i;
                break;
            }
        }
        // Nếu không có ID nào còn thiếu, sử dụng ID mới lớn hơn max
        if (newId == -1) {
            newId = maxId + 1;
        }
        // Tạo userId mới và thiết lập cho user
        String newUserId = "KH" + String.format("%06d", newId);
        user.setUserId(newUserId);
        // Chèn user mới vào cơ sở dữ liệu
        String query = "INSERT INTO Account (userId, userName, password, fullName, email, phoneNumber, address, gender, dateOfBirth) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Thiết lập giá trị cho các cột
            statement.setString(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFullName());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhoneNumber());
            statement.setString(7, user.getAddress());
            statement.setString(8, user.getGender());
            statement.setString(9, user.getDateOfBirth());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                Cart cart = new Cart();
                cart.setUserId(user.getUserId());
                cart.setCartId(user.getUserId());
                cart.setTotalPrice(0.0);
                CartDAO cartDAO = new CartDAO();
                boolean cartCreated = cartDAO.addCart(cart);
                if (!cartCreated) {
                    throw new SQLException("Failed to create cart for user: " + user.getUserId());
                }
                return true;
            }
            return false;
        }
    }

    // Đọc 
    public User readUser(String username) throws SQLException {
        query = "SELECT * FROM Account WHERE userName = ?"; 
        statement = connection.prepareStatement(query);
        statement.setString(1, username);
        result = statement.executeQuery();

        if (result.next()) {
            User user = new User(
                result.getString("userId"),
                result.getString("userName"),
                result.getString("password"), 
                result.getString("fullName"), 
                result.getString("email"), 
                result.getString("phoneNumber"), 
                result.getString("address"),  
                result.getString("gender"), 
                result.getString("dateOfBirth") 
            );
            return user;
        }
        return null;
    }
    
    // cập nhật
    public boolean updateUser(User user) throws SQLException {
        StringBuilder queryBuilder = new StringBuilder("UPDATE Account SET ");
        boolean firstField = true;
        if (user.getFullName() != null && !user.getFullName().isEmpty()) {
            if (!firstField) queryBuilder.append(", ");
            queryBuilder.append("fullName = ?");
            firstField = false;
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            if (!firstField) queryBuilder.append(", ");
            queryBuilder.append("password = ?");
            firstField = false;
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            if (!firstField) queryBuilder.append(", ");
            queryBuilder.append("email = ?");
            firstField = false;
        }
        if (user.getPhoneNumber() != null && !user.getPhoneNumber().isEmpty()) {
            if (!firstField) queryBuilder.append(", ");
            queryBuilder.append("phoneNumber = ?");
            firstField = false;
        }
        if (user.getAddress() != null && !user.getAddress().isEmpty()) {
            if (!firstField) queryBuilder.append(", ");
            queryBuilder.append("address = ?");
            firstField = false;
        }
        if (user.getGender() != null && !user.getGender().isEmpty()) {
            if (!firstField) queryBuilder.append(", ");
            queryBuilder.append("gender = ?");
            firstField = false;
        }
        if (user.getDateOfBirth() != null && !user.getDateOfBirth().isEmpty()) {
            if (!firstField) queryBuilder.append(", ");
            queryBuilder.append("dateOfBirth = ?");
            firstField = false;
        }
        queryBuilder.append(" WHERE userName = ?");
        // Câu lệnh SQL hoàn chỉnh
        String query = queryBuilder.toString();
        statement = connection.prepareStatement(query);
        int index = 1;
        if (user.getFullName() != null && !user.getFullName().isEmpty()) {
            statement.setString(index++, user.getFullName());
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            statement.setString(index++, user.getPassword());
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            statement.setString(index++, user.getEmail());
        }
        if (user.getPhoneNumber() != null && !user.getPhoneNumber().isEmpty()) {
            statement.setString(index++, user.getPhoneNumber());
        }
        if (user.getAddress() != null && !user.getAddress().isEmpty()) {
            statement.setString(index++, user.getAddress());
        }
        if (user.getGender() != null && !user.getGender().isEmpty()) {
            statement.setString(index++, user.getGender());
        }
        if (user.getDateOfBirth() != null && !user.getDateOfBirth().isEmpty()) {
            statement.setString(index++, user.getDateOfBirth());
        }
        statement.setString(index, user.getUserName());
        int insertedLines = statement.executeUpdate();
        return insertedLines > 0;
    }
    
    //Xoá người dùng thì xoá giỏ hàng người dùng đó
    public boolean deleteUser(String username) throws SQLException {       
        try {
            connection.setAutoCommit(false);         
            // Lấy userId của người dùng từ bảng Account
            String getUserIdQuery = "SELECT userId FROM Account WHERE userName = ?";
            statement = connection.prepareStatement(getUserIdQuery);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            String userId = null;
            if (resultSet.next()) {
                userId = resultSet.getString("userId");
            }
            if (userId != null) {
                // Xóa các mục trong bảng CartItems có liên quan đến userId
                String deleteCartItemsQuery = "DELETE FROM CartItems WHERE cartId IN (SELECT cartId FROM Cart WHERE userId = ?)";
                statement = connection.prepareStatement(deleteCartItemsQuery);
                statement.setString(1, userId);
                statement.executeUpdate();
                // Xóa các mục trong bảng Cart có liên quan đến userId
                String deleteCartQuery = "DELETE FROM Cart WHERE userId = ?";
                statement = connection.prepareStatement(deleteCartQuery);
                statement.setString(1, userId);
                statement.executeUpdate();
                // Xóa người dùng khỏi bảng Account
                String deleteUserQuery = "DELETE FROM Account WHERE userName = ?";
                statement = connection.prepareStatement(deleteUserQuery);
                statement.setString(1, username);
                int rowsDeleted = statement.executeUpdate();
                connection.commit();
                return rowsDeleted > 0; 
            } else {
                return false;
            }
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e; 
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    //Đọc trả về mảng 2 chiều
    public String[][] readAccountsTableData() throws SQLException {
        // Đếm số lượng dòng trong bảng Account
        resultCountRows = connection.prepareStatement("SELECT COUNT(*) FROM Account").executeQuery();
        int rows = 0, columns = 9;
        if(resultCountRows.next()) {
            rows = resultCountRows.getInt(1);
        }
        if (rows == 0) {
            return new String[0][0];
        }
        query = "SELECT * FROM Account";
        statement = connection.prepareStatement(query);
        result = statement.executeQuery();
        String[][] accounts = new String[rows][columns];
        int aux = 0;
        while(result.next()) {
            for(int i = 0; i < columns; i++) {
                accounts[aux][i] = result.getString(i + 1);
            }
            aux++;
        }
        return accounts;
    }
    
}
