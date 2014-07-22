package Model;

import java.io.Serializable;
import java.util.Date;

public class MessageSerial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String from,to,subject,replyTo;
	private boolean isUnread;
	private Object content;
	private Date getDateReceive;
	
	public Date getGetDateReceive() {
		return getDateReceive;
	}
	public void setGetDateReceive(Date getDateReceive) {
		this.getDateReceive = getDateReceive;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
	public boolean getIsUnread() {
		return isUnread;
	}
	public void setIsUnread(boolean isUnread) {
		this.isUnread = isUnread;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	
}
