package Model.Repository;

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
//        map.put("javax.persistence.jdbc.url", "jdbc:mysql://db4free.net/dbguimateste");
//    	map.put("javax.persistence.jdbc.user", "guima");
//      map.put("javax.persistence.jdbc.password", "1q2w3e");
    	String dbName = "guimahos_autoescola";
    	String dbUserName = "guimahos_guima";
    	String dbPassword = "1q2w3e";
    	
    	
        map.put("javax.persistence.jdbc.url", "jdbc:mysql://185.27.134.191:3306/" + dbName + "?user=" + dbUserName + "&password=" + dbPassword + "&useUnicode=true&characterEncoding=UTF-8");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("autoEscolaNew",map);
        entityManager =  factory.createEntityManager();
        }catch(GenericJDBCException  erro){
        	JOptionPane.showMessageDialog(null, "Falha na Comunicação com o banco de dados");
        	
        }catch (Throwable e) {
			System.out.println(e.getMessage());
		}
    	
    	return entityManager;

        
    }

    public static EntityManager getManager() throws SQLException {
        return manager;
    }
}
