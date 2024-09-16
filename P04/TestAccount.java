public class TestAccount {
    public static void main(String[] args) {
        Account acc1 = new Account();
        if (acc1.getAccountNumber() != 1) {
            System.err.println("FAIL: Expected account number 1 but got " + acc1.getAccountNumber());
        }

        Account acc2 = new Account();
        if (acc2.getAccountNumber() != 2) {
            System.err.println("FAIL: Expected account number 2 but got " + acc2.getAccountNumber());
        }

        Media media = new Media("The Little Shop of Horrors", "https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0");
        String playResult = acc1.play(media);
        if (!playResult.equals("Playing The Little Shop of Horrors (https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0)")) {
            System.err.println("FAIL: Expected 'Playing The Little Shop of Horrors (https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0)' but got '" + playResult + "'");
        }
    }
}
