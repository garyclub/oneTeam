package kr.co.oraclejava.board;

public class Pagination {
	private int bno;
	private long startnum;
	private long endnum;
	
	
	public Pagination() {}

	public Pagination(int bno) {
		setBno(bno);
	}
	public Pagination(int bno, long startnum, long endnum) {
		setBno(bno);
		setStartnum(startnum);
		setEndnum(endnum);
	}
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public long getStartnum() {
		return startnum;
	}
	public void setStartnum(long startnum) {
		this.startnum = startnum;
	}
	public long getEndnum() {
		return endnum;
	}
	public void setEndnum(long endnum) {
		this.endnum = endnum;
	}
	@Override
	public String toString() {
		return "Pagination [bno=" + bno + ", startnum=" + startnum
				+ ", endnum=" + endnum + "]";
	}
}
