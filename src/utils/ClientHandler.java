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

	JTextArea serverMessage;

	DBConnector db = new DBConnector();
	ArrayList<String> stdList = db.stdList();
	User user = null;
	int counter = 0;

	public ClientHandler(Socket socket, JTextArea display) throws IOException, SQLException {
		this.clientIn = new DataInputStream(socket.getInputStream());
		this.clientOut = new DataOutputStream(socket.getOutputStream());
		this.socket = socket;
		this.serverMessage = display;
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

				// If client request is to login
				if(request.equals("login")) {
					String userName = db.searchByID(param);

					serverMessage.append("\n [" + this.address +"] is attempting to Login ....");

					if(userName != "") {
						serverMessage.append("\n " + userName + " successfully logged in.");
						output = "userFound," + userName;

					} else {
						serverMessage.append("\n [SERVER] Sorry user not found");
					}
				} 

				// If client request to display next student
				else if (request.equals("next")) {
					int size = Integer.parseInt(inputArray[2]);
					int currentIndex = Integer.parseInt(param);

					if(Integer.parseInt(param) == size - 1) {
						currentIndex = 0;
						output = "nxtLast," + currentIndex;
						serverMessage.append("\n\n Max index reach [Restarting from Index 1]");
					} else {
						int index = Integer.parseInt(param) + 1;
						output = "nxt," + index;
						serverMessage.append("\n [" + inputArray[3] + "] 'Next' button pressed. [" + index + "]");
					}
				} 

				// If client request to display previous student
				else if (request.equals("prev")) {
					int size = Integer.parseInt(inputArray[2]);
					int currentIndex = Integer.parseInt(param);

					if(Integer.parseInt(param) == 0) {
						currentIndex = size - 1;
						output = "prevFirst," + currentIndex;
						serverMessage.append("\n\n First index reach [Restarting from last index]");
					} else {
						int index = Integer.parseInt(param) - 1;
						output = "prev," + index;
						serverMessage.append("\n [ " + inputArray[3] + "] 'Previous' button pressed. [" + index + "]");
					}
				} 

				// If client wants to clear all fields
				else if(request.equals("clear")) {
					int currentIndex = 0;
					output = "clear," + currentIndex;
					serverMessage.append("\n [" + inputArray[1] + "] 'Clear' button pressed. [Cleared all fields]");
				} 

				// If Client wants to search a student by is last name
				else if(request.equals("search")) {
					String sname = param;
					serverMessage.append("\n [" + inputArray[3] + "] 'Search' button pressed. [Searching for '" + sname + "']");

					int i = db.findIndexByLastName(sname);
					int size = Integer.parseInt(inputArray[2]);

					if( i > size + 1 || i < 0) {
						output = "notFound,null";
						serverMessage.append("\n '" + sname + "' not found.");	
					} else {
						output = "found," + i;
						serverMessage.append("\n Index " + sname + " successfully found.");

					}
				} 

				// if student wants to logout
				else if(request.equals("logout")) {
					serverMessage.append("\n [SERVER] Loggin out " + param);
					Thread.currentThread().interrupt();
				}
				// Send the response to the client
				clientOut.writeUTF(output);
			}catch (IOException ex) {
				System.out.println(ex);
			} 
		} 
	} 


}
