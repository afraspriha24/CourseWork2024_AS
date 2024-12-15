package main.java.com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.controller.Controller;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Represents the base class for all game levels, managing common gameplay mechanics, user interactions,
 * enemy behaviors, projectiles, and level transitions.
 */
public abstract class LevelParent extends Observable {

	// Constants
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;

	// Attributes for screen and stage
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	// Core game elements
	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	// Game state tracking
	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;
	private int currentNumberOfEnemies;
	private LevelView levelView;
	private int score;
	private final Stage stage;
	private final Controller controller;

	/**
	 * Constructs a LevelParent instance with the given parameters.
	 *
	 * @param backgroundImageName  the path to the background image for the level
	 * @param screenHeight         the height of the game screen
	 * @param screenWidth          the width of the game screen
	 * @param playerInitialHealth  the initial health of the player
	 * @param stage                the stage on which the game is displayed
	 * @param controller           the controller handling level transitions
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth, Stage stage, Controller controller) {
        this.stage = stage;
        this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		this.score = 0;
		this.controller = controller;

		initializeTimeline();
		friendlyUnits.add(user);
	}

	// Abstract methods to be implemented by subclasses
	protected abstract void initializeFriendlyUnits();

	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes the scene with background, friendly units, and UI components.
	 *
	 * @return the initialized game scene
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		levelView.showScoreDisplay();
		levelView.showHealthDisplay();
		return scene;
	}

	/**
	 * Starts the game by activating the game timeline.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Transitions to the next level using the specified level name.
	 *
	 * @param levelName the name of the next level's class
	 */
	public void goToNextLevel(String levelName) {
		if (controller != null) {
			controller.goToLevel(levelName); // Directly call Controller method
		} else {
			System.out.println("Controller is null. Cannot transition to the next level.");
		}
	}

	/**
	 * Updates the game scene, including spawning enemies, managing collisions, and checking game state.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Initializes the game timeline, setting it to run indefinitely with periodic updates.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Sets up the game background, including key event handling for player controls.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP) user.moveUp();
				if (kc == KeyCode.DOWN) user.moveDown();
				if (kc == KeyCode.SPACE) fireProjectile();
			}
		});
		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
			}
		});
		root.getChildren().add(background);
	}

	/**
	 * Fires a projectile from the user plane and adds it to the game scene and projectile list.
	 */
	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	/**
	 * Generates enemy fire by spawning projectiles for each enemy.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	/**
	 * Spawns a projectile for an enemy and adds it to the game scene and projectile list.
	 *
	 * @param projectile the projectile to be spawned
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Updates the position and state of all active actors in the game.
	 */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	/**
	 * Removes all destroyed actors from the game scene and their respective lists.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	/**
	 * Removes destroyed actors from the specified list and the game scene.
	 *
	 * @param actors the list of actors to be checked and updated
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream()
				.filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Handles collisions between friendly and enemy planes.
	 */
	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handles collisions between user projectiles and enemy units.
	 */
	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and friendly units.
	 */
	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * Detects and processes collisions between two lists of actors, applying damage to both parties.
	 *
	 * @param actors1 the first list of actors
	 * @param actors2 the second list of actors
	 */
	private void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();

					// Check if the actor is the boss and update health
					if (actor instanceof Boss && levelView instanceof LevelViewLevelTwo) {
						Boss boss = (Boss) actor;

						if (boss.isShieldActive()) {
							((LevelViewLevelTwo) levelView).displayShieldOn();
						} else {
							((LevelViewLevelTwo) levelView).updateBossHealth(boss.getHealth());
						}
					}
				}
			}
		}
	}

	/**
	 * Handles enemies that penetrate the user's defenses, reducing the user's health and destroying the enemy.
	 */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	/**
	 * Updates the game UI to reflect the current state of the user's health.
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	/**
	 * Updates the kill count and increments the score for each enemy destroyed.
	 */
	private void updateKillCount() {
		int newKills = currentNumberOfEnemies - enemyUnits.size();
		for (int i = 0; i < newKills; i++) {
			user.incrementKillCount();
			updateScore(100); // Add 100 points per enemy kill
		}
	}

	/**
	 * Checks if an enemy has penetrated the user's defenses.
	 *
	 * @param enemy the enemy to check
	 * @return true if the enemy has moved beyond the screen bounds, false otherwise
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	/**
	 * Stops the game and displays the win screen.
	 */
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage(score, stage);
	}

	/**
	 * Stops the game and displays the game-over screen.
	 */
	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage(score, stage);
	}

	/**
	 * Cleans up the current level, resetting all game objects and clearing resources.
	 */
	public void cleanupLevel() {

		// Stop and clear the timeline to prevent any further updates
		timeline.stop();
		timeline.getKeyFrames().clear();

		// Remove all children from the root node (visual elements)
		getRoot().getChildren().clear();

		// Clear all collections to remove references to actors and projectiles
		friendlyUnits.clear();
		enemyUnits.clear();
		userProjectiles.clear();
		enemyProjectiles.clear();

		// Reset the level state variables
		currentNumberOfEnemies = 0;
		score = 0;

	}

	/**
	 * Retrieves the user's plane in the game.
	 *
	 * @return the {@code UserPlane} representing the player's character.
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Retrieves the root group for the current game scene.
	 *
	 * @return the {@code Group} containing all visual elements of the scene.
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Gets the current number of enemy units present in the game.
	 *
	 * @return the number of enemies in the {@code enemyUnits} list.
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds a new enemy unit to the game.
	 *
	 * @param enemy the {@code ActiveActorDestructible} enemy to add.
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Retrieves the maximum Y position an enemy can spawn at.
	 *
	 * @return the maximum Y-coordinate for enemy placement.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Retrieves the width of the game screen.
	 *
	 * @return the screen width as a double.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks whether the user's plane is destroyed.
	 *
	 * @return {@code true} if the user's plane is destroyed, {@code false} otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Updates the count of active enemy units in the game.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

	/**
	 * Increments the player's score and updates the score display.
	 *
	 * @param points the number of points to add to the player's score.
	 */
	private void updateScore(int points) {
		score += points;
		levelView.updateScore(score); // Update score in the UI
	}

}
