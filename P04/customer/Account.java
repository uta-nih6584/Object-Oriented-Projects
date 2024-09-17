package customer;
import product.Media;

public class Account {
	public static int nextAccountNumber = 1;
	private int accountNumber;

    	public Account() {
        this.accountNumber = nextAccountNumber++;
    }

    	public int getAccountNumber() {
        return accountNumber;
    }

    	public String play(Media media) {
        return "Playing " + media.toString();
    }
}


