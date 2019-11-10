package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

import models.Student;
import models.User;

/*
 * @author Emmanuel Sedicol
 */

public class DBConnector {

	// *********** Connection Method *********** //
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", "esedicol");
		connectionProps.put("password", "krlafe8IM");

		conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/BSCY4",connectionProps);
		return conn;
	}

	// Search User by it UID //
	public String searchByID (String id) {
		User user;
		String name = "";
		try {
			Connection conn = getConnection();

			// MySQL Query //
			String query = "SELECT * FROM users WHERE UID = '" + id + "';";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				user = new User(rs.getString("UID"), rs.getString("UNAME"));
				name = user.getUNAME();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "SQL Query Failed :)");
		}	
		return name;
	}


	// Get all Students in the database and store in an Array List //
	public ArrayList<Student> getStudentsList() {
		ArrayList<Student> studentList = new ArrayList<Student>();

		try {
			Connection connection = getConnection();
			Statement st;
			ResultSet rs;
			Student students;

			String query = "SELECT * FROM  `students` ";

			st = connection.createStatement();
			rs = st.executeQuery(query);

			while(rs.next())
			{
				students = new Student(rs.getString("SID"),rs.getString("STUD_ID"),  rs.getString("FNAME"),rs.getString("SNAME"));
				studentList.add(students);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}
	
	public ArrayList<String> stdList() {
		ArrayList<Student> std = getStudentsList();
		ArrayList<String> stdList = new ArrayList<String>();
		
		for(int i = 0; i < std.size(); i++) {
			Student s = std.get(i);
			String SID = s.getSid();
			String STDID = s.getStud_id();
			String fname = s.getFname();
			String lname = s.getSname();
			
			String combine = SID + "," + STDID + "," + fname + "," + lname;
			stdList.add(combine);
		}
		return stdList;
	}


    public ResultSet returnRecord(String id) throws SQLException{
        Statement stmt = getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String sqlGet = "SELECT * FROM users WHERE UID ='" + id + "'";
        stmt.executeQuery(sqlGet);
        ResultSet rs = stmt.getResultSet();
        return rs;
    }


	public Student getStudent(String sname) {
		Student students = null;
		try {
			Connection connection = getConnection();
			Statement st;
			ResultSet rs;

			String query = "SELECT * FROM students WHERE SNAME ='" + sname + "'";

			st = connection.createStatement();
			rs = st.executeQuery(query);

			while(rs.next())
			{
				students = new Student(rs.getString("SID"),rs.getString("STUD_ID"),  rs.getString("FNAME"),rs.getString("SNAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	
	public static void main(String[] args) {
		DBConnector dc = new DBConnector();
		ArrayList<String> stdList = dc.stdList();
		System.out.println(stdList);
	}
}



