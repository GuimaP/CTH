package Model.Repository;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactoryRepository {
	private final static EntityManager manager = openEntity();
	
	private static EntityManager openEntity(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("autoEscolaNew");
                
		return factory.createEntityManager();
	}
	
	public static EntityManager getManager() throws SQLException{
            return manager;
	}
}
