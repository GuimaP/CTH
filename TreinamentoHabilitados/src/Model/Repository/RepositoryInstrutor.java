package model.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.Carro;
import model.Funcionario;

/**
 *@author Guima
 * Classe responsavel pela Persistencia da Base de dados
 */
public class RepositoryInstrutor extends Repository<model.Funcionario> {
   
    
    public Funcionario findInstrutorSingle(String cpf){
//    	Session session = ConnectionFactoryConfig.getSession();//.getCurrentSession();
    	Session session = ConnectionFactoryConfig.openManger().openSession();
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
    
    @Override
    public void adicionar(Funcionario obj) {
    	super.adicionar(obj);
    	Carro c = obj.getTbCarroPlacaCarro();
    	c.setOcupado(true);
    	new RepositoryCarro().atualizar(c);
    }
    
    public List<Funcionario> getAllInstrutor() throws Exception{
    	Session session = ConnectionFactoryConfig.openManger().openSession();
    	List<Funcionario> ls = new ArrayList<Funcionario>();
    	if(session != null || !session.isConnected()){
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
