package Model.Repository;

import Main.Start;
import Model.Login;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class LoginRepository {

	public LoginRepository() {

	}

	public boolean isAutentica(Login Usuario) throws SQLException, Exception {
		Session session = ConnectionFactoryConfig.getSession()
				.openSession();
		Login ls = null;
		session.getTransaction().begin();
		try {

			
			Criteria filtro = session.createCriteria(Login.class);
			filtro.add(Restrictions.eq("usuario", Usuario.getUsuario()));
			filtro.add(Restrictions.eq("senha", Usuario.getSenha()));
			ls = (Login) filtro.uniqueResult();
			session.getTransaction().commit();
			session.close();
			
		} catch (Exception e) {

			e.printStackTrace();}
		
		return ls != null;
	}
}
