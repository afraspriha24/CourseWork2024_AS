package main.java.com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the "Game Over" image displayed on the screen when the player loses the game.
 * This class extends {@link ImageView} and sets up the "Game Over" image with specified layout coordinates.
 */
public class GameOverImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	/**
	 * Constructs a {@code GameOverImage} object and positions it on the screen.
	 *
	 * @param xPosition the X-coordinate where the image will be placed.
	 * @param yPosition the Y-coordinate where the image will be placed.
	 */
	public GameOverImage(double xPosition, double yPosition) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}
}
