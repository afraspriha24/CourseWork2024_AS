package main.java.com.example.demo;

/**
 * Represents a projectile fired by the user's plane in the game.
 * The `UserProjectile` moves horizontally to the right with a fixed velocity.
 */
public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 125;
	private static final int HORIZONTAL_VELOCITY = 15;

	/**
	 * Constructs a `UserProjectile` at the specified initial position.
	 *
	 * @param initialXPos the initial x-coordinate of the projectile.
	 * @param initialYPos the initial y-coordinate of the projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile.
	 * Moves the projectile horizontally to the right based on its velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the projectile's behavior each frame.
	 * Includes position updates.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
