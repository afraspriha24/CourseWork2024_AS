package main.java.com.example.demo;

import javafx.scene.Group;

/**
 * Represents the UI components and specific features for Level Two in the game.
 * Extends the {@link LevelView} class to add functionalities unique to Level Two,
 * such as managing the boss's shield visibility.
 */
public class LevelViewLevelTwo extends LevelView {

	static final int SHIELD_X_POSITION = 1150;
	static final int SHIELD_Y_POSITION = 500;

	private final Group root;
	private final ShieldImage shieldImage;

	/**
	 * Constructs a `LevelViewLevelTwo` object with the specified root and initial number of hearts.
	 *
	 * @param root            the root group for UI components.
	 * @param heartsToDisplay the initial number of hearts to display.
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);

		addImagesToRoot();
	}

	/**
	 * Adds the shield image to the root group for rendering.
	 */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}

	/**
	 * Displays the boss's shield on the UI.
	 */
	public void showShield() {
		shieldImage.showShield();
	}

	/**
	 * Hides the boss's shield from the UI.
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}
}
