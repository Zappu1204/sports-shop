/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author truongmanhtuan
 */
public class Cart {
    private String cartId;
    private String userId;
    private double totalPrice;

    // Constructor
    public Cart(String cartId, String userId, double totalPrice) {
        this.cartId = cartId;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    // Getter and Setter methods
    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
    