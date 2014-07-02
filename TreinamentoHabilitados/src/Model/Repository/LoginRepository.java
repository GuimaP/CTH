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
				.getCurrentSession();
		Login ls = null;
		try {

			session.getTransaction().begin();
			Criteria filtro = session.createCriteria(Login.class);
			filtro.add(Restrictions.eq("usuario", Usuario.getUsuario()));
			filtro.add(Restrictions.eq("senha", Usuario.getSenha()));
			ls = (Login) filtro.uniqueResult();
			session.getTransaction().commit();

		} catch (Exception e) {/*Handled Exception*/ e.printStackTrace();}
		
		return ls != null;
	}
}
