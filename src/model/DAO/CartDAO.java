/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

/**
 *
 * @author truongmanhtuan
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Cart;
import model.CartItemSQL;
import model.MySQLConnector;
import model.Product;

public class CartDAO {
    private Connection connection;
    public CartDAO() throws SQLException {
        connection = MySQLConnector.getConnection();
    }
    
    //Thêm giỏ hàng
    public boolean addCart(Cart cart) {      
        // Thiết lập cartId chính là userId
        String cartId = cart.getUserId();
        cart.setCartId(cartId);
        // Thêm giỏ hàng vào cơ sở dữ liệu
        String sql = "INSERT INTO Cart (cartId, userId, totalPrice) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cartId);
            ps.setString(2, cart.getUserId());
            ps.setDouble(3, cart.getTotalPrice());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Tìm kiếm gior hàng
    public Cart getCartByUserId(String userId) {
        String sql = "SELECT * FROM Cart WHERE userId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userId);  // Thiết lập userId vào truy vấn
            ResultSet rs = ps.executeQuery();  // Thực hiện truy vấn

            if (rs.next()) {
                String cartId = rs.getString("cartId");
                double totalPrice = rs.getDouble("totalPrice");
                return new Cart(cartId, userId, totalPrice);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Tính tổng giá trị giỏ hàng từ bảng CartItems và Product
    public double calculateTotalPrice(String cartId) {
        String sql = "SELECT SUM(p.price * ci.quantity) AS totalPrice " +
                     "FROM CartItems ci " +
                     "JOIN Product p ON ci.productId = p.productId " +
                     "WHERE ci.cartId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cartId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("totalPrice");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    // Cập nhật tổng giá trị giỏ hàng sau khi tính toán
    public boolean updateTotalPrice(String cartId, double totalPrice) {
        String sql = "UPDATE Cart SET totalPrice = ? WHERE cartId = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, totalPrice);
            ps.setString(2, cartId);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm sản phẩm vào giỏ hàng
    public boolean addProductToCart(String cartId, String productId, int quantity) {
        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        if (isProductInCart(cartId, productId)) {          
            return updateProductQuantity(cartId, productId, quantity);
        } else {          
            String sql = "INSERT INTO CartItems (cartId, productId, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, cartId);
                ps.setString(2, productId);
                ps.setInt(3, quantity);
                int result = ps.executeUpdate();
                return result > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
    
    //Đếm số lượng tring giỏ hàng
    public int getProductQuantityInCart(String cartId, String productId) throws SQLException {
        String query = "SELECT quantity FROM CartItems WHERE cartId = ? AND productId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cartId);
            statement.setString(2, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("quantity");
            } else {
                return 0; // Nếu sản phẩm chưa có trong giỏ, trả về 0
            }
        }
    }

    
    // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
    private boolean isProductInCart(String cartId, String productId) {
        String sql = "SELECT 1 FROM CartItems WHERE cartId = ? AND productId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cartId);
            ps.setString(2, productId);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Nếu có kết quả, sản phẩm đã tồn tại
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    public boolean updateProductQuantity(String userId, String productId, int quantity) {
        String sql = "UPDATE CartItems SET quantity = ? WHERE cartId = ? AND productId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, quantity); // Đặt giá trị mới thay vì cộng vào giá trị cũ
            ps.setString(2, userId); // Sử dụng userId làm cartId
            ps.setString(3, productId); // Định danh productId
            int result = ps.executeUpdate();
            return result > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi
        }
    }
    
    //Xoá
    public boolean deleteProductCart(String userId, String productId) throws SQLException {
        String sql = "DELETE FROM CartItems WHERE cartId = ? AND productId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userId); // cartId chính là userId
            ps.setString(2, productId);
            int result = ps.executeUpdate();
            return result > 0; // Trả về true nếu xóa thành công
        }
    }
    //Đọc giỏ hàng    
    public String[][] readCartTableData(String cartId) throws SQLException {
    // Bước 1: Đếm số lượng dòng dữ liệu trong giỏ hàng
        String countQuery = "SELECT COUNT(*) FROM CartItems ci JOIN Product p ON ci.productId = p.productId WHERE ci.cartId = ?";
        PreparedStatement countStatement = connection.prepareStatement(countQuery);
        countStatement.setString(1, cartId);
        ResultSet countResult = countStatement.executeQuery();
        int rows = 0, columns = 5; 
        if (countResult.next()) {
            rows = countResult.getInt(1);
        }
        if (rows == 0) {
            return new String[0][0];
        }
        // Truy vấn lấy dữ liệu chi tiết của giỏ hàng
        String query = "SELECT p.productId, p.name, p.price, ci.quantity, (p.price * ci.quantity) AS total " +
                       "FROM CartItems ci " +
                       "JOIN Product p ON ci.productId = p.productId " +
                       "WHERE ci.cartId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, cartId);
        ResultSet result = statement.executeQuery();
        String[][] cartData = new String[rows][columns];
        int aux = 0;
        while (result.next()) {
            cartData[aux][0] = result.getString("productId");
            cartData[aux][1] = result.getString("name");
            cartData[aux][2] = String.valueOf(result.getDouble("price"));
            cartData[aux][3] = String.valueOf(result.getInt("quantity"));
            cartData[aux][4] = String.valueOf(result.getDouble("total"));
            aux++;
        }
        return cartData;
    }
    
    // Phương thức main để thử nghiệm
//    public static void main(String[] args) throws SQLException {
//        // Tạo đối tượng Cart
//        Cart cart = new Cart("2", "KH000002", 0); // Thay đổi thông tin theo nhu cầu
//
//        // Tạo đối tượng CartDAO
//        CartDAO cartDAO = new CartDAO();
//
//        // Thêm giỏ hàng vào cơ sở dữ liệu
//        boolean isAdded = cartDAO.addCart(cart);
//        if (isAdded) {
//            System.out.println("Giỏ hàng đã được thêm thành công!");
//        } else {
//            System.out.println("Không thể thêm giỏ hàng.");
//        }
//
//        // Thêm sản phẩm vào giỏ hàng
//        String productId = "SP000002";  // Mã sản phẩm
//        int quantity = 4;  // Số lượng sản phẩm
//
//        boolean isProductAdded = cartDAO.addProductToCart(cart.getCartId(), productId, quantity);
//        if (isProductAdded) {
//            System.out.println("Sản phẩm đã được thêm vào giỏ hàng.");
//        } else {
//            System.out.println("Không thể thêm sản phẩm vào giỏ hàng.");
//        }
//
//        // Tính toán tổng giá trị giỏ hàng
//        double totalPrice = cartDAO.calculateTotalPrice(cart.getCartId());
//        System.out.println("Tổng giá trị giỏ hàng: " + totalPrice);
//
//        // Cập nhật tổng giá trị giỏ hàng
//        boolean isUpdated = cartDAO.updateTotalPrice(cart.getCartId(), totalPrice);
//        if (isUpdated) {
//            System.out.println("Cập nhật tổng giá trị giỏ hàng thành công!");
//        } else {
//            System.out.println("Không thể cập nhật tổng giá trị giỏ hàng.");
//        }
//    }
    
//    public static void main(String[] args) {
//    // Giả sử bạn đã có lớp CartDAO và kết nối cơ sở dữ liệu đã được thiết lập
//        try {
//            CartDAO cartDAO = new CartDAO(); // CartDAO là lớp chứa hàm readCartTableData
//            String cartId = "KH000001"; // Giả định cartId là "U12345"
//
//            // Gọi hàm readCartTableData để lấy dữ liệu giỏ hàng
//            String[][] cartData = cartDAO.readCartTableData(cartId);
//
//            // Kiểm tra và in kết quả
//            if (cartData.length == 0) {
//                System.out.println("Không có sản phẩm nào trong giỏ hàng với cartId: " + cartId);
//            } else {
//                System.out.println("Dữ liệu giỏ hàng cho cartId: " + cartId);
//                System.out.printf("%-15s %-20s %-10s %-10s %-10s\n", 
//                                  "ProductId", "Name", "Price", "Quantity", "Total");
//                System.out.println("-----------------------------------------------------------------");
//                for (String[] row : cartData) {
//                    System.out.printf("%-15s %-20s %-10s %-10s %-10s\n", 
//                                      row[0], row[1], row[2], row[3], row[4]);
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Lỗi khi lấy dữ liệu giỏ hàng: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

}


