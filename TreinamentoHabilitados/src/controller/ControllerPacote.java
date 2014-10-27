package controller;

import java.util.List;

import model.Pacote;
import model.repository.RepositoryPacote;

public class ControllerPacote {
	private Pacote p;
	private RepositoryPacote repP;
	
	public void adicionar (Pacote p) throws Exception{
		try {
			this.p = p;
			repP = new RepositoryPacote();
			repP.adicionar(p);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		
	}
	public List<Pacote> buscarTodos() throws Exception{
		try {
			repP = new RepositoryPacote();
			return repP.buscarTodos();
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}

}
