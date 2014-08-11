package model.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Pacote;
import model.Pesquisa;

public class RepositoryPesquisa extends Repository<Pesquisa>{
	
<<<<<<< HEAD
	public void adicionar (Pesquisa p)throws Exception{
		try {
			Session session = ConnectionFactoryConfig.openManger().openSession();
			session.beginTransaction();
			session.persist(p);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}
	
	public List<Pesquisa> buscarTodos()throws Exception{
		
		try {
			Session session = ConnectionFactoryConfig.openManger().openSession();
			List<Pesquisa> listPesquisa = new ArrayList<Pesquisa>();
			Criteria c = session.createCriteria(Pesquisa.class);
			listPesquisa = c.list();
			return listPesquisa;
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		
		
=======
	public void adicionar (Pesquisa p){
		Session session = ConnectionFactoryConfig.openManger().openSession();
		session.beginTransaction();
		session.persist(p);
		session.getTransaction().commit();
		
	}
	
	public List<Pesquisa> buscarTodos(){
		
		Session session = ConnectionFactoryConfig.openManger().openSession();
		List<Pesquisa> listPesquisa = new ArrayList<Pesquisa>();
		Criteria c = session.createCriteria(Pesquisa.class);
		listPesquisa = c.list();
		
		
		return listPesquisa;
>>>>>>> 7d65b131852601e51864f3810890bf9d83e08edc
	}
	

}
