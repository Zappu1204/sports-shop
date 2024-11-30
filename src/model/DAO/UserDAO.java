/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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


/**
 *
 * @author truongmanhtuan
 */
public class UserDAO {
    private final Connection connection;
    private String query;
    private PreparedStatement statement;
    private ResultSet result, resultCountRows;
    private int insertedLines = 0;
    
    public UserDAO() throws SQLException {
        connection = MySQLConnector.getConnection();
    }
    public boolean addUser(User user) throws SQLException {
        // Lấy tất cả các userId hiện có từ cơ sở dữ liệu
        String getAllIdsQuery = "SELECT CAST(SUBSTRING(userId, 3) AS UNSIGNED) AS numericId FROM Account";
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
            // Nếu thêm user thành công, tạo giỏ hàng cho user mới
            if (rowsInserted > 0) {
                Cart cart = new Cart();
                cart.setUserId(user.getUserId());
                cart.setCartId(user.getUserId()); // Cart ID sẽ trùng với User ID
                cart.setTotalPrice(0.0);

                // Sử dụng hàm addCart để thêm giỏ hàng
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

    // Đọc người dùng
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
    
    // cập nhật người 
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
    
    public boolean deleteUser(String username) throws SQLException {       
        try {
            // Bắt đầu giao dịch để đảm bảo tính nhất quán
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
                // Cam kết giao dịch
                connection.commit();
                return rowsDeleted > 0; // Trả về true nếu xóa thành công
            } else {
                return false; // Không tìm thấy userId
            }
        } catch (SQLException e) {
            // Nếu có lỗi, rollback giao dịch
            if (connection != null) {
                connection.rollback();
            }
            throw e; // Ném lại ngoại lệ để xử lý ngoài
        } finally {
            // Đảm bảo đóng các tài nguyên
            if (statement != null) {
                statement.close();
            }
        }
    }

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
    
    public String[][] searchUsers(String fullName) throws SQLException {
        String sql = "SELECT userId, userName, fullName, email, phoneNumber, address, gender, dateOfBirth " +
                     "FROM Users WHERE fullName LIKE ?";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + fullName + "%"); // Sử dụng LIKE để tìm kiếm gần đúng
            try (ResultSet rs = stmt.executeQuery()) {
                List<String[]> users = new ArrayList<>();
                while (rs.next()) {
                    users.add(new String[]{
                        rs.getString("userId"),
                        rs.getString("userName"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getString("gender"),
                        rs.getString("dateOfBirth")
                    });
                }
                return users.toArray(new String[0][]); // Chuyển danh sách thành mảng 2 chiều
            }
        }
    }


//     test thêm người dùng
//    public static void main(String[] args) {
//       
//        try {
//            // Tạo đối tượng UserDAO
//            UserDAO userDAO = new UserDAO();
//
//            // Lấy số lượng người dùng hiện tại và gán vào biến count
//            int currentCount = userDAO.getUserCount();
//            User.setCount(currentCount + 1); // Đặt giá trị đếm tiếp theo
//
//            // Tạo đối tượng User
//            User newUser = new User(
//                "manhtuan111",
//                "password123",
//                "Truong Manh Tuan",
//                "mtuan74204@gmail.com",
//                "0966325274",
//                "Bac Ninh",
//                "Nam",
//                "2004-07-04"
//            );
//
//            // Gọi phương thức addUser
//            boolean isAdded = userDAO.addUser(newUser);
//
//            if (isAdded) {
//                System.out.println("User added successfully!");
//            } else {
//                System.out.println("Failed to add user.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//   }
//    
//    test show
//    public static void main(String[] args) {
//    try {
//        // Tạo đối tượng UserDAO để kết nối với cơ sở dữ liệu
//        UserDAO userDAO = new UserDAO();
//
//        // Tìm kiếm người dùng theo tên đăng nhập
//        User foundUser = userDAO.readUser("manhtuan"); // Thay "manhtuan" bằng tên người dùng muốn tìm
//
//        // Kiểm tra kết quả và in ra thông tin người dùng nếu tìm thấy
//        if (foundUser != null) {
//            System.out.println("User found!");
//            System.out.println("UserID: " + foundUser.getUserId());
//            System.out.println("Full Name: " + foundUser.getFullName());
//            System.out.println("Email: " + foundUser.getEmail());
//            System.out.println("Phone Number: " + foundUser.getPhoneNumber());
//            System.out.println("Address: " + foundUser.getAddress());
//            System.out.println("Gender: " + foundUser.getGender());
//            System.out.println("Date of Birth: " + foundUser.getDateOfBirth());
//        } else {
//            System.out.println("User not found.");
//        }
//    } catch (SQLException e) {
//        // Xử lý ngoại lệ nếu có lỗi khi kết nối cơ sở dữ liệu hoặc thực thi câu truy vấn
//        e.printStackTrace();
//    }
//}
    
// cap nhat
//    public static void main(String[] args) {
//        try {
//            // Tạo đối tượng UserDAO
//            UserDAO userDAO = new UserDAO();
//
//            // Cập nhật thông tin người dùng
//            User updatedUser = new User("manhtuan", "newPassword123", "Truong Manh Tuan", "newemail@gmail.com", "0966325274", "Bac Ninh", "Nam", "2004-07-04");
//
//            boolean isUpdated = userDAO.updateUser(updatedUser);
//
//            if (isUpdated) {
//                System.out.println("User updated successfully!");
//            } else {
//                System.out.println("Failed to update user.");
//            }
//        } catch (SQLException e) {
//            // Xử lý ngoại lệ nếu có lỗi khi kết nối cơ sở dữ liệu hoặc thực thi câu truy vấn
//            e.printStackTrace();
//        }
//    }
//    
    
//    //Xoá người dùng
//    public static void main(String[] args) {
//        try {
//            UserDAO userDAO = new UserDAO();
//
//            // Xóa người dùng có tên đăng nhập là "manhtuan"
//            boolean isDeleted = userDAO.deleteUser("0966325274");
//
//            if (isDeleted) {
//                System.out.println("User deleted successfully!");
//            } else {
//                System.out.println("Failed to delete user.");
//            }
//        } catch (SQLException e) {
//            // Xử lý ngoại lệ nếu có lỗi khi kết nối cơ sở dữ liệu hoặc thực thi câu truy vấn
//            e.printStackTrace();
//        }
//    }
//    public static void main(String[] args) {
//        try {
//            // Tạo đối tượng UserDAO để kết nối và thao tác với cơ sở dữ liệu
//            UserDAO userDAO = new UserDAO();
//
//            // Gọi phương thức readAccountsTableData để lấy dữ liệu từ bảng Account
//            String[][] accounts = userDAO.readAccountsTableData();
//
//            // Kiểm tra nếu bảng không có dữ liệu
//            if (accounts.length == 0) {
//                System.out.println("No data found in the Account table.");
//            } else {
//                // In tên các cột của bảng (giả sử bảng có 9 cột)
//                System.out.println("Account data:");
//                System.out.println("userId\tuserName\tpassword\tfullName\temail\tphoneNumber\taddress\tgender\tdateOfBirth");
//
//                // In các dữ liệu trong bảng
//                for (int i = 0; i < accounts.length; i++) {
//                    for (int j = 0; j < accounts[i].length; j++) {
//                        System.out.print(accounts[i][j] + "\t"); // In từng giá trị trong bảng
//                    }
//                    System.out.println(); // Xuống dòng sau mỗi hàng
//                }
//            }
//        } catch (SQLException e) {
//            // Xử lý lỗi SQL
//            e.printStackTrace();
//        }
//    }

}
