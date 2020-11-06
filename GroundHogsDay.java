// Import the GUI libraries
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Scanner;
import javax.swing.Timer;
import java.awt.*;
import java.util.Random;

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
	static JFrame frame;
	static final int TIMER_LIMIT = 30;
	static int seconds = 0;
	static Timer timer;
	static JLabel mapImage;
	static JLabel goFur;
	static Random randomGenerator = new Random();
	// CREATE MAIN WINDOW
	// This method is called by the main method to set up the main GUI window.

	private static void createMainWindow () {
		// Create and set up the window.
		frame = new JFrame ("Ground Hogs Day");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setResizable (false);

		// The panel that will hold the components in the frame.
		JLayeredPane contentPane = new JLayeredPane ();
		contentPane.setPreferredSize(new Dimension (700, 394));

		// timer
		timer = new Timer(1000, new TimeOutListener());
		timer.start();

		//add the map.
		JLabel goFur = new JLabel(new ImageIcon("groundhog.png"));
		JLabel mapImage = new JLabel(new ImageIcon("grassbackground.png"));
		
		mapImage.setSize(700, 394);
		contentPane.add(mapImage);
		goFur.setSize(new Dimension (500, 600));
		
		contentPane.add(goFur);
		contentPane.setLayer(goFur, 10);

		
		goFur.setVisible(true);


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




		;




		//Add components to the content pane panel


		// Add the panel to the frame
		frame.setContentPane(contentPane);

		//size the window.
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}



	/**
	 * HELPER METHODS
	 * Methods that you create to manage repetitive tasks.
	 */



	/**
	 * EVENT LISTENERS
	 * Subclasses that handle events (button clicks, mouse clicks and moves,
	 * key presses, timer expirations)
	 */
	private static class TimeOutListener implements ActionListener {

		JFrame loser = new JFrame ();
		//a loser frame if the time limit runs out
		public void actionPerformed(ActionEvent evt) {
			if (seconds == 10) {
				loser = new JFrame ();
				loser.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
				loser.setResizable (false);
				loser.setLocationRelativeTo(null);
				loser.setVisible(true);
				frame.setVisible(false);
				
				JPanel gameOverPane = new JPanel();
				gameOverPane.setLayout(new BorderLayout(5, 10));
				gameOverPane.setPreferredSize(new Dimension (700, 394));

		
				//buttons and labels 
				JButton GameOverQuitButton = new JButton("Quit");
				JLabel gameOver = new JLabel("Game Over");
				
				gameOver.setFont(new Font("Comic Sans MS", Font.ITALIC | Font.BOLD, 25));
				gameOverPane.add(GameOverQuitButton , BorderLayout.CENTER);
				GameOverQuitButton.setVisible(true);
				
				
				gameOverPane.add(gameOver);
				GameOverQuitButton.setVisible(true);
				
				loser.setSize(new Dimension (400 , 200));
				loser.add(GameOverQuitButton);
				timer.stop();
				
				loser.setContentPane(gameOverPane);

				//size the window.
				loser.pack();
				loser.setLocationRelativeTo(null);
				loser.setVisible(true);
			}
			else { 
				seconds++;
			}
		}
		private static void changeGofurLocation () {
			int goFurX = randomGenerator.nextInt(700);
			int goFurY = randomGenerator.nextInt(394);
			goFur.setLocation(goFurX, goFurY);
			goFur.setVisible(true);
		}
	}
}




