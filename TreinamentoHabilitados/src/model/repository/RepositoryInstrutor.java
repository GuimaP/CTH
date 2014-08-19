package model.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.Carro;
import model.Funcionario;

/**
 * @author Guima Classe responsavel pela Persistencia da Base de dados
 */

public class RepositoryInstrutor extends Repository<model.Funcionario> {

	public Funcionario findInstrutorSingle(String cpf) {
		// Session session =
		// ConnectionFactoryConfig.getSession();//.getCurrentSession();
		Session session = ConnectionFactoryConfig.openManger().openSession();
		Funcionario f = null;
		if (session != null) {
			session.beginTransaction();
			Criteria filtro = session.createCriteria(Funcionario.class);
			filtro.add(Restrictions.eq("cpf", cpf));
			f = (Funcionario) filtro.uniqueResult();
			session.getTransaction().commit();
			return f;

		}

		return f;
	}

	@Override
	public void adicionar(Funcionario obj) throws Exception {
		EntityManager em = ConnectionFactoryRepository.getManager();
		em.getTransaction().begin();
		try {

			em.persist(obj);
			obj.getTbCarroPlacaCarro().setOcupado(true);
			em.merge(obj.getTbCarroPlacaCarro());
			// Carro c = obj.getTbCarroPlacaCarro();
			// c.setOcupado(true);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		}
	}

	public List<Funcionario> getAllInstrutor() throws Exception {
		try {
			// Session session = ConnectionFactoryConfig.openManger()
			// .openSession();

			/*
			 * if (session != null || !session.isConnected()) { Criteria c =
			 * session.createCriteria(Funcionario.class); ls = c.list(); }
			 */

			EntityManager em = ConnectionFactoryRepository.getManager();

			return em.createQuery("from Funcionario ").getResultList();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * @author Guilherme
	 * @throws java.sql.SQLException
	 * @params Objeto para Persistencia com a Classe
	 * 
	 */

}
