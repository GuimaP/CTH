package model.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.Carro;
import model.Instrutor;

/**
 * @author Guima Classe responsavel pela Persistencia da Base de dados
 */

public class RepositoryInstrutor extends Repository<model.Instrutor> {

	public Instrutor findInstrutorSingle(String cpf) throws SQLException {
		// Session session =
		// ConnectionFactoryConfig.getSession();//.getCurrentSession();
		EntityManager em = ConnectionFactoryRepository.getManager();
		Instrutor f = null;
			em.getTransaction().begin();
			f = (Instrutor) em.createQuery("from Instrutor where cpf = :cpf").setParameter("cpf", cpf).getSingleResult();
			em.getTransaction().commit();
			return f;
	}

	@Override
	public void adicionar(Instrutor obj) throws Exception {
		EntityManager em = ConnectionFactoryRepository.getManager();
		try {
			
			em.getTransaction().begin();
			em.persist(obj);
			obj.getTbCarroPlacaCarro().setOcupado(true);
			em.merge(obj.getTbCarroPlacaCarro());
			// Carro c = obj.getTbCarroPlacaCarro();
			//c.setOcupado(true);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		}
	}
	
	public void deletarInstrutor(Instrutor func) throws Exception{
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

	public List<Instrutor> getAllInstrutor() throws Exception {
		try {
			EntityManager em = ConnectionFactoryRepository.getManager();

			return em.createQuery("from Instrutor ").getResultList();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public ObservableList<Instrutor> buscaInstrutorTeste() throws Exception{
		try{
		ObservableList<Instrutor> listInstrutor = FXCollections.observableArrayList();
		List<Instrutor> instrutores = new ArrayList<Instrutor>();
		EntityManager session = ConnectionFactoryRepository.getManager();
		
		instrutores = session.createQuery("from Instrutor").getResultList();
		
		for(Instrutor f : instrutores){
			listInstrutor.add(f);
		}
		
		return listInstrutor;
		
		}catch(Exception e){
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
