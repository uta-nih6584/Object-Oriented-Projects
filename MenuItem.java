package mdi;

public class MenuItem {
    private String title;
    private Runnable action;

    public MenuItem(String title, Runnable action) {
        this.title = title;
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void run() {
        action.run();
    }
}

