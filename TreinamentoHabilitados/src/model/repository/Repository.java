/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.repository;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Guima
 */
public class Repository<T> {

	public void adicionar(T obj) throws Exception {
		EntityManager em = null;
		try {
			
//			Session session = ConnectionFactoryConfig.openManger()
//					.openSession();
			em = ConnectionFactoryRepository.getManager();
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			if(em != null){
				em.getTransaction().rollback();
				
			}
			throw new Exception(e);
		}
	}

//	public List<T> buscarTodos() throws Exception {
//
//		try {
//			List<T> lista = null;
////			Session em = ConnectionFactoryConfig.getSession();// getCurrentSession();
//			EntityManager em = ConnectionFactoryRepository.getManager();
//			em.getTransaction().begin();
//			em.createQuery();
//			lista = c.list();
//			em.getTransaction().commit();
//
//			em.close();
//			return lista;
//		} catch (Exception e) {
//			throw new Exception();
//		}
//
//	}

	public void atualizar(T obj) {
//		Session em = ConnectionFactoryConfig.getSession();// .getCurrentSession();
		EntityManager em = null;
		try {
			em = ConnectionFactoryRepository.getManager();
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();

		} catch (SQLException e) {
			if(em != null){
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}

	}

}
