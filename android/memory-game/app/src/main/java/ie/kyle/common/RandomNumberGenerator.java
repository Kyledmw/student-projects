package ie.kyle.common;
import java.util.Random;

/**
 ********************************************************************
 *
 * A wrapper class around a {@link Random} object.
 * It allows a user to generate a number between
 * & including the given min and max values
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class RandomNumberGenerator {
	
	private int _min;
	private int _max;
	private Random _rand;

	public RandomNumberGenerator(int min, int max) {
		_rand = new Random();
		_min = min;
		_max = max;
	}

	/**
	 * Generate a number between the given min and max values
	 *
	 * @return randomly generated integer
	 */
	public int generate() {
		return _rand.nextInt(_max - _min + 1) + _min;
	}
}
