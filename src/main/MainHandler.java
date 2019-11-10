package main;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

import frames.Client;
import frames.Server;

public class MainHandler extends JFrame {
	
	public MainHandler() {
		setTitle("Thread Frame");
		setSize(200, 200);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	
		JButton newClient = new JButton("New Client");
		newClient.setBounds(0, 0, 200, 180);
		add(newClient);
		newClient.addActionListener(e -> new Client());
	}
	
	public static void main(String[] args) throws SQLException {
		new MainHandler();
		new Server();
	}
	
}
