package test;

import product.Media;
import customer.Unlimited;
import customer.Account;

public class TestAccount {
    public static void main(String[] args) {
        Unlimited acc1 = new Unlimited(); 
        if (acc1.getAccountNumber() != 1) {
            System.err.println("FAIL: Expected account number 1 but got " + acc1.getAccountNumber());
        }

        Unlimited acc2 = new Unlimited(); 
        if (acc2.getAccountNumber() != 2) {
            System.err.println("FAIL: Expected account number 2 but got " + acc2.getAccountNumber());
        }

        Media media = new Media("The Little Shop of Horrors", "https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0", 10);
        String playResult = acc1.play(media);
        if (!playResult.equals("Playing The Little Shop of Horrors (https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0, 10 points)")) {
            System.err.println("FAIL: Expected 'Playing The Little Shop of Horrors (https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0, 10 points)' but got '" + playResult + "'");
        }
    }
}
