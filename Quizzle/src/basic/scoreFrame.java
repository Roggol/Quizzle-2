package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class scoreFrame extends JFrame implements ActionListener{

	JPanel panel;
	JFrame frame;
	JLabel scoresList1;
	JLabel scoresList2;
	JLabel scoresList3;
	JLabel scoresList4;
	JLabel scoresList5;
	JButton back;
	
	public scoreFrame(String[] arrayLines, final String username, final String quizName){
		prepareGUI();
		ArrayList<Scores> scores = new ArrayList<Scores>();
		//creates new array of type scores
		for (int i = 1; i < arrayLines.length; i++) {
			//for all entries in file
			String[] configString = arrayLines[i].split(",");	
			String Highscore = configString[1];
			int highScoreFromFile = Integer.valueOf(Highscore);
			//turns string from file into int
			scores.add(new Scores(configString[0], highScoreFromFile));
			//adds score to the array
			
		}
			for(int j = 0; j < 5; j++){//adds 5 empty scores to scores to fill array
			scores.add(new Scores("---",0));
			}
			ScoreComparator comparator = new ScoreComparator();
	        Collections.sort(scores, comparator);
	        //sorts the array using a bubble sort
			
			String top1 = "1. " + scores.get(0).getUser() + " " + scores.get(0).getScore();
			String top2 = "2. " + scores.get(1).getUser() + " " + scores.get(1).getScore();
			String top3 = "3. " + scores.get(2).getUser() + " " + scores.get(2).getScore();
			String top4 = "4. " + scores.get(3).getUser() + " " + scores.get(3).getScore();
			String top5 = "5. " + scores.get(4).getUser() + " " + scores.get(4).getScore();
			//sets the strings for the top 5 scores
			
			ActionListener listener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					if(event.getSource() == back){
						new Startup(username, quizName);
						//back to main menu
						frame.setVisible(false);
						frame.dispose();
						//delete frame
					}
					
				}
			};
		back = new JButton("<-- back");
		back.addActionListener(listener);
		back.setFocusable(false);
		back.setBackground(Color.GRAY);
		back.setSize(100, 50);
		back.setLocation(500, 580);
		
		scoresList1 = new JLabel(top1);
		scoresList1.setSize (600, 50);
		scoresList1.setLocation(1100, 300);
		
		scoresList2 = new JLabel(top2);
		scoresList2.setSize (600, 50);
		scoresList2.setLocation(1100, 400);
		
		scoresList3 = new JLabel(top3);
		scoresList3.setSize (600, 50);
		scoresList3.setLocation(1100, 500);
		
		scoresList4 = new JLabel(top4);
		scoresList4.setSize (600, 50);
		scoresList4.setLocation(1100, 600);
		
		scoresList5 = new JLabel(top5);
		scoresList5.setSize (600, 50);
		scoresList5.setLocation(1100, 700);
		
		panel.add(scoresList1);
		panel.add(scoresList2);
		panel.add(scoresList3);
		panel.add(scoresList4);
		panel.add(scoresList5);
		panel.add(back);
		frame.add(panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	private void prepareGUI() {
		frame = new JFrame("Quizzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(null);
		frame.add(panel);
		frame.setVisible(true);
}	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
	
}