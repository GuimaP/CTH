package Model;

import java.util.Date;
import java.util.List;

import javax.mail.Address;

public class MensagemEmail {
	private List<Address> from;
	private List<Address> to;
	private String subject;
	private String texto;
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
	
	
	public List<Address> getFrom() {
		return from;
	}
	public void setFrom(List<Address> from) {
		this.from = from;
	}
	public List<Address> getTo() {
		return to;
	}
	public void setTo(List<Address> to) {
		this.to = to;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}