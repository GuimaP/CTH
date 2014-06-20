    package modelo;

import java.util.Date;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity

public class Funcionario {
	
	private String nome;
	private Date data;
	private String cnh;
	private String validadeCnh;
	private String primeiraCnh;
	private String rg;
	@Id
	private String cpf;
	private String telefone;
	private String celular;
	private String status;
        
	@OneToOne
        @JoinColumn(name = "placa")//Digo que é uma chave estrangeira ...
	private Carro tbCarroPlacaCarro;
	
	public Carro getTbCarroPlacaCarro() {
		return tbCarroPlacaCarro;
	}
	public void setTbCarroPlacaCarro(Carro tbCarroPlacaCarro) {
		this.tbCarroPlacaCarro = tbCarroPlacaCarro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public String getValidadeCnh() {
		return validadeCnh;
	}
	public void setValidadeCnh(String validadeCnh) {
		this.validadeCnh = validadeCnh;
	}
	public String getPrimeiraCnh() {
		return primeiraCnh;
	}
	public void setPrimeiraCnh(String primeiraCnh) {
		this.primeiraCnh = primeiraCnh;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return (nome);
	}
	

}
