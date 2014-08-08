package controller;

import java.sql.SQLException;
import java.util.List;

import model.Servico;
import model.repository.RepositoryServico;

public class ControllerServico {
	private Servico s;
	private  RepositoryServico repoS;
	
public void adicionarServico(Servico s)throws SQLException {
		
		this.s = s;
		repoS = new RepositoryServico();
		repoS.adicionar(s);
		
	}
	
	public List<Servico> buscarServico (){
		repoS = new RepositoryServico();
		return repoS.buscbarServico();
	}
}
