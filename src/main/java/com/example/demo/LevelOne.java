package main.java.com.example.demo;

import com.example.demo.controller.Controller;
import javafx.stage.Stage;

/**
 * Represents the first level in the game.
 * This level introduces basic gameplay mechanics such as enemy spawning, health management, and progression to the next level.
 */
public class LevelOne extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * Constructs a LevelOne object with the specified screen dimensions, stage, and controller.
	 *
	 * @param screenHeight the height of the game screen.
	 * @param screenWidth  the width of the game screen.
	 * @param stage        the current game stage.
	 * @param controller   the controller managing the game's flow.
	 */
	public LevelOne(double screenHeight, double screenWidth, Stage stage, Controller controller) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, stage, controller);
	}

	/**
	 * Initializes the player's unit and adds it to the game root.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks if the game is over by evaluating the player's status.
	 * Ends the game if the player is destroyed or progresses to the next level upon reaching the kill target.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget()) {
			cleanupLevel();
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Spawns enemy units based on the current number of enemies and spawn probability.
	 * Ensures that the total number of enemies does not exceed the predefined limit.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Creates and returns a LevelView instance configured for Level One.
	 *
	 * @return the {@link LevelView} instance for Level One.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the player has reached the required number of kills to advance to the next level.
	 *
	 * @return {@code true} if the player has reached the kill target; {@code false} otherwise.
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}
