package customer;

import product.Media;

public abstract class Account {
    private static int nextAccountNumber = 1;
    private int accountNumber;

    public Account() {
        this.accountNumber = nextAccountNumber++;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public abstract String play(Media media);
}


