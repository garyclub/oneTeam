package kr.co.oraclejava.board;

import java.io.Serializable;

public class BoardCommentVO implements Serializable{
	private long cno;
	private int bno;
	private long no;
	private String bcomment;
	private String regdate;
	private String user_id;
	
	public long getCno() {
		return cno;
	}
	public void setCno(long cno) {
		this.cno = cno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getBcomment() {
		return bcomment;
	}
	public void setBcomment(String bcomment) {
		this.bcomment = bcomment;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "BoardCommentVO [cno=" + cno + ", bno=" + bno + ", no=" + no
				+ ", bcomment=" + bcomment + ", regdate=" + regdate
				+ ", user_id=" + user_id + "]";
	}
	
}
