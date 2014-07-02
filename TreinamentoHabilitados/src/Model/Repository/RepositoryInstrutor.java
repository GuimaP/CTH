package Model.Repository;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import Model.Funcionario;

/**
 *@author Guima
 * Classe responsavel pela Persistencia da Base de dados
 */
public class RepositoryInstrutor extends Repository<Model.Funcionario> {
    private EntityManager connection;
    
    public RepositoryInstrutor(EntityManager connection){ //Recebo uma conexão ja criada
        this.connection = connection;
   };
    
    
    public Funcionario findInstrutorSingle(String cpf){
        Funcionario f =null;
        if(connection != null){
            EntityManager em = connection;
            f = em.find(Funcionario.class, cpf);
            return f;
            
        }
        
        return f;
    }
    /**
     *@author Guilherme
     * @throws java.sql.SQLException
     * @params Objeto para Persistencia com a Classe
     * 
     */
    
}
