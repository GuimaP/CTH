package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.Aula;

import model.Cliente;
import model.Pacote;
import model.Pesquisa;
import model.Servico;

import model.repository.RepositoryAula;
import model.repository.RepositoryCliente;
import model.repository.RepositoryPacote;

import model.repository.RepositoryCliente;

import model.repository.RepositoryPesquisa;
import model.repository.RepositoryServico;

public class ControllerFormCliente {
	private Cliente c;
	private Servico s;
	private Pesquisa p;

	private Pacote pacote;
	private Aula a;

	private RepositoryServico repoS;
	private RepositoryCliente repC;
	private RepositoryPesquisa repPes;
	private RepositoryServico repoSer;
	private RepositoryPacote repoPac;
	private RepositoryAula repoA;
	
	public void adicionarCliente(Cliente c) throws Exception {
		try {
			this.c = c;
			repC = new RepositoryCliente();
			repC.adicionar(c);

		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<Cliente> buscarCliente() throws Exception {
		try {
			repC = new RepositoryCliente();
			return repC.buscarTodos();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void adicionarPesquisa(Pesquisa p) throws Exception {
		try {
			this.p = p;
			repPes = new RepositoryPesquisa();
			repPes.adicionar(p);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<Pesquisa> buscarPesquisa() throws Exception {
		try {
			repPes = new RepositoryPesquisa();
			return repPes.buscarTodos();
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	public List<Servico> buscarServico() throws Exception {
		try {
			repoSer = new RepositoryServico();
			return repoSer.buscarServico();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void adicionarPacote(Pacote pacote) throws Exception {
		try {
			this.pacote = pacote;
			repoPac = new RepositoryPacote();
			repoPac.adicionar(pacote);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<Pacote> buscarPacote() throws Exception {
		try {
			repoPac = new RepositoryPacote();
			return repoPac.buscarTodos();

		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	public void adicionarAula(Aula a)throws Exception{
		try {
			this.a = a;
			repoA = new RepositoryAula();
			repoA.adicionar(a);
		} catch (Exception e) {
			throw new Exception(e);
		}




	}
}
