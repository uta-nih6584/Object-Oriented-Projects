package mdi;

import customer.Account;
import customer.Alacarte;
import customer.Student;
import customer.Unlimited;
import java.util.Scanner;
import moes.Moes;
import product.Media;

public class Main {
    private Moes moes;
    private Menu menu;
    private String output;

    public Main() {
        moes = new Moes();
        menu = new Menu();
        initializeMenu();
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
        System.out.print("Enter student's email: ");
        String email = scanner.nextLine();
        System.out.print("Enter student's ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Select account type (1 for Alacarte, 2 for Unlimited): ");
        int accountType = Integer.parseInt(scanner.nextLine());

        Account account;
        if (accountType == 1) {
            System.out.print("Enter initial points for Alacarte: ");
            int points = Integer.parseInt(scanner.nextLine());
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
        System.out.print("Enter media type: ");
        String type = scanner.nextLine();
        System.out.print("Enter media duration (in minutes): ");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter media URL: ");
        String url = scanner.nextLine();
        System.out.print("Enter points required to view media: ");
        int points = Integer.parseInt(scanner.nextLine());

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

    public static void main(String[] args) {
        Main app = new Main();
        app.mdi();
    }
}
