package model.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Pacote;

public class RepositoryPacote extends Repository<Pacote> {

	public void adicionar(Pacote p) throws Exception {
		EntityManager em = ConnectionFactoryRepository.getManager();
	
		try {
			
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		}
	}

	public List<Pacote> buscarTodos() throws Exception {

		try {

			EntityManager sessiom = ConnectionFactoryRepository.getManager();
			
			return sessiom.createQuery("from Pacote").getResultList();
		} catch (Exception e) {
			throw new Exception(e);
		}

	}
}
