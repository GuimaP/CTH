package Model;



import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import javax.persistence.Transient;



import com.towel.time.Hour;

import Model.Enums.Prioridade;
@Entity
public class Tarefa implements Serializable {
	@Id
	@GeneratedValue
	private long idTarefa;
	//@Transient //Ignoro a persistencia do campo
	@Transient
	private java.time.LocalDateTime dateHoraTarefa;
	@Column(columnDefinition="date")
	private java.util.Date dataCompromisso;
	
	@Column
	private String horaCompromisso;
	@Column(nullable=false)
	private String descricao;
	@Column(nullable=false)
	private Prioridade prioridade;
	
//	public long getIdTarefa() {
//		return idTarefa;
//	}
//	public void setIdTarefa(long idTarefa) {
//		this.idTarefa = idTarefa;
//	}
//	
//	
//	/**
//	 * Ajustado termporariamente, até ter uma compatibilidade com a nova API de LocalDateTime no Hibernate
//	 * @return LocalDateTime
//	 */
//	@Deprecated
//	public java.time.LocalDateTime getDataTarefa() {
//		if(dateHoraTarefa == null){
//			dateHoraTarefa = LocalDateTime.now();
//		}
//		dateHoraTarefa.withYear(dataCompromisso.getYear());
//		dateHoraTarefa.withMonth(dataCompromisso.getMonth());
//		dateHoraTarefa.withDayOfMonth(dataCompromisso.getDate());
//		String[] vtrTime = horaCompromisso.split(":");
//		int hora = Integer.parseInt(vtrTime[0]);
//		int minuto = Integer.parseInt(vtrTime[1]);
//		dateHoraTarefa.withHour(hora);
//		dateHoraTarefa.withMinute(minuto);
//		System.out.println("String> "+ horaCompromisso);
//		System.out.println("LocalDate> "+dateHoraTarefa + "\n " + vtrTime[0] + " : " + vtrTime[1]);
//		System.out.println("Date Util> "+dataCompromisso);
//		return dateHoraTarefa;
//	}
//	/**
//	 * Ajustado termporariamente, até ter uma compatibilidade com a nova API de LocalDateTime no Hibernate
//	 * @param Local Date Time,e depois transforma em Date
//	 */
//	@Deprecated
//	public void setDataTarefa(java.time.LocalDateTime dataTarefa) {
//		this.dateHoraTarefa = dataTarefa;
//		int day,month,year,hour,minutes;
//		day = dateHoraTarefa.getDayOfMonth();
//		month = dateHoraTarefa.getMonthValue();
//		year = dateHoraTarefa.getYear();
//		hour = dateHoraTarefa.getHour();
//		minutes = dateHoraTarefa.getMinute();
//		
//		
//		System.out.println(year+ "/"+month + "/"+ day + "  h -> "+ hour+":"+minutes);
//		
//		java.util.Date dt = new java.util.Date();
//		
//		horaCompromisso = hour+":"+minutes;
//		System.out.println(horaCompromisso);
//
//		dt.setYear(year-1900); //Gambiarra para pegar o ano certo... posak
//		dt.setMonth(month-1); //Gambs pra pegar o mes certo... fazê o que...
//		dt.setDate(day);
//		
//
//		
//		
//		System.out.println(dt);
//		
//		dataCompromisso = dt;
//			
//	}
//	
//	public java.util.Date getDateComprimisso(){
////		int day,month,year,hour,minutes;
////		day = dataCompromisso.getDate();
////		month = dataCompromisso.getMonth();
////		year = dataCompromisso.getYear();
////		
////		java.util.Date dt = new java.util.Date(year, month, day);
////		return dt;
//		
//		return dataCompromisso;
//	}
//	
//	public void setDateHoraTarefa(java.time.LocalDateTime dateHoraTarefa) {
//		this.dateHoraTarefa = dateHoraTarefa;
//	}
//	public void setDataCompromisso(java.util.Date dataCompromisso) {
//		this.dataCompromisso = dataCompromisso;
//	}
//	public String getDescricao() {
//		return descricao;
//	}
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}
//	public Prioridade getPrioridade() {
//		return prioridade;
//	}
//	public void setPrioridade(Prioridade prioridade) {
//		this.prioridade = prioridade;
//	}
	
	@Override
	public String toString() {
		return this.descricao;
	}

	public long getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(long idTarefa) {
		this.idTarefa = idTarefa;
	}

	public java.util.Date getDataCompromisso() {
		return dataCompromisso;
	}

	public void setDataCompromisso(java.util.Date dataCompromisso) {
		horaCompromisso = dataCompromisso.getHours() + ":" + dataCompromisso.getMinutes();
		this.dataCompromisso = dataCompromisso;
	}

	public String getHoraCompromisso() {
		 
		return horaCompromisso;
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
