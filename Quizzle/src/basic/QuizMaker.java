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

public class QuizMaker extends JFrame implements ActionListener {

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
	
	private int questionNumber = 1;

	public QuizMaker(final String username, final String quizName, final int numberOfQuestions) {

		prepareGUI();

		next = new JButton("Save -->");
		//Labelled save with an arrow to make it more clear that's what the button does
		userName = new JLabel("Welcome: " + username);
		back = new JButton("<-- back");
		questionTitle = new JTextField("Question 1");
		AnswerA = new JTextField ("A: ");
		AnswerB = new JTextField ("B: ");
		AnswerC = new JTextField ("C: ");
		AnswerD = new JTextField ("D: ");
		Answer = new JTextField ("Correct Answer (A,B,C or D)");
		
		try {
			ReadFile file = new ReadFile(
					quizName + "\\questions.txt");
			String[] arrayLines = file.OpenFile();
			if (!(questionNumber >= arrayLines.length)){
				//if the question already exists in the file, print what is saved for that question
				String[] configstring = arrayLines[questionNumber].split(",");
				//splits line by commas
				String qTitle = configstring[0];
				questionTitle.setText(qTitle);
				AnswerA.setText(configstring[1]);
				AnswerB.setText(configstring[2]);
				AnswerC.setText(configstring[3]);
				AnswerD.setText(configstring[4]);
				Answer.setText(configstring[5]);
				//sets the text to contain the relevant text from the file
			}

		} catch (IOException e) {
			e.printStackTrace();

		}
		
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == next) {
					if(!(Answer.getText().equals("A")||Answer.getText().equals("B")||Answer.getText().equals("C")||Answer.getText().equals("D"))){
						JOptionPane.showMessageDialog(null, "Not a valid Answer");
					}else{
						questionNumber++;
					
						if (questionNumber > numberOfQuestions){
							new GUI(username, quizName, numberOfQuestions);
							//launch Quiz
							frame.setVisible(false);
							frame.dispose();
						}
						try {
							ReadFile file = new ReadFile(
								quizName + "\\questions.txt");
							String[] arrayLines = file.OpenFile();
							try{
								WriteFile writer = new WriteFile(quizName + "\\questions.txt",false);
								WriteFile writer1 = new WriteFile(quizName + "\\questions.txt",true);
								writer.writeToFile("questionTitle, AnswerA, AnswerB, AnswerC, AnswerD");
								int length = arrayLines.length + 2;
								// +1 for headings and +1 so the most recent question is included
							
								for (int i = 0; i < length; i++) {
									if (i == 0) {
										//do nothing because this is the headings line
									}
									else if(i == questionNumber-1){
										writer1.writeToFile(questionTitle.getText() + "," + AnswerA.getText() + "," + AnswerB.getText() + "," + AnswerC.getText() + "," + AnswerD.getText() + "," + Answer.getText());
										//write current boxes to file
									}else if(i < arrayLines.length){
										writer1.writeToFile(arrayLines[i]);
										//write whatever is in that line of the file back to the file
									}

								}
								
							
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
							if (!(questionNumber >= arrayLines.length)){
								//if the question already exists in the file, print what is saved for that question
								String[] configstring = arrayLines[questionNumber].split(",");
								//splits line by commas
								String qTitle = configstring[0];
								questionTitle.setText(qTitle);
								AnswerA.setText(configstring[1]);
								AnswerB.setText(configstring[2]);
								AnswerC.setText(configstring[3]);
								AnswerD.setText(configstring[4]);
								Answer.setText(configstring[5]);
								//sets the text to contain the relevant text from the file
							}else{
								refresh(questionNumber, numberOfQuestions);
								//if the question does not exist in the file, input default values
							}

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

						}
					}
				}
				if(event.getSource() == back){
					questionNumber --;
					if(questionNumber == 1){
						back.setVisible(false);
						//to make it so you can't reach negative question numbers
					}
					try {
						ReadFile file = new ReadFile(
								quizName + "\\questions.txt");
						String[] arrayLines = file.OpenFile();
						String[] configstring = arrayLines[questionNumber].split(",");
						//read file and input the text from the file relevant to each box
						String qTitle = configstring[0];
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
	public void refresh(int questionNumber, int numberOfQuestions){
		if(questionNumber > 1){
			back.setVisible(true);
		}
		next.setText("save -->");
		if(questionNumber == numberOfQuestions){
			next.setText("finish!");
		}
		back.setText("<-- back");
		AnswerA.setText("A: ");
		AnswerB.setText("B: ");
		AnswerC.setText("C: ");
		AnswerD.setText("D: ");
		Answer.setText("Correct Answer");
		questionTitle.setText("Question " + questionNumber);			
				
		
	}
	private void prepareGUI() {
		//prepares GUI
		frame = new JFrame("Quizzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.setVisible(true);
	}

}
