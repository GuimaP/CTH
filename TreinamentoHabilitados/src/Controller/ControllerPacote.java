package controller;

import java.util.List;

import model.Pacote;
import model.repository.RepositoryPacote;

public class ControllerPacote {
	private Pacote p;
	private RepositoryPacote repP;
	
	public void adicionar (Pacote p){
		this.p = p;
		repP = new RepositoryPacote();
		repP.adicionar(p);
		
	}
	public List<Pacote> buscarTodos(){
		repP = new RepositoryPacote();
		return repP.buscarTodos();
	}

}
