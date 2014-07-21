package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.mail.Address;

public class MensagemEmail implements Serializable{
	/**
	 * 
	 */
	//
	private static final long serialVersionUID = 2L;
	private String from;
	private String to;
	private String subject;
	private String texto="";
	private String replyTo;
	public String getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
	private boolean isUnread;
	private Date dataRecebida;
	
	public Date getDataRecebida() {
		return dataRecebida;
	}
	public void setDataRecebida(Date dataRecebida) {
		this.dataRecebida = dataRecebida;
	}
	public boolean isUnread() {
		return isUnread;
	}
	public void setUnread(boolean isUnread) {
		this.isUnread = isUnread;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}
