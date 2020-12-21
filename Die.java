import java.util.Random;

public class Die {
	// instance variables
	public int sides; // number of sides, default = 6
	public int faceValue; // the face value (what is rolled), default = random 1 thru 6
	public Random randomizer; // the random object for random value generation
	
	// constructors
	public Die(int numberOfSides) {
		// set the number of sides
		this.sides = numberOfSides;
		// create a default random object for the attribute randomizer
		this.randomizer = new Random();
		// random value for the face value
		this.roll();
		}
	
	// default constructor of a 6-sided die
	public Die() {
		this(6);
		}
	
	// instance methods
	
	// roll instance method
	public void roll() {
		// generate a random value based on the number of sides
		this.faceValue = this.randomizer.nextInt(this.sides) + 1;
		}
	
	// getFaceValue instance method
	public int getFaceValue() {
		// return the value of the instance variable faceValue
		return this.faceValue;
		}
}
