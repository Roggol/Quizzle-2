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

public class QuizMaker extends JFrame implements ActionListener {

	GUImanager g = new GUImanager();

	private JFrame frame;
	private JPanel panel;
	JButton next;
	JLabel userName;
	JLabel quizName;
	JButton back;
	JTextField questionTitle;
	JTextField AnswerA;
	JTextField AnswerB;
	JTextField AnswerC;
	JTextField AnswerD;
	JTextField Answer;
	String[] configstring;
	
	private int count = 1;
	int height = 1000;
	int width = 720;

	public QuizMaker(final String username, final String quizName, final int Number) {

		prepareGUI();

		next = new JButton("Next -->");
		userName = new JLabel("Welcome: " + username);
		back = new JButton("<-- back");
		questionTitle = new JTextField("Question 1");
		AnswerA = new JTextField ("A: ");
		AnswerB = new JTextField ("B: ");
		AnswerC = new JTextField ("C: ");
		AnswerD = new JTextField ("D: ");
		Answer = new JTextField ("Correct Answer (A,B,C or D)");
		
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == next) {

					if(!(Answer.getText().equals("A")||Answer.getText().equals("B")||Answer.getText().equals("C")||Answer.getText().equals("D"))){
						JOptionPane.showMessageDialog(null, "Not a valid Answer");
					}else{
						count++;
					
						if (count > Number){
							new GUI(username, quizName, Number);
							frame.setVisible(false);
							frame.dispose();
						}
						try {
							ReadFile file = new ReadFile(
								quizName + "\\questions.txt");
							String[] arrayLines = file.OpenFile();
							/*for (int j = 1; j < arrayLines.length; j++) {
							configstring = arrayLines[j].split(",");	
							
							}*/
							try{
								WriteFile writer = new WriteFile(quizName + "\\questions.txt",false);
								WriteFile writer1 = new WriteFile(quizName + "\\questions.txt",true);
								writer.writeToFile("questionTitle, AnswerA, AnswerB, AnswerC, AnswerD");
							
								for (int i = 0; i < arrayLines.length; i++) {
									if (i == 0 || i == count) {
										// do nothing
									} else {
										writer1.writeToFile(arrayLines[i]);
									}

								}
								writer1.writeToFile(questionTitle.getText() + "," + AnswerA.getText() + "," + AnswerB.getText() + "," + AnswerC.getText() + "," + AnswerD.getText() + "," + Answer.getText());
							
							} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
						} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}

						try {
							ReadFile file = new ReadFile(
									quizName + "\\questions.txt");
							String[] arrayLines = file.OpenFile();
							if (!(count+1 > arrayLines.length)){
								configstring = arrayLines[count+1].split(",");
								String qTitle = configstring[0];
								System.out.println("" + count + " " + arrayLines.length);
								questionTitle.setText(qTitle);
								AnswerA.setText(configstring[1]);
								AnswerB.setText(configstring[2]);
								AnswerC.setText(configstring[3]);
								AnswerD.setText(configstring[4]);
								Answer.setText(configstring[5]);
							}else{
								refresh(count);
							}

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

						}
					}
				}
				if(event.getSource() == back){
					count --;
					if(count == 1){
						back.setVisible(false);
					}
					try {
						ReadFile file = new ReadFile(
								quizName + "\\questions.txt");
						String[] arrayLines = file.OpenFile();
						configstring = arrayLines[count].split(",");
						String qTitle = configstring[0];
						System.out.println("" + count);
						questionTitle.setText(qTitle);
						AnswerA.setText(configstring[1]);
						AnswerB.setText(configstring[2]);
						AnswerC.setText(configstring[3]);
						AnswerD.setText(configstring[4]);
						Answer.setText(configstring[5]);
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

			}

		};
		next.addActionListener(listener);
		next.setFocusable(false);
		next.setBackground(Color.GRAY);
		next.setSize(100, 50);
		next.setLocation(600, 480);
		back.addActionListener(listener);
		back.setFocusable(false);
		back.setBackground(Color.GRAY);
		back.setSize(100, 50);
		back.setLocation(400, 480);
		back.setVisible(false);
		
		questionTitle.setSize (600, 50);
		questionTitle.setLocation(1000, 100);
		
		AnswerA.setSize (600, 50);
		AnswerA.setLocation(1000, 200);
		
		AnswerB.setSize (600, 50);
		AnswerB.setLocation(1000, 300);
		
		AnswerC.setSize (600, 50);
		AnswerC.setLocation(1000, 400);
		
		AnswerD.setSize (600, 50);
		AnswerD.setLocation(1000, 500);
		
		Answer.setSize (600, 50);
		Answer.setLocation(1000, 600);
		
		
		userName.setSize (600, 50);
		userName.setLocation(1700, 100);
		panel.setLayout(null);
		panel.add(next);
		panel.add(userName);
		panel.add(back);
		panel.add(questionTitle);
		panel.add(AnswerA);
		panel.add(AnswerB);
		panel.add(AnswerC);
		panel.add(AnswerD);
		panel.add(Answer);
		
		frame.add(panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void refresh(int count){
		if(count > 1){
			back.setVisible(true);
		}
		next.setText("Next -->");
		back.setText("<-- back");
		AnswerA.setText("A: ");
		AnswerB.setText("B: ");
		AnswerC.setText("C: ");
		AnswerD.setText("D: ");
		Answer.setText("Correct Answer");
		questionTitle.setText("Question " + count);
				
				

		
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
