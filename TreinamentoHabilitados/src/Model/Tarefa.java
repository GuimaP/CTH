package Model;



import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.towel.time.Hour;

import Model.Enums.Prioridade;
@Entity
public class Tarefa implements Serializable {
	@Id
	@GeneratedValue
	private long idTarefa;
	@Transient //Ignoro a persistencia do campo
	private java.time.LocalDateTime dateHoraTarefa;
	@Column(columnDefinition="date")
	private java.util.Date dataCompromisso;
	
	private String horaCompromisso;
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
	/**
	 * Ajustado termporariamente, até ter uma compatibilidade com a nova API de LocalDateTime no Hibernate
	 * @return LocalDateTime
	 */
	public java.time.LocalDateTime getDataTarefa() {
		dateHoraTarefa.withYear(dataCompromisso.getYear());
		dateHoraTarefa.withMonth(dataCompromisso.getMonth());
		dateHoraTarefa.withDayOfMonth(dataCompromisso.getDate());
		String[] vtrTime = horaCompromisso.split(":");
		dateHoraTarefa.withHour(Integer.parseInt(vtrTime[0]));
		dateHoraTarefa.withMinute(Integer.parseInt(vtrTime[1]));
		System.out.println(horaCompromisso);
		System.out.println(dateHoraTarefa);
		return dateHoraTarefa;
	}
	/**
	 * Ajustado termporariamente, até ter uma compatibilidade com a nova API de LocalDateTime no Hibernate
	 * @param Local Date Time,e depois transforma em Date
	 */
	public void setDataTarefa(java.time.LocalDateTime dataTarefa) {
		this.dateHoraTarefa = dataTarefa;
		int day,month,year,hour,minutes;
		day = dateHoraTarefa.getDayOfMonth();
		month = dateHoraTarefa.getMonthValue();
		year = dateHoraTarefa.getYear();
		hour = dateHoraTarefa.getHour();
		minutes = dateHoraTarefa.getMinute();
		
		
		System.out.println(year+ "/"+month + "/"+ day + "  h -> "+ hour+":"+minutes);
		
		java.util.Date dt = new java.util.Date();
		
		horaCompromisso = hour+":"+minutes;
		System.out.println(horaCompromisso);

		dt.setYear(year-1900); //Gambiarra para pegar o ano certo... posak
		dt.setMonth(month-1); //Gambs pra pegar o mes certo... fazê o que...
		dt.setDate(day);
		

		
		
		System.out.println(dt);
		
		dataCompromisso = dt;
			
	}
	
	public java.util.Date getDateComprimisso(){
		int day,month,year,hour,minutes;
		day = dateHoraTarefa.getDayOfMonth();
		month = dateHoraTarefa.getMonthValue();
		year = dateHoraTarefa.getYear();
		
		java.util.Date dt = new java.util.Date(year, month, day);
		return dt;
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
