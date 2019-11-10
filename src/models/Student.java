package models;


public class Student {

    private String sid, stud_id, fname, sname;
    
	public Student(String sid, String stud_id, String fname, String sname) {
		this.sid = sid;
		this.stud_id = stud_id;
		this.fname = fname;
		this.sname = sname;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getStud_id() {
		return stud_id;
	}

	public void setStud_id(String stud_id) {
		this.stud_id = stud_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

}