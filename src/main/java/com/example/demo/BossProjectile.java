package main.java.com.example.demo;

/**
 * Represents a projectile fired by the boss in the game.
 * The projectile moves horizontally toward the player's position.
 */
public class BossProjectile extends Projectile {

	// Constants defining the image properties and movement behavior
	private static final String IMAGE_NAME = "fireball.png";
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructs a BossProjectile at a specified vertical position.
	 *
	 * @param initialYPos The initial vertical position of the projectile.
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the horizontal position of the projectile by applying its velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the projectile's behavior by calling its position update method.
	 * This method ensures the projectile moves as intended.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
