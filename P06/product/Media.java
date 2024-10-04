package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Represents a media item with a title, URL, and associated points.
 * 
 * 
 * @author ##Najat Hussein##
 * @version 0.2
 * @since 1.0
 */
public class Media {
    private String title;
    private String type; // New field for the media type
    private int duration; // New field for the duration of the media
    private String url;
    private int points;

    /**
     * Constructs a Media object with the specified title, type, duration, URL, and points.
     * 
     * @param title The title of the media item.
     * @param type The type of media (e.g., Horror, Comedy, etc.).
     * @param duration The duration of the media item in minutes.
     * @param url The URL where the media can be found.
     * @param points The number of points associated with the media.
     * @since 1.0
     */
    public Media(String title, String type, int duration, String url, int points) {
        this.title = title;
        this.type = type;
        this.duration = duration;
        this.url = url;
        this.points = points;
    }

    /**
     * Constructs a Media object by reading from a BufferedReader.
     * 
     * @param br The BufferedReader used to read the media item.
     * @throws IOException If an I/O error occurs during reading.
     * @since 1.0
     */
    public Media(BufferedReader br) throws IOException {
        this.title = br.readLine();
        this.type = br.readLine();
        this.duration = Integer.parseInt(br.readLine());
        this.url = br.readLine();
        this.points = Integer.parseInt(br.readLine());
    }

    /**
     * Saves the media item to a file using the provided BufferedWriter.
     * 
     * @param bw The BufferedWriter used to write the media item.
     * @throws IOException If an I/O error occurs during writing.
     * @since 1.0
     */
    public void save(BufferedWriter bw) throws IOException {
        bw.write(title + '\n');
        bw.write(type + '\n');
        bw.write(Integer.toString(duration) + '\n');
        bw.write(url + '\n');
        bw.write(Integer.toString(points) + '\n');
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
     * Returns the URL of the media item.
     * 
     * @return The URL where the media can be accessed.
     * @since 1.0
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the title of the media item.
     * 
     * @return The title of the media.
     * @since 1.0
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the type of the media item.
     * 
     * @return The type of the media (e.g., Horror, Comedy).
     * @since 1.0
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the duration of the media item in minutes.
     * 
     * @return The duration of the media item.
     * @since 1.0
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Returns a string representation of the Media object.
     * 
     * @return A string representation of the media item including title, type, duration, URL, and points.
     * @since 1.0
     */
    @Override
    public String toString() {
        return title + " (" + type + ", " + duration + " mins, " + url + ", " + points + " points)";
    }
}
