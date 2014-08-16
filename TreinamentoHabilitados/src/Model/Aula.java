package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.crypto.Data;

@Entity
@Table(name = "tb_aula")

public class Aula {
	
	@Id
	@GeneratedValue
	private long idAula;
	private String descAulas;
	private java.util.Date data;
	
	@ManyToOne
	@JoinColumn(name = "cpf")
	private Funcionario instrutor;
	private long idPacote;
	

	
	@ManyToOne
	@JoinColumn(name = "id")
	private Pacote pacote;
	
	
	public long getIdPacote() {
		return idPacote;
	}
	public void setIdPacote(long idPacote) {
		this.idPacote = idPacote;
	}
	public Funcionario getInstrutor() {
		return instrutor;
	}
	public void setInstrutor(Funcionario instrutor) {
		this.instrutor = instrutor;
	}

	public java.util.Date getData() {
		return data;
	}
	public void setData(java.util.Date dataSelecionada) {
		this.data = dataSelecionada;
	}
	public long getId() {
		return idAula;
	}
	public void setId(long id) {
		this.idAula = id;
	}
	public String getDescAulas() {
		return descAulas;
	}
	public void setDescAulas(String descAulas) {
		this.descAulas = descAulas;
	}

}
