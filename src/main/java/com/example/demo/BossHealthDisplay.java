package main.java.com.example.demo;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * Manages the display of the boss's health or shield status on the game UI.
 * This class creates and updates a health display component dynamically
 * based on the boss's health and shield status.
 */
public class BossHealthDisplay {

    // Constants for layout and font properties
    private static final double HEALTH_X_POSITION = 1100; // Top-right position
    private static final double HEALTH_Y_POSITION = 45;
    private static final int FONT_SIZE = 24;

    // UI components
    private HBox container;
    private Label healthLabel;

    /**
     * Constructs a BossHealthDisplay object and initializes the UI components
     * for displaying the boss's health or shield status.
     */
    public BossHealthDisplay() {
        initializeContainer();
        initializeHealthLabel();
    }

    /**
     * Initializes the container (HBox) for the health display and sets its position.
     */
    private void initializeContainer() {
        container = new HBox();
        container.setLayoutX(HEALTH_X_POSITION);
        container.setLayoutY(HEALTH_Y_POSITION);
    }

    /**
     * Initializes the health label with default text and styles,
     * and adds it to the container.
     */
    private void initializeHealthLabel() {
        healthLabel = new Label("Boss Health: 20"); // Initial health
        healthLabel.setFont(Font.font("Arial", FONT_SIZE));
        healthLabel.setStyle("-fx-text-fill: white; -fx-padding: 10;");
        container.getChildren().add(healthLabel);
    }

    /**
     * Updates the health display with the boss's current health.
     *
     * @param health The current health value of the boss.
     */
    public void updateHealth(int health) {
        Platform.runLater(() -> healthLabel.setText("Boss Health: " + health));
    }

    /**
     * Updates the health display to indicate that the boss's shield is active.
     */
    public void displayShieldOn() {
        Platform.runLater(() -> healthLabel.setText("Shield On"));
    }

    /**
     * Retrieves the container (HBox) for the health display,
     * which can be added to the game UI.
     *
     * @return The container holding the health display.
     */
    public HBox getContainer() {
        return container;
    }
}
