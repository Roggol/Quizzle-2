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

public class QuizMaker extends JFrame implements ActionListener {

	GUImanager g = new GUImanager();

	private JFrame frame;
	private JPanel panel;
	JButton next;
	JLabel userName;
	JLabel quizName;
	JButton back;
	private int count = 0;
	int height = 1000;
	int width = 720;

	public QuizMaker(final String username, final String quizName, int Number) {

		prepareGUI();

		next = new JButton("START");
		userName = new JLabel("Welcome: " + username);
		back = new JButton("Scores");
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == next) {
					new GUI(username,quizName);
					frame.setVisible(false);
					frame.dispose();
				}
				if(event.getSource() == back){
					
					try {
						ReadFile file = new ReadFile(
								quizName + "\\scores.txt");
						String[] arrayLines = file.OpenFile();
						new scoreFrame(arrayLines);
						//System.out.println(""+ arrayLines[0]);
						
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
		next.setLocation(840, 480);
		back.addActionListener(listener);
		back.setFocusable(false);
		back.setBackground(Color.GRAY);
		back.setSize(100, 50);
		back.setLocation(840, 530);
		
		
		userName.setSize (600, 50);
		userName.setLocation(1700, 100);
		panel.setLayout(null);
		panel.add(next);
		panel.add(userName);
		panel.add(back);
		frame.add(panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void questions(int Number, int i){
		switch (i){
			case 0:
				i++;
				break;
			case 1:
				break;
		}
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
