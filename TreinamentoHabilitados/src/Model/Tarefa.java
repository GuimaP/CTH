package Model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import Model.Enums.Prioridade;
@Entity
public class Tarefa implements Serializable {
	@Id
	@GeneratedValue
	private long idTarefa;
	@Transient //Ignoro a persistencia do campo
	private java.time.LocalDateTime dateHoraTarefa;
	@Column(columnDefinition="dateTime")
	private java.util.Date dataCompromisso;
	@Column(nullable=false)
	private String descricao;
	@Column(nullable=false)
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
		int day,month,year,hour,minutes;
		day = dateHoraTarefa.getDayOfMonth();
		month = dateHoraTarefa.getMonthValue();
		year = dateHoraTarefa.getYear();
		hour = dateHoraTarefa.getHour();
		minutes = dateHoraTarefa.getMinute();
		
		java.util.Date dt = new java.util.Date(year, month, day, hour, minutes);
		System.out.println(dt);
		dataCompromisso = dt;
			
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
