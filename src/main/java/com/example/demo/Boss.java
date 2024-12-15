package main.java.com.example.demo;

import java.util.*;

/**
 * Represents the boss character in the game. The boss moves vertically
 * following a predefined pattern, fires projectiles, and can activate a shield
 * to block incoming damage.
 */
public class Boss extends FighterPlane {

	// Constants
	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double BOSS_FIRE_RATE = 0.1; // Probability of firing
	private static final double BOSS_SHIELD_PROBABILITY = 0.002;
	private static final int FIRE_COOLDOWN_FRAMES = 30; // Cooldown frames
	private static final int IMAGE_HEIGHT = 300;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HEALTH = 20;
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 475;
	private static final int MAX_FRAMES_WITH_SHIELD = 500;

	// Instance variables
	private final List<Integer> movePattern;
	private boolean isShielded;
	private int fireCooldownCounter;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private int framesWithShieldActivated;

	/**
	 * Constructs a Boss object with default properties, including its image,
	 * size, health, and movement pattern.
	 */
	public Boss() {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		fireCooldownCounter = FIRE_COOLDOWN_FRAMES;
		isShielded = false;
		initializeMovePattern();
	}

	/**
	 * Updates the boss's position based on its movement pattern. Prevents the
	 * boss from moving outside the defined boundaries.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Updates the boss's state, including its position, shield status, and firing cooldown.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
		fireCooldownCounter++;
	}

	/**
	 * Fires a projectile if the boss meets the firing conditions, such as cooldown
	 * elapsed and probability threshold.
	 *
	 * @return A BossProjectile instance if the boss fires, or null if not.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (canFireProjectile()) {
			fireCooldownCounter = 0;
			BossProjectile projectile = new BossProjectile(getProjectileInitialPosition());
			return projectile;
		}
		return null;
	}

	/**
	 * Reduces the boss's health if it is not shielded. No damage is taken when
	 * the shield is active.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	/**
	 * Initializes the boss's movement pattern as a shuffled list of velocities
	 * to control its vertical movement.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the boss's shield status. Activates or deactivates the shield based
	 * on a probability condition and a maximum frame count.
	 */
	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
			if (framesWithShieldActivated >= MAX_FRAMES_WITH_SHIELD) {
				deactivateShield();
			}
		} else if (Math.random() < BOSS_SHIELD_PROBABILITY) {
			activateShield();
		}
	}

	/**
	 * Retrieves the next move in the boss's movement pattern. Resets and reshuffles
	 * the pattern if the current move reaches its maximum frame count.
	 *
	 * @return The next vertical movement value.
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * Checks if the boss is allowed to fire a projectile based on cooldown and
	 * probability conditions.
	 *
	 * @return true if the boss can fire, false otherwise.
	 */
	private boolean canFireProjectile() {
		return fireCooldownCounter >= FIRE_COOLDOWN_FRAMES && Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Calculates the initial y-position for the boss's projectile.
	 *
	 * @return The y-coordinate for the projectile.
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Activates the boss's shield and resets the shield frame counter.
	 */
	private void activateShield() {
		isShielded = true;
		framesWithShieldActivated = 0;
	}

	/**
	 * Deactivates the boss's shield.
	 */
	private void deactivateShield() {
		isShielded = false;
	}

	/**
	 * Checks whether the boss's shield is active.
	 *
	 * @return true if the shield is active, false otherwise.
	 */
	public boolean isShieldActive() {
		return isShielded;
	}
}
