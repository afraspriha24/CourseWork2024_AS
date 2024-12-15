package main.java.com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Represents the visual shield image for the boss in the game.
 * The shield can be shown or hidden dynamically during gameplay.
 */
public class ShieldImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";
	private static final int SHIELD_SIZE = 200;

	/**
	 * Constructs a new `ShieldImage` object and initializes its position and visual properties.
	 *
	 * @param xPosition the x-coordinate for the shield's position.
	 * @param yPosition the y-coordinate for the shield's position.
	 * @throws IllegalArgumentException if the shield image resource cannot be found.
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		try {
			this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("Image resource not found: " + IMAGE_NAME, e);
		}
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	/**
	 * Makes the shield visible on the screen.
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hides the shield from the screen.
	 */
	public void hideShield() {
		this.setVisible(false);
	}
}
