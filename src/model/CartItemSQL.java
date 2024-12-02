package model;

public class CartItemSQL {
    private String productId;
    private String name;
    private double price;
    private int quantity;
    private double total;

    public CartItemSQL(String productId, String name, double price, int quantity, double total) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }
    
}
