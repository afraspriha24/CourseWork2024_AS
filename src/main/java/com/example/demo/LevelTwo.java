package main.java.com.example.demo;

import com.example.demo.controller.Controller;
import javafx.stage.Stage;

/**
 * Represents the second level in the game.
 * This level introduces a boss fight with shield mechanics, health management, and a unique UI.
 */
public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final String NEXT_LEVEL = null; // Placeholder for future levels
	private static final int PLAYER_INITIAL_HEALTH = 5;

	private final Boss boss;
	private LevelViewLevelTwo levelView;
	private boolean bossSpawned = false;

	/**
	 * Constructs a LevelTwo object with the specified screen dimensions, stage, and controller.
	 *
	 * @param screenHeight the height of the game screen.
	 * @param screenWidth  the width of the game screen.
	 * @param stage        the current game stage.
	 * @param controller   the controller managing the game's flow.
	 */
	public LevelTwo(double screenHeight, double screenWidth, Stage stage, Controller controller) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, stage, controller);
		boss = new Boss();
	}

	/**
	 * Initializes the player's unit and adds it to the game root.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks if the game is over by evaluating the player's and boss's status.
	 * Ends the game if the player is destroyed or progresses to victory if the boss is defeated.
	 * Updates the UI to reflect the boss's shield and health status.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else {
			if (boss.isShieldActive()) {
				levelView.displayShieldOn(); // Update UI for shield
				levelView.showShield();
			} else {
				levelView.updateBossHealth(boss.getHealth()); // Update health display
				levelView.hideShield();
			}
			if (boss.isDestroyed()) {
				winGame();
			}
		}
	}

	/**
	 * Spawns the boss as the main enemy unit for Level Two.
	 * Ensures the boss is added to the game only once and remains until defeated.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0 && !boss.isDestroyed() && !bossSpawned) {
			addEnemyUnit(boss);
			bossSpawned = true;
		}
	}

	/**
	 * Creates and returns a LevelViewLevelTwo instance for Level Two.
	 * This specialized view includes UI components tailored for the boss fight.
	 *
	 * @return the {@link LevelViewLevelTwo} instance for Level Two.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}
}
