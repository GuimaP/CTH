package Model.Repository;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import Model.Tarefa;

public class RepositoryTarefa extends Repository<Model.Tarefa> {
//	public java.util.List<Model.Tarefa> getAllTarefasToday(
//			java.time.LocalDateTime dt) {
//		try {
//
//			Session s = (Session) ConnectionFactoryConfig.getSession()
//					.getCurrentSession();
//			java.util.Date date = new java.util.Date(dt.getYear() - 1900,
//					dt.getMonthValue() - 1, dt.getDayOfMonth());
//			Criteria filtro = s.createCriteria(Tarefa.class);
//			filtro.add(Restrictions.eq("dataCompromisso", date));
//			java.util.List<Tarefa> list = filtro.list();
//			System.out.println(date);
//			System.out.println("aospdksapokdsao");
//			System.out.println(list);
//			return list;
//
//		} catch (Exception er) {
//			er.printStackTrace();
//			System.out.println("error?");
//			return null;
//		}
//	}

	public java.util.List<Model.Tarefa> getAllTarefasToday(java.util.Date dt)
			throws Throwable {
		java.util.List<Tarefa> list = new java.util.ArrayList<Tarefa>();
		
		
		Session session = Model.Repository.ConnectionFactoryConfig.getSession();
				//.openSession(); //Pego a conexão Já existente
//		if(!session.isConnected()){
//			ConnectionFactoryConfig.generateSession();
//		}
		
	
		
		try{
			
		session.getTransaction().begin();
		
//		session.beginTransaction();
//		Criteria filtro = session.createCriteria(Tarefa.class);
//		
//		java.util.Date date = new java.util.Date(dt.getYear(),dt.getMonth(),dt.getDate());
//		
//		System.out.println(pegarTodos());
//		filtro.add(Restrictions.eq("dataCompromisso", date));
		
		
		Query q = session.createQuery("from Tarefa as t where t.dataCompromisso = :data");
		String strData = new SimpleDateFormat("yyyy-MM-dd").format(dt);
		q.setString("data", strData);
		
		list = q.list();
		
		session.getTransaction().commit();
		
		}catch(Throwable erro){
//			session.getTransaction().rollback();
			
			throw new Throwable(erro);
		}

		System.out.println(list);
		
		return list;
	}
}
