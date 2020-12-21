
public class Athlete {
	// instance variables are entered attributes from the panel
	private String name;
	private double muscle;
	private double endurance;
	private double speed;
	private double angle;
	
	private Die tripDie;
	private double result;
	
	// constructor
	public Athlete(String name, double muscle, double endurance, double speed, double angle) {
		this.name = name;
		this.muscle = muscle;
		this.endurance = endurance;
		this.speed = speed;
		this.angle = angle;
		
		// create trip die
		// number of sides varies per event
		this.tripDie = new Die();
		
		// they start with a result of zero
		this.result = 0.0;
		}
	
	// instance methods 
	
	// get the name of the athlete
	public String getName() {
		return this.name;
		}
	
	// these are the different events
	public double hurdles() {
		// they have a 1/35 chance of tripping
		tripDie = new Die(5); tripDie.roll(); 
		// if a 35 is rolled, they tripped and add 4 seconds
		if (tripDie.getFaceValue() == 35) {
			result = result + 4;
			}
		// the best time possible is 16 seconds for this event
		// to get this, you want speed=10, endurance=3, muscle=7
		// for every number the entered values are off these "optimal values" add 1 second
		result = result + 16 + (10 - this.speed) + Math.abs(3 - this.endurance) + Math.abs(7 - this.muscle);
		return result;
		// in the PentathlonPanel, place will be calculated as 16s = 1st, 17s = 2nd, 18s=3rd, etc.
	}
	
	public double fourHundred() {
		// they have a 1/45 chance of tripping
		tripDie = new Die(45); tripDie.roll(); 
		// if a 45 is rolled, they tripped and add 2 seconds
		if (tripDie.getFaceValue() == 45) {
			result = result + 2;
			}
		// the best time possible is 60 seconds for this event
		// to get this, you want speed=8, endurance=6, muscle=6
		// for every number the entered values are off these "optimal values" add 1 second
		result = result + 60 + Math.abs(8 - this.speed) + Math.abs(6 - this.endurance) + Math.abs(6 - this.muscle);
		return result;
		// in the PentathlonPanel, every place increases by a second
	}
	
	public double steeple() {
		// they have a 1/30 chance of tripping
		tripDie = new Die(30); tripDie.roll(); 
		// if a 30 is rolled, they tripped and add 4 seconds
		if (tripDie.getFaceValue() == 30) {
			result = result + 4;
			}
		// the best time possible is 11 minutes for this event
		// to get this, you want speed=6, endurance=10, muscle=4
		// for every number the entered values are off these "optimal values" add 1 second
		result = result + Math.abs(6 - this.speed) + Math.abs(10 - this.endurance) + Math.abs(4 - this.muscle);
		return result;
		// in the PentathlonPanel, 11 minutes is added, and every place increases by a second
	}
	
	public double shotPut() {
		// the best distance possible is 13 meters for this event
		// to get this, you want speed=8, endurance=2, muscle=10
		// for every number the entered values are off these "optimal values" subtract 0.25 meters
		result = 13 - (Math.abs(8 - this.speed)*0.25) - (Math.abs(2 - this.endurance)*0.25) - (Math.abs(10 - this.muscle)*0.25);
		// in the PentathlonPanel, every place increases by 0.25 meters

		// to factor in the launch angle, 45 is the best choice
		// every time they're off this by 5, subtract 0.25 more meters
		result = result - (((Math.abs(45 - angle)) / 5) *0.25);
		
		// they have a 1/40 chance of tripping
		tripDie = new Die(40); tripDie.roll(); 
		// if a 40 is rolled, they tripped and get a distance of zero
		// no matter the entered attributes
		if (tripDie.getFaceValue() == 40) {
			result = 0;
			}
		result = (result * 100); result = Math.round(result); result = (result / 100); // to get rid of all the extra decimals
		return result;
	}
	
	public double longJump() {
		// the best distance possible is 5 meters for this event
		// to get this, you want speed=10, endurance=4, muscle=6
		// for every number the entered values are off these "optimal values" subtract 0.10 meters
		result = 5 - (Math.abs(10 - this.speed)*0.10) - (Math.abs(4 - this.endurance)*0.10) - (Math.abs(6 - this.muscle)*0.10);
		// in the PentathlonPanel, every place increases by 0.10 meters
		
		// to factor in the launch angle, 50 is the best choice
		// every time they're off this by 5, subtract 0.10 more meters
		result = result - (((Math.abs(50 - angle)) / 5) *0.10);
		
		// they have a 1/35 chance of fouling over the line
		tripDie = new Die(35); tripDie.roll(); 
		// if a 35 is rolled, they fouled and get a distance of zero
		// no matter the entered attributes
		if (tripDie.getFaceValue() == 35) {
			result = 0;
			}
		result = (result * 100); result = Math.round(result); result = (result / 100); // to get rid of all the extra decimals
		return result;
	}
}
