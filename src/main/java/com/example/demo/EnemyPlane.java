package main.java.com.example.demo;

/**
 * Represents an enemy plane in the game.
 * Enemy planes move horizontally across the screen and can fire projectiles.
 * They are destructible entities with a specific health value.
 */
public class EnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane.png";
	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01;

	/**
	 * Constructs an EnemyPlane object with the given initial position.
	 *
	 * @param initialXPos the initial X-coordinate of the enemy plane.
	 * @param initialYPos the initial Y-coordinate of the enemy plane.
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally.
	 * This method simulates the movement of the enemy plane across the screen.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile from the enemy plane, based on a probabilistic fire rate.
	 *
	 * @return an instance of {@link EnemyProjectile} if the fire rate condition is met; otherwise, null.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPostion);
		}
		return null;
	}

	/**
	 * Updates the enemy plane's state, including position and any other behaviors.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
