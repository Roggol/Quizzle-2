package basic;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QuizChoose extends JFrame implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	JButton newQuiz;
	JLabel userName;
	JButton oldQuiz;
	JButton back;
	JTextField quizMaker;
	String quizName;
	//initialise objects and variables
	//more comments to do

	public QuizChoose(final String username) {
		prepareGUI();//setup GUI

		newQuiz = new JButton("New Quiz");
		userName = new JLabel("Welcome: " + username);
		oldQuiz = new JButton("Existing Quiz");
		quizMaker = new JTextField("Enter Quiz Name Here");
		//setup objects
		
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == newQuiz) {
					//create quiz
					quizName = quizMaker.getText();
					File dir = new File(quizName);
					dir.mkdir();
					try{
						File file = new File (quizName + "//scores.txt");
						PrintWriter printWriter = new PrintWriter (file);
						printWriter.close ();
					}catch(IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try{
						File file = new File (quizName + "//questionNumber.txt");
						PrintWriter printWriter = new PrintWriter (file);
						printWriter.close ();
					}catch(IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try{
						File file = new File (quizName + "//questions.txt");
						PrintWriter printWriter = new PrintWriter (file);
						printWriter.close ();
					}catch(IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new QuestionNumber(username, quizName);
					frame.setVisible(false);
					frame.dispose();
				}
				if(event.getSource() == oldQuiz){
					// use existing quiz
					quizName = quizMaker.getText();
					if (Files.isDirectory(Paths.get(quizName))) {
						new Startup(username, quizName);
						frame.setVisible(false);
						frame.dispose();
						
					}else{
						JOptionPane.showMessageDialog(null, "Invalid Quiz");
					}
						
				}
				if(event.getSource() == back){
					new Login();
					frame.setVisible(false);
					frame.dispose();
					
				}

			}

		};
		newQuiz.addActionListener(listener);
		newQuiz.setFocusable(false);
		newQuiz.setBackground(Color.GRAY);
		newQuiz.setSize(200, 50);
		newQuiz.setLocation(840, 480);
		oldQuiz.addActionListener(listener);
		oldQuiz.setFocusable(false);
		oldQuiz.setBackground(Color.GRAY);
		oldQuiz.setSize(200, 50);
		oldQuiz.setLocation(840, 530);

		quizMaker.setSize (200, 50);
		quizMaker.setLocation(840, 580);
		
		back = new JButton("Log out");
		back.addActionListener(listener);
		back.setFocusable(false);
		back.setBackground(Color.GRAY);
		back.setSize(200, 50);
		back.setLocation(840, 630);
		
		userName.setSize (600, 50);
		userName.setLocation(1200, 200);
		panel.setLayout(null);
		panel.add(newQuiz);
		panel.add(userName);
		panel.add(oldQuiz);
		panel.add(quizMaker);
		panel.add(back);
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.add(panel);
		frame.setVisible(true);
	}

}

