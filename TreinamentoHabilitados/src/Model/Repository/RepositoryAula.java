package model.repository;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Aula;

public class RepositoryAula extends Repository<Aula> {

	public void adicionar(Aula aula) throws Exception{
		try {
			Session sessiom = ConnectionFactoryConfig.openManger().openSession();
			sessiom.beginTransaction();
			sessiom.persist(aula);
			sessiom.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		
	}

	public List<Aula> buscarTodos() throws Exception{
		try {
			Session session = ConnectionFactoryConfig.openManger().openSession();
			List<Aula> listAula = new ArrayList<Aula>();
			Criteria c = session.createCriteria(Aula.class);
			listAula = c.list();

			return listAula;
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	
	
	}
}
