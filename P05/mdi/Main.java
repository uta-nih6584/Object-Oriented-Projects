package mdi;

import customer.Alacarte; // Adjust according to your actual package structure
import customer.Student; // Adjust according to your actual package structure
import java.util.Scanner; // Ensure this class exists in the customer package
import moes.Moes; // Ensure Media class exists in the product package
import product.Media;

public class Main {
    private Moes moes;  // Instance of Moes to manage media and students
    private Menu menu;  // Instance of Menu for user interaction
    private String output; // Stores output information

    public Main() {
        moes = new Moes();  // Initialize the Moes instance
        menu = new Menu();   // Initialize the Menu instance
        initializeMenu();    // Populate the menu with items
    }

    private void initializeMenu() {
        // Add menu items with method references
        menu.addMenuItem(new MenuItem("Add Student", this::addStudent));
        menu.addMenuItem(new MenuItem("List Students", this::listStudents));
        menu.addMenuItem(new MenuItem("Add Media", this::addMedia));
        menu.addMenuItem(new MenuItem("List Media", this::listMedia));
        menu.addMenuItem(new MenuItem("Play Media", this::playMedia));
        menu.addMenuItem(new MenuItem("List Available Points", this::listAvailablePoints));
        menu.addMenuItem(new MenuItem("Buy Points", this::buyPoints));
        menu.addMenuItem(new MenuItem("Exit", this::endApp));
    }

    public void mdi() {
        boolean running = true;
        while (running) {
            menu.run(); // Prompt for user choice and execute
        }
    }

    public void endApp() {
        System.out.println("Exiting the application...");
        System.exit(0); // Exit the application
    }

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student's email: ");
        String email = scanner.nextLine();
        
        // Prompt for student ID
        System.out.print("Enter student's ID: ");
        int studentId = Integer.parseInt(scanner.nextLine()); // Get ID from user input
        
        // Prompt for initial points
        System.out.print("Enter initial points: ");
        int points = Integer.parseInt(scanner.nextLine());

        // Create a new Student and add to Moes
        Student student = new Student(name, studentId, email, new Alacarte(points)); // points should be int
        moes.addStudent(student);
        System.out.println("Student added: " + name);
    }

    public void listStudents() {
        output = moes.getStudentList(); // Assuming this returns a String
        System.out.println(output);
    }

    public void addMedia() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();
        System.out.print("Enter media type: ");
        String type = scanner.nextLine();
        System.out.print("Enter media duration (in minutes): "); // Added prompt for duration
        int duration = Integer.parseInt(scanner.nextLine()); // Get duration from user input

        // Create a new Media and add to Moes
        Media media = new Media(title, type, duration); // Make sure Media constructor matches
        moes.addMedia(media);
        System.out.println("Media added: " + title);
    }

    public void listMedia() {
        output = moes.getMediaList(); // Assuming this returns a String
        System.out.println(output);
    }

    public void playMedia() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student index: ");
        int studentIndex = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter media index: ");
        int mediaIndex = Integer.parseInt(scanner.nextLine());

        String result = moes.playMedia(studentIndex, mediaIndex); // Assuming this returns a String
        System.out.println(result);
    }

    public void listAvailablePoints() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student index: ");
        int studentIndex = Integer.parseInt(scanner.nextLine());

        int points = moes.getPoints(studentIndex); // Assuming this returns an int
        System.out.println("Available points: " + points);
    }

    public void buyPoints() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student index: ");
        int studentIndex = Integer.parseInt(scanner.nextLine());
        int currentPoints = moes.getPoints(studentIndex);
        
        System.out.println("Current points: " + currentPoints);
        System.out.print("Enter points to buy: ");
        int pointsToBuy = Integer.parseInt(scanner.nextLine());

        if (pointsToBuy < 0) {
            System.out.println("Cannot purchase negative points!");
            return;
        }

        String result = moes.buyPoints(studentIndex, pointsToBuy); // Assuming this returns a String
        System.out.println(result);
    }

    public static void main(String[] args) {
        Main app = new Main(); // Create an instance of Main
        app.mdi(); // Start the menu-driven interface
    }
}
