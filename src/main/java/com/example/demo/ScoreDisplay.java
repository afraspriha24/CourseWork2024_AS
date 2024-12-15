package main.java.com.example.demo;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * Represents a display for showing the player's score in the game.
 * This class is responsible for dynamically updating and rendering the score UI element.
 */
public class ScoreDisplay {

    private static final double SCORE_X_POSITION = 1100; // Top-right position
    private static final double SCORE_Y_POSITION = 25;
    private static final int FONT_SIZE = 24;

    private HBox container;
    private Label scoreLabel;

    /**
     * Constructs a new `ScoreDisplay` object and initializes its components.
     */
    public ScoreDisplay() {
        initializeContainer();
        initializeScoreLabel();
    }

    /**
     * Initializes the container for the score display.
     * The container is positioned at the top-right corner of the screen.
     */
    private void initializeContainer() {
        container = new HBox();
        container.setLayoutX(SCORE_X_POSITION);
        container.setLayoutY(SCORE_Y_POSITION);
    }

    /**
     * Initializes the score label with default styling and a starting score of 0.
     */
    private void initializeScoreLabel() {
        scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(Font.font("Arial", FONT_SIZE));
        scoreLabel.setStyle("-fx-text-fill: white; -fx-padding: 10;");
        container.getChildren().add(scoreLabel);
    }

    /**
     * Updates the displayed score to the provided value.
     *
     * @param score the new score value to display.
     */
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    /**
     * Retrieves the container holding the score display.
     * This container can be added to the game's root layout.
     *
     * @return the `HBox` container for the score display.
     */
    public HBox getContainer() {
        return container;
    }
}
