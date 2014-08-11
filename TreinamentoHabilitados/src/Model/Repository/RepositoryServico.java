package model.repository;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD





=======
>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Servico;

public class RepositoryServico extends Repository<Servico> {
<<<<<<< HEAD
	
	
	
	
	public void adicionar (Servico servico)throws Exception {
		try{
		Session session = ConnectionFactoryConfig.openManger().openSession();
		session.beginTransaction();
		session.persist(servico);
		session.getTransaction().commit();
		}catch(Exception e){
			throw new Exception(e);
		}
		
	}
	
	public List<Servico> buscarServico ()throws Exception{
		try {
			Session sessio = ConnectionFactoryConfig.openManger().openSession();
			List<Servico> listServico = new ArrayList<Servico>();
			Criteria c  = sessio.createCriteria(Servico.class);
			listServico = c.list();
			return listServico;	
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}
	
=======

	public void adicionar(Servico servico) throws Exception {

		try {
			Session session = ConnectionFactoryConfig.openManger()
					.openSession();
			session.beginTransaction();
			session.persist(servico);
			session.getTransaction().commit();
		} catch (Exception e) {

			throw new Exception(e);

		}

	}

	public List<Servico> buscarServico() throws Exception {
		try {
			Session sessio = ConnectionFactoryConfig.openManger().openSession();
			List<Servico> listServico = new ArrayList<Servico>();
			Criteria c = sessio.createCriteria(Servico.class);
			listServico = c.list();
			return listServico;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
}
