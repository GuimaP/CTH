package controller;

import java.sql.SQLException;
import java.util.List;

import model.Servico;
import model.repository.RepositoryServico;

public class ControllerServico {
	private Servico s;
	private RepositoryServico repoS;

	public void adicionarServico(Servico s) throws Exception {
		try {
			this.s = s;
			repoS = new RepositoryServico();
			repoS.adicionar(s);

		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	public List<Servico> buscarServico() throws Exception {
		try {
			repoS = new RepositoryServico();
			return repoS.buscarServico();
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

}
