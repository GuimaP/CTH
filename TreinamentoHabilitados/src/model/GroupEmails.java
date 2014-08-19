package model;

import java.util.List;

public class GroupEmails {
	private String nome;
	private List<MensagemEmail> lsEmails;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<MensagemEmail> getLsEmails() {
		return lsEmails;
	}
	public void setLsEmails(List<MensagemEmail> lsEmails) {
		this.lsEmails = lsEmails;
	}
	
	
}
