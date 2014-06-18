package Model.Repository;

import Main.Start;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
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
	
	
	public boolean isAutentica(Login Usuario) throws SQLException, Exception{
		boolean isDoSomething = false;
		//Query q = manager.createQuery("FROM Login l order by l.usuario");
		Session session = (Session) ConnectionFactoryRepository.getManager().getDelegate();
		try{
                if(session.isConnected()){
                
                session.getTransaction().begin();
                isDoSomething = true;
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
                }else {
                    throw new SQLException("Não foi possivel se conectar na base de Dados");
                }
		}catch (Exception e){
			if(isDoSomething){
                            session.getTransaction().rollback();
                        }
			throw new Exception(e);
		}finally{
			
		
		}
		
	}
}
