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

public class User {
    
    // These are the fields (or properties) that store information about a user.
    
    private String userId;        // Unique identifier for the user.
    private String username;      // User's login name.
    private String password;      // User's login password.
    private String firstName;     // User's first name.
    private String lastName;      // User's last name.
    private String email;         // User's email address.
    private String phoneNumber;   // User's phone number.
    private String gender;        // User's gender.
    private int roleId;           // User's role (e.g., admin, regular user).
    private int departmentId;     // User's department within the organization.
    private String status;        // User's account status (e.g., active, inactive).

    // Constructor: This is how we create a new user and set their details.
    public User(String userId, String username, String password, String firstName, String lastName,
                String email, String phoneNumber, String gender, int roleId, int departmentId, String status) {
        
        // The `this` keyword makes sure we are setting the fields of this class
        // with the values passed into the constructor.
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.roleId = roleId;
        this.departmentId = departmentId;
        this.status = status;
    }

    // Getters and Setters: These methods allow other parts of the program
    // to safely access and modify the fields of the User class.

    public String getUserId() { return userId; } // Get the user's ID.
    public void setUserId(String userId) { this.userId = userId; } // Update the user's ID.

    public String getUsername() { return username; } // Get the username.
    public void setUsername(String username) { this.username = username; } // Update the username.

    public String getPassword() { return password; } // Get the password.
    public void setPassword(String password) { this.password = password; } // Update the password.

    public String getFirstName() { return firstName; } // Get the first name.
    public void setFirstName(String firstName) { this.firstName = firstName; } // Update the first name.

    public String getLastName() { return lastName; } // Get the last name.
    public void setLastName(String lastName) { this.lastName = lastName; } // Update the last name.

    public String getEmail() { return email; } // Get the email address.
    public void setEmail(String email) { this.email = email; } // Update the email address.

    public String getPhoneNumber() { return phoneNumber; } // Get the phone number.
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; } // Update the phone number.

    public String getGender() { return gender; } // Get the gender.
    public void setGender(String gender) { this.gender = gender; } // Update the gender.

    public int getRoleId() { return roleId; } // Get the role ID.
    public void setRoleId(int roleId) { this.roleId = roleId; } // Update the role ID.

    public int getDepartmentId() { return departmentId; } // Get the department ID.
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; } // Update the department ID.

    public String getStatus() { return status; } // Get the account status.
    public void setStatus(String status) { this.status = status; } // Update the account status.
}