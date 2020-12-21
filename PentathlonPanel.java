import java.awt.BorderLayout; 
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton; //
import javax.swing.JComboBox; //
import javax.swing.JLabel; //
import javax.swing.JPanel;
import javax.swing.JTextField; //


public class PentathlonPanel extends JPanel implements ActionListener {
	// instance variables
	private JLabel athlete;
	private JLabel instructions;
	private JLabel name;
	private JLabel muscle;
	private JLabel endurance;
	private JLabel speed;
	private JLabel result;
	
	private JComboBox eventChoice;
	private JComboBox angleChoice; // for long jump and shot put
	private JLabel event;
	private JLabel angle;
	private Double[] anglesNumber = {30.0, 35.0, 40.0, 45.0, 50.0};
	// the combo box has a different list in the PentathlonPanel constructor
	// this list is of Doubles for the calculations, since I couldn't extract the number from the combobox list
	private JButton competeButton;
	
	protected JTextField nameField; // protected so athlete class can access these
	protected JTextField muscleField;
	protected JTextField enduranceField;
	protected JTextField speedField;
	
	private JLabel resultPlace;
	
	protected Athlete player;
	private String athleteName; // these are all to store the entered values,  
	private double muscleValue; // so an athlete can use them when competing
	private double enduranceValue;
	private double speedValue;
	private double angleValue;
	
	// constructor 
	public PentathlonPanel() {
		// set the panel to be 600x600 pixels
		super.setPreferredSize(new Dimension(800, 600));
		
		// for the event combo box, there are 5 choices
		String[] choices = {"100m Hurdles", "400m", "3k Steeplechase", "Shot Put", "Long Jump"};
		// create the combobox for these choices
		this.eventChoice = new JComboBox(choices);
		// make this panel respond to the comboBox action events
		this.eventChoice.addActionListener(this);
		
		// for the angle combo box, there are 5 choices
		String[] angles = {"30 Degrees", "35 Degrees", "40 Degrees", "45 Degrees", "50 Degrees"};
		this.angleChoice = new JComboBox(angles);
		this.angleChoice.addActionListener(this);
		
		// create the compete button
		this.competeButton = new JButton("Compete!");
		// this responds to the action listener
		this.competeButton.addActionListener(this);
		
		// create all the labels and corresponding text fields 
		// for the body of the panel
			this.athlete = new JLabel("Create Your Athlete");
			this.instructions = new JLabel("*Attributes cannot add to be greater than 20*");
		
			this.name = new JLabel("Name");
			this.nameField = new JTextField(); // the user will enter their name
			
			this.muscle = new JLabel("Muscle % (max:10)");
			this.muscleField = new JTextField(); // the user will enter their muscle strength
			
			this.endurance = new JLabel("Endurance (max: 10)");
			this.enduranceField = new JTextField(); // the user will enter the value
			
			this.speed = new JLabel("Maximum Speed (max: 10)");
			this.speedField = new JTextField(); // the user will enter their value
			
			this.event = new JLabel("Choose Your Event:"); // in the center panel, this will go above the event combobox
			this.angle = new JLabel("If you chose Shot Put or Long Jump, choose your Launch Angle:"); // above the angle combobox
			
			this.result = new JLabel("Results");
			this.resultPlace = new JLabel("---");
		
		// set BorderLayout for this panel
		super.setLayout(new BorderLayout());
		
			// add the athlete and instructions to the PAGE_START
			// using a generic panel to organize in 1 row and 1 column
			JPanel topPanel = new JPanel();
			topPanel.setLayout(new GridLayout(1, 1));
			topPanel.add(this.athlete); topPanel.add(this.instructions);
			super.add(topPanel, BorderLayout.PAGE_START);
			
			// for the center section, use a generic panel
			// with 7 rows, 2 columns
			// for the name, muscle, endurance, speed, eventchoice, and result
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new GridLayout(7, 2));
			centerPanel.add(this.name); centerPanel.add(this.nameField); // row 1
			centerPanel.add(this.muscle); centerPanel.add(this.muscleField); // row 2
			centerPanel.add(this.endurance); centerPanel.add(this.enduranceField); // row 3
			centerPanel.add(this.speed); centerPanel.add(this.speedField); // row 4
			centerPanel.add(this.event); centerPanel.add(this.angle); // row 5
			centerPanel.add(this.eventChoice); centerPanel.add(this.angleChoice); // row 6
			centerPanel.add(this.result); centerPanel.add(this.resultPlace); // row 7
			super.add(centerPanel, BorderLayout.CENTER);
			
