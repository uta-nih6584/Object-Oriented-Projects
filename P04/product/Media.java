package product;

import java.net.MalformedURLException;
import java.net.URL;

public class Media {
    private String title;
    private String url;
    private int points;

    public Media(String title, String url, int points) {
        this.title = title;
        validateUrl(url);
        this.url = url;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    private void validateUrl(String url) {
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL: " + url, e);
        }
    }

    @Override
    public String toString() {
        return title + " (" + url + ", " + points + " points)";
    }
}
