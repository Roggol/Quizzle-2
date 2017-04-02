package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
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
				if (event.getSource() == start) {
					//if the start button is pressed
					usernameCheck = username.getText();
					passwordCheck = password.getText();
					//gets string from text fields
					boolean found = false; 
					//sets up boolean to check if the username is contained within the file
					try {
						ReadFile file = new ReadFile(
								"login.txt");//reads file
						String[] arrayLines = file.OpenFile();
						//array of lines from file
						String[] configString = new String[2];
						// new string array of length 2
				
						for (int i = 0; i < arrayLines.length; i++) {
							// for loop for amount of items in array
							configString = arrayLines[i].split(",");
							if (configString[0].contains(usernameCheck)&&configString[1].contains(passwordCheck)) {
								//checks to see if the username and password match
								found = true;//username found	
							}
						}if(found == false){
							JOptionPane.showMessageDialog(null, "Username or password does not match" );
						}else{
							// if the username and password are matched
							new QuizChoose(usernameCheck);
							//launch the quiz chooser window
							frame.setVisible(false);
							frame.dispose();
							//remove frame
						}
							
						

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if (event.getSource() == signUp){
					boolean found = false;
					usernameCheck = username.getText();
					passwordCheck = password.getText();
					//get Text from box
					try {
						ReadFile file = new ReadFile(
								"login.txt");
						//reads file
						String[] arrayLines = file.OpenFile();
						//array of lines from file
						String[] configString = new String[2];
						// new string array of length 2

						WriteFile writer = new WriteFile("login.txt",false);
						//writer that deletes all previous things inside file
						WriteFile writer1 = new WriteFile("login.txt",true);
						//writer that appends the file
						writer.writeToFile("Username,Password");
						//set file title for comma separated format
					
						for (int i = 0; i < arrayLines.length; i++) {
							// for loop for amount of items in array
							configString = arrayLines[i].split(",");
							if (configString[0].contains(usernameCheck)) {
								//checks to see if the username is inside the array
								found = true;
								//username found
							
							}
							writer1.writeToFile(arrayLines[i]);
						}if(found == false){
							writer1.writeToFile(usernameCheck + "," + passwordCheck);
							//adds username and password to file							
						}else{
							JOptionPane.showMessageDialog(null, "Username taken" );
							//tells them that the username already exists
						}
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

	private void prepareGUI() {
		//sets up GUI
		frame = new JFrame("Quizzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.add(panel);
		frame.setVisible(true);
	}

}
