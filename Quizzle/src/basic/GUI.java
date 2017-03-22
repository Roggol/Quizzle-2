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

	JPanel panel;
	JFrame frame;

	JButton greenButton;
	JButton redButton;
	JButton yellowButton;
	JButton blueButton;
	JLabel quizTitle;
	JLabel quizQuestion;
	JLabel Quizzle;
	JLabel scoreTrack;
	int height = 1920;
	int width = 1080;
	int qNumber = 1;
	JLabel lastAnswer;
	JButton lastAnswerButton;
	char answer;
	char buttonPressed;
	int score = 0;
	boolean setup = true;

	public GUI(final String username, final String quizName, final int noOfQs) {
		// g.prepareGUI(frame, panel, height, width);
		// prepareGUI(frame, panel, height, width);
		prepareGUI();

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
				CheckAnswer(username, quizName, noOfQs);

			}

		};

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

		panel.setLayout(null);

		g.Font(quizTitle, java.awt.Font.PLAIN);
		g.Font(quizQuestion, java.awt.Font.PLAIN);

		panel.add(redButton, BorderLayout.PAGE_START);
		panel.add(yellowButton, BorderLayout.PAGE_START);
		panel.add(blueButton, BorderLayout.PAGE_START);
		panel.add(greenButton, BorderLayout.PAGE_START);
		panel.add(quizTitle);
		panel.add(quizQuestion);
		panel.add(scoreTrack);
		panel.add(lastAnswer);
		panel.add(lastAnswerButton);

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO
					.read(new File(
							"U:/Year 13/Computer Science/Coursework/workspace/Quizzle Logo.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setLocation(700, 10);
		picLabel.setSize(500, 200);
		panel.add(picLabel);

		// frame.setLayout(layout);//

		frame.add(panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		frame.setVisible(true);

		Prep(quizName);

		// }
	}

	private void CheckAnswer(String username, String quizName, int noOfQuestions) {
		boolean end = false;
		try {
			String[] configstring;
			ReadFile file = new ReadFile(
					quizName + "\\questions.txt");
			String[] arrayLines = file.OpenFile();
			configstring = arrayLines[qNumber].split(",");
			String AnswerString = configstring[5];
			answer = AnswerString.charAt(0);
			
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		if (buttonPressed == answer) {
			score++;
			scoreTrack.setText("Score: " + score);
			lastAnswerButton.setBackground(Color.GREEN);
			lastAnswerButton.setText(" :)");

			if (qNumber == noOfQuestions ) {
					logScore(username,quizName,score);
					JOptionPane.showMessageDialog(null, "You scored: " + score + " out of " + noOfQuestions );
					end = true;
					new QuizChoose(username);
					frame.setVisible(false);
					frame.dispose();
					
				
			}
		} else {
			lastAnswerButton.setBackground(Color.RED);
			lastAnswerButton.setText(" :(");
			if (qNumber == noOfQuestions ) {
				logScore(username,quizName,score);
				JOptionPane.showMessageDialog(null, "You scored: " + score + " out of " + noOfQuestions );
				end = true;
				new QuizChoose(username);
				frame.setVisible(false);
				frame.dispose();
			}
		}
		qNumber++;
		setup = true;
		if (end == false){
			Prep(quizName);	
		}

	}

	private void logScore(String username, String quizName,int score) {
		boolean found = false;
		int highScoreFromFile = 0;
		try {
			ReadFile file = new ReadFile(
					quizName + "\\scores.txt");
			String[] arrayLines = file.OpenFile();

			String Highscore = "";
			String[] configstring = new String[2];

			int i;
			WriteFile writer = new WriteFile(quizName + "\\scores.txt",false);
			WriteFile writer1 = new WriteFile(quizName + "\\scores.txt",true);
			writer.writeToFile("Username,Password");
			for (i = 0; i < arrayLines.length; i++) {
				if (arrayLines[i].contains(username)) {
					found = true;
					configstring = arrayLines[i].split(",");

					Highscore = configstring[1];
					highScoreFromFile = Integer.valueOf(Highscore);
					if (highScoreFromFile > score) {
						writer1.writeToFile(arrayLines[i]);

					} else {
						found = false;
					}
				} else if (i == 0) {
					// do nothing
				} else {
					writer1.writeToFile(arrayLines[i]);
				}

			}
			try {
				if (highScoreFromFile >= score) {
					found = true;

				}

				if (found == false) {
					writer1.writeToFile(username + "," + score);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void Prep(String quizName) {
		if (setup == true) {
			
			try {
				String[] configstring;
				ReadFile file = new ReadFile(
						quizName + "\\questions.txt");
				String[] arrayLines = file.OpenFile();
				configstring = arrayLines[qNumber].split(",");
				String qTitle = configstring[0];

				g.setTextFit(quizQuestion, qTitle);
				quizTitle.setText(quizName);
				redButton.setText("A: " + (configstring[1]));
				yellowButton.setText("B: " + (configstring[2]));
				greenButton.setText("C: " + (configstring[3]));
				blueButton.setText("D: " + (configstring[4]));
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			frame.repaint();
			setup = false;
			
		}
	}

	private void prepareGUI() {
		frame = new JFrame("Quizzle");
		frame.setSize(height, width);
		// frame.setLayout(new GridLayout(6,3));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();

		frame.add(panel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
