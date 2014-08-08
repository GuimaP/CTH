package model.repository;

import java.util.ArrayList;
import java.util.List;

import org.bridj.cpp.std.list;
import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Cliente;

public class RepositoryCliente extends Repository<Cliente>{

	public void adicionar (Cliente cliente){
		try{
			Session sessiom = ConnectionFactoryConfig.openManger().openSession();
			sessiom.beginTransaction();
			sessiom.persist(cliente);
			sessiom.getTransaction().commit();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Cliente> buscarTodos(){
		Session session = ConnectionFactoryConfig.openManger().openSession();
		List<Cliente> listCliente = new ArrayList<Cliente>();
		Criteria c = session.createCriteria(Cliente.class);
		listCliente = c.list();
		
		
		
		
		return listCliente;
		
	}
	
}
