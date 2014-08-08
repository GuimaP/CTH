package model.repository;

import java.util.ArrayList;
import java.util.List;




import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Servico;

public class RepositoryServico extends Repository<Servico> {
	
	
	
	
	public void adicionar (Servico servico) {
		try{
		Session session = ConnectionFactoryConfig.openManger().openSession();
		session.beginTransaction();
		session.persist(servico);
		session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public List<Servico> buscbarServico (){
		Session sessio = ConnectionFactoryConfig.openManger().openSession();
		List<Servico> listPacote = new ArrayList<Servico>();
		Criteria c  = sessio.createCriteria(Servico.class);
		listPacote = c.list();
		return listPacote;
	}
	
}
