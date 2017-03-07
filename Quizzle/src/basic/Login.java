package basic;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login {
	GUImanager g = new GUImanager();

	private JFrame frame;
	private JPanel panel;
	JButton start;
	int height = 1000;
	int width = 720;
	JTextField username;
	JTextField password;
	String usernameCheck;
	String passwordCheck;
	JButton signUp;

	public Login() {

		prepareGUI();

		start = new JButton("Login");
		signUp = new JButton("Sign up");
		username = new JTextField("UserName");
		password = new JTextField("Password");
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == start) {
					usernameCheck = username.getText();
					passwordCheck = password.getText();
					boolean found = false;
					try {
						ReadFile file = new ReadFile(
								"login.txt");
						String[] arrayLines = file.OpenFile();
						int i;
						for (i = 0; i < arrayLines.length; i++) {
							if((i+1) != arrayLines.length){
								if (arrayLines[i].contains(usernameCheck)
									&& arrayLines[i + 1]
											.contains(passwordCheck)) {
								 found = true;
								} 

							}
						}if(found == true){
							new QuizChoose(usernameCheck);
							frame.setVisible(false);
							frame.dispose();
						}else{
							JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
						}
							
						

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if (event.getSource() == signUp){
					usernameCheck = username.getText();
					passwordCheck = password.getText();
					try {
						WriteFile file = new WriteFile(
								"login.txt", true);
						
						file.writeToFile(usernameCheck);
						file.writeToFile(passwordCheck);
						System.out.println("Test");
						
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		};
		start.addActionListener(listener);
		start.setFocusable(false);
		start.setBackground(Color.GRAY);
		start.setSize(100, 50);
		start.setLocation(1000, 480);
		signUp.addActionListener(listener);
		signUp.setFocusable(false);
		signUp.setBackground(Color.GRAY);
		signUp.setSize(100, 50);
		signUp.setLocation(1000, 530);

		username.setSize(100, 50);
		password.setSize(100, 50);
		username.setLocation(800, 480);
		password.setLocation(900, 480);
		panel.setLayout(null);
		panel.add(start);
		panel.add(username);
		panel.add(password);
		panel.add(signUp);
		frame.add(panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

	private void prepareGUI() {
		frame = new JFrame("Quizzle");
		frame.setSize(height, width);
		// frame.setLayout(new GridLayout(6,3));
//
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(new FlowLayout());

		frame.add(panel);
		frame.setVisible(true);
	}

}
