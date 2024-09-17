package product;

/**
 * Represents a media item with a title, URL, and associated points.
 * 
 * @author ##YOUR NAME##
 * @version 0.2
 * @since 1.0
 */
public class Media {
    private String title;
    private String url;
    private int points;

    /**
     * Constructs a Media object with the specified title, URL, and points.
     * 
     * @param title The title of the media item.
     * @param url The URL where the media can be found.
     * @param points The number of points associated with the media.
     * @since 1.0
     */
    public Media(String title, String url, int points) {
        this.title = title;
        this.url = url;
        this.points = points;
    }

    /**
     * Returns the number of points associated with the media item.
     * 
     * @return The number of points.
     * @since 1.0
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns a string representation of the Media object.
     * 
     * @return A string representation of the media item including title, URL, and points.
     * @since 1.0
     */
    @Override
    public String toString() {
        return title + " (" + url + ", " + points + " points)";
    }
}
