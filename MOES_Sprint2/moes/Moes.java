package moes;

import customer.Student;
import customer.Alacarte;
import customer.Unlimited;
import product.Media;
import java.util.ArrayList;
import java.util.List;

public class Moes {
    private List<Media> library = new ArrayList<>();
    private List<Student> customers = new ArrayList<>();

    public void addMedia(Media media) {
        library.add(media);
    }

    public String getMediaList() {
        StringBuilder menu = new StringBuilder();
        for (int i = 0; i < library.size(); i++) {
            Media media = library.get(i);
            menu.append(i)
                .append(") ")
                .append(media.toString())
                .append("\n");
        }
        return menu.toString();
    }

    public void addStudent(Student student) {
        customers.add(student);
    }


    public String getStudentList() {
        StringBuilder menu = new StringBuilder();
        for (int i = 0; i < customers.size(); i++) {
            Student student = customers.get(i);
            menu.append(i)
                .append(") ")
                .append(student.toString())
                .append("\n");
        }
        return menu.toString();
    }

    public int getPoints(int studentIndex) {
        if (studentIndex < 0 || studentIndex >= customers.size()) {
            throw new IndexOutOfBoundsException("Invalid student index.");
        }
        Student student = customers.get(studentIndex);
        if (student.getAccount() instanceof Alacarte) {
            return ((Alacarte) student.getAccount()).getPointsRemaining();
        } else if (student.getAccount() instanceof Unlimited) {
            return Integer.MAX_VALUE;
        } else {
            throw new UnsupportedOperationException("Unknown subclass of Account");
        }
    }

    public String buyPoints(int studentIndex, int points) {
        if (studentIndex < 0 || studentIndex >= customers.size()) {
            throw new IndexOutOfBoundsException("Invalid student index.");
        }
        Student student = customers.get(studentIndex);
        if (student.getAccount() instanceof Alacarte) {
            ((Alacarte) student.getAccount()).buyPoints(points);
            return "Student now has " + ((Alacarte) student.getAccount()).getPointsRemaining() + " points.";
        } else if (student.getAccount() instanceof Unlimited) {
            return "Student has an unlimited account and needs no additional points.";
        } else {
            throw new UnsupportedOperationException("Unknown subclass of Account");
        }
    }

    public String playMedia(int studentIndex, int mediaIndex) {
        if (studentIndex < 0 || studentIndex >= customers.size()) {
            throw new IndexOutOfBoundsException("Invalid student index.");
        }
        if (mediaIndex < 0 || mediaIndex >= library.size()) {
            throw new IndexOutOfBoundsException("Invalid media index.");
        }
        Student student = customers.get(studentIndex);
        Media media = library.get(mediaIndex);
        return student.requestMedia(media);
    }
}

