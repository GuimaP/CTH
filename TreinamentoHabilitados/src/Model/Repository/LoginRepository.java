package Model.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import modelo.Login;


public class LoginRepository {
	
	public LoginRepository(){
		
	}
	
	
	public boolean isAutentica(Login Usuario){
		EntityManager manager = ConnectionFactoryRepositoryDois.getManager();
		//Query q = manager.createQuery("FROM Login l order by l.usuario");
		Session session = (Session) manager.getDelegate();
		
		Criteria filtro= session.createCriteria(Login.class);
		filtro.add(Restrictions.eq("usuario", Usuario.getUsuario()));		
		filtro.add(Restrictions.eq("senha", Usuario.getSenha()));
		//TypedQuery<Login> query = manager.createQuery("select l from login l where l.usuario = :p",Login.class);
		//query.setParameter("p", Usuario.getUsuario());
		//	Login usuarioBanco = (Login)query.getSingleResult();
		
		Login ls =(Login) filtro.uniqueResult();
		
		if(ls!= null)
			return true;
		
		return false;
		
		
	}
}
