package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.swing.JOptionPane;

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
		int indexInicial = replyTo.indexOf("<");
		int indexFinal = replyTo.indexOf(">");
		String reply = replyTo.substring(indexInicial, indexFinal);
		reply = reply.replaceAll("<", "");
		System.out.println( reply);
		return reply;
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
	
	
	public List<String> getFromList() {
		List<String>lsFrom = new ArrayList<String>();
		String[] vtr = from.split("<");
		for(String s: vtr){
			s = s.replace('>', ' ');
			lsFrom.add(s);
			System.out.println(s);;
		}
		return lsFrom;
	}
	
	public String getFrom(){
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String[] getTo() {
		to.replaceAll(";", "|");
		String[] vtrS = to.split("|");
		
		
		return vtrS;
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
	
//	@Override
//	public boolean equals(Object obj) {
//		
//		if(obj== null){
//			return false;
//		}else if(obj.getClass() !=this.getClass()){
//			return false;
//		}
//		
//		MensagemEmail e = (MensagemEmail) obj;
//		
//		if(e.dataRecebida == dataRecebida && e.getSubject().equals(subject)){
//			return true;
//		}else {
//			return false;
//		}
//		
//	}
	
}
