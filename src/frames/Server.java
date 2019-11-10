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

import utils.DBConnector;
import utils.ClientHandler;

public class Server extends JPanel {

	public JTextArea message = new JTextArea();
	public JFrame frame; 

	DBConnector db = new DBConnector();


	public Server() throws SQLException {

		frame =  new JFrame("Server Frame");
		frame.setBounds(300, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		message.setBounds(0, 0, 500, 430);
		frame.getContentPane().add(message);

		JButton exit = new JButton("EXIT");
		exit.setBounds(0, 430, 250, 50);
		frame.getContentPane().add(exit);
		// Non zero value to exit says abnormal termination of JVM
		exit.addActionListener(e -> System.exit(1)); 

		JButton clear = new JButton("CLEAR");
		clear.setBounds(250, 430, 250, 50);
		frame.getContentPane().add(clear);
		clear.addActionListener(e -> message.setText(""));
		frame.setVisible(true);

		connect();

		try {
			// Create a server socket on port 8000
			ServerSocket serverSocket = new ServerSocket(8000);
			// Display in text area that server started successfully
			message.append("\n Server started at " + new Date() + '\n');

			// Waiting for client to connect
			message.append("\n [SERVER] Waiting for Client to connect ... ");
			while(true){
				Socket client = serverSocket.accept();
				message.append("\n [SERVER] New Client Connected at: " + new Date() + " ["  +  client.getInetAddress() + "]");
				
				// When a new Client connects create a thread of that socket //
				ClientHandler cl = new ClientHandler(client, message);
				
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




