package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.crypto.Data;

@Entity
@Table(name = "tb_aula")

public class Aula {
	
	@Id
	@GeneratedValue
	private int id;
	private String descAulas;
	private Data data;
	private Data hora;
	
	@ManyToOne
	@JoinColumn
		
			private Pacote descPacote;
	
	
	public Data getHora() {
		return hora;
	}
	public void setHora(Data hora) {
		this.hora = hora;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescAulas() {
		return descAulas;
	}
	public void setDescAulas(String descAulas) {
		this.descAulas = descAulas;
	}

}
