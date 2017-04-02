package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QuestionNumber extends JFrame implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	JButton next;
	JLabel userName;
	JLabel quizName;
	JTextField questionNumber;
	JButton back;
	String questionNo;
	//initialise variables and objects

	public QuestionNumber(final String username, final String quizName) {

		prepareGUI();

		next = new JButton("Enter");
		userName = new JLabel("Welcome: " + username);
		questionNumber = new JTextField("Number of Questions(2-20)");
		//sets up objects
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == next) {
					// if next button is pressed
					questionNo = questionNumber.getText();
					// get string from box
					try{
						int questionNoInt = Integer.parseInt(questionNo);
						//turn int into string
						if(questionNoInt < 2){
							questionNoInt = 2;
							//sets mininum number of questions as 2
							JOptionPane.showMessageDialog(null, "Integer too low. 2 Selected");
						}else if(questionNoInt>20){
							questionNoInt=20;
							//sets maximum number of questions as 20
							JOptionPane.showMessageDialog(null, "Integer too high. 20 selected");
						}else{
							//if number of questions is accepted, returns the number they input
							JOptionPane.showMessageDialog(null, questionNoInt + " question(s) selected");
						}
						new QuizMaker(username,quizName,questionNoInt);
						//launches quizmaker
						try {
							WriteFile writer = new WriteFile(quizName + "\\questionNumber.txt",true);
							writer.writeToFile("" + questionNoInt);
							//writes the question number to file
						} catch (IOException e) {
							e.printStackTrace();
						}
						frame.setVisible(false);
						frame.dispose();
						//remove frame
					}catch (java.lang.NumberFormatException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Not an integer");

					}
					
					
				}
			}

		};
		
		//object setup
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

	}

	private void prepareGUI() {
		frame = new JFrame("Quizzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.add(panel);
		frame.setVisible(true);
	}

}
