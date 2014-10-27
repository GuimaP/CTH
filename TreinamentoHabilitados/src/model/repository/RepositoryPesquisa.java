package model.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Pacote;
import model.Pesquisa;

public class RepositoryPesquisa extends Repository<Pesquisa> {

	public void adicionar(Pesquisa p) throws Exception {
		try {
			EntityManager session = ConnectionFactoryRepository.getManager();
					
			session.getTransaction().begin();
			session.persist(p);
			session.getTransaction().commit();
		
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	public List<Pesquisa> buscarTodos() throws Exception {

		try {
			EntityManager session = ConnectionFactoryRepository.getManager();
			
			return session.createQuery("from Pesquisa").getResultList();
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

}
