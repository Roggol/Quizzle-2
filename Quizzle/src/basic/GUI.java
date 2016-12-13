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
	int i = 1;
	int noOfQuestions = 10;
	String questionLabel = "";
	String quizLabel = "History";
	String redLabel = "";
	String yellowLabel = "";
	String greenLabel = "";
	String blueLabel = "";
	JLabel lastAnswer;
	JButton lastAnswerButton;
	char answer;
	char buttonPressed;
	int score = 0;
	boolean setup = true;

	public GUI(final String username) {
		// g.prepareGUI(frame, panel, height, width);
		// prepareGUI(frame, panel, height, width);
		prepareGUI();

		redButton = new JButton(redLabel);
		yellowButton = new JButton(yellowLabel);
		greenButton = new JButton(greenLabel);
		blueButton = new JButton(blueLabel);
		quizQuestion = new JLabel(questionLabel);
		quizTitle = new JLabel(quizLabel);
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
				CheckAnswer(username);

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
		scoreTrack.setLocation(1500, 200);
		lastAnswer.setLocation(1500, 500);
		lastAnswerButton.setLocation(1600, 620);

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

		Prep();

		// }
	}

	private void CheckAnswer(String username) {
		if (buttonPressed == answer) {
			score++;
			scoreTrack.setText("Score: " + score);
			lastAnswerButton.setBackground(Color.GREEN);
			lastAnswerButton.setText(" :)");

			if (i == 42) {
				if (score == 42) {
					JOptionPane.showMessageDialog(null,
							"You got the best possible score?");
					JOptionPane.showMessageDialog(null,
							"That makes you a genius, or an idiot.");
					JOptionPane.showMessageDialog(null,
							"But congratulations either way!");
					logScore(username, score);
					System.exit(0);

				} else {
					JOptionPane.showMessageDialog(null,
							"You win I guess, try again for a score of 42");
					logScore(username, score);
					System.exit(0);

				}
			}
		} else {
			lastAnswerButton.setBackground(Color.RED);
			lastAnswerButton.setText(" :(");
			if (i == 42) {
				if (buttonPressed == 'A') {
					JOptionPane.showMessageDialog(null,
							"Sorry, you lose. Be more assertive next time.");

					System.exit(0);
				}
				if (buttonPressed == 'C') {
					JOptionPane.showMessageDialog(null,
							"Congratulations, you lose!");
					System.exit(0);
				}

			}
			if (i == 3) {
				if (buttonPressed == 'B') {
					score++;
					scoreTrack.setText("Score: " + score);
					lastAnswerButton.setBackground(Color.GREEN);
					lastAnswerButton.setText(" :)");
				}
			}
			if (i == 5) {
				if (buttonPressed == 'A') {
					score++;
					scoreTrack.setText("Score: " + score);
					lastAnswerButton.setBackground(Color.GREEN);
					lastAnswerButton.setText(" :)");
				}
			}
		}
		i++;
		setup = true;
		Prep();

	}

	private void logScore(String username, int score) {
		boolean found = false;
		int highScoreFromFile = 0;
		try {
			ReadFile file = new ReadFile(
					"scores.txt");
			String[] arrayLines = file.OpenFile();

			String Highscore = "";
			String[] configstring = new String[2];

			int i;
			WriteFile writer = new WriteFile(
					"scores.txt",
					false);
			WriteFile writer1 = new WriteFile(
					"scores.txt",
					true);
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

	private void Prep() {
		if (setup == true) {
			switch (i) {
			case 0:
				i++;
				break;
			case 1:
				questionLabel = "What year did WW2 start?";
				redLabel = "1945";
				yellowLabel = "1939";
				greenLabel = "1938";
				blueLabel = "1940";
				answer = 'B';
				break;
			case 2:
				questionLabel = "Who killed Hitler?";
				redLabel = "The French";
				yellowLabel = "The Russians";
				greenLabel = "Himself";
				blueLabel = "A dog";
				answer = 'C';
				break;
			case 3:
				questionLabel = "Who started the fire?";
				redLabel = "We didn't";
				yellowLabel = "Probably Hitler";
				greenLabel = "A Communist";
				blueLabel = "A dog";
				answer = 'A';
				break;

			case 4:
				questionLabel = "If 4 is less than 5 and 5 is less than 6, what does this have to do with history?";
				redLabel = "8";
				yellowLabel = "7";
				greenLabel = "Nothing. This quiz is a sham";
				blueLabel = "A dog";
				answer = 'C';
				break;
			case 5:
				questionLabel = "What happened?";
				redLabel = "These questions aren't about history are they.";
				yellowLabel = "The big bang and then everything else.";
				greenLabel = "A Communist";
				blueLabel = "A dog";
				answer = 'B';
				break;
			case 6:
				questionLabel = "WHAT IS THIS!?!?!?";
				redLabel = "A quiz about history";
				yellowLabel = "Probably Hitler";
				greenLabel = "A Communist";
				blueLabel = "A dog";
				answer = 'B';
				break;
			case 7:
				questionLabel = "Why are you wasting your time?";
				redLabel = "Nothing better to do I guess";
				yellowLabel = "Time is a construct of human precipitation";
				greenLabel = "A Communist";
				blueLabel = "A dog";
				answer = 'A';
				break;
			case 8:
				questionLabel = "What time is it?";
				redLabel = "Mr Wolf";
				yellowLabel = "Stop this";
				greenLabel = "I'm bored now";
				blueLabel = "A dog";
				answer = 'A';
				break;
			case 9:
				questionLabel = "What does a dyslexic worship?";
				redLabel = "Mr Wolf";
				yellowLabel = "Probably Hitler";
				greenLabel = "A Communist";
				blueLabel = "A dog";
				answer = 'D';
				break;

			case 10:
				questionLabel = "What is the answer to this question?";
				redLabel = "IDK";
				yellowLabel = "Probably Hitler";
				greenLabel = "A Communist";
				blueLabel = "A plot twist";
				answer = 'D';
				break;
			case 11:
				questionLabel = "What is the probability of you guessing this answer correctly?";
				redLabel = "25%";
				yellowLabel = "25%";
				greenLabel = "50%";
				blueLabel = "A dog doesn't understand paradoxes";
				answer = 'D';
				break;
			case 12:
				questionLabel = "Which of the following is the most common cause of death?";
				redLabel = "Life";
				yellowLabel = "Probably Hitler";
				greenLabel = "A communist";
				blueLabel = "A dog";
				answer = 'A';
				break;

			case 13:
				questionLabel = "When?";
				redLabel = "Now";
				yellowLabel = "Then";
				greenLabel = "A long long time ago in a galaxy far far away";
				blueLabel = "Tuesday";
				answer = 'A';
				break;
			case 14:
				questionLabel = "Are these questions fair?";
				redLabel = "no.";
				yellowLabel = "NO!";
				greenLabel = "Well, no not really.";
				blueLabel = "Yes, from a certain point of view.";
				answer = 'D';
				break;
			case 15:
				questionLabel = "Don't you hate it when a sentence doesn't end the way you _____";
				redLabel = "Execpt!";
				yellowLabel = "Expect?";
				greenLabel = "Want?";
				blueLabel = "Octopus?";
				answer = 'D';
				break;
			case 16:
				questionLabel = "When was the apple invented?";
				redLabel = "At the beginning of time";
				yellowLabel = "At the creation of earth";
				greenLabel = "Before the android";
				blueLabel = "July 14th 1605";
				answer = 'C';
				break;
			case 17:

				questionLabel = "How Long does this quiz go on for?";
				redLabel = "As Long as it takes";
				yellowLabel = "Until the end of time itself";
				greenLabel = "These are procedurally generated questions";
				blueLabel = "42";
				answer = 'D';
				break;
			case 18:
				questionLabel = "Now for the actual questions on history. Did you remember to delete yours?";
				redLabel = "Yes";
				yellowLabel = "Incognito mode. Ha.";
				greenLabel = "Damnit";
				blueLabel = "No";
				answer = 'B';
				break;
			case 19:
				questionLabel = "Now for the actual questions on history. Did you remember to delete yours?";
				redLabel = "Yes";
				yellowLabel = "Incognito mode. Ha.";
				greenLabel = "Damnit";
				blueLabel = "No";
				answer = 'B';
				break;
			case 20:
				questionLabel = "Did it break?";
				redLabel = "No the question repeated.";
				yellowLabel = "No the button didn't work the first time";
				greenLabel = "I don't make mistakes.";
				blueLabel = "Yes";
				answer = 'C';
				break;
			case 21:
				questionLabel = "What is the airspeed velocity of an unladen swallow?";
				redLabel = "Yes";
				yellowLabel = "What do you mean- African or European swallow?";
				greenLabel = "What's airspeed velocity?";
				blueLabel = "3666667 beard seconds per jiffy";
				answer = 'D';
				break;
			case 22:
				questionLabel = "How can mirrors be real if our eyes aren't real?";
				redLabel = "Woah, that is really deep";
				yellowLabel = "witchcraft";
				greenLabel = "If babies could speak they would be the most intelligent";
				blueLabel = "I don't understand Jayden smith";
				answer = 'A';
				break;
			case 23:
				questionLabel = "What's the difference between a duck?";
				redLabel = "One has 2 legs and both the same.";
				yellowLabel = "Geese";
				greenLabel = "Quack";
				blueLabel = "This question makes no sense.";
				answer = 'A';
				break;
			case 24:
				questionLabel = "Now for a 4 question joke. So I was driving down the road in my___";
				redLabel = "Car";
				yellowLabel = "Lorry";
				greenLabel = "Canoe";
				blueLabel = "Dog";
				answer = 'C';
				break;
			case 25:
				questionLabel = "And all the tires fell off. How do I scrape the:";
				redLabel = "Gum";
				yellowLabel = "Pancakes";
				greenLabel = "jellyfish";
				blueLabel = "pterodactyl";
				answer = 'B';
				break;
			case 26:
				questionLabel = "off the dog's roof. _____";
				redLabel = "Purple";
				yellowLabel = "Blue";
				greenLabel = "Red";
				blueLabel = "Chameleons";
				answer = 'A';
				break;
			case 27:
				questionLabel = "Because ___ doesn't have any____";
				redLabel = "Larry, donuts";
				yellowLabel = "Einstein, quantum computers";
				greenLabel = "ice cream, bones";
				blueLabel = "Dog";
				answer = 'C';
				break;
			case 28:
				questionLabel = "Time for a joke. Knock knock.";
				redLabel = "Time is a construct of human perception.";
				yellowLabel = "Who are you?";
				greenLabel = "Go away";
				blueLabel = "A dog";
				answer = 'A';
				break;
			case 29:
				questionLabel = "Ok nevermind, you start the knock knock joke.";
				redLabel = "Knock Knock";
				yellowLabel = "Not this one";
				greenLabel = "Don't pick a different answer";
				blueLabel = "There aren't any tricks to this question";
				answer = 'A';
				break;
			case 30:
				questionLabel = "Who's there?";
				redLabel = "......";
				yellowLabel = "Mr Wolf";
				greenLabel = "A Communist";
				blueLabel = "A dog";
				answer = 'A';
				break;
			case 31:
				questionLabel = "Who sent you?";
				redLabel = "Mr Wolf";
				yellowLabel = "You did";
				greenLabel = "Probably Hitler";
				blueLabel = "A dog";
				answer = 'B';
				break;
			case 32:
				questionLabel = "Memes?";
				redLabel = "Me-Mes";
				yellowLabel = "Mems";
				greenLabel = "May-Mays";
				blueLabel = "Memes.";
				answer = 'D';
				break;
			case 33:
				questionLabel = "What is the least occuring correct answer so far?";
				redLabel = "A";
				yellowLabel = "C";
				greenLabel = "B";
				blueLabel = "D";
				answer = 'B';
				break;
			case 34:
				questionLabel = "When life gives you lemons:";
				redLabel = "Make Lemonade";
				yellowLabel = "Kill life";
				greenLabel = "Throw the lemons at life's face and look angry";
				blueLabel = "Retire to the south of France";
				answer = 'A';
				break;
			case 35:
				questionLabel = "Do you understand physics?";
				redLabel = "What does moving things with your mind have to do with this quiz?";
				yellowLabel = "Not as much as that smart guy aberto Eintein";
				greenLabel = "No but I understand History";
				blueLabel = "A dog";
				answer = 'C';
				break;
			case 36:
				questionLabel = "When did world war 2 finish?";
				redLabel = "The day after tommorow";
				yellowLabel = "Last tuesday";
				greenLabel = "I think a monday, not sure tho";
				blueLabel = "OMG A HISTORY QUESTIONS YES!";
				answer = 'D';
				break;
			case 37:
				questionLabel = "Who created the Nazi party?";
				redLabel = "Some guy I guess, not Hitler tho";
				yellowLabel = "Probably Hitler";
				greenLabel = "Definitely Hitler - very confident about that.";
				blueLabel = "A communist dog";
				answer = 'A';
				break;
			case 38:
				questionLabel = "Are you happy with this quiz?";
				redLabel = "Mr wolf";
				yellowLabel = "Probably Hitler";
				greenLabel = "I guess so.";
				blueLabel = "Octopus?";
				answer = 'C';
				break;

			case 39:
				questionLabel = "What have you learnt about history today?";
				redLabel = "A huge amount";
				yellowLabel = "Nothing";
				greenLabel = "A very small amount";
				blueLabel = "World war 1 started in 1939";
				answer = 'C';
				break;
			case 40:
				questionLabel = "I guess this quiz has finally gone full circle.";
				redLabel = "Yeah, that's pretty neat";
				yellowLabel = "1939";
				greenLabel = "I'm just glad it's almost over.";
				blueLabel = "I need to get a score of 42 to win.";
				answer = 'B';
				break;

			case 42:
				questionLabel = "Do you want to win?";
				redLabel = "Yes?";
				yellowLabel = "YES!";
				greenLabel = "No not really";
				blueLabel = "42";
				answer = 'B';
				break;

			default:
				questionLabel = "What's the meaning of life?";
				redLabel = "none";
				yellowLabel = "none";
				greenLabel = "none";
				blueLabel = "42";
				answer = 'D';
				System.out.println("" + i);
				break;

			}

			/*
			g.setTextFit(quizTitle, quizLabel);
			g.setTextFit(redButton, "A: " + redLabel);
			g.setTextFit(yellowButton, "B: " + yellowLabel);
			g.setTextFit(greenButton, "C: " + greenLabel);
			g.setTextFit(blueButton, "D: " + blueLabel);
			*/
			g.setTextFit(quizQuestion, questionLabel);
			//quizQuestion.setText(questionLabel);
			quizTitle.setText(quizLabel);
			redButton.setText("A: " + redLabel);
			yellowButton.setText("B: " + yellowLabel);
			greenButton.setText("C: " + greenLabel);
			blueButton.setText("D: " + blueLabel);
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
