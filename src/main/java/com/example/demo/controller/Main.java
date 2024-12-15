package main.java.com.example.demo.controller;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Main class for the Sky Battle game application.
 * This class serves as the entry point for the application and manages
 * the initialization and display of the start menu and game launch.
 */
public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300; // Width of the application window
	private static final int SCREEN_HEIGHT = 750; // Height of the application window
	private static final String TITLE = "Sky Battle"; // Title of the game
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg"; // Path to background image

	private Controller myController; // Controller instance to manage game logic and transitions

	/**
	 * The main entry point for JavaFX applications.
	 *
	 * @param stage The primary stage for this application, onto which
	 *              the application scene can be set.
	 */
	@Override
	public void start(Stage stage) {
		// Set up the main stage properties
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);

		// Show the start menu
		showStartMenu(stage);
	}

	/**
	 * Displays the start menu with game title, instructions, and buttons for
	 * starting the game or exiting the application.
	 *
	 * @param stage The primary stage where the start menu is displayed.
	 */
	private void showStartMenu(Stage stage) {
		// Create title label
		Label titleLabel = new Label("Sky Battle");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));
		titleLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-effect: dropshadow(gaussian, black, 3, 0.5, 2, 2);");

		// Create buttons
		Button startButton = new Button("Start Game");
		Button exitButton = new Button("Exit");

		// Style buttons
		startButton.setStyle(
				"-fx-font-size: 20px; -fx-background-color: #0096FF; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 10; -fx-background-radius: 10;");
		exitButton.setStyle(
				"-fx-font-size: 20px; -fx-background-color: #FF5733; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 10; -fx-background-radius: 10;");

		// Button actions
		startButton.setOnAction(e -> startGame(stage));
		exitButton.setOnAction(e -> System.exit(0));

		// Create instructions text
		Label instructionsLabel = new Label(
				"Instructions:\n1. Use arrow keys to move the jet up and down.\n2. Use the spacebar to shoot bullets. Hold it to shoot continuously.\n3. Avoid enemy bullets and destroy enemy planes.");
		instructionsLabel.setFont(Font.font("Arial", 18));
		instructionsLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-padding: 20; -fx-alignment: center;");

		// Layout for the menu
		VBox menuLayout = new VBox(20);
		menuLayout.setAlignment(Pos.CENTER);
		menuLayout.getChildren().addAll(titleLabel, startButton, exitButton, instructionsLabel);

		// Background image
		ImageView backgroundImage = new ImageView(new Image(getClass().getResourceAsStream(BACKGROUND_IMAGE_NAME)));
		backgroundImage.setFitWidth(SCREEN_WIDTH);
		backgroundImage.setFitHeight(SCREEN_HEIGHT);
		backgroundImage.setPreserveRatio(false);

		// Combine background and menu layout
		StackPane root = new StackPane();
		root.getChildren().addAll(backgroundImage, menuLayout);

		// Set the scene
		Scene startMenuScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		stage.setScene(startMenuScene);
		stage.show();
	}

	/**
	 * Starts the game by initializing the controller and launching the gameplay.
	 *
	 * @param stage The primary stage where the game will be displayed.
	 */
	private void startGame(Stage stage) {
		try {
			// Initialize the controller and launch the game
			myController = new Controller(stage);
			myController.launchGame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method that launches the JavaFX application.
	 *
	 * @param args Command-line arguments (not used).
	 */
	public static void main(String[] args) {
		launch();
	}
}
