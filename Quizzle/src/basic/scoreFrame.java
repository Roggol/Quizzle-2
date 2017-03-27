package basic;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class scoreFrame extends JFrame implements ActionListener{
GUImanager g = new GUImanager();
	
	JPanel panel;
	JFrame frame;
	int height = 1000;
	int width = 720;
	JLabel scoresList1;
	JLabel scoresList2;
	JLabel scoresList3;
	JLabel scoresList4;
	JLabel scoresList5;
	JButton back;
	String Highscore;
	int highScoreFromFile;
	String top1;
	String top2;
	String top3;
	String top4;
	String top5;
	String[] configstring;
	private ArrayList<Scores> scores;


	
	public scoreFrame(String[] arrayLines, final String username, final String quizName){
		prepareGUI();
		int i;
        scores = new ArrayList<Scores>();
		for (i = 1; i < arrayLines.length; i++) {
			configstring = arrayLines[i].split(",");	
			Highscore = configstring[1];
			highScoreFromFile = Integer.valueOf(Highscore);

			addScore(configstring[0], highScoreFromFile);
			
		}
			addScore("---", 0);
			addScore("---", 0);
			addScore("---", 0);
			addScore("---", 0);
			addScore("---", 0);
			sort();
			
			top1 = "1. " + scores.get(0).getUser() + " " + scores.get(0).getScore();
			top2 = "2. " + scores.get(1).getUser() + " " + scores.get(1).getScore();
			top3 = "3. " + scores.get(2).getUser() + " " + scores.get(2).getScore();
			top4 = "4. " + scores.get(3).getUser() + " " + scores.get(3).getScore();
			top5 = "5. " + scores.get(4).getUser() + " " + scores.get(4).getScore();
		//
			ActionListener listener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					if(event.getSource() == back){
						new Startup(username, quizName);
						frame.setVisible(false);
						frame.dispose();
						
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
		frame.setSize(height, width);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(null);

		frame.add(panel);
		frame.setVisible(true);
}
	private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
        
}
	public void addScore(String name, int score) {
        scores.add(new Scores(name, score));
}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}