package model.repository;

import java.sql.SQLException;
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

	public Funcionario findInstrutorSingle(String cpf) throws SQLException {
		// Session session =
		// ConnectionFactoryConfig.getSession();//.getCurrentSession();
		EntityManager em = ConnectionFactoryRepository.getManager();
		Funcionario f = null;
			em.getTransaction().begin();
			f = (Funcionario) em.createQuery("from Funcionario where cpf = :cpf").setParameter("cpf", cpf).getSingleResult();
			em.getTransaction().commit();
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
	
	public void deletarInstrutor(Funcionario func) throws Exception{
		EntityManager em = ConnectionFactoryRepository.getManager();
		try{
		em.getTransaction().begin();
		em.remove(func);
		em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			throw new Exception(e);
		}
	}

	public List<Funcionario> getAllInstrutor() throws Exception {
		try {
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
