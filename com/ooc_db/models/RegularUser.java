/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ooc_db.models;

/**
 * CA 1 Integrated Databases, Cloud and OCC (20%)
 * @author Students:
 * Rhuan Mendanha Eli Raimundo & Henrique Queiroz de Morais.
 * Student Number: (Rhuan)2023186 & (Henrique)2023288.
 */

public class RegularUser extends User {
    
    // Constructor for RegularUser class. It initializes the RegularUser with
    // all the same properties as the User class using the super() method.
    public RegularUser(String userId, String username, String password, String firstName, String lastName,
                       String email, String phoneNumber, String gender, int roleId, int departmentId, String status) {
        
        // Call the constructor from the User class to initialize all fields.
        super(userId, username, password, firstName, lastName, email, phoneNumber, gender, roleId, departmentId, status);
        
    }

    // Method specific to RegularUser, showing their privileges in the system.
    public void displayUserPrivileges() {
        
        // Print out a message explaining what Regular Users can do: in this case,
        // they are limited to only viewing user data.
        System.out.println("\u001B[36m User Privileges: View users only. \u001B[0m");
    }
}