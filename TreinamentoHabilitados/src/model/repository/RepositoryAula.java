package model.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Aula;

public class RepositoryAula extends Repository<Aula> {

	public void adicionar(Aula aula) throws Exception{
		try {
			Session sessiom = ConnectionFactoryConfig.openManger().openSession();
		//	EntityManager session = ConnectionFactoryRepository.getManager();
			sessiom.getTransaction().begin();
			sessiom.persist(aula);
			sessiom.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		
	}

	/*
	public List<Aula> buscarTodos() throws Exception{
		try {
//			Session session = ConnectionFactoryConfig.openManger().openSession();
			//EntityManager session = ConnectionFactoryRepository.getManager();
			List<Aula> listAula = sessio.createQuery("from Aula").getResultList();

			return listAula;
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	
	
	}

*/
}
