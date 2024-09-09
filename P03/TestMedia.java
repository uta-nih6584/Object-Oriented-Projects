public class TestMedia {
    public static void main(String[] args) {
        Media media = new Media("The Little Shop of Horrors", "https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0");

        if (!media.toString().equals("The Little Shop of Horrors (https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0)")) {
            System.err.println("FAIL: Expected 'The Little Shop of Horrors (https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0)' but got '" + media.toString() + "'");
        }

        Media media2 = new Media("Public Domain Film", "https://example.com/film");

        if (!media2.toString().equals("Public Domain Film (https://example.com/film)")) {
            System.err.println("FAIL: Expected 'Public Domain Film (https://example.com/film)' but got '" + media2.toString() + "'");
        }
    }
}


