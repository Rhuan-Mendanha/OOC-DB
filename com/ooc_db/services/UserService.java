/*
 * This is where the magic happens! We're working with a database that stores user info.
 * This code lets us view users, add new ones, and delete existing ones from the database.
 */

package com.ooc_db.services;

import com.ooc_db.models.User;
import java.sql.*;
import java.util.Scanner;

/**
 * This is the UserService class.
 * It helps manage users in our database. 
 * The code is written by: 
 * Rhuan Mendanha, Eli Raimundo, and Henrique Queiroz.
 */

public class UserService {
    
    private Connection connection;

    // This is the constructor, it gets called when we create a UserService object.
    // It saves the database connection so we can use it later.
    public UserService(Connection connection) {
        this.connection = connection;
    }

    // This method shows us all users in the database.
    public void displayAllUsers() {
        
        // This is the SQL query to grab all the information we need about users.
        String query = "SELECT u.user_id, u.username, u.first_name, u.last_name, u.email, u.phone_number, " +
                "u.gender, r.role_name, d.name AS department_name, u.status " +
                "FROM Users u " +
                "JOIN Role r ON u.role_id = r.role_id " +
                "JOIN Departments d ON u.department_id = d.department_id";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Prints a nice heading with color
            System.out.println("\u001B[33m\n========== USER LIST ==========\u001B[0m");

            // This loop goes through each user and shows their details
            while (resultSet.next()) {
                
                System.out.println("\u001B[33m---------------------------------\u001B[0m");
                System.out.println("\u001B[36mUser ID ⮕ " + resultSet.getString("user_id"));
                System.out.println("\u001B[36mUsername ⮕ " + resultSet.getString("username"));
                System.out.println("\u001B[36mFirst Name ⮕ " + resultSet.getString("first_name"));
                System.out.println("\u001B[36mLast Name ⮕ " + resultSet.getString("last_name"));
                System.out.println("\u001B[36mEmail ⮕ " + resultSet.getString("email"));
                System.out.println("\u001B[36mPhone ⮕ " + resultSet.getString("phone_number"));
                System.out.println("\u001B[36mGender ⮕ " + resultSet.getString("gender"));
                System.out.println("\u001B[36mRole ⮕ " + resultSet.getString("role_name"));
                System.out.println("\u001B[36mDepartment ⮕ " + resultSet.getString("department_name"));
                System.out.println("\u001B[36mStatus ⮕ " + resultSet.getString("status"));
            }

            System.out.println("\u001B[33m---------------------------------\u001B[0m");
            
        } 
        
