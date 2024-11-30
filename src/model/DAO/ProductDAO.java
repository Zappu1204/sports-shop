package model.DAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.MySQLConnector;
import model.Product;
/**
 *
 * @author truongmanhtuan
 */

public class ProductDAO {
    private Connection connection;
    private String query;
    private PreparedStatement statement;
    private ResultSet result, resultCountRows;
    public ProductDAO() throws SQLException {
        connection = MySQLConnector.getConnection();
    }
    public boolean addProduct(Product product) throws SQLException {
        // Lấy tất cả các productId hiện có từ cơ sở dữ liệu
        String getAllIdsQuery = "SELECT CAST(SUBSTRING(productId, 3) AS UNSIGNED) AS numericId FROM Product";
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
        // Tạo productId mới và thiết lập cho product
        String newProductId = "SP" + String.format("%06d", newId);
        product.setProductId(newProductId);
        // Chèn sản phẩm vào cơ sở dữ liệu
        String insertQuery = "INSERT INTO Product (productId, name, description, price, quantityInStock, category, brand, status) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setString(1, product.getProductId());
            insertStatement.setString(2, product.getName());
            insertStatement.setString(3, product.getDescription());
            insertStatement.setDouble(4, product.getPrice());
            insertStatement.setInt(5, product.getQuantityInStock());
            insertStatement.setString(6, product.getCategory());
            insertStatement.setString(7, product.getBrand());
            insertStatement.setString(8, product.getStatus());
            int rowsInserted = insertStatement.executeUpdate();
            return rowsInserted > 0;
        }
    }
    //Đếm số lượng 
    public int getQuantityInStock(String productId) throws SQLException {
        String query = "SELECT quantityInStock FROM Product WHERE productId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("quantityInStock");
            } else {
                return 0; // Nếu không tìm thấy sản phẩm, trả về 0
            }
        }
    }
    // Đọc thông tin sản phẩm dựa trên name
    public Product readProduct(String name) throws SQLException {
        query = "SELECT * FROM Product WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantityInStock"),
                        resultSet.getString("category"),
                        resultSet.getString("brand"),
                        resultSet.getString("status")
                    );
                    product.setProductId(resultSet.getString("productId"));
                    return product;
                }
            }
        }
        return null;
    }
    
    // Đọc thông tin sản phẩm dựa trên Id
    public Product readProductId(String productId) throws SQLException {
        query = "SELECT * FROM Product WHERE productId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantityInStock"),
                        resultSet.getString("category"),
                        resultSet.getString("brand"),
                        resultSet.getString("status")
                    );
                    product.setProductId(resultSet.getString("productId"));
                    return product;
                }
            }
        }
        return null;
    }
    
    // Cập nhật thông tin sản phẩm
    public boolean updateProduct(Product product) throws SQLException {
        StringBuilder queryBuilder = new StringBuilder("UPDATE Product SET ");
        boolean firstField = true;
        // Kiểm tra từng thuộc tính của sản phẩm và thêm vào câu lệnh SQL nếu có giá trị
        if (product.getName() != null && !product.getName().isEmpty()) {
            if (!firstField) queryBuilder.append(", ");
            queryBuilder.append("name = ?");
            firstField = false;
        }
        if (product.getDescription() != null && !product.getDescription().isEmpty()) {
            if (!firstField) queryBuilder.append(", ");
            queryBuilder.append("description = ?");
            firstField = false;
        }
        if (product.getPrice() != 0) {
            if (!firstField) queryBuilder.append(", ");
            queryBuilder.append("price = ?");
            firstField = false;
        }
        if (product.getQuantityInStock() != 0) {
            if (!firstField) queryBuilder.append(", ");
            queryBuilder.append("quantityInStock = ?");
            firstField = false;
        }
        if (product.getCategory() != null && !product.getCategory().isEmpty()) {
            if (!firstField) queryBuilder.append(", ");
            queryBuilder.append("category = ?");
            firstField = false;
        }
        if (product.getBrand() != null && !product.getBrand().isEmpty()) {
            if (!firstField) queryBuilder.append(", ");
            queryBuilder.append("brand = ?");
            firstField = false;
        }
        if (product.getStatus() != null && !product.getStatus().isEmpty()) {
            if (!firstField) queryBuilder.append(", ");
            queryBuilder.append("status = ?");
            firstField = false;
        }
        queryBuilder.append(" WHERE productId = ?");

        // Tạo câu lệnh SQL hoàn chỉnh
        String query = queryBuilder.toString();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int index = 1;

            // Gán giá trị cho các trường có dữ liệu
            if (product.getName() != null && !product.getName().isEmpty()) {
                statement.setString(index++, product.getName());
            }
            if (product.getDescription() != null && !product.getDescription().isEmpty()) {
                statement.setString(index++, product.getDescription());
            }
            if (product.getPrice() != 0) {
                statement.setDouble(index++, product.getPrice());
            }
            if (product.getQuantityInStock() != 0) {
                statement.setInt(index++, product.getQuantityInStock());
            }
            if (product.getCategory() != null && !product.getCategory().isEmpty()) {
                statement.setString(index++, product.getCategory());
            }
            if (product.getBrand() != null && !product.getBrand().isEmpty()) {
                statement.setString(index++, product.getBrand());
            }
            if (product.getStatus() != null && !product.getStatus().isEmpty()) {
                statement.setString(index++, product.getStatus());
            }
            // Gán giá trị productId
            statement.setString(index, product.getProductId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    // Xóa sản phẩm dựa trên productId
    public boolean deleteProduct(String productId) throws SQLException {
        query = "DELETE FROM Product WHERE productId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }
    
    public String[][] readProductsTableData() throws SQLException {
        //Đếm số lượng dòng trong bảng Product
        resultCountRows = connection.prepareStatement("SELECT COUNT(*) FROM Product").executeQuery();
        int rows = 0, columns = 8;       
        if (resultCountRows.next()) {
            rows = resultCountRows.getInt(1);
        }
        if (rows == 0) {
            return new String[0][0];
        }
        query = "SELECT * FROM Product";
        statement = connection.prepareStatement(query);
        result = statement.executeQuery();
        String[][] products = new String[rows][columns];
        int aux = 0;
        //Duyệt qua kết quả truy vấn và điền vào mảng hai chiều
        while(result.next()) {
            for(int i = 0; i < columns; i++) {
                products[aux][i] = result.getString(i + 1);
            }
            aux++;
        }
        return products;
    }
    
    public String[][] searchProducts(String keyword) throws SQLException {
        // Tạo truy vấn tìm kiếm
        String query = "SELECT * FROM Product WHERE Name LIKE ? OR Description LIKE ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "%" + keyword + "%");
        statement.setString(2, "%" + keyword + "%");

        // Đếm số lượng dòng kết quả
        PreparedStatement countStatement = connection.prepareStatement(
            "SELECT COUNT(*) FROM Product WHERE Name LIKE ? OR Description LIKE ?"
        );
        countStatement.setString(1, "%" + keyword + "%");
        countStatement.setString(2, "%" + keyword + "%");
        ResultSet resultCountRows = countStatement.executeQuery();
        int rows = 0, columns = 8;
        if (resultCountRows.next()) {
            rows = resultCountRows.getInt(1);
        }
        if (rows == 0) {
            return new String[0][0];
        }
        ResultSet result = statement.executeQuery();
        String[][] products = new String[rows][columns];
        int aux = 0;
        while (result.next()) {
            for (int i = 0; i < columns; i++) {
                products[aux][i] = result.getString(i + 1); 
            }
            aux++;
        }
        return products;
    }

//    check add
//    public static void main(String[] args) throws SQLException {
//        ProductDAO productDAO = new ProductDAO();
//        Product newProduct = new Product("OK","Đá bóng", 150000,5,"Dụng cụ","Nike","Còn hàng");
//        boolean isAdded = productDAO.addProduct(newProduct);
//        if (isAdded) {
//            System.out.println("User added successfully!");
//        } else {
//            System.out.println("Failed to add user.");
//        }
//    }
//    check delete
//    public static void main(String[] args) throws SQLException {
//        ProductDAO productDAO = new ProductDAO();
//        boolean isAdded = productDAO.deleteProduct("SP000002");
//        if (isAdded) {
//            System.out.println("User added successfully!");
//        } else {
//            System.out.println("Failed to add user.");
//        }
//    }
    
//    show
//    public static void main(String[] args) throws SQLException {
//        // Khởi tạo đối tượng ProductDAO để truy vấn dữ liệu
//        ProductDAO productDAO = new ProductDAO();
//
//        // Gọi phương thức readProduct để tìm sản phẩm theo tên
//        Product isAdded = productDAO.readProductId("SP000002");
//
//        // Kiểm tra kết quả truy vấn và in ra thông tin chi tiết của sản phẩm
//        if (isAdded != null) {
//            System.out.println("Product found successfully!");
//
//            // In ra từng thuộc tính của sản phẩm
//            System.out.println("Product ID: " + isAdded.getProductId());
//            System.out.println("Name: " + isAdded.getName());
//            System.out.println("Description: " + isAdded.getDescription());
//            System.out.println("Price: " + isAdded.getPrice());
//            System.out.println("Quantity in Stock: " + isAdded.getQuantityInStock());
//            System.out.println("Category: " + isAdded.getCategory());
//            System.out.println("Brand: " + isAdded.getBrand());
//            System.out.println("Status: " + isAdded.getStatus());
//
//        } else {
//            System.out.println("Product not found.");
//        }
//}

//    public static void main(String[] args) {
//        try {
//            // Tạo đối tượng UserDAO để kết nối và thao tác với cơ sở dữ liệu
//            ProductDAO userDAO = new ProductDAO();
//
//            // Gọi phương thức readProductsTableData để lấy dữ liệu từ bảng Product
//            String[][] products = userDAO.readProductsTableData();
//
//            // Kiểm tra nếu bảng không có dữ liệu
//            if (products.length == 0) {
//                System.out.println("No data found in the Product table.");
//            } else {
//                // In tiêu đề các cột của bảng (giả sử bảng Product có 8 cột)
//                System.out.println("Product data:");
//                System.out.println("productId\tproductName\tprice\tquantity\tdescription\tcategoryId\tcreatedDate\tupdatedDate");
//
//                // In các dữ liệu trong bảng
//                for (int i = 0; i < products.length; i++) {
//                    for (int j = 0; j < products[i].length; j++) {
//                        System.out.print(products[i][j] + "\t"); // In từng giá trị trong bảng
//                    }
//                    System.out.println(); // Xuống dòng sau mỗi hàng
//                }
//            }
//        } catch (SQLException e) {
//            // Xử lý lỗi SQL
//            e.printStackTrace();
//        }
//    }
//    public static void main(String[] args) throws SQLException {
//        // Tạo kết nối cơ sở dữ liệu
//        ProductDAO productDAO = new ProductDAO();
//        Product product = new Product();       
//        product.setProductId("SP000001"); // ID sản phẩm để xác định dòng cần cập nhật
//        product.setName("Smartphone X"); // Thông tin mới của sản phẩm
//
//        try {
//            // Gọi hàm updateProduct để cập nhật
//            boolean updated = productDAO.updateProduct(product);
//            if (updated) {
//                System.out.println("Product updated successfully.");
//            } else {
//                System.out.println("Failed to update product.");
//            }
//        } catch (SQLException e) {
//            System.err.println("Database error occurred: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
}