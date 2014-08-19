package model;

import java.io.Serializable;
import java.util.List;

public class MenssagemResultQuery implements Serializable{

	/**
	 * 
	 */
	private List<String> viewItens;
	private List<MensagemEmail> lsEmails;
	
	public List<String> getViewItens() {
		return viewItens;
	}
	public void setViewItens(List<String> viewItens) {
		this.viewItens = viewItens;
	}
	public List<MensagemEmail> getLsEmails() {
		return lsEmails;
	}
	public void setLsEmails(List<MensagemEmail> lsEmails) {
		this.lsEmails = lsEmails;
	}
}
