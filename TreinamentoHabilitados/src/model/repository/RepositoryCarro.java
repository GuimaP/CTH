/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import model.Carro;

/**
 *
 * @author Guima
 */

public class RepositoryCarro {

	public void adicionar(Carro carro) throws Exception {
		try {
//			Session session = ConnectionFactoryConfig.openManger()
			EntityManager session = ConnectionFactoryRepository.getManager();
			session.getTransaction().begin();
			session.persist(carro);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	public List<Carro> buscarTodos() throws Exception {
		try {
			List<Carro> lista = null;
//			Session session = ConnectionFactoryConfig.openManger()
//					.openSession();// getCurrentSession();
			EntityManager session = ConnectionFactoryRepository.getManager();
//			session.createCriteria(Carro.class);
//			Criteria c = session.createCriteria(Carro.class);
			lista = session.createQuery("from Carro").getResultList();

//			session.close();
			return lista;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}
	
	public ObservableList<Carro> buscaCarroObservable() throws Exception{
		try {
			ObservableList<Carro> listCarro = FXCollections.observableArrayList();
		
			List<Carro> list = new ArrayList<Carro>();
			EntityManager session = ConnectionFactoryRepository.getManager();
			list =  session.createQuery("from Carro").getResultList();
			for(Carro c : list){
				listCarro.add(c);
			}
			return listCarro;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public ObservableList<Carro> buscaCarrosDisponiveis() throws Exception{
		try {
			ObservableList<Carro> listCarro = FXCollections.observableArrayList();
		
			List<Carro> list = new ArrayList<Carro>();
			EntityManager session = ConnectionFactoryRepository.getManager();
			  Query query = session.createQuery("from Carro");
			  list = query.getResultList();
			for(Carro c : list){
				if(!c.isOcupado()){
					listCarro.add(c);
				}
			}
			return listCarro;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void deleteCarro(Carro carro) throws Exception {
		EntityManager em = ConnectionFactoryRepository.getManager();
		try{
			em.getTransaction().begin();
			em.remove(carro);
			em.getTransaction().commit();
		}catch (Exception e){
			if(em.getTransaction() != null && em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			throw new Exception(e);
		}
		
	}
	
	public void atualizar(Carro obj) {
//		Session em = ConnectionFactoryConfig.getSession();// .getCurrentSession();
		EntityManager em = null;
		em = ConnectionFactoryRepository.getManager();
		try{
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
		}catch(Exception er) {
			try{
				em.getTransaction().rollback();
			}catch(Exception er1){
				//Transação não está ativa
				er1.printStackTrace();
			}
			er.printStackTrace();
		}
		

	}

}
