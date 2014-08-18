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

import model.Carro;

/**
 *
 * @author Guima
 */

public class RepositoryCarro extends Repository<Carro> {

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

}
