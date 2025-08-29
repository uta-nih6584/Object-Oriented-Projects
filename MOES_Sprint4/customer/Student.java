package customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import product.Media;

public class Student {
    private static int nextStudentId = 1; 
    private int studentId; 
    private String name;
    private String email;
    private Account account; 

    public Student(String name, int studentId, String email, Account account) {
        this.name = name;
        this.studentId = studentId; 
        this.email = email;
        this.account = account; 
    }

    public Student(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.studentId = Integer.parseInt(br.readLine());
        this.email = br.readLine();

        // Read the account type from the stream
        String accountType = br.readLine();
        switch (accountType) {
            case "Unlimited":
                this.account = new Unlimited(br);
                break;
            case "Alacarte":
                this.account = new Alacarte(br);
                break;
            default:
                throw new IOException("Unknown account type: " + accountType);
        }
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(studentId + '\n');
        bw.write(email + '\n');
        bw.write(account.getClass().getName() + '\n'); // Save the account type
        account.save(bw); // Save the account
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
