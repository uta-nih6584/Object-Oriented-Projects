package mdi;

import customer.Student;
import moes.Moes;

public class Main {
    private Moes moes = new Moes();
    private Menu menu = new Menu();
    private StringBuilder output = new StringBuilder();  
    private boolean running = true;

    public Main() {
        setupMenu();
    }

    private void setupMenu() {
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
        while (running) {
            System.out.println(output.toString());
            output.setLength(0);  
            menu.run();
        }
    }

    public void endApp() {
        running = false;
    }

    private void addStudent() {
        String name = Menu.getString("Enter student name: ");
        String email = Menu.getString("Enter student email: ");
        Student student = new Student(name, email);
        moes.addStudent(student);
        output.append("Student added: ").append(student.toString()).append("\n");
    }

    public void listStudents() {
        output.append(moes.getStudentList());
    }

    public void addMedia() {
        String mediaName = Menu.getString("Enter media name: ");
        output.append("Media added: ").append(mediaName);
    }

    public void listMedia() {
        output.append(moes.getMediaList());
    }

    public void playMedia() {
        int studentIndex = Menu.getInt("Enter student index: ");
        int mediaIndex = Menu.getInt("Enter media index: ");
        output.append(moes.playMedia(studentIndex, mediaIndex));
    }

    public void listAvailablePoints() {
        int studentIndex = Menu.getInt("Enter student index: ");
        output.append("Available points: ").append(moes.getPoints(studentIndex));
    }

    public void buyPoints() {
        int studentIndex = Menu.getInt("Enter student index: ");
        int currentPoints = moes.getPoints(studentIndex);
        int pointsToBuy = Menu.getInt("Current points: " + currentPoints + "\nEnter points to buy: ");
        if (pointsToBuy >= 0) {
            output.append(moes.buyPoints(studentIndex, pointsToBuy));
        } else {
            output.append("Cannot purchase negative points!");
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.mdi();
    }
}
