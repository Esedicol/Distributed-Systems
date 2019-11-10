package models;

public class User {
	
	private String UID;
	private String UNAME;
	
	
	public User(String uID, String uNAME) {
		UID = uID;
		UNAME = uNAME;
	}


	public String getUID() {
		return UID;
	}


	public void setUID(String uID) {
		UID = uID;
	}


	public String getUNAME() {
		return UNAME;
	}


	public void setUNAME(String uNAME) {
		UNAME = uNAME;
	}
	
}
