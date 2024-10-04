package mdi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<MenuItem> menuItems; 

    public Menu() {
        menuItems = new ArrayList<>(); 
    }

    
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    
    public void run() {
        printMenu(); 
        int choice = getUserChoice(); 

        if (choice >= 0 && choice < menuItems.size()) {
            menuItems.get(choice).execute(); 
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

   
    private void printMenu() {
            System.out.println("\n                __|__                ");
            System.out.println("       --o--o--o--o--o--o--         ");
            System.out.println("          * Mavs Online *           ");
            System.out.println("       * Entertainment System *      ");
            System.out.println("         *       Menu       *       ");
            System.out.println("          * * * * * * * * * *       ");
            System.out.println("           \\     | |     /          ");
            System.out.println("            \\    | |    /           ");
            System.out.println("             \\___|_|___/            ");
        
    
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(i + ". " + menuItems.get(i).getLabel()); 
        }
    }

   
    private int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an option: ");
        return Integer.parseInt(scanner.nextLine());
    }
}