/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.management.relation.Role;

/**
 *
 * @author truongmanhtuan
 */
public class Admin extends Account{
    private String role ;
    
    public Admin(String userName, String password, String role) {
        super(userName, password);
        this.role = "admin";
    }
    
}
