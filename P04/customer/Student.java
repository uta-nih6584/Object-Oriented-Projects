package customer;

import product.Media;

public class Student {
    private String name;
    private int id;
    private String email;
    private Account account;

    public Student(String name, int id, String email) {
        if (!isValidUTAEmail(email)) {
            throw new IllegalArgumentException("Non-UTA email address: " + email);
        }
        this.name = name;
        this.id = id;
        this.email = email;
        this.account = new Account();
    }

    private boolean isValidUTAEmail(String email) {
        return email.matches("[a-zA-Z]{2,}[0-9]{4}@mavs\\.uta\\.edu");
    }

    public String requestMedia(Media media) {
        return account.play(media);
    }

    @Override
    public String toString() {
        return name + " (" + id + ", " + email + ", Account #" + account.getAccountNumber() + ")";
    }
}

