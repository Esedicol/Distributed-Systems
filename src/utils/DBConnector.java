package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

import models.User;

/*
 * @author Emmanuel Sedicol
 */

public class DBConnector {
	
	boolean result;
	
	public DBConnector() {
	}

	// *********** Connection Method *********** //
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", "esedicol");
		connectionProps.put("password", "krlafe8IM");

		conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/BSCY4",connectionProps);
		return conn;
	}
	
	
	public User searchByID (String id) {
		User user = null;
		try {
			Connection conn = getConnection();

			// MySQL Query //
			String query = "SELECT * FROM users WHERE UID = '" + id + "';";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				user = new User(rs.getInt("UID"), rs.getString("UNAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "SQL Query Failed :)");
		}	
		return user;
	}
	

}
