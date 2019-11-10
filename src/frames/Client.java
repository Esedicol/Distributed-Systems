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
	public JButton loginBtn = new JButton("Login");
	public JFrame frame; 

	private Socket socket;
	private DataInputStream inputFromServer;
	private DataOutputStream outputToServer;

	int currentUserIndex = 0;
	boolean connectedToServer = false;
	boolean isLogin = false;
	String serverResponse;
	

	public Client() {
		frame =  new JFrame("MySQL CRUD");
		frame.setBounds(100, 100, 300, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		try {
			// Create a socket to connect to the server
			socket = new Socket("localhost", 8000);

			// Create an input and output stream to the server
			inputFromServer = new DataInputStream(socket.getInputStream());
			outputToServer = new DataOutputStream(socket.getOutputStream());

			connectedToServer = true;
			clientMessage.append(" YAY were connected to the server.");
		}
		catch (IOException ex) {
			clientMessage.append(ex.toString());
		}

		userId = new JTextField();
		userId.setBounds(0, 0, 200, 40);
		frame.getContentPane().add(userId);

		clientMessage.setBounds(0, 300, 300, 500);
		frame.getContentPane().add(clientMessage);

		loginBtn.addActionListener(e -> {
			
			if(userId.getText().equals("")) {
				clientMessage.append("\n Empty Login Input");	
			} else {
				login(userId.getText());
			}

		});
		loginBtn.setBounds(200, 0, 100, 40);
		frame.getContentPane().add(loginBtn);
	}

	// Initialize the contents of the frame //
	public void initialize() {

		clientMessage.setBounds(0, 300, 300, 500);
		frame.getContentPane().add(clientMessage);

		// -------------- TextFields -------------- //
		SID = new JTextField(stdArray.get(0).getSid());
		SID.setBounds(85, 40, 135, 40);
		frame.getContentPane().add(SID);

		firstName = new JTextField(stdArray.get(0).getFname());
		firstName.setBounds(85, 80, 135, 40);
		frame.getContentPane().add(firstName);

		lastName = new JTextField(stdArray.get(0).getSname());
		lastName.setBounds(85, 120, 135, 40);
		frame.getContentPane().add(lastName);

		STD_ID = new JTextField(stdArray.get(0).getStud_id());
		STD_ID.setBounds(85, 160, 135, 40);
		frame.getContentPane().add(STD_ID);


		searchName = new JTextField("Search by last name");
		searchName.setBounds(0, 220, 150, 40);
		frame.getContentPane().add(searchName);


		// -------------- Buttons -------------- //
		JButton prev = new JButton("Prev");
		prev.setBounds(10, 100, 50, 40);
		frame.getContentPane().add(prev);
		prev.addActionListener(e -> prevBtn());

		JButton next = new JButton("Next");
		next.setBounds(235, 100, 50, 40);
		frame.getContentPane().add(next);
		next.addActionListener(e -> nextBtn());

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
						displayStudent(s);
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

	public void displayStudent(Student student) {
		SID.setText(student.getSid());
		firstName.setText(student.getFname());
		lastName.setText(student.getSname());
		STD_ID.setText(student.getStud_id());
	}


	// Method to send login request to server
	public void login(String userID) {
		if(connectedToServer == true) {
			try {
				// if we are connected to the server request to login from server
				outputToServer.writeUTF("login," + userID);
				outputToServer.flush();

				// server response
				serverResponse = inputFromServer.readUTF();
				String[] arr = serverResponse.split(",");
				
				if(arr[0].equals("userFound")) {
					initialize();
					isLogin = true;
					userId.setVisible(false);
					loginBtn.setVisible(false);
					
					clientMessage.append("\n Welcome back " + arr[1]);
				} else {
					clientMessage.append("\n Login failed. Please try again.");
				}
				


			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	
	public void nextBtn() {
		if(isLogin) {
			try {
				outputToServer.writeUTF("next," + currentUserIndex + "," + stdArray.size());
				outputToServer.flush();
				
				serverResponse = inputFromServer.readUTF();
				String[] arr = serverResponse.split(",");
				
				if(arr[0].equals("nxt")) {
					currentUserIndex = Integer.parseInt(arr[1]);
					displayStudent(stdArray.get(currentUserIndex));
					clientMessage.append("\n Next Button Pressed");
				} else if(arr[0].equals("nxtLast")) {
					currentUserIndex = Integer.parseInt(arr[1]);
					displayStudent(stdArray.get(currentUserIndex));
					clientMessage.append("\n Last index reached [Starting from Index 1]");
				} 
			} catch(IOException o) {
				o.printStackTrace();
			}
		}
	}
	
	
	
	public void prevBtn() {
		if(isLogin) {
			try {
				outputToServer.writeUTF("prev," + currentUserIndex + "," + stdArray.size());
				outputToServer.flush();
				
				serverResponse = inputFromServer.readUTF();
				String[] arr = serverResponse.split(",");
				
				if(arr[0].equals("prev")) {
					currentUserIndex = Integer.parseInt(arr[1]);
					displayStudent(stdArray.get(currentUserIndex));
					clientMessage.append("\n Previous Button Pressed");
				} else if(arr[0].equals("prevFirst")) {
					currentUserIndex = Integer.parseInt(arr[1]);
					displayStudent(stdArray.get(currentUserIndex));
					clientMessage.append("\n First index reached [Starting from Index 5]");
				} 
			} catch(IOException o) {
				o.printStackTrace();
			}
		}
	}
}



