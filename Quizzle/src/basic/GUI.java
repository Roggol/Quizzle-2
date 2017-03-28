package basic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends JFrame implements ActionListener {

	GUImanager g = new GUImanager();

	JPanel panel; //Panel for the frame
	JFrame frame; //Frame of the GUI
	
	JButton greenButton; // Buttons and Labels for the quiz
	JButton redButton;
	JButton yellowButton;
	JButton blueButton;
	JLabel quizTitle;
	JLabel quizQuestion;
	JLabel Quizzle;
	JLabel scoreTrack;
	int qNumber = 1; // The intial question number
	JLabel lastAnswer; 
	JButton lastAnswerButton;
	char answer; // the answer taken from the text file as a char
	char buttonPressed; // the answer taken from the button press as a char
	int score = 0; //default score


	public GUI(final String username, final String quizName, final int noOfQs) {
		
		prepareGUI(); //sets up GUI
		
		//intitialise buttons and Labels
		redButton = new JButton("");
		yellowButton = new JButton("");
		greenButton = new JButton("");
		blueButton = new JButton("");
		quizQuestion = new JLabel("");
		quizTitle = new JLabel("");
		scoreTrack = new JLabel("Score: " + score);
		lastAnswer = new JLabel("Last answer:");
		lastAnswerButton = new JButton("");

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				//checks which button was pressed
				if (event.getSource() == redButton) {
					buttonPressed = 'A';
				}
				if (event.getSource() == greenButton) {
					buttonPressed = 'C';
				}
				if (event.getSource() == yellowButton) {
					buttonPressed = 'B';
				}
				if (event.getSource() == blueButton) {
					buttonPressed = 'D';
				}
				CheckAnswer(username, quizName, noOfQs); //Runs check answer
				

			}

		};
		
		//button and Label setup
		redButton.addActionListener(listener);
		greenButton.addActionListener(listener);
		yellowButton.addActionListener(listener);
		blueButton.addActionListener(listener);

		redButton.setFocusable(false);
		yellowButton.setFocusable(false);
		greenButton.setFocusable(false);
		blueButton.setFocusable(false);
		lastAnswerButton.setFocusable(false);

		greenButton.setBackground(new Color(75, 255, 25));
		blueButton.setBackground(Color.CYAN);
		redButton.setBackground(new Color(210, 0, 25));
		yellowButton.setBackground(Color.YELLOW);
		lastAnswerButton.setBackground(Color.GRAY);

		redButton.setSize(400, 200);
		blueButton.setSize(400, 200);
		greenButton.setSize(400, 200);
		yellowButton.setSize(400, 200);
		quizQuestion.setSize(1500, 50);
		quizTitle.setSize(800, 100);
		scoreTrack.setSize(200, 200);
		lastAnswer.setSize(400, 100);
		lastAnswerButton.setSize(100, 100);

		redButton.setLocation(100, 400);
		yellowButton.setLocation(700, 400);
		greenButton.setLocation(100, 700);
		blueButton.setLocation(700, 700);
		quizQuestion.setLocation(150, 250);
		quizTitle.setLocation(300, 90);
		scoreTrack.setLocation(1700, 200);
		lastAnswer.setLocation(1700, 500);
		lastAnswerButton.setLocation(1700, 620);

		panel.setLayout(null);//sets layout to null

		g.Font(quizTitle, java.awt.Font.PLAIN); // maximises the text size
		g.Font(quizQuestion, java.awt.Font.PLAIN); // maximises the text size
		
		//add buttons and labels
		panel.add(redButton);
		panel.add(yellowButton);
		panel.add(blueButton);
		panel.add(greenButton);
		panel.add(quizTitle);
		panel.add(quizQuestion);
		panel.add(scoreTrack);
		panel.add(lastAnswer);
		panel.add(lastAnswerButton);

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO
					.read(new File(
							"Quizzle Logo.png")); //locates image

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture)); //creates Label for image
		picLabel.setLocation(700, 10);
		picLabel.setSize(500, 200);
		panel.add(picLabel); //adds image


		frame.add(panel);//adds panel to frame
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//maximises frame size

		frame.setVisible(true);

		Prep(quizName);//Fills in buttons and Labels based on Question Number

	}

	private void CheckAnswer(String username, String quizName, int noOfQuestions) {
		boolean end = false;//boolean to check if the quiz has ended
		try {
			String[] configString; //initialises array to be used
			ReadFile file = new ReadFile(
					quizName + "\\questions.txt");//attempts to read file in the directory of the quiz name with the file called questions
			String[] arrayLines = file.OpenFile();//Grabs the data from file and puts it into the array
			configString = arrayLines[qNumber].split(",");//splits the string at the position of the question number from the file into parts separated by a comma
			String AnswerString = configString[5];//Grabs the string at the 5th position in the new array
			answer = AnswerString.charAt(0); //turns the string into a char to be compared
			
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		if (buttonPressed == answer) {//checks if the char from button pressed and from the file match
			score++;//if they do increment score
			scoreTrack.setText("Score: " + score);//update the score Label
			lastAnswerButton.setBackground(Color.GREEN);
			lastAnswerButton.setText(" :)");//update the Button to show whether they got the previous answer right

			if (qNumber == noOfQuestions ) {//checks to see if the quiz has ended
					logScore(username,quizName,score);//logs the score if the quiz is over
					JOptionPane.showMessageDialog(null, "You scored: " + score + " out of " + noOfQuestions );//tells them their score
					end = true;//quiz has ended
					new QuizChoose(username);//goes back to the frame where you choose the quiz
					frame.setVisible(false);
					frame.dispose();//removes frame
					
				
			}
		} else {
			lastAnswerButton.setBackground(Color.RED);
			lastAnswerButton.setText(" :(");
			if (qNumber == noOfQuestions ) {//checks to see if the quiz has ended
				logScore(username,quizName,score);//logs the score if the quiz is over
				JOptionPane.showMessageDialog(null, "You scored: " + score + " out of " + noOfQuestions );//tells them their score
				end = true;//quiz has ended
				new QuizChoose(username);//goes back to the frame where you choose the quiz
				frame.setVisible(false);
				frame.dispose();//removes frame
			}
		}
		qNumber++;//increments question number
		if (end == false){
			Prep(quizName);	//Sets up boxes again if the quiz has not ended
		}

	}

	private void logScore(String username, String quizName,int score) { //takes inputs of username, quizname and score
		boolean found = false;//boolean to check if the username has been found to make sure the same user does not have multiple scores
		int highScoreFromFile = 0;//int to check if the username is found if their new highscore exceeds their old one
		try {
			ReadFile file = new ReadFile(
					quizName + "\\scores.txt");//reads file
			String[] arrayLines = file.OpenFile();//array of lines from file

			String Highscore = "";//Initialises highscore string
			String[] configstring = new String[2];// new string array of length 2


			WriteFile writer = new WriteFile(quizName + "\\scores.txt",false);//writer that deletes all previous things inside file
			WriteFile writer1 = new WriteFile(quizName + "\\scores.txt",true);//writer that appends the file
			writer.writeToFile("Username,Password");//set file title for comma separated format
			for (int i = 0; i < arrayLines.length; i++) {// for loop for amount of items in array
				if (arrayLines[i].contains(username)) {//checks to see if the username is inside the array
					found = true;//sername found
					configstring = arrayLines[i].split(",");//splits username and score

					Highscore = configstring[1];//finds score
					highScoreFromFile = Integer.valueOf(Highscore);//turns the score string into int
					if (highScoreFromFile >= score) {
						writer1.writeToFile(arrayLines[i]);//if the score from the file is greater than or equal the current score, just re-write the array line

					} else {					
						writer1.writeToFile(username + "," + score); //current score is greater therefore the old highscore is no longer a high score
					}
				} else if (i == 0) {
					// do nothing because this is the title line
				} else {
					writer1.writeToFile(arrayLines[i]);
					//if username is not found just write the array line
				}

			}

			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void Prep(String quizName) {
			
			try {
				String[] configstring;
				ReadFile file = new ReadFile(
						quizName + "\\questions.txt");//finds file
				String[] arrayLines = file.OpenFile();//reads lines from file
				configstring = arrayLines[qNumber].split(",");//splits lines by comma
				
				String qTitle = configstring[0];			
				quizQuestion.setText(qTitle);
				quizTitle.setText(quizName);
				redButton.setText("A: " + (configstring[1]));
				yellowButton.setText("B: " + (configstring[2]));
				greenButton.setText("C: " + (configstring[3]));
				blueButton.setText("D: " + (configstring[4]));
				//sets up Labels and buttons based on data from file
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			frame.repaint();// refreshes frame

	}

	private void prepareGUI() {//sets up GUI
		frame = new JFrame("Quizzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.add(panel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
