package Model;

import javax.xml.crypto.Data;

public class Agendamento {
	
	private int id;
	private Data data;
	private String hora;  // Não sei qual tipo de variavel podemos usar para pegar as horas
	private Aula aulas;
	
	public Aula getAulas() {
		return aulas;
	}
	public void setAulas(Aula aulas) {
		this.aulas = aulas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}

}
