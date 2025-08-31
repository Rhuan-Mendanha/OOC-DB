/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ooc_db.main;

import com.ooc_db.services.DatabaseConnection;
import com.ooc_db.services.UserService;
import com.ooc_db.models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Scanner allows us to read user input from the console.
        Scanner scanner = new Scanner(System.in);

        // Show a fancy welcome message to the user.
        Welcome.displayWelcomeMessage();

        // This infinite loop keeps the program running until the user logs in with valid credentials.
        while (true) {
            
            // Ask the user for their username and password.
            System.out.print("\u001B[36m Enter username: \u001B[0m");
            String usernameInput = scanner.nextLine();

            System.out.print("\u001B[36m Enter password: \u001B[0m");
            String passwordInput = scanner.nextLine();

            // Check if the user provided the correct credentials.
            if (usernameInput.equals("ooc2023") && passwordInput.equals("ooc2023")) {
                
                try {
                    
                    // Establish a connection to the database.
                    Connection connection = DatabaseConnection.getConnection();
                    System.out.println("\n\u001B[32m Successfully connected to the database! \u001B[0m");

                    // Create a UserService object to manage users in the database.
                    UserService userService = new UserService(connection);

                    // This loop manages the main menu of the program.
                    while (true) {
                        
                        // Display the menu options to the user.
                        System.out.println("\n\u001B[36m ============================== \u001B[0m");
                        System.out.println("\u001B[33m           Main Menu           \u001B[0m");
                        System.out.println("\u001B[36m ============================== \u001B[0m");
                        System.out.println("\u001B[36m 1 ⮕ View All Users \u001B[0m");
                        System.out.println("\u001B[36m 2 ⮕ Add New User \u001B[0m");
                        System.out.println("\u001B[36m 3 ⮕ Delete User \u001B[0m");
                        System.out.println("\u001B[36m 4 ⮕ Exit \u001B[0m");
                        System.out.println("\u001B[36m ============================== \u001B[0m");
                        System.out.print("\u001B[33m Choose an option (1 to 4): \u001B[0m");

                        // Read the user's menu choice.
                        String input = scanner.nextLine();

                        int choice;
                        
                        try {
                            // Convert the user's choice to an integer.
                            choice = Integer.parseInt(input);
                        } 
                        
                        catch (NumberFormatException e) {
                            
                            // If the input isn't a number, show an error message and restart the loop.
                            System.out.println("\u001B[31mError: Please enter a valid number (1-4).\u001B[0m");
                            continue;
                        }

                        // Handle the user's menu choice using a switch statement.
                        switch (choice) {
                            
                            case 1:
                                // Show all users in the database.
                                userService.displayAllUsers();
                                break;
                                
                            case 2:
                                // Collect user details from the console to add a new user.
                                String userId = UserService.getValidUserId(scanner);
                                String username = UserService.getValidUsername(scanner);
                                String password = UserService.getValidPassword(scanner);
                                String firstName = UserService.getValidFirstName(scanner);
                                String lastName = UserService.getValidLastName(scanner);
                                String email = UserService.getValidEmail(scanner);
                                String phoneNumber = UserService.getValidPhoneNumber(scanner);
                                String gender = UserService.getValidGender(scanner);
                                int roleId = UserService.getValidRoleId(scanner);
                                int departmentId = UserService.getValidDepartmentId(scanner);
                                String status = UserService.getValidStatus(scanner);

                                // Create a new User object with the inputted details.
                                User user = new User(userId, username, password, firstName, lastName, email, phoneNumber, gender, roleId, departmentId, status);

                                // Add the new user to the database.
                                userService.addUser(user);
                                break;
                                
                            case 3:
                                // Get the ID of the user to delete.
                                String userIdToDelete = UserService.getValidUserId(scanner);

                                // Delete the user from the database.
                                userService.deleteUser(userIdToDelete);
                                break;
                                
                            case 4:
                                // Exit the program.
                                System.out.println("\u001B[32m\nThank you for using the OOC System. Goodbye!\u001B[0m\n");
                                scanner.close(); // Close the scanner to free resources.
                                System.exit(0); // Stop the program completely.
                                
                            default:
                                // If the user enters an invalid menu option, show an error message.
                                System.out.println("\u001B[31m Invalid choice. Please try again. \u001B[0m");
                        }
                    }
                } 
                
                catch (SQLException e) {
                    // If there's an issue connecting to the database, show an error message.
                    System.err.println("\u001B[31m Error: Unable to connect to the database. " + e.getMessage() + "\u001B[0m");
                }
            } 
            
            else {
                // If the username or password is wrong, show an error message.
                System.out.println("\u001B[31m Invalid credentials. Please try again. \n\u001B[0m");
            }
        }
    }
}