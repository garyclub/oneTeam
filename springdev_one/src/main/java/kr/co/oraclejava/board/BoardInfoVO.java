package kr.co.oraclejava.board;

import java.io.Serializable;

public class BoardInfoVO implements Serializable{
	private int bno;
	private String bname;
	private String user_id;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "BoardInfoVO [bno=" + bno + ", bname=" + bname + ", user_id="
				+ user_id + "]";
	}
}
