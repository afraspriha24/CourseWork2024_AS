package main.java.com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The ActiveActor class serves as an abstract base class for all game actors that
 * have graphical representations and can update their positions dynamically.
 * It extends the JavaFX ImageView to manage the visual representation of the actor.
 */
public abstract class ActiveActor extends ImageView {

	private static final String IMAGE_LOCATION = "/com/example/demo/images/"; // Path to the images folder

	/**
	 * Constructs an ActiveActor instance with a specific image, size, and initial position.
	 *
	 * @param imageName    The name of the image file to use for the actor.
	 * @param imageHeight  The height of the image. The width is adjusted to maintain the aspect ratio.
	 * @param initialXPos  The initial x-coordinate of the actor.
	 * @param initialYPos  The initial y-coordinate of the actor.
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true); // Ensures the image maintains its aspect ratio
	}

	/**
	 * Updates the position of the actor. This method must be implemented by subclasses
	 * to define how the actor's position changes over time.
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by a specified amount.
	 *
	 * @param horizontalMove The distance to move the actor along the x-axis.
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by a specified amount.
	 *
	 * @param verticalMove The distance to move the actor along the y-axis.
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}
