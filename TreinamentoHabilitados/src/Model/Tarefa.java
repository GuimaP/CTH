package Model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import Model.Enums.Prioridade;
@Entity
public class Tarefa implements Serializable {
	@Id
	@GeneratedValue
	private long idTarefa;
	@Column (columnDefinition="dateTime")
	private java.time.LocalDateTime dateHoraTarefa;
	private String descricao;
	private Prioridade prioridade;
	
	public long getIdTarefa() {
		return idTarefa;
	}
	public void setIdTarefa(long idTarefa) {
		this.idTarefa = idTarefa;
	}
	public java.time.LocalDateTime getDataTarefa() {
		return dateHoraTarefa;
	}
	public void setDataTarefa(java.time.LocalDateTime dataTarefa) {
		this.dateHoraTarefa = dataTarefa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Prioridade getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}
	
	
	
}
