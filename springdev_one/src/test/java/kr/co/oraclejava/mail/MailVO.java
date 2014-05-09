package kr.co.oraclejava.mail;

import java.io.Serializable;

public class MailVO implements Serializable{
	private String smtpHost;
	private String fromAddr;
	private String toAddr;
	private String subject;
	private String mailBody;
	
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public String getFromAddr() {
		return fromAddr;
	}
	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}
	public String getToAddr() {
		return toAddr;
	}
	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	
	@Override
	public String toString() {
		return "MailVO [smtpHost=" + smtpHost + ", fromAddr=" + fromAddr
				+ ", toAddr=" + toAddr + ", subject=" + subject + ", mailBody="
				+ mailBody + "]";
	}
}
