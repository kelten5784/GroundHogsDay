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
	static JLabel goFur;
	static Random randomGenerator = new Random();
	static JLabel time;
	static JLabel Points;
	static int scorePoints;
	static JPanel gameOverPane = new JPanel();
	static JFrame loser = new JFrame ();
	static JLabel Time;
	static final int MAX_SECONDS = 10;

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
		goFur.setSize(new Dimension (409, 900));
		goFur.addMouseListener(new GoFurClickListener());
		contentPane.add(goFur);
		contentPane.setLayer(goFur, 10);
		goFur.setVisible(true);

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

	private static class QuitButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			int answer = JOptionPane.showConfirmDialog(null, "Are you sure your want to quit?", 
					"Quit?", JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
	//sets the listener when you click on the gofur
	private static class GoFurClickListener implements MouseListener {
		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
			int goFurX = randomGenerator.nextInt(300);
			int goFurY = randomGenerator.nextInt(394);
			goFur.setLocation(goFurX, goFurY);
			goFur.setVisible(true);

			if (scorePoints == 10) {
	
			}
			else {
				scorePoints++;
				Points.setText("Points: " + scorePoints );;
				

			}
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
				gameOverPane.setBackground(Color.YELLOW);


				//buttons and labels 
				JButton GameOverQuitButton = new JButton("Quit");
				JLabel gameOver = new JLabel("Game Over");

				gameOver.setFont(new Font("Comic Sans MS", Font.ITALIC | Font.BOLD, 25));
				
				GameOverQuitButton.setVisible(true);
				GameOverQuitButton.addActionListener(new QuitButtonListener());
				
				gameOverPane.add(GameOverQuitButton );
				gameOver.setAlignmentX(Component.CENTER_ALIGNMENT);

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
