package kr.co.oraclejava.mvc;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class MemoVO implements Serializable{
	
	@NotBlank
	private String title;
	
	@Length(min = 4, max = 12)
	private String pwd;
	
	@Length(min = 2, max = 6)
	private String name;
	
	@Length(min = 10, max = 1000)
	private String content;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "MemoVO [title=" + title + ", pwd=" + pwd + ", name=" + name
				+ ", content=" + content + "]";
	}
	
}
