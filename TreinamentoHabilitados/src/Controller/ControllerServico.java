package controller;

import java.sql.SQLException;
import java.util.List;

import model.Servico;
import model.repository.RepositoryServico;

public class ControllerServico {
	private Servico s;
<<<<<<< HEAD
	private  RepositoryServico repoS;
	
public void adicionarServico(Servico s)throws Exception {
=======
	private RepositoryServico repoS;

	public void adicionarServico(Servico s) throws Exception {
>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
		try {
			this.s = s;
			repoS = new RepositoryServico();
			repoS.adicionar(s);
<<<<<<< HEAD
			
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}
	
	public List<Servico> buscarServico ()throws Exception{
=======

		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	public List<Servico> buscarServico() throws Exception {
>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
		try {
			repoS = new RepositoryServico();
			return repoS.buscarServico();
		} catch (Exception e) {
			throw new Exception(e);
		}
<<<<<<< HEAD
		
		
		
	}
=======

	}

>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
}
