// Anna Foster
// I received some help from Professor Lee 

import javax.swing.JFrame;

public class PentathlonApp {

	public static void main(String[] args) {
		// create our new frame
		JFrame pentathlonFrame = new JFrame("Welcome to the Virtual Pentathlon!");
		
		// when the window is closed, terminate the java app
		pentathlonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// add a new panel object to the animation frame
		pentathlonFrame.getContentPane().add(new PentathlonPanel());
		
		// pack the frame
		pentathlonFrame.pack();
		
		// make the frame visible
		pentathlonFrame.setVisible(true);
		
	}

}
