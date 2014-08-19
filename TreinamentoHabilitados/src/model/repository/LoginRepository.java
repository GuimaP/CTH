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
//		Session session = ConnectionFactoryConfig.openManger().openSession();
		EntityManager session = ConnectionFactoryRepository.getManager();
				//.getCurrentSession();

		try {
			session.getTransaction().begin();
//			Query q = session.createQuery("from Login as l where l.usuario = :user and l.senha = :pass");
			Query query = session.createQuery("from Login  where usuario = :user and senha = :pass");
			query.setParameter("user",Usuario.getUsuario());
			query.setParameter("pass", Usuario.getSenha());
			Login f = (Login) query.getSingleResult();
			
//			System.out.println(session.isConnected());
			System.out.println(session.isOpen());
			session.getTransaction().commit();		
			return f != null;
			
			
			
		} catch (Exception e) {

			throw new Exception(e);
		}
		
		
	}
}
