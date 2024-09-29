package mdi;

import java.util.function.Consumer;

public class MenuItem {
    private final String text;
    private final Runnable action;

    public MenuItem(String text, Runnable action) {
        this.text = text;
        this.action = action;
    }

    public String getText() {
        return text;
    }

    public void execute() {
        action.run();
    }
}

