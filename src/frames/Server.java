package frames;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import utils.ClientHandler;
import utils.DBConnector;

public class Server extends JPanel {
	DBConnector db = new DBConnector();
	public JTextArea message = new JTextArea();
	
	public JFrame frame; 

	public Server() {
		frame =  new JFrame("MySQL CRUD");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		message.setBounds(0, 0, 500, 430);
		frame.getContentPane().add(message);
		
		JButton exit = new JButton("EXIT");
		exit.setBounds(0, 430, 500, 50);
		frame.getContentPane().add(exit);

		connect();

		try {
			// Create a server socket
			ServerSocket serverSocket = new ServerSocket(8000);
			message.append("\n Server started at " + new Date() + '\n');
			
//			// Create threads for incoming sockets
			while(true){
				Socket client = serverSocket.accept();
				message.append("\n New Client Connected at: " + new Date() + " [" + client.getInetAddress().getHostAddress() + "]");
				
				// When a new Client connects create a thread of that socket //
				ClientHandler cl = new ClientHandler(client);
				cl.start();;
			}			
		} catch (IOException e) {
            e.printStackTrace();
		} 
	}


	public boolean connect() {
		try {
			db.getConnection();
			message.append(" Successfully Connected to Database at: " + new Date());
			return true;
		} catch (SQLException e) {
			message.append("Connection to Database Failed");
			e.printStackTrace();
		}
		return false;
	}
}




