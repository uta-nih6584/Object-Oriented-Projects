import java.net.MalformedURLException;
import java.net.URL;

public class Media {	
	private String title;
	private String url;

public Media(String title, String url) {
	this.title = title;
	validateUrl(url);
        this.url = url;
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
        return title + " (" + url + ")";
    }
}
