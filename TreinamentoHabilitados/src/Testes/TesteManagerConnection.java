package Testes;

import javax.persistence.EntityManager;

import modelo.Login;

import org.hibernate.Session;

import Model.Repository.ConnectionFactoryRepository;
import Model.Repository.ConnectionFactoryRepositoryDois;

public class TesteManagerConnection {
	public static void main(String[] args) {
		try{
		//	Session sessao = ConnectionFactoryRepository.getManager().getCurrentSession();
		//	System.out.println("conectou");
			EntityManager manager = ConnectionFactoryRepositoryDois.getManager();
			//System.out.println("conectous");
			
			Login l = new Login();
			l.setUsuario("guima");
			l.setSenha("123");
			manager.getTransaction().begin();
			manager.persist(l);
			manager.getTransaction().commit();
			System.out.println("persistiu");
		}catch(Exception e){
		
			e.printStackTrace();
		}	
	}
}
