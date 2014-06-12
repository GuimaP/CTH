package Model.Repository;

import Main.Start;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.Login;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class LoginRepository {
	
	public LoginRepository(){
		
	}
	
	
	public boolean isAutentica(Login Usuario){
		
		//Query q = manager.createQuery("FROM Login l order by l.usuario");
		Session session = (Session) ConnectionFactoryRepository.getManager().getDelegate();
		try{
		
			session.getTransaction().begin();
		Criteria filtro= session.createCriteria(Login.class);
		filtro.add(Restrictions.eq("usuario", Usuario.getUsuario()));		
		filtro.add(Restrictions.eq("senha", Usuario.getSenha()));
		//TypedQuery<Login> query = manager.createQuery("select l from login l where l.usuario = :p",Login.class);
		//query.setParameter("p", Usuario.getUsuario());
		//	Login usuarioBanco = (Login)query.getSingleResult();
		
		Login ls =(Login) filtro.uniqueResult();
		session.getTransaction().commit();

                
		if(ls!= null)
			return true;
		
		return false;
		
		}catch (Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			
		
		}
		return false;
	}
}
