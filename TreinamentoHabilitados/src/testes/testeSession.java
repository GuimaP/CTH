package testes;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;

import model.Login;
import model.Tarefa;
import model.repository.ConnectionFactoryConfig;
import model.repository.ConnectionFactoryRepository;

public class testeSession {
	public static void main(String[] args) {
		try{
		Session s = (Session)ConnectionFactoryRepository.getManager().getDelegate();//.getSession().openSession();
		Login l = new Login();
		l.setUsuario("guima");
		l.setSenha("123");
		
		//s.getTransaction().begin();
		
		
		
		s.persist(l);
		s.close();
		
		//s.getTransaction().commit();
		
		}catch(GenericJDBCException err){
			System.out.println("Generic");
		}catch (JDBCException err){
			System.out.println("JDBC");
		}catch(Throwable err){
			err.printStackTrace();
			System.out.println("Throwable");
		}
	}
}
