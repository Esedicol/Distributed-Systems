package frames;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import utils.ClientHandler;
import utils.DBConnector;

public class ServerFrame extends JFrame {
	DBConnector db = new DBConnector();
	JTextArea message = new JTextArea();
	JButton exit = new JButton("EXIT");

	public ServerFrame() {
		setTitle("Server Frame");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		message.setBounds(0, 0, 500, 300);
		add(message);
		
		exit.setBounds(0, 300, 500, 50);
		add(exit);

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
			message.append("Successfully Connected to Database at: " + new Date());
			return true;
		} catch (SQLException e) {
			message.append("Connection to Database Failed");
			e.printStackTrace();
		}
		return false;
	}
}

