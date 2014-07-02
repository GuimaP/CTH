package Model.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactoryRepository {

    private final static EntityManager manager = openEntity();

    private static EntityManager openEntity() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("javax.persistence.jdbc.url", "jdbc:mysql://db4free.net/dbguimateste");
//        map.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost/dbguimateste");
        map.put("javax.persistence.jdbc.user", "guima");
        map.put("javax.persistence.jdbc.password", "1q2w3e");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("autoEscolaNew",map);

        return factory.createEntityManager();
    }

    public static EntityManager getManager() throws SQLException {
        return manager;
    }
}
