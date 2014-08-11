package controller;

import java.sql.SQLException;
import java.util.List;

import model.Servico;
import model.repository.RepositoryServico;

public class ControllerServico {
	private Servico s;
	private  RepositoryServico repoS;
	
<<<<<<< HEAD
public void adicionarServico(Servico s)throws Exception {
		try {
			this.s = s;
			repoS = new RepositoryServico();
			repoS.adicionar(s);
			
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}
	
	public List<Servico> buscarServico ()throws Exception{
		try {
			repoS = new RepositoryServico();
			return repoS.buscarServico();
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		
		
=======
public void adicionarServico(Servico s)throws SQLException {
		
		this.s = s;
		repoS = new RepositoryServico();
		repoS.adicionar(s);
		
	}
	
	public List<Servico> buscarServico (){
		repoS = new RepositoryServico();
		return repoS.buscbarServico();
>>>>>>> 7d65b131852601e51864f3810890bf9d83e08edc
	}
}
