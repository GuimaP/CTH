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

/**
 *
 * @author Guima
 */
public class Repository<T>  {
    
    public void Adicionar(T obj) throws SQLException{
        EntityManager em = ConnectionFactoryRepository.getManager();
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }
    
    public List<T> pegarTodos(T obj) throws SQLException{
        List<T> lista = null;
        EntityManager em = ConnectionFactoryRepository.getManager();
        Session session = (Session) em.getDelegate();
        session.createCriteria(obj.getClass());
        Criteria c = session.createCriteria(obj.getClass());
        lista = c.list();
        
        return lista;
    }
    
    
}
