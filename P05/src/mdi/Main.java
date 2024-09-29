package mdi;

import moes.Moes;  // Adjust import according to your package structure
import product.Media; // Adjust import according to your package structure
import product.Student; // Adjust import according to your package structure

import java.util.Scanner;

public class Main {
    private final Moes moes;
    private final Menu menu;
    private String output; // Field to collect output information
    private boolean running;

    public Main() {
        moes = new Moes(); // Initialize your Moes instance
        menu = new Menu("Main Menu");
        initializeMenu();
        running = true; // Set running state to true
    }

    private void initializeMenu() {
        menu.addItem(new MenuItem("Add Student", this::addStudent));
        menu.addItem(new MenuItem("List Students", this::listStudents));
        menu.addItem(new MenuItem("Add Media", this::addMedia));
        menu.addItem(new MenuItem("List Media", this::listMedia));
        menu.addItem(new MenuItem("Play Media", this::playMedia));
        menu.addItem(new MenuItem("Buy Points", this::buyPoints));
        menu.addItem(new MenuItem("Check Points", this::listAvailablePoints));
        menu.addItem(new MenuItem("Exit", this::endApp));
    }

    public void mdi() {
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.println(output); // Display any previous output
            menu.display();
            System.out.print("Enter your choice: ");
            int choice = Menu.getInt(scanner); // Using a static method for input
            menu.execute(choice);
        }
        scanner.close();
    }

    public void endApp() {
        running = false; // Set running to false to exit the loop
    }

    // Student Methods
    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        // Collect other required data for Student
        Student student = new Student(name); // Adjust constructor as necessary
        moes.addStudent(student);
        output = "Added student: " + name;
    }

    public void listStudents() {
        output = moes.getStudentList(); // Ensure this returns a String
    }

    // Media Methods
    public void addMedia() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();
        // Collect other required data for Media
        Media media = new Media(title); // Adjust constructor as necessary
        moes.addMedia(media);
        output = "Added media: " + title;
    }

    public void listMedia() {
        output = moes.getMediaList(); // Ensure this returns a String
    }

    public void playMedia() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student index: ");
        int studentIndex = Menu.getInt(scanner);
        System.out.print("Enter media index: ");
        int mediaIndex = Menu.getInt(scanner);
        output = moes.playMedia(studentIndex, mediaIndex); // Ensure this returns a String
    }

    public void listAvailablePoints() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student index: ");
        int studentIndex = Menu.getInt(scanner);
        int points = moes.getPoints(studentIndex);
        output = "Student points: " + points;
    }

    public void buyPoints() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student index: ");
        int studentIndex = Menu.getInt(scanner);
        System.out.print("Enter number of points to buy: ");
        int pointsToBuy = Menu.getInt(scanner);
        if (pointsToBuy < 0) {
            output = "Cannot purchase negative points!";
            return;
        }
        String result = moes.buyPoints(studentIndex, pointsToBuy);
        output = result; // Set output to display the result
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.mdi(); // Start the menu-driven interface
    }
}

