package customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import product.Media;

public abstract class Account {
    private static int nextAccountNumber = 1;
    private int accountNumber;

    public Account() {
        this.accountNumber = nextAccountNumber++;
    }

    public Account(BufferedReader br) throws IOException {
        this.accountNumber = Integer.parseInt(br.readLine());
        nextAccountNumber = Integer.parseInt(br.readLine());
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(Integer.toString(accountNumber) + '\n');
        bw.write(Integer.toString(nextAccountNumber) + '\n');
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public abstract String play(Media media);
}
