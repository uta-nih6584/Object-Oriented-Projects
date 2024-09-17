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
        this.account = new Unlimited(); 
    }

    public String requestMedia(Media media) {
        return account.play(media);
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return name + " (" + studentId + ", " + email + ", Account #" + account.getAccountNumber() + ")";
    }
}
