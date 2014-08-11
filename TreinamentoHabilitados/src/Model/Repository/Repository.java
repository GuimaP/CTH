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
		try {
			EntityManager em = null;
			Session session = ConnectionFactoryConfig.openManger()
					.openSession();
			session.getTransaction().begin();
			session.persist(obj);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<T> buscarTodos() throws Exception {

		try {
			List<T> lista = null;
			Session session = ConnectionFactoryConfig.getSession();// getCurrentSession();
			if (!session.isConnected()) {
				ConnectionFactoryConfig.generateSession();
			}
			session.beginTransaction();
			session.createCriteria(this.getClass());
			Criteria c = session.createCriteria(this.getClass());
			lista = c.list();
			session.getTransaction().commit();

			session.close();
			return lista;
		} catch (Exception e) {
			throw new Exception();
		}

	}

	public void atualizar(T obj) {
		Session s = ConnectionFactoryConfig.getSession();// .getCurrentSession();
		s.beginTransaction();
		s.merge(obj);
		s.getTransaction().commit();

	}

}
