package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private String cartId;
    private String userId;
    private double totalPrice;

    public Cart(String cartId, String userId, double totalPrice) {
        this.cartId = cartId;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    public Cart() {
        
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
    