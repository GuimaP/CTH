/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Repository;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Guima
 */
public class Repository<T>  {
    
    public void adicionar(T obj) {
        
        EntityManager em = null;
        Session session = ConnectionFactoryConfig.getSession();//getCurrentSession(); 
        session.getTransaction().begin();
        session.persist(obj);
        session.getTransaction().commit();
    }
    
    public List<T> pegarTodos() throws SQLException{
        List<T> lista = null;
        Session session = ConnectionFactoryConfig.getSession();//getCurrentSession();
        session.beginTransaction();
        session.createCriteria(this.getClass());
        Criteria c = session.createCriteria(this.getClass());
        lista = c.list();
        session.getTransaction().commit();
        
        session.close();
        return lista;
    }
    
   public void atualizar (T obj){
	   Session s = ConnectionFactoryConfig.getSession();//.getCurrentSession();
	   s.beginTransaction();
	   s.merge(obj);
	   s.getTransaction().commit();
	   
   }
    
}
