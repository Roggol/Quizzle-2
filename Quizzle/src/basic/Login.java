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
	JTextField username;
	JTextField password;
	String usernameCheck;
	String passwordCheck;
	JButton signUp;
	
	//Initialise buttons, strings, integers and Labels

	public Login() {

		prepareGUI();//sets up GUI

		start = new JButton("Login");
		signUp = new JButton("Sign up");
		username = new JTextField("UserName");
		password = new JTextField("Password");
		//sets up buttons and Text fields
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == start) {//if the start button is pressed
					usernameCheck = username.getText();
					passwordCheck = password.getText();//gets string from text fields
					boolean found = false; //sets up boolean to check if the username is contained within the file
					try {
						ReadFile file = new ReadFile(
								"login.txt");//locates file
						String[] arrayLines = file.OpenFile();//gets array of lines from file
						for (int i = 0; i < arrayLines.length; i++) {// for loop for number of lines in the file
							if((i+1) != arrayLines.length){// if its not the last line of the file
								if (arrayLines[i].contains(usernameCheck)//check if the username is found
									&& arrayLines[i + 1]
											.contains(passwordCheck)) {//if the password is also found in the next line
								 found = true;//username and password matched
								} 

							}
						}if(found == true){// if the username and password are matched
							new QuizChoose(usernameCheck);//launch the quiz chooser window
							frame.setVisible(false);
							frame.dispose();//remove frame
						}else{
							JOptionPane.showMessageDialog(null, "Incorrect Username or Password");// Tell them that the username and password do not match
						}
							
						

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if (event.getSource() == signUp){
					usernameCheck = username.getText();
					passwordCheck = password.getText();//get Text from box
					try {
						WriteFile file = new WriteFile(
								"login.txt", true);// find file
						
						file.writeToFile(usernameCheck);//write username
						file.writeToFile(passwordCheck);//write password
						
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		};
		
		//objects setup
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

	private void prepareGUI() {//sets up GUI
		frame = new JFrame("Quizzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.add(panel);
		frame.setVisible(true);
	}

}
