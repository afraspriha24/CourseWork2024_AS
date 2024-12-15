package main.java.com.example.demo;

/**
 * Represents a projectile fired by an enemy plane in the game.
 * Enemy projectiles move horizontally across the screen and are destructible.
 */
public class EnemyProjectile extends Projectile {

	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs an EnemyProjectile object with the given initial position.
	 *
	 * @param initialXPos the initial X-coordinate of the projectile.
	 * @param initialYPos the initial Y-coordinate of the projectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the enemy projectile by moving it horizontally.
	 * This method simulates the projectile's movement across the screen.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the enemy projectile.
	 * This method combines position updates and any other relevant logic.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
