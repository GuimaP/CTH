package modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Cnh {
	
	private String primeiraHabilitacao;
	@Id
	private String registroCnh;
	private String dtValidade;

	public String getPrimeiraHabilitacao() {
		return primeiraHabilitacao;
	}

	public void setPrimeiraHabilitacao(String primeiraHabilitacao) {
		this.primeiraHabilitacao = primeiraHabilitacao;
	}

	public String getRegistroCnh() {
		return registroCnh;
	}

	public void setRegistroCnh(String registroCnh) {
		this.registroCnh = registroCnh;
	}

	public String getDtValidade() {
		return dtValidade;
	}

	public void setDtValidade(String dtValidade) {
		this.dtValidade = dtValidade;
	}

}
