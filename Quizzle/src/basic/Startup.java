package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Startup extends JFrame implements ActionListener {

	GUImanager g = new GUImanager();

	private JFrame frame;
	private JPanel panel;
	JButton start;
	JLabel userName;
	JLabel quizName;
	JButton scores;
	JButton back;
	JButton edit;
	public Startup(final String username, final String quizName) {

		prepareGUI();

		start = new JButton("START");
		userName = new JLabel("Welcome: " + username);
		scores = new JButton("Scores");
		edit = new JButton("Edit Quiz");
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == start) {
					//Start button pressed
					int questionNum = 2;
					try {
						ReadFile file = new ReadFile(
								quizName + "\\questionNumber.txt");
						String[] arrayLines = file.OpenFile();
						String questionNumber = arrayLines[0];
						questionNum = Integer.parseInt(questionNumber);
						//find number of questions from file
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					new GUI(username,quizName, questionNum);
					//launch quiz
					frame.setVisible(false);
					frame.dispose();
				}
				if(event.getSource() == scores){
					//scores pressed
					try {
						ReadFile file = new ReadFile(
								quizName + "\\scores.txt");
						String[] arrayLines = file.OpenFile();
						new scoreFrame(arrayLines, username, quizName);
						//launch High Score screen
						frame.setVisible(false);
						frame.dispose();
						//delete frame
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				if(event.getSource() == back){
					new QuizChoose(username);
					//launch choose quiz screen
					frame.setVisible(false);
					frame.dispose();
					//delete frame
					
				}if (event.getSource() == edit) {
					int questionNum = 2;
					try {
						ReadFile file = new ReadFile(
								quizName + "\\questionNumber.txt");
						String[] arrayLines = file.OpenFile();
						String questionNumber = arrayLines[0];
						questionNum = Integer.parseInt(questionNumber);
						//get question number from file
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					new QuizMaker(username,quizName, questionNum);
					//launch quiz maker screen
					frame.setVisible(false);
					frame.dispose();
					//delete frame
				}

			}

		};
		start.addActionListener(listener);
		start.setFocusable(false);
		start.setBackground(Color.GRAY);
		start.setSize(100, 50);
		start.setLocation(840, 480);
		scores.addActionListener(listener);
		scores.setFocusable(false);
		scores.setBackground(Color.GRAY);
		scores.setSize(100, 50);
		scores.setLocation(840, 530);
		
		edit.addActionListener(listener);
		edit.setFocusable(false);
		edit.setBackground(Color.GRAY);
		edit.setSize(100, 50);
		edit.setLocation(840, 580);
		
		back = new JButton("Back");
		back.addActionListener(listener);
		back.setFocusable(false);
		back.setBackground(Color.GRAY);
		back.setSize(100, 50);
		back.setLocation(840, 630);
		
		userName.setSize (600, 50);
		userName.setLocation(1200, 200);
		panel.setLayout(null);
		panel.add(back);
		panel.add(start);
		panel.add(userName);
		panel.add(scores);
		panel.add(edit);
		frame.add(panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

	private void prepareGUI() {
		frame = new JFrame("Quizzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.setVisible(true);
	}

}
