package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Pacote;
import model.Pesquisa;
import model.Servico;
import model.repository.RepositoryCliente;
import model.repository.RepositoryPesquisa;
import model.repository.RepositoryServico;

public class ControllerFormCliente {
	private Cliente c;
	private Servico s;
	private Pesquisa p;
	private RepositoryServico repoS;
	private RepositoryCliente repC;
	private RepositoryPesquisa repPes;
	
	public void adicionarCliente(Cliente c) throws SQLException {
		this.c = c;
		repC = new RepositoryCliente();
		repC.adicionar(c);
	}

	public List<Cliente> buscarCliente() {
		repC = new RepositoryCliente();
		return repC.buscarTodos();
	}

	public void adicionarPesquisa (Pesquisa p ){
		this.p = p;
		repPes = new RepositoryPesquisa();
		repPes.adicionar(p);
	}
	public List<Pesquisa> buscarPesquisa(){
		repPes = new RepositoryPesquisa();
		return repPes.buscarTodos();
		
	}
}
