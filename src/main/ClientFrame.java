package main;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utils.DBConnector;

@SuppressWarnings("serial")
public class ClientFrame extends JPanel {
	DBConnector db = new DBConnector();

	Connection conn;
	Statement st;
	ResultSet rs;
	
	private JFrame frame;
	private JTextField txtSsn;
	
	public ClientFrame() {
		initialize();
	}
	
	// Launch the application //
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame();

					window = new ClientFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Initialize the contents of the frame //
	public void initialize() {

		frame =  new JFrame("Client Frame");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtSsn = new JTextField();
		txtSsn.setBounds(120, 40, 130, 40);
		frame.getContentPane().add(txtSsn);
		txtSsn.setColumns(10);
	}
	

}