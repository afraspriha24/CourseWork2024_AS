package main.java.com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the "You Win" image displayed when the player wins the game.
 * This image is initially hidden and can be shown upon victory.
 */
public class WinImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;

	/**
	 * Constructs a `WinImage` at the specified position.
	 *
	 * @param xPosition the x-coordinate where the image will be displayed.
	 * @param yPosition the y-coordinate where the image will be displayed.
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}

	/**
	 * Makes the "You Win" image visible on the screen.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}
}
