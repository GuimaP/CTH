package model.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Pacote;

<<<<<<< HEAD
public class RepositoryPacote extends Repository<Pacote> {

	public void adicionar(Pacote p) throws Exception {
		try {
			Session session = ConnectionFactoryConfig.openManger()
					.openSession();
			session.beginTransaction();
			session.persist(p);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<Pacote> buscarTodos() throws Exception {

		try {

			Session session = ConnectionFactoryConfig.openManger()
					.openSession();
			List<Pacote> listPacote = new ArrayList<Pacote>();
			Criteria c = session.createCriteria(Pacote.class);
			listPacote = c.list();

			return listPacote;
		} catch (Exception e) {
			throw new Exception(e);
		}
=======
public class RepositoryPacote extends Repository<Pacote>{
	
	
	public void adicionar (Pacote p ){
		Session session = ConnectionFactoryConfig.openManger().openSession();
		session.beginTransaction();
		session.persist(p);
		session.getTransaction().commit();
	}
	
	public List<Pacote> buscarTodos (){
		Session session = ConnectionFactoryConfig.openManger().openSession();
		List<Pacote> listPacote = new ArrayList<Pacote>();
		Criteria c = session.createCriteria(Pacote.class);
		listPacote = c.list();
		
		
		return listPacote;
>>>>>>> 7d65b131852601e51864f3810890bf9d83e08edc
	}
}
