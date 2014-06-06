package Model.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectionFactoryRepository {
	private static final SessionFactory manager = openManger();/*Metodos estaticos já sobem prontos
								na inicialização do programa,portanto, o atributo manager
								já estará instanciando na inicialização
	 							*/
	
	@SuppressWarnings("deprecation")
	private static SessionFactory openManger(){
		try{
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			return cfg.buildSessionFactory();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ExceptionInInitializerError(e); //Para a execução do programa...
		}
	}
	
	public static SessionFactory getManager(){
		return manager;
	}
}
