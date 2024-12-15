package main.java.com.example.demo;

/**
 * Represents a projectile in the game, which can be fired by various actors.
 * This is an abstract class that extends {@link ActiveActorDestructible}.
 * It provides common behavior for projectiles, such as being destroyed upon impact.
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructs a new `Projectile` object with the specified parameters.
	 *
	 * @param imageName     the name of the image file representing the projectile.
	 * @param imageHeight   the height of the projectile's image.
	 * @param initialXPos   the initial X position of the projectile.
	 * @param initialYPos   the initial Y position of the projectile.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Handles the logic for when the projectile takes damage, destroying it.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Updates the position of the projectile.
	 * This method must be implemented by concrete subclasses.
	 */
	@Override
	public abstract void updatePosition();
}
