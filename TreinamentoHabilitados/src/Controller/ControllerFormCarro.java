package controller;

import java.util.List;

import model.Carro;
import model.repository.RepositoryCarro;

public class ControllerFormCarro {
	private Carro c;
	private RepositoryCarro repoC;

	public void adicionar(Carro c) throws Exception {
		try {
			this.c = c;
			repoC = new RepositoryCarro();
			repoC.adicionar(c);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<Carro> buscarCarro() throws Exception {
		try {
			repoC = new RepositoryCarro();
			return repoC.buscarTodos();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
