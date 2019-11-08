package models;

public class User {
	
	private int UID;
	private String UNAME;
	
	
	public User(int uID, String uNAME) {
		UID = uID;
		UNAME = uNAME;
	}


	public int getUID() {
		return UID;
	}


	public void setUID(int uID) {
		UID = uID;
	}


	public String getUNAME() {
		return UNAME;
	}


	public void setUNAME(String uNAME) {
		UNAME = uNAME;
	}
	
	


}
