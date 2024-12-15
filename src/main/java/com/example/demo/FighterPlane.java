package main.java.com.example.demo;

/**
 * Represents a fighter plane in the game, serving as an abstract base class for user and enemy planes.
 * Fighter planes are destructible and can fire projectiles.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	private int health;

	/**
	 * Constructs a FighterPlane object with the specified properties.
	 *
	 * @param imageName    the name of the image representing the plane.
	 * @param imageHeight  the height of the image.
	 * @param initialXPos  the initial X-coordinate of the plane.
	 * @param initialYPos  the initial Y-coordinate of the plane.
	 * @param health       the initial health of the plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile from the fighter plane.
	 * Subclasses must implement this method to specify the type of projectile fired.
	 *
	 * @return the projectile fired by the fighter plane.
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Reduces the plane's health by 1 and destroys the plane if its health reaches zero.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Calculates the X-coordinate for firing a projectile, applying an offset.
	 *
	 * @param xPositionOffset the horizontal offset for the projectile's position.
	 * @return the calculated X-coordinate for the projectile.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the Y-coordinate for firing a projectile, applying an offset.
	 *
	 * @param yPositionOffset the vertical offset for the projectile's position.
	 * @return the calculated Y-coordinate for the projectile.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the plane's health has reached zero.
	 *
	 * @return {@code true} if the plane's health is zero; {@code false} otherwise.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Retrieves the current health of the fighter plane.
	 *
	 * @return the current health value.
	 */
	public int getHealth() {
		return health;
	}
}
