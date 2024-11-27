/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DAO.ProductDAO;
import model.Product;

/**
 *
 * @author truongmanhtuan
 */
public class Manage_Products_Frame extends javax.swing.JFrame {

    /**
     * Creates new form Manage_Products_Frame
     */
    public Manage_Products_Frame() throws SQLException {
        setTitle("Manage products");
        initComponents();
        displayData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_productId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_description = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_price = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_quantityInStock = new javax.swing.JTextField();
        txt_category = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_brand = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btn_createProduct = new javax.swing.JButton();
        btn_updateProduct = new javax.swing.JButton();
        btn_deleteProduct = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox_Stuatus = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_showProduct = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 700));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Product Id");

        txt_productId.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        txt_productId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_productIdActionPerformed(evt);
            }
        });

        jLabel2.setText("Name");

        txt_name.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });

        txt_description.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        txt_description.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descriptionActionPerformed(evt);
            }
        });

        jLabel3.setText("Description");

        jLabel4.setText("Price");

        txt_price.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        txt_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_priceActionPerformed(evt);
            }
        });

        jLabel5.setText("Quantity In Stock");

        txt_quantityInStock.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        txt_quantityInStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quantityInStockActionPerformed(evt);
            }
        });

        txt_category.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        txt_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_categoryActionPerformed(evt);
            }
        });

        jLabel6.setText("Category");

        jLabel7.setText("Brand");

        txt_brand.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        txt_brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_brandActionPerformed(evt);
            }
        });

        jLabel8.setText("Status");

        btn_createProduct.setBackground(new java.awt.Color(0, 102, 102));
        btn_createProduct.setForeground(new java.awt.Color(255, 255, 255));
        btn_createProduct.setText("Create Product");
        btn_createProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_createProductActionPerformed(evt);
            }
        });

        btn_updateProduct.setBackground(new java.awt.Color(0, 102, 102));
        btn_updateProduct.setForeground(new java.awt.Color(255, 255, 255));
        btn_updateProduct.setText("Update Product");
        btn_updateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateProductActionPerformed(evt);
            }
        });

        btn_deleteProduct.setForeground(new java.awt.Color(255, 51, 51));
        btn_deleteProduct.setText("Delete Product");
        btn_deleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteProductActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 11)); // NOI18N
        jLabel9.setText("Select from table to delete");

        jLabel11.setIcon(new javax.swing.ImageIcon("/Users/truongmanhtuan/NetBeansProjects/Store_Management_App/images/icons/product_icon_128.png")); // NOI18N

        jComboBox_Stuatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "   ", "In stock", "Out of stock", "Suspension of sales" }));
        jComboBox_Stuatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_StuatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_createProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_deleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_updateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_category, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_description, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_productId, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(25, 25, 25)
                                .addComponent(txt_quantityInStock, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_brand, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                    .addComponent(jComboBox_Stuatus, 0, 186, Short.MAX_VALUE))))
                        .addGap(22, 22, 22))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(111, 111, 111))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel11)
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_productId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_quantityInStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox_Stuatus, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_createProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_updateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_deleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 350, 700);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setText("Manage product information");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(440, 10, 360, 30);

        btn_back.setForeground(new java.awt.Color(255, 51, 51));
        btn_back.setText("Back");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        jPanel1.add(btn_back);
        btn_back.setBounds(1070, 650, 105, 32);

        table_showProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Id", "Name", "Description", "Price", "Quantity In Stock", "Category", "Brand", "Status"
            }
        ));
        jScrollPane1.setViewportView(table_showProduct);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(350, 60, 840, 570);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_productIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_productIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_productIdActionPerformed

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void txt_descriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descriptionActionPerformed

    private void txt_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_priceActionPerformed

    private void txt_quantityInStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quantityInStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantityInStockActionPerformed

    private void txt_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_categoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_categoryActionPerformed

    private void txt_brandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_brandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_brandActionPerformed

    private void btn_createProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_createProductActionPerformed
        // Lấy dữ liệu từ các trường nhập
        String name = txt_name.getText().trim();
        String description = txt_description.getText().trim();
        String priceText = txt_price.getText().trim();
        String quantityText = txt_quantityInStock.getText().trim();
        String category = txt_category.getText().trim();
        String brand = txt_brand.getText().trim();
        String status = jComboBox_Stuatus.getSelectedItem().toString().trim();        

        // Kiểm tra tính hợp lệ của các trường nhập liệu
        if (name.isEmpty() || description.isEmpty() || priceText.isEmpty() || quantityText.isEmpty() ||
            category.isEmpty() || brand.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            int quantityInStock = Integer.parseInt(quantityText);
            ProductDAO productDAO = new ProductDAO();
            if (productDAO.readProduct(name) != null) {
                JOptionPane.showMessageDialog(this, "Product already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Product newProduct = new Product(name, description, price, quantityInStock, category, brand, status);            
            if (productDAO.addProduct(newProduct)) {
                JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                displayData(); 
                resetForm(); 
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add product.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format for price or quantity!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection error!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_createProductActionPerformed

    private void btn_updateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateProductActionPerformed
        String productId = txt_productId.getText().trim();
        String name = txt_name.getText().trim();
        String description = txt_description.getText().trim();
        String priceText = txt_price.getText().trim();
        String quantityText = txt_quantityInStock.getText().trim();
        String category = txt_category.getText().trim();
        String brand = txt_brand.getText().trim();
        String status = jComboBox_Stuatus.getSelectedItem().toString().trim();        

        if (productId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a product ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Double price = null;
        Integer quantityInStock = null;

        try {
            // Truy vấn thông tin sản phẩm từ cơ sở dữ liệu
            ProductDAO productDAO = new ProductDAO();
            Product existingProduct = productDAO.readProductId(productId);

            // Nếu không có sản phẩm, thông báo lỗi
            if (existingProduct == null) {
                JOptionPane.showMessageDialog(this, "Product not found in the database!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Nếu không nhập giá trị mới, dùng giá trị cũ từ database, nếu không thì parse giá trị mới          
            if (priceText.trim().isEmpty()) {
                price = existingProduct.getPrice();
            } else {
                try {
                    price = Double.parseDouble(priceText);  
                    // Không cần kiểm tra số 0 nữa vì ta cho phép giá trị 0
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Price must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Nếu không nhập số lượng mới, dùng số lượng cũ từ database, nếu không thì parse số lượng mới
            if (quantityText.trim().isEmpty()) {
                quantityInStock = existingProduct.getQuantityInStock();
            } else {
                try {
                    quantityInStock = Integer.parseInt(quantityText);  
                    // Không cần kiểm tra số 0 nữa vì ta cho phép giá trị 0
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Quantity must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Price and Quantity must be valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error while retrieving product information!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }

        // Tạo đối tượng Product với các giá trị đã lấy và xử lý
        Product product = new Product(productId, name, description, price, quantityInStock, category, brand, status);       
        try {
            ProductDAO productDAO = new ProductDAO();
            // Cập nhật sản phẩm vào cơ sở dữ liệu
            if (productDAO.updateProduct(product)) {
                JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                displayData(); // Cập nhật lại dữ liệu hiển thị
                resetForm();   // Reset form nhập liệu
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update product!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        System.out.println(quantityText);
    }//GEN-LAST:event_btn_updateProductActionPerformed

    private void btn_deleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteProductActionPerformed
        int selectedRow = table_showProduct.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String productId = table_showProduct.getValueAt(selectedRow, 0).toString();
        String name = table_showProduct.getValueAt(selectedRow, 1).toString();
        // Hiển thị thông báo xác nhận
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete the product: " + name + "?", 
            "Confirm Deletion", 
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                ProductDAO productDAO = new ProductDAO();
                if (productDAO.deleteProduct(productId)) {
                    JOptionPane.showMessageDialog(this, "User deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    displayData(); // Cập nhật lại dữ liệu trong bảng
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete product!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_deleteProductActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void jComboBox_StuatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_StuatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_StuatusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Manage_Products_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Products_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Products_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Products_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Manage_Products_Frame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Manage_Products_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_createProduct;
    private javax.swing.JButton btn_deleteProduct;
    private javax.swing.JButton btn_updateProduct;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBox_Stuatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_showProduct;
    private javax.swing.JTextField txt_brand;
    private javax.swing.JTextField txt_category;
    private javax.swing.JTextField txt_description;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_productId;
    private javax.swing.JTextField txt_quantityInStock;
    // End of variables declaration//GEN-END:variables
    private void displayData() throws SQLException {
        ProductDAO productDAO = new ProductDAO();
        try {
            String[][] products = productDAO.readProductsTableData();
            DefaultTableModel model = new DefaultTableModel(products, 
                new String[] {"ProductId", "Name", "Description", "Price", "Quantity In Stock", "Category", "Brand", "Status"}) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Không cho phép chỉnh sửa bất kỳ ô nào
                }
            };
            table_showProduct.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void resetForm() {
        txt_productId.setText("");
        txt_name.setText("");
        txt_description.setText("");
        txt_price.setText("");
        txt_quantityInStock.setText("");
        txt_category.setText("");
        txt_brand.setText("");
        jComboBox_Stuatus.setSelectedIndex(0); 
    }
}