package Model.Repository;

import java.util.ArrayList;
import java.util.List;




import org.hibernate.Criteria;
import org.hibernate.Session;

import Model.Pacote;

public class RepositoryPacote extends Repository<Pacote> {
	
	
	
	
	public void adicionar (Pacote pacote) {
		try{
		Session session = ConnectionFactoryConfig.openManger().openSession();
		session.beginTransaction();
		session.persist(pacote);
		session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public List<Pacote> buscarPacote (){
		Session sessio = ConnectionFactoryConfig.openManger().openSession();
		List<Pacote> listPacote = new ArrayList<Pacote>();
		Criteria c  = sessio.createCriteria(Pacote.class);
		listPacote = c.list();
		return listPacote;
	}
	
}
