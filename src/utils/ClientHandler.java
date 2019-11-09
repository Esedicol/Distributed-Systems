package utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import frames.ServerFrame;

public class ClientHandler extends Thread{

	private Socket client;
	private DataInputStream inputFromClient;
	private DataOutputStream outputToClient;
	private InetAddress addr;

	DBConnector conn = new DBConnector();
	ServerFrame sr;


	// So for every thread created we have record of the socket itself, the input and output and its address //
	public ClientHandler(Socket socket) throws IOException{
		this.client = socket;
		this.inputFromClient = new DataInputStream(client.getInputStream());
		this.outputToClient = new DataOutputStream(client.getOutputStream());
		this.addr = socket.getInetAddress();
	}

	public void run(){
		while(true) {
			try {
				
				while(true) {
					String input = inputFromClient.readUTF();
					String [] arr = input.split(",");
					
					if(arr[0] == "newClient") {
						
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}


