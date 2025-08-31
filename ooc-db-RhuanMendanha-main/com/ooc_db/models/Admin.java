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

public class Admin extends User {

    // Constructor for the Admin class. This sets up an Admin object using all the details
    // provided in the parameters. It uses the User class's constructor (via super).
    public Admin(String userId, String username, String password, String firstName, String lastName,
                 String email, String phoneNumber, String gender, int roleId, int departmentId, String status) {
        
        // Call the constructor from the User class to initialize all the fields.
        super(userId, username, password, firstName, lastName, email, phoneNumber, gender, roleId, departmentId, status);
    }

    // This method is specific to Admins and shows what special abilities they have in the system.
    public void displayAdminPrivileges() {
        
        // Prints out a message highlighting the privileges Admin users have, in this case:
        // they can add, delete, and view users in the system.
        System.out.println("\u001B[36m Admin Privileges: Add, Delete, and View users. \u001B[0m");
    }
}
