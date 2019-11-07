package frames;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utils.DBConnector;

@SuppressWarnings("serial")
public class ServerFrame extends JPanel {
	DBConnector db = new DBConnector();

	Connection conn;
	Statement st;
	ResultSet rs;
	
	private JFrame frame;
	private JTextField txtSsn;
	
	public ServerFrame() {
		initialize();
	}
	
	// Launch the application //
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerFrame window = new ServerFrame();

					window = new ServerFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Initialize the contents of the frame //
	public void initialize() {

		frame =  new JFrame("Server Frame");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtSsn = new JTextField();
		txtSsn.setBounds(120, 40, 130, 40);
		frame.getContentPane().add(txtSsn);
		txtSsn.setColumns(10);
	}
	

}