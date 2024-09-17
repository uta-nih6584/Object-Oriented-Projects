package customer;

import product.Media;

public class Student {
    private static int nextStudentId = 1;
    private int studentId;
    private String name;
    private String email;
    private Account account;

    public Student(String name, int id, String email) {
        this.name = name;
        this.studentId = id;
        this.email = email;
        validateEmail(email);
        this.account = new Unlimited(); // Ensure Unlimited is used correctly
    }

    private void validateEmail(String email) {
        if (!email.endsWith("@mavs.uta.edu")) {
            throw new IllegalArgumentException("Non-UTA email address: " + email);
        }
    }

    public String requestMedia(Media media) {
        return account.play(media);
    }

    @Override
    public String toString() {
        return name + " (" + studentId + ", " + email + ", Account #" + account.getAccountNumber() + ")";
    }
}
