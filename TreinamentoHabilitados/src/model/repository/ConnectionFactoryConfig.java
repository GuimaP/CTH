package model.repository;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.Ejb3Configuration;

import model.*;

public class ConnectionFactoryConfig {
	
	private static final SessionFactory sessionFactory = openManger();
	private static Session session;
	
	
		public static SessionFactory openManger(){
					Configuration cfg = new Configuration();
			 		
					cfg.configure("hibernate.cfg.xml");
			 		cfg.addAnnotatedClass(Login.class); 
			 		cfg.addAnnotatedClass(Carro.class);
			 		cfg.addAnnotatedClass(Cliente.class);
			 		cfg.addAnnotatedClass(Funcionario.class);
			 		cfg.addAnnotatedClass(Tarefa.class);
			 		cfg.addAnnotatedClass(Login.class);
			 		cfg.addAnnotatedClass(Login.class);
			 		cfg.addAnnotatedClass(Servico.class);
			 		cfg.addAnnotatedClass(Pacote.class);
			 		cfg.addAnnotatedClass(Pesquisa.class);
			 		cfg.addAnnotatedClass(Aula.class);
			 		return cfg.buildSessionFactory();
			 	}
			 	
	
		public static void generateSession(){
			session = sessionFactory.openSession();
		}
	
		
	public static Session getSession(){
		if(!session.isConnected()){
			generateSession();
		}
		return session;
	}
}
