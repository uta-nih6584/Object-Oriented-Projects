package test;

import product.Media;

public class TestMedia {
    public static void main(String[] args) {
        int failureCount = 0;

        try {
            Media media1 = new Media("YouTube Video", "https://youtube.com", 5);
        } catch (RuntimeException e) {
            System.err.println("FAIL: Valid URL failed validation: https://youtube.com");
            failureCount++;
        }

        try {
            Media media2 = new Media("Library Media", "file://media/lib/garp.mp4", 10);
        } catch (RuntimeException e) {
            System.err.println("FAIL: Valid URL failed validation: file://media/lib/garp.mp4");
            failureCount++;
        }

        String[] invalidUrls = {
            "hello.world",
            "htt://badurl.com",
            "flub://badurl.com"
        };

        for (String url : invalidUrls) {
            try {
                new Media("Test Media", url, 5);
                System.err.println("FAIL: Invalid URL accepted: " + url);
                failureCount++;
            } catch (RuntimeException e) {
            }
        }

        System.exit(failureCount);
    }
}
