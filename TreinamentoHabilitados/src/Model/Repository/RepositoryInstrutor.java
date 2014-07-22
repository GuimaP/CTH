package Model.Repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import Model.Funcionario;

/**
 *@author Guima
 * Classe responsavel pela Persistencia da Base de dados
 */
public class RepositoryInstrutor extends Repository<Model.Funcionario> {
   
    
    public Funcionario findInstrutorSingle(String cpf){
    	Session session = ConnectionFactoryConfig.getSession();//.getCurrentSession();
    	Funcionario f =null;
        if(session != null){
            session.beginTransaction();
            Criteria filtro = session.createCriteria(Funcionario.class);
            filtro.add(Restrictions.eq("cpf", cpf));
            f = (Funcionario) filtro.uniqueResult();
            session.getTransaction().commit();
            return f;
            
        }
        
        return f;
    }
    
    public List<Funcionario> getAllInstrutor() throws Exception{
    	Session session = ConnectionFactoryConfig.getSession();
    	List<Funcionario> ls = new ArrayList<Funcionario>();
    	if(session != null || !session.isConnected()){
    		session.beginTransaction();
    		Criteria c = session.createCriteria(Funcionario.class);
    		ls = c.list();
    	}
    	
    	return ls;
    }
    
    
    
    
    /**
     *@author Guilherme
     * @throws java.sql.SQLException
     * @params Objeto para Persistencia com a Classe
     * 
     */
    
}
