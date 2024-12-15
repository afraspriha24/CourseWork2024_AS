package main.java.com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents the heart display UI component that visually tracks the player's remaining health.
 * Hearts are displayed as images in a horizontal layout and can be removed dynamically.
 */
public class HeartDisplay {

	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";
	private static final int HEART_HEIGHT = 50;
	private static final int INDEX_OF_FIRST_ITEM = 0;

	private HBox container;
	private double containerXPosition;
	private double containerYPosition;
	private int numberOfHeartsToDisplay;

	/**
	 * Constructs a {@code HeartDisplay} object at the specified position with the given number of hearts.
	 *
	 * @param xPosition        the X-coordinate of the heart display container.
	 * @param yPosition        the Y-coordinate of the heart display container.
	 * @param heartsToDisplay  the initial number of hearts to display.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the container for the heart display.
	 * The container is an {@link HBox} positioned at the specified coordinates.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Populates the container with heart images.
	 * Each heart is represented by an {@link ImageView} and styled with a fixed height.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));
			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Removes one heart from the display, starting from the leftmost heart.
	 * If no hearts remain, this method does nothing.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty()) {
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
		}
	}

	/**
	 * Retrieves the container holding the heart images.
	 *
	 * @return the {@link HBox} containing the heart images.
	 */
	public HBox getContainer() {
		return container;
	}
}
