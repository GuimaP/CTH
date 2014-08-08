package model.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Pacote;
import model.Pesquisa;

public class RepositoryPesquisa extends Repository<Pesquisa>{
	
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
	}
	

}
