package test;

import customer.Alacarte;  
import product.Media;

public class TestAlacarte {
    public static void main(String[] args) {
        int failureCount = 0;

        Alacarte alacarte = new Alacarte(20);
        if (alacarte.getPointsRemaining() != 20) {
            System.err.println("FAIL: Expected 20 points remaining but got " + alacarte.getPointsRemaining());
            failureCount++;
        }

        alacarte.buyPoints(30);
        if (alacarte.getPointsRemaining() != 50) {
            System.err.println("FAIL: Expected 50 points remaining but got " + alacarte.getPointsRemaining());
            failureCount++;
        }

        Media media = new Media("Sample Movie", "https://example.com/movie", 10);
        String playResult = alacarte.play(media);
        if (!playResult.equals("Playing Sample Movie (https://example.com/movie, 10 points)")) {
            System.err.println("FAIL: Incorrect play result: " + playResult);
            failureCount++;
        }

        if (alacarte.getPointsRemaining() != 40) {
            System.err.println("FAIL: Expected 40 points remaining but got " + alacarte.getPointsRemaining());
            failureCount++;
        }

        System.exit(failureCount);
    }
}
