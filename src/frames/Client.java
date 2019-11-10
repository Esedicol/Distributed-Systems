package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
	private DataInputStream inputFromServer;
	private DataOutputStream outputToServer;

	int currentUserIndex = 0;

	public Client() {
		frame =  new JFrame("MySQL CRUD");
		frame.setBounds(100, 100, 300, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		authenticate();

		try {
			// Create a socket to connect to the server
			Socket socket = new Socket("localhost", 8000);

			// Create an input stream to receive data from the server
			inputFromServer = new DataInputStream(socket.getInputStream());

			// Create an output stream to send data to the server
			outputToServer = new DataOutputStream(socket.getOutputStream());
		}
		catch (IOException ex) {
			clientMessage.append(ex.toString());
		}
	}

	// Initialize the contents of the frame //
	public void initialize() {

		clientMessage.setBounds(0, 300, 300, 500);
		frame.getContentPane().add(clientMessage);

		// -------------- TextFields -------------- //
		SID = new JTextField(" SID");
		SID.setBounds(85, 40, 135, 40);
		frame.getContentPane().add(SID);

		firstName = new JTextField(" First Name");
		firstName.setBounds(85, 80, 135, 40);
		frame.getContentPane().add(firstName);

		lastName = new JTextField(" Last Name");
		lastName.setBounds(85, 120, 135, 40);
		frame.getContentPane().add(lastName);

		STD_ID = new JTextField("Student ID");
		STD_ID.setBounds(85, 160, 135, 40);
		frame.getContentPane().add(STD_ID);


		searchName = new JTextField("Search by last name");
		searchName.setBounds(0, 220, 150, 40);
		frame.getContentPane().add(searchName);


		// -------------- Buttons -------------- //
		JButton prev = new JButton("Prev");
		prev.setBounds(10, 100, 50, 40);
		frame.getContentPane().add(prev);
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentUserIndex == 0) {
					currentUserIndex = stdArray.size() -1;
					displayUser(stdArray.get(currentUserIndex));
				} else {
					currentUserIndex = currentUserIndex - 1;
					displayUser(stdArray.get(currentUserIndex));
				}
			}
		});

		JButton next = new JButton("Next");
		next.setBounds(235, 100, 50, 40);
		frame.getContentPane().add(next);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentUserIndex == stdArray.size() -1) {
					currentUserIndex = 0;
					displayUser(stdArray.get(currentUserIndex));
				} else {
					currentUserIndex = currentUserIndex + 1;
					displayUser(stdArray.get(currentUserIndex));
				}
			}
		});

		JButton search = new JButton("Search");
		search.setBounds(150, 220, 150, 40);
		frame.getContentPane().add(search);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(searchName.getText().isEmpty()) {
					clientMessage.append("\n Empty inputs in seach field.");
					JOptionPane.showMessageDialog(null," Empty");
				} else {
					String sname = searchName.getText().toString().strip();
					Student s = db.getStudent(sname);

					if(s != null) {
						displayUser(s);
						clientMessage.append("\n User " + s.getFname() + " found.");
					} else {
						clientMessage.append("\n User not found.");
						JOptionPane.showMessageDialog(null," User not found.");
					}
				}
			}
		});

		JButton clear = new JButton("Clear");
		clear.setBounds(0, 260, 150, 40);
		frame.getContentPane().add(clear);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SID.setText("");
				firstName.setText("");
				lastName.setText("");
				STD_ID.setText("");

				currentUserIndex = 0;
			}
		});

		JButton logout = new JButton("Logout");
		logout.setBounds(150, 260, 150, 40);
		frame.getContentPane().add(logout);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	public void authenticate() {
		userId = new JTextField("  Enter User ID");
		userId.setBounds(0, 0, 200, 40);
		frame.getContentPane().add(userId);

		clientMessage.setBounds(0, 300, 300, 500);
		frame.getContentPane().add(clientMessage);

		JButton login = new JButton("Login");
		login.addActionListener(e -> login(userId.getText()));
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

	public void displayUser(Student student) {
		SID.setText(Integer.toString(student.getSid()));
		firstName.setText(student.getFname());
		lastName.setText(student.getSname());
		STD_ID.setText(Integer.toString(student.getStud_id()));
	}

	public void login(String id) {
		if(id.equals("")){
			JOptionPane.showMessageDialog(null,"Please Input ID");
		} else {
			try {
				outputToServer.writeUTF("login");
				outputToServer.flush();

				String response = inputFromServer.readUTF();
				clientMessage.append(response);
				
				
			} catch (Exception e) {
                System.out.println(e);
            }
			
		}


	}
}



