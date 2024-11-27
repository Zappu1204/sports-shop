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
import java.util.List;

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
    //Thêm người dùng
    public boolean addUser(User user) throws SQLException {
    // Lấy userId lớn nhất hiện có
        String getMaxIdQuery = "SELECT MAX(CAST(SUBSTRING(userId, 3) AS UNSIGNED)) AS maxId FROM Account";
        try (PreparedStatement maxIdStatement = connection.prepareStatement(getMaxIdQuery);
             ResultSet maxIdResult = maxIdStatement.executeQuery()) {

            int maxId = 0;
            if (maxIdResult.next()) {
                maxId = maxIdResult.getInt("maxId");
            }

//            Tạo userId mới
            int newId = maxId + 1;
            String newUserId = "KH" + String.format("%06d", newId);
            user.setUserId(newUserId);

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
                return rowsInserted > 0;
            }
        }
    }

    // Đọc người dùng
    public User readUser(String username) throws SQLException {
    query = "SELECT * FROM Account WHERE userName = ?"; 
    statement = connection.prepareStatement(query);
    statement.setString(1, username);
    result = statement.executeQuery();
    
    if (result.next()) {
        // Khởi tạo đối tượng User từ dữ liệu trả về
        User user = new User(
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
    
    //Xoá người dùng
    public boolean deleteUser(String username) throws SQLException {
        String query = "DELETE FROM Account WHERE userName = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, username);
        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0; 
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

        // Truy vấn tất cả dữ liệu từ bảng Account
        query = "SELECT * FROM Account";
        statement = connection.prepareStatement(query);
        result = statement.executeQuery();

        // Khởi tạo mảng 2 chiều để lưu trữ kết quả
        String[][] accounts = new String[rows][columns];
        int aux = 0;

        // Duyệt qua kết quả truy vấn và điền vào mảng 2 chiều
        while(result.next()) {
            for(int i = 0; i < columns; i++) {
                accounts[aux][i] = result.getString(i + 1);  // Cột trong JDBC bắt đầu từ 1
            }
            aux++;
        }

        return accounts;
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

    String[][] readProductsTableData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
