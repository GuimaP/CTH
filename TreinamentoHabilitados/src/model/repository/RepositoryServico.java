package model.repository;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Servico;

public class RepositoryServico extends Repository<Servico> {

	public void adicionar(Servico servico) throws Exception {
		EntityManager session = ConnectionFactoryRepository.getManager();
		try {

			session.getTransaction().begin();
			session.persist(servico);
			session.getTransaction().commit();
		} catch (Exception e) {

			session.getTransaction().rollback();
			throw new Exception(e);

		}

	}

	public List<Servico> buscarServico() throws Exception {
		try {
			EntityManager em = ConnectionFactoryRepository.getManager();
			List<Servico> listServico = em.createQuery("from Servico")
					.getResultList();
			return listServico;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}
	
	public ObservableList<Servico> buscaServicoTeste() throws Exception{
		try {
			ObservableList<Servico> listServico = FXCollections.observableArrayList();
		
			List<Servico> list = new ArrayList<Servico>();
			EntityManager session = ConnectionFactoryRepository.getManager();
			list =  session.createQuery("from Servico").getResultList();
			for(Servico s : list){
				listServico.add(s);
			}
			return listServico;
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		
		
		
		
	}

	public void deleteServico(Servico servico) {
		EntityManager em = ConnectionFactoryRepository.getManager();
		try{
			em.getTransaction().begin();
			em.remove(servico);
			em.getTransaction().commit();
			
		}catch(Exception e){
			try{
				em.getTransaction().rollback();
			}catch (Throwable e1){
				throw e1;
			}
			throw e;
		}
		
	}

}