        catch (SQLException e) {
            // If there’s an error, this will print out the error message
            System.err.println("\u001B[31mError fetching users ⮕ " + e.getMessage() + "\u001B[0m");
        }
    }

    // This method adds a new user to the database
    public void addUser(User user) {
        
        // This SQL query adds a new user with all the details
        String query = "INSERT INTO Users (user_id, username, password, first_name, last_name, email, " +
                "phone_number, gender, role_id, department_id, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // We set the values for each of the placeholders in the query
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getPhoneNumber());
            preparedStatement.setString(8, user.getGender());
            preparedStatement.setInt(9, user.getRoleId());
            preparedStatement.setInt(10, user.getDepartmentId());
            preparedStatement.setString(11, user.getStatus());

            // Run the query and add the user
            preparedStatement.executeUpdate();
            System.out.println("\u001B[32mUser added successfully!\u001B[0m");
            
        } 
        
        catch (SQLException e) {
            // If there’s an error adding the user, we’ll see the error here
            System.err.println("\u001B[31mError adding user: " + e.getMessage() + "\u001B[0m");
        }
    }

    // This method deletes a user from the database using their user ID
    public void deleteUser(String userId) {
        
        // This SQL query deletes a user by their user_id
        String query = "DELETE FROM Users WHERE user_id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);

            // If the user is found and deleted, this will return how many rows were affected
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("\u001B[32mUser deleted successfully!\u001B[0m");
            } 
            
            else {
                System.out.println("\u001B[33mNo user found with ID: " + userId + "\u001B[0m");
            }
            
        } 
        
        catch (SQLException e) {
            // If something goes wrong, this will print the error
            System.err.println("\u001B[31mError deleting user: " + e.getMessage() + "\u001B[0m");
        }
    }

    // These methods help make sure the data entered is correct, like checking that the user ID is 8 digits.
    public static String getValidUserId(Scanner scanner) {
        
        // Keep asking for a valid user ID until we get one
        while (true) {
            System.out.println("\u001B[33m========== USER ID VALIDATION ==========\u001B[0m");
            System.out.print("\u001B[36mEnter user ID (must start with '202' and be 8 digits total): ");
            String userId = scanner.nextLine();
            
            // Check if the user ID is in the correct format
            if (userId.matches("^202\\d{5}$")) return userId;
            
            System.out.println("\u001B[31mError: Invalid user ID. It must start with '202' and be exactly 8 digits.\u001B[0m");
        }
    }

    // This checks if the username is lowercase and only has letters and numbers
    public static String getValidUsername(Scanner scanner) {
        
        while (true) {
            System.out.println("\u001B[33m========== USERNAME VALIDATION ==========\u001B[0m");
            System.out.print("\u001B[36mEnter username (lowercase letters and numbers only): ");
            String username = scanner.nextLine();
            
            if (username.matches("^[a-z0-9]{1,100}$")) return username;
            
            System.out.println("\u001B[31mError: Invalid username. It must be lowercase letters and numbers only, max 100 characters.\u001B[0m");
        }
    }

    // This checks if the password only has letters and numbers
    public static String getValidPassword(Scanner scanner) {
        while (true) {
            System.out.println("\u001B[33m========== PASSWORD VALIDATION ==========\u001B[0m");
            System.out.print("\u001B[36mEnter password (letters and numbers only): ");
            String password = scanner.nextLine();
            
            if (password.matches("^[a-zA-Z0-9]+$")) return password;
            
            System.out.println("\u001B[31mError: Invalid password. It must contain only letters and numbers.\u001B[0m");
        }
    }

    // These are similar methods for validating the first name, last name, email, phone number, gender, role, and department.
    // They just make sure the input follows the rules.
    public static String getValidFirstName(Scanner scanner) {
        
        while (true) {
            System.out.println("\u001B[33m========== FIRST NAME VALIDATION ==========\u001B[0m");
            System.out.print("\u001B[36mEnter first name (starts with capital letter, max 8 characters): ");
            String firstName = scanner.nextLine();
            
            if (firstName.matches("^[A-Z][a-zA-Z]{0,7}$")) return firstName;
            
            System.out.println("\u001B[31mError: Invalid first name. It must start with a capital letter and be max 8 characters.\u001B[0m");
        }
    }

    public static String getValidLastName(Scanner scanner) {
        
        while (true) {
            System.out.println("\u001B[33m========== LAST NAME VALIDATION ==========\u001B[0m");
            System.out.print("\u001B[36mEnter last name (starts with capital letter, max 15 characters): ");
            String lastName = scanner.nextLine();
            
            if (lastName.matches("^[A-Z][a-zA-Z]{0,14}$")) return lastName;
            
            System.out.println("\u001B[31mError: Invalid last name. It must start with a capital letter and be max 15 characters.\u001B[0m");
        }
    }

    public static String getValidEmail(Scanner scanner) {
        
        while (true) {
            System.out.println("\u001B[33m========== EMAIL VALIDATION ==========\u001B[0m");
            System.out.print("\u001B[36mEnter email (must be lowercase and end with .com): ");
            String email = scanner.nextLine();
            
            if (email.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")) return email;
            
            System.out.println("\u001B[31mError: Invalid email. It must be lowercase and end with '.com'.\u001B[0m");
        }
    }

    public static String getValidPhoneNumber(Scanner scanner) {
        
        while (true) {
            System.out.println("\u001B[33m========== PHONE NUMBER VALIDATION ==========\u001B[0m");
            System.out.print("\u001B[36mEnter phone number (numbers only): ");
            String phoneNumber = scanner.nextLine();
            
            if (phoneNumber.matches("^[0-9]+$")) return phoneNumber;
            
            System.out.println("\u001B[31mError: Invalid phone number. It must contain only numbers.\u001B[0m");
        }
    }

    public static String getValidGender(Scanner scanner) {
        
        while (true) {
            System.out.println("\u001B[33m========== GENDER VALIDATION ==========\u001B[0m");
            System.out.print("\u001B[36mEnter gender (Male/Female/Not specify): ");
            String gender = scanner.nextLine();
            
            if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Not specify"))
                return gender;
            
            System.out.println("\u001B[31mError: Invalid gender. Choose Male, Female, or Not specify.\u001B[0m");
        }
    }

    public static int getValidRoleId(Scanner scanner) {
        
        while (true) {
            System.out.println("\u001B[33m========== ROLE ID VALIDATION ==========\u001B[0m");
            System.out.println("\u001B[36mEnter role ID (choose between 1, 2, or 3):");
            System.out.println("\u001B[36m1 ⮕ Admin");
            System.out.println("\u001B[36m2 ⮕ Manager");
            System.out.println("\u001B[36m3 ⮕ Regular");
            System.out.print("\u001B[36mYour choice: ");
            String roleIdStr = scanner.nextLine();
            
            if (roleIdStr.equals("1") || roleIdStr.equals("2") || roleIdStr.equals("3")) {
                return Integer.parseInt(roleIdStr);
            }
            
            System.out.println("\u001B[31m Error: Invalid role ID. Please enter 1, 2, or 3. \u001B[0m");
        }
    }

    public static int getValidDepartmentId(Scanner scanner) {
        
        while (true) {
            System.out.println("\u001B[33m========== DEPARTMENT ID VALIDATION ==========\u001B[0m");
            System.out.println("\u001B[36mEnter department ID (choose between 1, 2, 3, or 4):");
            System.out.println("\u001B[36m1 ⮕ HR");
            System.out.println("\u001B[36m2 ⮕ Finance");
            System.out.println("\u001B[36m3 ⮕ IT");
            System.out.println("\u001B[36m4 ⮕ Marketing");
            System.out.print("\u001B[36mYour choice: ");
            String departmentIdStr = scanner.nextLine();
            
            if (departmentIdStr.equals("1") || departmentIdStr.equals("2") || departmentIdStr.equals("3") || departmentIdStr.equals("4")) {
                return Integer.parseInt(departmentIdStr);
            }
            
            System.out.println("\u001B[31m Error: Invalid department ID. Please enter 1, 2, 3, or 4. \u001B[0m");
        }
    }

    public static String getValidStatus(Scanner scanner) {
        
        while (true) {
            System.out.println("\u001B[33m========== STATUS VALIDATION ==========\u001B[0m");
            System.out.println("\u001B[36mEnter status (Choose between 1 or 2):");
            System.out.println("\u001B[36m1 ⮕ Leaver");
            System.out.println("\u001B[36m2 ⮕ Current");
            System.out.print("\u001B[36mYour choice: ");
            String status = scanner.nextLine();
            
            if (status.equals("1") || status.equals("2")) {
                return status;
            }
            
            System.out.println("\u001B[31m Error: Invalid status. Please enter 1 or 2. \u001B[0m");
        }
    }
}