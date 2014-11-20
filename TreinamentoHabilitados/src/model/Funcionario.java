package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import model.enums.EnumPermissaoAcessoSistema;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {
	
	@Id
	@GeneratedValue
	private long id;
	private String nome ="";
	private String rg = "";
	private String cpf = "";
	private String telefone = ""; 
	private String celular = "";
	private EnumPermissaoAcessoSistema permissaoAcesso;
	@OneToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="usuario")
	private Login loginUsuario;
	
	public Login getLoginUsuario() {
		return loginUsuario;
	}
	public void setLoginUsuario(Login loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public EnumPermissaoAcessoSistema getPermissaoAcesso() {
		return permissaoAcesso;
	}
	public void setPermissaoAcesso(EnumPermissaoAcessoSistema permissaoAcesso) {
		this.permissaoAcesso = permissaoAcesso;
	}
	public long getId() {
		return id;
	}
}
