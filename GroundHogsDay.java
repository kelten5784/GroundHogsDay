// Import the GUI libraries
import javax.swing.*;
import javax.swing.border.EmptyBorder;



import java.util.Scanner;
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
	static int seconds;
	static Timer timer;
	static JLabel mapImage;
	static JLabel woodChuck;
	static Random randomGenerator = new Random();
	static JLabel time;
	static JLabel Points;
	static int scorePoints;
	static JPanel gameOverPane = new JPanel();
	static JFrame loser = new JFrame ();
	static JLabel Time;
	static final int MAX_SECONDS = 10;
	static JLabel nextLevel;
	// CREATE MAIN WINDOW
	// This method is called by the main method to set up the main GUI window.

	private static void createMainWindow () {
		// Create and set up the window.
		frame = new JFrame ("Ground Hogs Day");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setResizable (false);
		frame.pack();


		// The panel that will hold the components in the frame.
		JLayeredPane contentPane = new JLayeredPane ();
		contentPane.setPreferredSize(new Dimension (700, 394));

		// timer
		timer = new Timer(1000, new TimeOutListener());
		timer.start();

		//woodChuck 
		woodChuck = new JLabel(new ImageIcon("3DpKaKY-unscreen.gif"));
		woodChuck.setSize(new Dimension (326, 326));
		woodChuck.setLocation(400 , 100);
		woodChuck.addMouseListener(new woodChuckClickListener());
		contentPane.add(woodChuck);
		contentPane.setLayer(woodChuck, 10);
		woodChuck.setVisible(true);

		//add the map.
		JLabel mapImage = new JLabel(new ImageIcon("grassbackground.png"));
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
		scorePoints = 0;
		Points = new JLabel("Points: " + scorePoints );
		Points.setFont(new Font("Comic Sans MS", Font.ITALIC | Font.BOLD, 16));
		sideBar.add(Points);

		seconds = MAX_SECONDS;
		Time = new JLabel("Time: " + seconds);
		Time.setFont(new Font("Comic Sans MS", Font.ITALIC | Font.BOLD, 16));
		sideBar.add(Time);

		//score prompt jlabel
		nextLevel = new JLabel("You have scored" + scorePoints + "Points!" );
		nextLevel.setFont(new Font("Comic Sans MS", Font.ITALIC | Font.BOLD, 16));
		nextLevel.setLocation(800, 800);
		contentPane.add(nextLevel);
		nextLevel.setVisible(true);
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

	private static class QuitButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			int answer = JOptionPane.showConfirmDialog(null, "Are you sure your want to quit?", 
					"Quit?", JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}




	//sets the listener when you click on the woodChuck
	private static class woodChuckClickListener implements MouseListener {
		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
			int woodChuckX = randomGenerator.nextInt(500);
			int woodChuckY = randomGenerator.nextInt(294);
			woodChuck.setLocation(woodChuckX, woodChuckY);


			if (scorePoints == 9) 	{
				seconds = 10;
		
			}
			else if (scorePoints == 19 ) {
				seconds = 9;
			
			}
			else if (scorePoints == 29 ) {
				seconds = 8;
			
			}
			else if (scorePoints == 39) {
				seconds = 7;
			}
			
			else if (scorePoints == 49) {
				seconds = 6;
			}
			else if (scorePoints == 59) {
				seconds = 5;
			}
			else if (scorePoints == 69) {
				seconds = 4;
			}
			else if (scorePoints == 79) {
				seconds = 3;
			}
			else if (scorePoints == 89) {
				seconds = 2;
			}
			else if (scorePoints == 99) {
				seconds = 1;
			}


		
			scorePoints++;
			Points.setText("Points: " + scorePoints );
		}
	}


	private static class NewGameButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			int answer = JOptionPane.showConfirmDialog(null, 
					"Are you sure your want to start a new game?", "New Game?", 
					JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				frame.setVisible(true);
				loser.setVisible(false);
				gameOverPane.setVisible(false);
				scorePoints = 0;
				seconds = MAX_SECONDS ;
				timer.start();
			}
			else {
				System.exit(0);
			}


		}
	}

	private static class TimeOutListener implements ActionListener {

		JFrame loser = new JFrame ();
		//a loser frame if the time limit runs out
		public void actionPerformed(ActionEvent evt) {
			if (seconds == 0) {
				loser = new JFrame ();
				loser.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
				loser.setResizable (false);
				loser.setLocationRelativeTo(null);
				loser.setVisible(true);
				frame.setVisible(false);

				JPanel gameOverPane = new JPanel();
				gameOverPane.setLayout(new BoxLayout(gameOverPane, BoxLayout.PAGE_AXIS));
				gameOverPane.setPreferredSize(new Dimension (700, 394));
				gameOverPane.setBackground(Color.MAGENTA);


				//buttons and labels 
				JButton GameOverQuitButton = new JButton("Quit");
				JLabel gameOver = new JLabel("Game Over");
				JLabel Score = new JLabel("Wow You got " + scorePoints + " Points You Suck");
				Score.setFont(new Font("Comic Sans MS", Font.ITALIC | Font.BOLD, 25));
				Score.setAlignmentX(Component.CENTER_ALIGNMENT);
				gameOverPane.add(Score);


				gameOver.setFont(new Font("Comic Sans MS", Font.ITALIC | Font.BOLD, 25));

				GameOverQuitButton.setVisible(true);
				GameOverQuitButton.addActionListener(new QuitButtonListener());

				gameOverPane.add(GameOverQuitButton );
				gameOver.setAlignmentX(Component.CENTER_ALIGNMENT);
				GameOverQuitButton.setAlignmentX(Component.CENTER_ALIGNMENT);





				//new game button
				JButton newGameButton = new JButton("New Game");
				newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
				newGameButton.addActionListener(new NewGameButtonListener());
				gameOverPane.add(newGameButton);
				gameOverPane.add(Box.createRigidArea(new Dimension(400, 10)));


				gameOverPane.add(gameOver);
				GameOverQuitButton.setVisible(true);

				loser.setSize(new Dimension (400 , 200));
				gameOverPane.add(GameOverQuitButton);
				gameOverPane.setAlignmentX(Component.CENTER_ALIGNMENT);
				timer.stop();

				loser.setContentPane(gameOverPane);

				//size the window.
				loser.pack();
				loser.setLocationRelativeTo(null);
				loser.setVisible(true);
			}
			else { 
				seconds--;
				Time.setText("Time: " + seconds);

			}
		}
	}
}
