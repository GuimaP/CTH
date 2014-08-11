package model.repository;

import model.Funcionario;
import model.Login;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import main.Start;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class LoginRepository {

	public LoginRepository() {

	}

	public boolean isAutentica(Login Usuario) throws SQLException, Exception {
		Session session = ConnectionFactoryConfig.getSession();
				//.getCurrentSession();
		Login ls = null;
		session.beginTransaction();
		try {
//			Criteria filtro = session.createCriteria(Login.class);
//			filtro.add(Restrictions.eq("usuario", Usuario.getUsuario()));
//			filtro.add(Restrictions.eq("senha", Usuario.getSenha()));
//			ls = (Login) filtro.uniqueResult();
//
			org.hibernate.Query q = session.createQuery("from Login as l where l.usuario = :user and l.senha = :pass");
			q.setString("user",Usuario.getUsuario());
			q.setString("pass", Usuario.getSenha());
			Login f = (Login) q.uniqueResult();
			
			System.out.println(session.isConnected());
			System.out.println(session.isOpen());
			session.getTransaction().commit();		
			return f != null;
			
			
			
		} catch (Exception e) {

			throw new Exception(e);
		}
		
		
	}
}
