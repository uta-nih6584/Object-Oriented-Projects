package mdi;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<MenuItem> items = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void addMenuItem(MenuItem item) {
        items.add(item);
    }

    public void run() {
        while (true) {
            System.out.println("\nSelect an option:");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i) + "] " + items.get(i).getTitle());
            }

            int choice = getInt("Enter your choice: ");
            if (choice == 0) break;  

            if (choice > 0 && choice <= items.size()) {
                items.get(choice - 1).run();  
            } else {
                System.out.println("Invalid choice, try again.");
            }
        }
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int getInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.print(prompt);
            scanner.next();
        }
        return scanner.nextInt();
    }
}
