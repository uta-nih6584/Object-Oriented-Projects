package mdi;

public class MenuItem {
    private String label; // Label for the menu item
    private Runnable action; // Action to perform when the menu item is selected

    public MenuItem(String label, Runnable action) {
        this.label = label; // Set the label for the menu item
        this.action = action; // Set the action to be performed
    }

    // Method to get the label of the menu item
    public String getLabel() {
        return label;
    }

    // Method to execute the action associated with the menu item
    public void execute() {
        action.run(); // Execute the action
    }
}
