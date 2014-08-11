package model.repository;

import java.util.ArrayList;
import java.util.List;




<<<<<<< HEAD


=======
>>>>>>> 7d65b131852601e51864f3810890bf9d83e08edc
import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Servico;

public class RepositoryServico extends Repository<Servico> {
	
	
	
	
<<<<<<< HEAD
	public void adicionar (Servico servico)throws Exception {
=======
	public void adicionar (Servico servico) {
>>>>>>> 7d65b131852601e51864f3810890bf9d83e08edc
		try{
		Session session = ConnectionFactoryConfig.openManger().openSession();
		session.beginTransaction();
		session.persist(servico);
		session.getTransaction().commit();
		}catch(Exception e){
<<<<<<< HEAD
			throw new Exception(e);
=======
			e.printStackTrace();
>>>>>>> 7d65b131852601e51864f3810890bf9d83e08edc
		}
		
	}
	
<<<<<<< HEAD
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
		
=======
	public List<Servico> buscbarServico (){
		Session sessio = ConnectionFactoryConfig.openManger().openSession();
		List<Servico> listPacote = new ArrayList<Servico>();
		Criteria c  = sessio.createCriteria(Servico.class);
		listPacote = c.list();
		return listPacote;
>>>>>>> 7d65b131852601e51864f3810890bf9d83e08edc
	}
	
}
