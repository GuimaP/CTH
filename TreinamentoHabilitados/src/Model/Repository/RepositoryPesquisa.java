package model.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Pacote;
import model.Pesquisa;

<<<<<<< HEAD
public class RepositoryPesquisa extends Repository<Pesquisa>{
	
	public void adicionar (Pesquisa p)throws Exception{
		try {
			Session session = ConnectionFactoryConfig.openManger().openSession();
=======
public class RepositoryPesquisa extends Repository<Pesquisa> {

	public void adicionar(Pesquisa p) throws Exception {
		try {
			Session session = ConnectionFactoryConfig.openManger()
					.openSession();
>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
			session.beginTransaction();
			session.persist(p);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception(e);
		}
<<<<<<< HEAD
		
	}
	
	public List<Pesquisa> buscarTodos()throws Exception{
		
		try {
			Session session = ConnectionFactoryConfig.openManger().openSession();
=======

	}

	public List<Pesquisa> buscarTodos() throws Exception {

		try {
			Session session = ConnectionFactoryConfig.openManger()
					.openSession();
>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
			List<Pesquisa> listPesquisa = new ArrayList<Pesquisa>();
			Criteria c = session.createCriteria(Pesquisa.class);
			listPesquisa = c.list();
			return listPesquisa;
		} catch (Exception e) {
			throw new Exception(e);
		}
<<<<<<< HEAD
		
		
		
	}
	
=======

	}
>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3

}
