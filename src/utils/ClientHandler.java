package utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextArea;

import models.User;

public class ClientHandler extends Thread {

	Socket socket;
	DataInputStream clientIn;
	DataOutputStream clientOut;
	InetAddress address;
	
	JTextArea display;
	
	DBConnector db = new DBConnector();
	ArrayList<String> stdList = db.stdList();
	User user = null;
	int counter = 0;
	


	public ClientHandler(Socket socket, JTextArea display) throws IOException, SQLException {
		this.clientIn = new DataInputStream(socket.getInputStream());
		this.clientOut = new DataOutputStream(socket.getOutputStream());
		this.socket = socket;
		this.display = display;
		this.address = socket.getInetAddress();
	}

	public void run() {

		while (true) {
			try {
				String clientInput = clientIn.readUTF();
				String[] inputArray = clientInput.split(",");
				String request = inputArray[0];
				String param = inputArray[1];
				String output = "";
				
				
				if(request.equals("login")) {
					String userName = db.searchByID(param);
					
					display.append("\n [" + this.address +"] is attempting to Login ....");
					
					if(userName != "") {
						display.append("\n " + userName + " successfully logged in.");
						output = "userFound," + userName;
						
					} else {
						display.append("\n [SERVER] Sorry user not found");
					}
				} else if (request.equals("next")) {
					int size = Integer.parseInt(inputArray[2]);
					int currentIndex = Integer.parseInt(param);
							
					if(Integer.parseInt(param) == size - 1) {
						currentIndex = 0;
						output = "nxtLast," + currentIndex;
						display.append("\n\n Max index reach [Restarting from Index 1]");
					} else {
						int index = Integer.parseInt(param) + 1;
						output = "nxt," + index;
						display.append("\n [SERVER] 'Next' button pressed. [" + index + "]");
					}
				} else if (request.equals("prev")) {
					int size = Integer.parseInt(inputArray[2]);
					int currentIndex = Integer.parseInt(param);
							
					if(Integer.parseInt(param) == 0) {
						currentIndex = size - 1;
						output = "prevFirst," + currentIndex;
						display.append("\n\n First index reach [Restarting from last index]");
					} else {
						int index = Integer.parseInt(param) - 1;
						output = "prev," + index;
						display.append("\n [SERVER] 'Previous' button pressed. [" + index + "]");
					}
				}
				
				
				


				// Send the response to the client
				clientOut.writeUTF(output);
			}catch (IOException ex) {
				System.out.println(ex);
			}
		}
	}
	

}