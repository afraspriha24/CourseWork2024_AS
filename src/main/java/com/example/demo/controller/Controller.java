package main.java.com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.com.example.demo.LevelParent;

/**
 * The Controller class manages transitions between levels in the Sky Battle game.
 * It also acts as an observer for level changes and handles dynamic level loading.
 */
public class Controller implements Observer {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne"; // Fully qualified class name for Level One
	private final Stage stage; // The primary stage where the game levels are displayed
	private boolean isLevelChanging = false; // Flag to prevent re-entrant level transitions

	/**
	 * Constructs a Controller instance.
	 *
	 * @param stage The primary stage where the game levels are displayed.
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launches the game by displaying the primary stage and starting Level One.
	 *
	 * @throws SecurityException If there is a security violation during level loading.
	 * @throws IllegalArgumentException If an invalid argument is passed during level initialization.
	 */
	public void launchGame() throws SecurityException, IllegalArgumentException {
		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Transitions to a specified level dynamically using reflection.
	 *
	 * @param className The fully qualified class name of the level to transition to.
	 */
	public void goToLevel(String className) {
		if (isLevelChanging) {
			return; // Prevent re-entrant calls
		}

		isLevelChanging = true;
		try {
			// Load the class dynamically
			Class<?> myClass = Class.forName(className);
			Constructor<?> constructor = myClass.getConstructor(double.class, double.class, Stage.class, Controller.class);

			// Create an instance of the level
			LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth(), stage, this);
			myLevel.addObserver(this);

			// Initialize the scene and set it on the stage
			Scene scene = myLevel.initializeScene();
			stage.setScene(scene);

			// Start the level
			myLevel.startGame();
		} catch (Exception e) {
			e.printStackTrace();

			// Show an error alert if level transition fails
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Error transitioning to " + className + ": " + e.getMessage());
			alert.show();
		} finally {
			isLevelChanging = false; // Allow future transitions
		}
	}

	/**
	 * Called when the observed object is changed.
	 * Handles level transitions when notified by the current level.
	 *
	 * @param observable The observable object.
	 * @param arg The argument passed by the observable, typically the class name of the next level.
	 */
	@Override
	public void update(Observable observable, Object arg) {
		try {
			goToLevel((String) arg);
		} catch (SecurityException | IllegalArgumentException e) {
			// Show an error alert if level transition fails
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}
}
