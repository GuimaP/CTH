package model.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.EntityManager;

import model.Funcionario;

public class RepositoryFuncionario extends Repository<Funcionario> {

	public void addFuncionario(Funcionario f) throws Exception {
		EntityManager em = ConnectionFactoryRepository.getManager();
		try {


			em.getTransaction().begin();
			em.persist(f);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		}

	}

	public ObservableList<Funcionario> buscaTodosFuncionarios()	throws Exception {
		ObservableList<Funcionario> listFun = FXCollections
				.observableArrayList();
		List<Funcionario> listListFun = new ArrayList<Funcionario>();
		EntityManager em = ConnectionFactoryRepository.getManager();
		
		try {
			em.getTransaction().begin();
			listListFun = em.createQuery("from Funcionario").getResultList();
			for (Funcionario f : listListFun) {
				listFun.add(f);
			}
			em.getTransaction().commit();
			return listFun;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		}

	}

	public void deleteFuncionario(Funcionario func) throws Exception {
		EntityManager em = ConnectionFactoryRepository.getManager();
		try{
			
			em.getTransaction().begin();
			em.remove(func);
			em.getTransaction().commit();
		}catch (Exception e){
			if(em != null & em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			throw new Exception("Falha na conexão com a base de dados"); 
		}
		
	}

}
