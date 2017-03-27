package basic;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Startup extends JFrame implements ActionListener {

	GUImanager g = new GUImanager();

	private JFrame frame;
	private JPanel panel;
	JButton start;
	JLabel userName;
	JLabel quizName;
	JButton scores;
	JButton back;
	String questionNumber;
	int questionNum = 1;
	int height = 1000;
	int width = 720;

	public Startup(final String username, final String quizName) {

		prepareGUI();

		start = new JButton("START");
		userName = new JLabel("Welcome: " + username);
		scores = new JButton("Scores");
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == start) {
					try {
						ReadFile file = new ReadFile(
								quizName + "\\questionNumber.txt");
						String[] arrayLines = file.OpenFile();
						questionNumber = arrayLines[0];
						questionNum = Integer.parseInt(questionNumber);
						//System.out.println(""+ arrayLines[0]);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new GUI(username,quizName, questionNum);
					frame.setVisible(false);
					frame.dispose();
				}
				if(event.getSource() == scores){
					
					try {
						ReadFile file = new ReadFile(
								quizName + "\\scores.txt");
						String[] arrayLines = file.OpenFile();
						new scoreFrame(arrayLines, username, quizName);
						//System.out.println(""+ arrayLines[0]);
						frame.setVisible(false);
						frame.dispose();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if(event.getSource() == back){
					new QuizChoose(username);
					frame.setVisible(false);
					frame.dispose();
					
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
		
		back = new JButton("Back");
		back.addActionListener(listener);
		back.setFocusable(false);
		back.setBackground(Color.GRAY);
		back.setSize(100, 50);
		back.setLocation(840, 580);
		
		userName.setSize (600, 50);
		userName.setLocation(1200, 200);
		panel.setLayout(null);
		panel.add(back);
		panel.add(start);
		panel.add(userName);
		panel.add(scores);
		frame.add(panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void prepareGUI() {
		frame = new JFrame("Quizzle");
		frame.setSize(height, width);
		// frame.setLayout(new GridLayout(6,3));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(new FlowLayout());

		frame.add(panel);
		frame.setVisible(true);
	}

}
