// Import the GUI libraries
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Scanner;
import javax.swing.Timer;
import java.awt.*;

import java.awt.event.*;
public class GroundHogsDay {

	/**
	 * MAIN METHOD
	 * This main method starts the GUI and runs the createMainWindow() method.
	 * This method should not be changed.
	 */
	public static void main (String [] args) {
		javax.swing.SwingUtilities.invokeLater (new Runnable () {
			public void run () {
				createMainWindow ();
			}
		});
	}


	/**
	 * STATIC VARIABLES AND CONSTANTS
	 * Declare the objects and variables that you want to access across
	 * multiple methods.
	 */



	// CREATE MAIN WINDOW
	// This method is called by the main method to set up the main GUI window.

	static JLabel mapImage = new JLabel(new ImageIcon("grassbackground.png"));

	private static void createMainWindow () {
		// Create and set up the window.
		JFrame frame = new JFrame ("Ground Hogs Day");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setResizable (false);

		// The panel that will hold the components in the frame.
		JLayeredPane contentPane = new JLayeredPane ();
		contentPane.setPreferredSize(new Dimension (700, 394));

		//add the map.
		mapImage.setSize(700, 394);
		contentPane.add(mapImage);



		// right pane 
		JPanel sideBar = new JPanel();
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.PAGE_AXIS));
		sideBar.setSize(new Dimension(175, 80));
		sideBar.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.add(sideBar);
		contentPane.setLayer(sideBar, 100);
		sideBar.setLocation(525, 0);

		//Components to right panel
		int scorePoints = 0;
		JLabel Points = new JLabel("Points " + scorePoints );
		Points.setFont(new Font("Comic Sans MS", Font.ITALIC | Font.BOLD, 16));
		sideBar.add(Points);

		//timer
		int delay = 30000; //milliseconds
		ActionListener taskPerformer = new ActionListener() {

			JFrame loser = new JFrame ();

			//a loser frame if the time limit runs out
			public void actionPerformed(ActionEvent evt) {
				if (delay == 30000) {  
					loser = new JFrame ();
					loser.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
					loser.setResizable (false);
					loser.setLocationRelativeTo(null);
					loser.setVisible(true);
					frame.setVisible(false);
					//buttons and labels 
					JLabel gameOver = new JLabel("Game Over");
					gameOver.setFont(new Font("Comic Sans MS", Font.ITALIC | Font.BOLD, 25));
					loser.add(gameOver);
					loser.setPreferredSize(new Dimension (400 , 200));
				}
			}
		};

		new Timer(delay, taskPerformer).start();

		//JLabel timer = new JLabel(delay);




		//Add components to the content pane panel


		// Add the panel to the frame
		frame.setContentPane(contentPane);

		//size the window.
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);




		/**
		 * HELPER METHODS
		 * Methods that you create to manage repetitive tasks.
		 */



		/**
		 * EVENT LISTENERS
		 * Subclasses that handle events (button clicks, mouse clicks and moves,
		 * key presses, timer expirations)
		 */

	}
}

