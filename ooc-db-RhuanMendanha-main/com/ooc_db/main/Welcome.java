/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ooc_db.main;

/**
 * CA 1 Integrated Databases, Cloud and OCC (20%)
 * @author Students:
 * Rhuan Mendanha, Eli Raimundo & Henrique Queiroz de Morais.
 * Student Number: (Rhuan)2023186 & (Henrique)2023288.
 */

public class Welcome {

    // This method is responsible for showing the welcome message on the screen.
    // It's all about giving the user a warm and friendly greeting when they start the program.
    public static void displayWelcomeMessage() {
        
        // These lines print out the welcome message with colorful formatting.
        // The special codes (like \u001B[36m) are for adding colors to the text in the terminal.
        System.out.println("\u001B[36m======================================\u001B[0m");
        System.out.println("\u001B[32m       Welcome to the OOC System   \u001B[0m");
        System.out.println("\u001B[36m======================================\u001B[0m");
        System.out.println("\u001B[33m      Please log in to continue...   \u001B[0m");
        System.out.println("\u001B[36m======================================\u001B[0m\n");
        
    }
}
