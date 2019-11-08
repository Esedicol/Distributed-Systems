package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.User;
import utils.DBConnector;

@SuppressWarnings("serial")
public class LoginFrame extends JPanel {
	DBConnector db = new DBConnector();
	ClientFrame clientWindow = new ClientFrame();

	public JFrame frame;
	private JTextField userId;


	public LoginFrame() {
		initialize();
	}

	// Initialize the contents of the frame //
	public void initialize() {

		frame =  new JFrame("Login Frame");
		frame.setBounds(100, 100, 300, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		userId = new JTextField();
		userId.setBounds(20, 20, 150, 40);
		frame.getContentPane().add(userId);
		userId.setColumns(10);
		userId.setText("  Enter User ID");

		userId.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				userId.setText("");
			}
		});



		JButton btbLogin = new JButton("LOGIN");
		btbLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = db.searchByID(userId.getText().strip());

				if(login(user)) {
					JOptionPane.showMessageDialog(null,user.getUNAME() + " is now Log In.");
					clientWindow.initialize();
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null,"Login Fail - User not found");
				}
			}
		});
		btbLogin.setBounds(180, 20, 100, 40);
		frame.getContentPane().add(btbLogin);
	}

	public boolean login(User user) {
		boolean result;
		if(user != null) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

}




