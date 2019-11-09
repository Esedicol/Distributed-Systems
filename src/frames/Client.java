package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import models.Student;
import models.User;
import utils.DBConnector;

@SuppressWarnings("serial")
public class Client extends JPanel {
	DBConnector db = new DBConnector();
	ArrayList<Student> stdArray = db.getStudentsList();

	public JTextArea clientMessage = new JTextArea();
	public JTextField SID, firstName, lastName, STD_ID, searchName, userId;
	public JFrame frame; 

	private Socket socket;
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	
	int currentUser = 0;

	public Client() {
		frame =  new JFrame("MySQL CRUD");
		frame.setBounds(100, 100, 300, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		authenticate();
		
	}

	// Initialize the contents of the frame //
	public void initialize() {
		
		clientMessage.setBounds(0, 300, 300, 200);
		frame.getContentPane().add(clientMessage);
		
		// -------------- TextFields -------------- //
		SID = new JTextField(" SID");
		SID.setBounds(80, 40, 135, 40);
		frame.getContentPane().add(SID);
		SID.setText(Integer.toString(stdArray.get(currentUser).getSid()));

		firstName = new JTextField(" First Name");
		firstName.setBounds(80, 80, 135, 40);
		frame.getContentPane().add(firstName);
		firstName.setText(stdArray.get(currentUser).getFname());

		lastName = new JTextField(" Last Name");
		lastName.setBounds(80, 120, 135, 40);
		frame.getContentPane().add(lastName);
		lastName.setText(stdArray.get(currentUser).getSname());

		STD_ID = new JTextField("Student ID");
		STD_ID.setBounds(80, 160, 135, 40);
		frame.getContentPane().add(STD_ID);
		STD_ID.setText(Integer.toString(stdArray.get(currentUser).getStud_id()));

		searchName = new JTextField("SNAME");
		searchName.setBounds(0, 220, 150, 40);
		frame.getContentPane().add(searchName);
		

		// -------------- Buttons -------------- //
		JButton prev = new JButton("Prev");
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		prev.setBounds(10, 100, 50, 40);
		frame.getContentPane().add(prev);

		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		next.setBounds(225, 100, 50, 40);
		frame.getContentPane().add(next);

		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		search.setBounds(150, 220, 150, 40);
		frame.getContentPane().add(search);

		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		clear.setBounds(0, 260, 150, 40);
		frame.getContentPane().add(clear);

		JButton logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		logout.setBounds(150, 260, 150, 40);
		frame.getContentPane().add(logout);
	}
	
	public void authenticate() {
		userId = new JTextField("  Enter User ID");
		userId.setBounds(0, 0, 200, 40);
		frame.getContentPane().add(userId);
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = db.searchByID(userId.getText().strip());
				if(!userId.getText().isEmpty()) {
					if(userIsFound(user)) {
						JOptionPane.showMessageDialog(null,user.getUNAME() + " is now Log In.");
						userId.setVisible(false);
						login.setVisible(false);
						initialize();
					} else {
						JOptionPane.showMessageDialog(null,"Login Failed - User not found or Empty Input");
					}
				} else {

				}

			}
		});
		login.setBounds(200, 0, 100, 40);
		frame.getContentPane().add(login);
	}
	
	public boolean userIsFound(User user) {
		boolean result;
		if(user != null) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	
	
	
	
	
}
