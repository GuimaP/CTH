package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_login")
public class Login implements Serializable{

	@Id
	private String usuario= "";
	
	@Column(nullable=false)
	private String senha;

	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
