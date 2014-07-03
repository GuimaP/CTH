package Model.Repository;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.Ejb3Configuration;

import Model.*;

public class ConnectionFactoryConfig {
	
	private static final SessionFactory sessionFactory = openManger();
	
		public static SessionFactory openManger(){
					Configuration cfg = new Configuration();
			 		cfg.configure("hibernate.cfg.xml");
			 		cfg.addAnnotatedClass(Login.class); //Informando ao Hibernate as classes anotadas.
			 		cfg.addAnnotatedClass(Carro.class);
			 		cfg.addAnnotatedClass(Cliente.class);
			 		cfg.addAnnotatedClass(Funcionario.class);
			 		cfg.addAnnotatedClass(Tarefa.class);
			 		cfg.addAnnotatedClass(Login.class);
			 		cfg.addAnnotatedClass(Login.class);
			 		return cfg.buildSessionFactory();
			 	}
			 	
	
	
	public static SessionFactory getSession(){
		return sessionFactory;
	}
}
