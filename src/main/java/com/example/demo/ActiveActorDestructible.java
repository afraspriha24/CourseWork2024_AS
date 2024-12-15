package main.java.com.example.demo;

/**
 * The ActiveActorDestructible class is an abstract extension of ActiveActor that
 * implements the Destructible interface. It represents a game actor that can
 * take damage and be destroyed.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed; // Indicates whether the actor has been destroyed

	/**
	 * Constructs an ActiveActorDestructible instance with a specific image, size, and initial position.
	 *
	 * @param imageName    The name of the image file to use for the actor.
	 * @param imageHeight  The height of the image. The width is adjusted to maintain the aspect ratio.
	 * @param initialXPos  The initial x-coordinate of the actor.
	 * @param initialYPos  The initial y-coordinate of the actor.
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Updates the position of the actor. This method must be implemented by subclasses
	 * to define how the actor's position changes over time.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the actor's state, such as checking collisions or modifying properties.
	 * This method must be implemented by subclasses.
	 */
	public abstract void updateActor();

	/**
	 * Defines how the actor takes damage. The specific behavior must be implemented by subclasses.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Marks the actor as destroyed and updates the destroyed status.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Sets the destroyed status of the actor.
	 *
	 * @param isDestroyed A boolean indicating whether the actor is destroyed.
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Checks if the actor is destroyed.
	 *
	 * @return true if the actor is destroyed, false otherwise.
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}
