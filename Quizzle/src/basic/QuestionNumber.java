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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QuestionNumber extends JFrame implements ActionListener {

	GUImanager g = new GUImanager();

	private JFrame frame;
	private JPanel panel;
	JButton next;
	JLabel userName;
	JLabel quizName;
	JTextField questionNumber;
	JButton back;
	String questionNo;
	int questionNoInt = 0;
	private int count = 0;
	int height = 1000;
	int width = 720;

	public QuestionNumber(final String username, final String quizName) {

		prepareGUI();

		next = new JButton("Enter");
		userName = new JLabel("Welcome: " + username);
		questionNumber = new JTextField("Number of Questions(1-20)");
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == next) {
					questionNo = questionNumber.getText();
					try{
						questionNoInt = Integer.parseInt(questionNo);
						if(questionNoInt < 1){
							questionNoInt = 1;
							JOptionPane.showMessageDialog(null, "Integer too low. 1 Selected");
						}else if(questionNoInt>20){
							questionNoInt=20;
							JOptionPane.showMessageDialog(null, "Integer too high. 20 selected");
						}else if(questionNoInt>0 && questionNoInt <21){
							JOptionPane.showMessageDialog(null, questionNoInt + " question(s) selected");
						}else{
							questionNoInt = 10;
							JOptionPane.showMessageDialog(null, "Not an integer, default selected (10)");
						}
						new QuizMaker(username,quizName,questionNoInt);
						frame.setVisible(false);
						frame.dispose();
					}catch (java.lang.NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Not an integer");

					}
					
					
				}
			}

		};
		next.addActionListener(listener);
		next.setFocusable(false);
		next.setBackground(Color.GRAY);
		next.setSize(400, 50);
		next.setLocation(840, 500);
		questionNumber.setSize (400, 50);
		questionNumber.setLocation(840, 400);
		
		
		userName.setSize (600, 50);
		userName.setLocation(1300, 100);
		panel.setLayout(null);
		panel.add(next);
		panel.add(userName);
		panel.add(questionNumber);
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
