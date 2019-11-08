package models;


class Student {

    private int sid, stud_id;
    private String fname, sname;
    
	public Student(int sid, int stud_id, String fname, String sname) {
		this.sid = sid;
		this.stud_id = stud_id;
		this.fname = fname;
		this.sname = sname;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getStud_id() {
		return stud_id;
	}

	public void setStud_id(int stud_id) {
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