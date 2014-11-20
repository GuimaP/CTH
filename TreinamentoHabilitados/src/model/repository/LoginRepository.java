package model.repository;

import model.Funcionario;
import model.Instrutor;
import model.Login;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import main.Start;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.GenericJDBCException;

public class LoginRepository {

	public LoginRepository() {

	}

	public boolean isAutentica(Login Usuario) throws  Exception {
		EntityManager em = ConnectionFactoryRepository.getManager();
		try {
			
			Login f = null;
			em.getTransaction().begin();
			Query query = em.createQuery("from Login  where usuario = :user and senha = :pass");
			query.setParameter("user",Usuario.getUsuario());
			query.setParameter("pass", Usuario.getSenha());
			
			int total = -1;
			total = query.getResultList().size();
			em.getTransaction().commit();
			
			if(total >0){
//				f = (Login) query.getSingleResult();
				System.out.println(em.isOpen());
				return true;
			}else {
				return false;
			}
			
			
		}catch (Exception e) {
			em.getTransaction().rollback();
			if(e.getMessage().contains("Could not open connection")){
				throw new Exception("N�o foi possivel abrir uma conexão com a base de dados");
			}else {
				return false;
			}
			
		}
		
	}
	
	public Funcionario getFuncionario(Login login){
		try{
			EntityManager em = ConnectionFactoryRepository.getManager();
			Query q = em.createQuery("from Funcionario where usuario = :login ");
			q.setParameter("login", login.getUsuario());
			return (Funcionario) q.getSingleResult();
		}catch (Exception e){
			
		}
		
		return null;
	}

	public void adicionar(Login login) {
		EntityManager em = ConnectionFactoryRepository.getManager();
		try{
			em.getTransaction().begin();
			em.persist(login);
			em.getTransaction().commit();
		}catch (Exception e){
			em.getTransaction().rollback();
			throw e;
		}
		
	}
	
	public boolean verifyUserRootExists(){
		EntityManager em = ConnectionFactoryRepository.getManager();
		try{
			
			em.getTransaction().begin();
			Query q = em.createQuery("from Login where usuario = :usuario");
			q.setParameter("usuario", "root");
			int total = q.getResultList().size(); 
			em.getTransaction().commit();
			return total > 0;
			
		}catch (Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	public void atualizar(Login login) {
		EntityManager em = ConnectionFactoryRepository.getManager();
		try{
			em.getTransaction().begin();
			em.merge(login);
			em.getTransaction().commit();
			
		}catch (Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		
	}
	
}
