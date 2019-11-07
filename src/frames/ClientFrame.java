package frames;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private JTextField SID, firstName, lastName, STD_ID;
	
	public ClientFrame() {
		initialize();
	}
	
	// Launch the application //
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame();
					LoginFrame loginWindow = new LoginFrame();

					loginWindow.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Initialize the contents of the frame //
	public void initialize() {

		frame =  new JFrame("Client Frame");
		frame.setBounds(100, 100, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		
		SID = new JTextField();
		SID.setBounds(80, 40, 135, 40);
		frame.getContentPane().add(SID);
		SID.setColumns(10);
		SID.setText("  SID");
		
		SID.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                SID.setText("");
            }
        });
		
		firstName = new JTextField();
		firstName.setBounds(80, 80, 135, 40);
		frame.getContentPane().add(firstName);
		firstName.setColumns(10);
		firstName.setText("  First Name");
	
		firstName.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                firstName.setText("");
            }
        });


		lastName = new JTextField();
		lastName.setBounds(80, 120, 135, 40);
		frame.getContentPane().add(lastName);
		lastName.setColumns(10);
		lastName.setText("  Last Name");
		
		lastName.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	lastName.setText("");
            }
        });

		STD_ID = new JTextField();
		STD_ID.setBounds(80, 160, 135, 40);
		frame.getContentPane().add(STD_ID);
		STD_ID.setColumns(10);
		STD_ID.setText("  Student ID");
		
		STD_ID.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	STD_ID.setText("");
            }
        });
	}
	

}