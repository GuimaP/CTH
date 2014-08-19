package model.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Servico;

public class RepositoryServico extends Repository<Servico> {

	public void adicionar(Servico servico) throws Exception {

		try {
			Session session = ConnectionFactoryConfig.openManger()
					.openSession();
			session.beginTransaction();
			session.persist(servico);
			session.getTransaction().commit();
		} catch (Exception e) {

			throw new Exception(e);

		}

	}

	public List<Servico> buscarServico() throws Exception {
		try {
			EntityManager em = ConnectionFactoryRepository.getManager();
			List<Servico> listServico = em.createQuery("from Servico").getResultList();
			return listServico;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

}
