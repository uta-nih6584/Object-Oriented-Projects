package product;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Represents a media item with a title, URL, and associated points.
 * 
 * @author ##YOUR NAME##
 * @version 0.2
 * @since 0.2
 */
public class Media {
    private String title;
    private String url;
    private int points;

    /**
     * Constructs Media with the specified title, URL, and points.
     * 
     * @param title the title of the media item
     * @param url the URL of the media item
     * @param points the points associated with the media item
     * @since 0.2
     */
    public Media(String title, String url, int points) {
        this.title = title;
        validateUrl(url);
        this.url = url;
        this.points = points;
    }

    /**
     * Returns the points associated with this media item.
     * 
     * @return the points associated with the media item
     * @since 0.2
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns a string representation of this media item.
     * 
     * @return a string representation of this media item
     * @since 0.2
     */
    @Override
    public String toString() {
        return title + " (" + url + ", " + points + " points)";
    }

    private void validateUrl(String url) {
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL: " + url, e);
        }
    }
}
