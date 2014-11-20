package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_auditoria")
public class Auditoria {
	@Id
	@GeneratedValue
	private long idAuditoria;
	private String modulo;
	private String nomeFuncionario;
	private String operacao;
	private Date dtOperacao;
	
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		operacao = operacao;
	}
	public Date getDtOperacao() {
		return dtOperacao;
	}
	public void setDtOperacao(Date dtOperacao) {
		this.dtOperacao = dtOperacao;
	}

}
