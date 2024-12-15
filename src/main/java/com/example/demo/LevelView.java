package main.java.com.example.demo;

import com.example.demo.controller.Controller;
import javafx.scene.Group;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static main.java.com.example.demo.LevelViewLevelTwo.SHIELD_X_POSITION;
import static main.java.com.example.demo.LevelViewLevelTwo.SHIELD_Y_POSITION;

/**
 * Represents the visual components and UI management for a level in the game.
 * This class handles health displays, score updates, shield visibility, and win/lose screens.
 */
public class LevelView {

	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = -160;
	private static final int LOSS_SCREEN_Y_POSISITION = -375;

	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	private final ScoreDisplay scoreDisplay;
	private final BossHealthDisplay healthDisplay;
	private final ShieldImage shieldImage;

	/**
	 * Constructs a `LevelView` object with the specified root and initial number of hearts.
	 *
	 * @param root            the root group for UI components.
	 * @param heartsToDisplay the initial number of hearts to display.
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSISITION);
		this.scoreDisplay = new ScoreDisplay();
		this.healthDisplay = new BossHealthDisplay();
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);

		root.getChildren().add(shieldImage); // Add the shield image to the root initially
		shieldImage.setVisible(false);
	}

	/**
	 * Displays the heart UI component on the screen.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays the score UI component on the screen.
	 */
	public void showScoreDisplay() {
		root.getChildren().add(scoreDisplay.getContainer());
	}

	/**
	 * Displays the health UI component on the screen.
	 */
	public void showHealthDisplay() {
		if (!root.getChildren().contains(healthDisplay.getContainer())) {
			root.getChildren().add(healthDisplay.getContainer());
		}
	}

	/**
	 * Updates the boss's health display dynamically.
	 *
	 * @param health the current health of the boss.
	 */
	public void updateBossHealth(int health) {
		healthDisplay.updateHealth(health);
	}

	/**
	 * Displays the shield active indicator on the UI.
	 */
	public void displayShieldOn() {
		healthDisplay.displayShieldOn();
		shieldImage.setVisible(true); // Show the shield
	}

	/**
	 * Hides the shield indicator from the UI.
	 */
	public void hideShield() {
		shieldImage.setVisible(false); // Hide the shield
	}

	/**
	 * Displays the win screen with the final score and options to replay or exit.
	 *
	 * @param finalScore the player's final score.
	 * @param stage      the current game stage.
	 */
	public void showWinImage(int finalScore, Stage stage) {
		root.getChildren().add(winImage);
		winImage.showWinImage();
		showFinalScore(finalScore, stage);
	}

	/**
	 * Displays the game-over screen with the final score and options to replay or exit.
	 *
	 * @param finalScore the player's final score.
	 * @param stage      the current game stage.
	 */
	public void showGameOverImage(int finalScore, Stage stage) {
		root.getChildren().add(gameOverImage);
		showFinalScore(finalScore, stage);
	}

	/**
	 * Displays the final score on the screen with replay and exit options.
	 *
	 * @param finalScore the player's final score.
	 * @param stage      the current game stage.
	 */
	private void showFinalScore(int finalScore, Stage stage) {
		// Final score label
		Label finalScoreLabel = new Label("Final Score: " + finalScore);
		finalScoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
		finalScoreLabel.setStyle("-fx-text-fill: white; -fx-effect: dropshadow(gaussian, black, 3, 0.5, 2, 2);");
		finalScoreLabel.setLayoutX(400);
		finalScoreLabel.setLayoutY(300);

		// Replay button
		Button replayButton = new Button("Replay");
		replayButton.setFont(Font.font("Arial", 20));
		replayButton.setStyle("-fx-background-color: #0096FF; -fx-text-fill: white; -fx-padding: 10 20;");
		replayButton.setLayoutX(450);
		replayButton.setLayoutY(400);
		replayButton.setOnAction(e -> restartGame(stage));

		// Exit button
		Button exitButton = new Button("Exit");
		exitButton.setFont(Font.font("Arial", 20));
		exitButton.setStyle("-fx-background-color: #FF5733; -fx-text-fill: white; -fx-padding: 10 20;");
		exitButton.setLayoutX(650);
		exitButton.setLayoutY(400);
		exitButton.setOnAction(e -> System.exit(0));

		// Add elements to root
		root.getChildren().addAll(finalScoreLabel, replayButton, exitButton);
	}

	/**
	 * Restarts the game by reloading Level One.
	 *
	 * @param stage the current game stage.
	 */
	private void restartGame(Stage stage) {
		try {
			Controller controller = new Controller(stage);
			controller.launchGame();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Updates the player's score display dynamically.
	 *
	 * @param score the player's current score.
	 */
	public void updateScore(int score) {
		scoreDisplay.updateScore(score);
	}

	/**
	 * Removes hearts from the heart display based on the remaining number of hearts.
	 *
	 * @param heartsRemaining the number of hearts left to display.
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}
}
