package mdi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<MenuItem> menuItems; // List to hold menu items

    public Menu() {
        menuItems = new ArrayList<>(); // Initialize the list of menu items
    }

    // Method to add a menu item
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    // Method to run the menu and handle user input
    public void run() {
        printMenu(); // Display the menu
        int choice = getUserChoice(); // Get the user's choice

        if (choice >= 0 && choice < menuItems.size()) {
            menuItems.get(choice).execute(); // Execute the selected menu item
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    // Method to print the menu items
    private void printMenu() {
        System.out.println("\n--- Mavs Online Entertainment System Menu ---");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(i + ". " + menuItems.get(i).getLabel()); // Print each menu item
        }
    }

    // Method to get the user's choice
    private int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an option: ");
        return Integer.parseInt(scanner.nextLine()); // Return the user's choice as an integer
    }
}
