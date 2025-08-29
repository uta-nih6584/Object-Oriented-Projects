package mdi;

import customer.Account;
import customer.Alacarte;
import customer.Student;
import customer.Unlimited;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import moes.Moes;
import product.Media;

public class Main {
    private static final String FILE_EXTENSION = ".moes"; // Define the file extension
    private Moes moes;
    private Menu menu;
    private String output;
    private String filename; // To remember the current filename

    public Main() {
        moes = new Moes();
        menu = new Menu();
        initializeMenu();
        filename = ""; // Initialize filename
    }

    private void initializeMenu() {
        menu.addMenuItem(new MenuItem("Exit", this::endApp));
        menu.addMenuItem(new MenuItem("Play Media", this::playMedia));
        menu.addMenuItem(new MenuItem("List Media", this::listMedia));
        menu.addMenuItem(new MenuItem("List Available Points", this::listAvailablePoints));
        menu.addMenuItem(new MenuItem("Buy Points", this::buyPoints));
        menu.addMenuItem(new MenuItem("Add Media", this::addMedia));
        menu.addMenuItem(new MenuItem("List Students", this::listStudents));
        menu.addMenuItem(new MenuItem("Add Student", this::addStudent));
        menu.addMenuItem(new MenuItem("New Moes", this::newMoes));
        menu.addMenuItem(new MenuItem("Save", this::save));
        menu.addMenuItem(new MenuItem("Save As", this::saveAs));
        menu.addMenuItem(new MenuItem("Open", this::open));
    }

    public void mdi() {
        boolean running = true;
        while (running) {
            menu.run();
        }
    }

    public void endApp() {
        System.out.println("Exiting the application...");
        System.exit(0);
    }

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("Operation canceled. Returning to the main menu.");
            return; // Exit if input is empty
        }
        
        System.out.print("Enter student's email: ");
        String email = scanner.nextLine();
        if (email.trim().isEmpty()) {
            System.out.println("Operation canceled. Returning to the main menu.");
            return; // Exit if input is empty
        }
    
        System.out.print("Enter student's ID: ");
        String idInput = scanner.nextLine();
        if (idInput.trim().isEmpty()) {
            System.out.println("Operation canceled. Returning to the main menu.");
            return; // Exit if input is empty
        }
        int id = Integer.parseInt(idInput);
    
        System.out.print("Select account type (1 for Alacarte, 2 for Unlimited): ");
        String accountTypeInput = scanner.nextLine();
        if (accountTypeInput.trim().isEmpty()) {
            System.out.println("Operation canceled. Returning to the main menu.");
            return; // Exit if input is empty
        }
        int accountType = Integer.parseInt(accountTypeInput);
    
        Account account;
        if (accountType == 1) {
            System.out.print("Enter initial points for Alacarte: ");
            String pointsInput = scanner.nextLine();
            if (pointsInput.trim().isEmpty()) {
                System.out.println("Operation canceled. Returning to the main menu.");
                return; // Exit if input is empty
            }
            int points = Integer.parseInt(pointsInput);
            account = new Alacarte(points);
        } else {
            account = new Unlimited();
        }
    
        Student student = new Student(name, id, email, account);
        moes.addStudent(student);
    
        System.out.println("Student added: [" + id + ", " + email + ", Account #" + account.getAccountNumber() + 
                           ", Points: " + (account instanceof Alacarte ? ((Alacarte) account).getPointsRemaining() : "N/A") + "]");
    }
    
    public void listStudents() {
        output = moes.getStudentList();
        System.out.println(output);
    }

    
    public void addMedia() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();
        if (title.trim().isEmpty()) {
            System.out.println("Operation canceled. Returning to the main menu.");
            return; // Exit if input is empty
        }
        
        System.out.print("Enter media type: ");
        String type = scanner.nextLine();
        if (type.trim().isEmpty()) {
            System.out.println("Operation canceled. Returning to the main menu.");
            return; // Exit if input is empty
        }
    
        System.out.print("Enter media duration (in minutes): ");
        String durationInput = scanner.nextLine();
        if (durationInput.trim().isEmpty()) {
            System.out.println("Operation canceled. Returning to the main menu.");
            return; // Exit if input is empty
        }
        int duration = Integer.parseInt(durationInput);
    
        System.out.print("Enter media URL: ");
        String url = scanner.nextLine();
        if (url.trim().isEmpty()) {
            System.out.println("Operation canceled. Returning to the main menu.");
            return; // Exit if input is empty
        }
    
        System.out.print("Enter points required to view media: ");
        String pointsInput = scanner.nextLine();
        if (pointsInput.trim().isEmpty()) {
            System.out.println("Operation canceled. Returning to the main menu.");
            return; // Exit if input is empty
        }
        int points = Integer.parseInt(pointsInput);
    
        Media media = new Media(title, type, duration, url, points);
        moes.addMedia(media);
        System.out.println("Media added: " + title + " (" + type + "), " + duration + " minutes, URL: " + url + ", Points: " + points);
    }
    
    public void listMedia() {
        output = moes.getMediaList();
        System.out.println(output);
    }

    public void playMedia() {
        System.out.println("Available Students:");
        System.out.println(moes.getStudentList());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student index: ");
        int studentIndex = Integer.parseInt(scanner.nextLine());

        System.out.println("Available Media:");
        System.out.println(moes.getMediaList());

        System.out.print("Enter media index: ");
        int mediaIndex = Integer.parseInt(scanner.nextLine());

        String result = moes.playMedia(studentIndex, mediaIndex);
        System.out.println(result);
    }

    public void listAvailablePoints() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student index: ");
        int studentIndex = Integer.parseInt(scanner.nextLine());

        int points = moes.getPoints(studentIndex);
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

        String result = moes.buyPoints(studentIndex, pointsToBuy);
        System.out.println(result);
    }

    // New method to create a new Moes object
    public void newMoes() {
        moes = new Moes();
        System.out.println("New Moes object created.");
    }

    // Save the current Moes object to the specified filename
    public void save() {
        if (filename.isEmpty()) {
            System.out.println("No filename specified. Use 'Save As' to specify a filename.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("magicCookie\n"); // Replace with your actual magic cookie
            writer.write("fileVersion\n"); // Replace with your actual file version
            moes.save(writer); // Assuming Moes has a save method that takes BufferedWriter
            System.out.println("Data saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // Save the current Moes object to a new filename
    public void saveAs() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new filename: ");
        String newFilename = scanner.nextLine();
        if (newFilename.trim().isEmpty()) {
            System.out.println("No filename specified. Not saving anything.");
            return;
        }
        if (!newFilename.endsWith(FILE_EXTENSION)) {
            newFilename += FILE_EXTENSION; // Add the file extension if not present
        }
        filename = newFilename; // Update the filename
        save(); // Call save method to save the data
    }

    // Open a Moes object from a specified filename
    public void open() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filename to open: ");
        String newFilename = scanner.nextLine();
        if (newFilename.trim().isEmpty()) {
            System.out.println("No filename specified. Not opening anything.");
            return;
        }
        if (!newFilename.endsWith(FILE_EXTENSION)) {
            newFilename += FILE_EXTENSION; // Add the file extension if not present
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(newFilename))) {
            String magicCookie = reader.readLine();
            String fileVersion = reader.readLine();
            if (!magicCookie.equals("magicCookie") || !fileVersion.equals("fileVersion")) {
                throw new IOException("Invalid file format.");
            }
            moes.load(reader); // Load Moes object from stream
            filename = newFilename; // Update the filename
            System.out.println("Data loaded successfully from " + filename);
        } catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.mdi();
    }
}