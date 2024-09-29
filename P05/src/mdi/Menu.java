package mdi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final String title;
    private final List<MenuItem> items;

    public Menu(String title) {
        this.title = title;
        this.items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void display() {
        System.out.println(title);
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getText());
        }
    }

    public void execute(int choice) {
        if (choice > 0 && choice <= items.size()) {
            items.get(choice - 1).execute();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public static int getInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid integer: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    public static String getString(Scanner scanner) {
        return scanner.nextLine();
    }
}

