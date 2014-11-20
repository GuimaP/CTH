package model.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

public class ConnectionFactoryRepository {

    private final static EntityManager manager = openEntity();
    	
    	
   

    private static EntityManager openEntity()  {
    	EntityManager entityManager = null;
    	try{
    	Map<String, String> map = new HashMap<String, String>();
        map.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost/db");
    	map.put("javax.persistence.jdbc.user", "root");
    	map.put("javax.persistence.jdbc.password", "1q2w3e");
    	
    	

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("autoEscolaNew",map);
        
        entityManager =  factory.createEntityManager();
        
        }catch (PersistenceException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, e.getCause());
			System.out.println(e.getCause());
			throw new NoResultException("Erro ao conectar na base de dados");
			
		}
    	return entityManager;


        
    }

    public static EntityManager getManager() {
    	System.out.println(manager.isOpen());
   	if(manager == null ){
    		openEntity();
     }
        return manager;
    }
}
