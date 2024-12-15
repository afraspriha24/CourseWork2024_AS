package main.java.com.example.demo;

/**
 * An interface that represents destructible entities in the game.
 * Classes implementing this interface must define behaviors for taking damage and destruction.
 */
public interface Destructible {

	/**
	 * Applies damage to the object.
	 * This method should define how the object handles damage, such as reducing health.
	 */
	void takeDamage();

	/**
	 * Destroys the object.
	 * This method should define how the object handles its destruction, such as removing itself from the game.
	 */
	void destroy();
}
