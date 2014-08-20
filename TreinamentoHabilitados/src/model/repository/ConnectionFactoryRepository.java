package model.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

public class ConnectionFactoryRepository {

    private final static EntityManager manager = openEntity();

    private static EntityManager openEntity() {
    	EntityManager entityManager = null;
    	try{
    	Map<String, String> map = new HashMap<String, String>();
        map.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/tcc");
    	map.put("javax.persistence.jdbc.user", "root");
    	map.put("javax.persistence.jdbc.password", "root");
    	
    	

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("autoEscolaNew",map);
        entityManager =  factory.createEntityManager();
        }catch(GenericJDBCException  erro){
        	JOptionPane.showMessageDialog(null, "Falha na Comunica��o com o banco de dados");
        	
        }catch (Throwable e) {
			System.out.println(e.getMessage());
		}
    	
    	return entityManager;

        
    }

    public static EntityManager getManager() throws SQLException {
        return manager;
    }
}