			// the compete button will be at the bottom of the page
			super.add(this.competeButton, BorderLayout.PAGE_END);	
			
		}
	
	public void actionPerformed(ActionEvent event) {
		// nothing is checked until the user clicks "Compete" to let them change their mind
		if (event.getSource() == this.competeButton) {
			// all the attributes should be entered, so get the values
			// assume they were entered with the correct type
			athleteName = nameField.getText();
			muscleValue = Double.parseDouble(muscleField.getText());
			enduranceValue = Double.parseDouble(enduranceField.getText());
			speedValue = Double.parseDouble(speedField.getText());
			//a loop to find and assign the angle
			for (int n = 0; n < 5; n++) {
				if (this.angleChoice.getSelectedIndex() == n) {
					angleValue = anglesNumber[n];
					}
				}
			// check the values are each at least 0 and a maximum of 10
			// if not, the result label shows the error message
			// and a runtime exception happens to ensure the code doesn't keep running
			if (this.muscleValue > 10 || this.muscleValue < 0) {
				this.resultPlace.setText("Your muscle cannot be greater than 10 or less than 0! Restart!");
				throw new RuntimeException("Your muscle cannot be less than 0 or greater than 10!");
				}
			if (this.enduranceValue > 10 || this.enduranceValue < 0) {
				this.resultPlace.setText("Your endurance cannot be less than 0 or greater than 10! Restart!");
				throw new RuntimeException("Your endurance cannot be less than 0 or greater than 10!");
				}
			if (this.speedValue > 10 || this.speedValue < 0) {
				this.resultPlace.setText("Your speed cannot be less than 0 or greater than 10! Restart!");
				throw new RuntimeException("Your speed cannot be less than 0 or greater than 10!");
				}
			
			// check they don't add to be greater than 20
			if (this.muscleValue + this.enduranceValue + this.speedValue > 20) {
				this.resultPlace.setText("Your attributes cannot add to be greater than 20! Restart!");
				throw new RuntimeException("Your attributes cannot add to be greater than 20!");
				}
			
			// check they don't add to be zero, for at least one attribute needs a value
			if (this.muscleValue + this.enduranceValue + this.speedValue == 0) {
				this.resultPlace.setText("Your attributes total cannot be 0! Restart!");
				throw new RuntimeException("Your attributes total cannot be 0!");
				}
			
			// create the player with the now approved values
			player = new Athlete(this.athleteName, this.muscleValue, this.enduranceValue, this.speedValue, this.angleValue);
			
			// using the comboBox, figure out the event selected
			// have the athlete "do" that event
				// the angle choice only matters for long jump and shot put
			if (this.eventChoice.getSelectedIndex() == 0) {
				// this is the hurdles event
				double time = player.hurdles();
				int place = ((int)time - 16) + 1; // 16s is winning time
				// put the results in the resultPlace label
				this.resultPlace.setText(player.getName() + " came in " + place + " place with a time of " + time + " seconds!");
				}
			
			if (this.eventChoice.getSelectedIndex() == 1) {
				// this is the 400m hurdles event
				double time = player.fourHundred();
				int place = ((int)time - 60) + 1; // 60s is winning time
				// put the results in the resultPlace label
				this.resultPlace.setText(player.getName() + " came in " + place + " place with a time of " + time + " seconds!");
				}
			
			if (this.eventChoice.getSelectedIndex() == 2) {
				// this is the steeple chase event
				double time = player.steeple();
				int place = ((int)time) + 1; // 11 minutes, 0 seconds is winning time
				// put the results in the resultPlace label
				this.resultPlace.setText(player.getName() + " came in " + place + " place with a time of 11 minutes, " + time + " seconds!");
				}
			
			if (this.eventChoice.getSelectedIndex() == 3) {
				// this is the shot put event
				double time = player.shotPut();
				int place = (int)((13 - time)*4) + 1; // 13m is winning distance
				// check to see if they tripped
				if (time == 0) {
					this.resultPlace.setText("Oh no! You tripped and threw a distance of 0 meters!");
					}
				else { // if they didn't trip
					// put the results in the resultPlace label
					this.resultPlace.setText(player.getName() + " came in " + place + " place with a distance of " + time + " meters!");
					}
				}
			
			if (this.eventChoice.getSelectedIndex() == 4) {
				// this is the long jump event
				double time = player.longJump();
				int place = (int)(50 - (time*10)) + 1; // 5m is winning distance
				// if they tripped
				if (time == 0) {
					this.resultPlace.setText("Oh no! You fouled and jumped a distance of 0 meters!");
					}
				else { // if they didn't trip
					// put the results in the resultPlace label
					this.resultPlace.setText(player.getName() + " came in " + place + " place with a distance of " + time + " meters!");
					}
				}
		}
	}
	
}	
