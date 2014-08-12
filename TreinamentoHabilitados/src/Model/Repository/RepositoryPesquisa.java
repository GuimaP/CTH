package model.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Pacote;
import model.Pesquisa;

public class RepositoryPesquisa extends Repository<Pesquisa> {

	public void adicionar(Pesquisa p) throws Exception {
		try {
			Session session = ConnectionFactoryConfig.openManger()
					.openSession();
			session.beginTransaction();
			session.persist(p);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	public List<Pesquisa> buscarTodos() throws Exception {

		try {
			Session session = ConnectionFactoryConfig.openManger()
					.openSession();
			List<Pesquisa> listPesquisa = new ArrayList<Pesquisa>();
			Criteria c = session.createCriteria(Pesquisa.class);
			listPesquisa = c.list();
			return listPesquisa;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

}
