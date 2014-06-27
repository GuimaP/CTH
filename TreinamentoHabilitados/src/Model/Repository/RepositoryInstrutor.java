package Model.Repository;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import modelo.Funcionario;

/**
 *@author Guima
 * Classe responsavel pela Persistencia da Base de dados
 */
public class RepositoryInstrutor{
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
    public void adicionar(Funcionario obj) throws SQLException{
       
        if(connection != null){ //Verifico se possui uma conexao criada
            connection.getTransaction().begin();//Inicio uma transação
            connection.persist(obj);
            connection.getTransaction().commit();// e finalizo ela
        }else { //se não eu lanço uma Exceptions
            connection.getTransaction().rollback(); //e dou rollback na transaction
            throw new SQLException("Houve um erro ao conectar a base de dados");
            
        }
        
    }
}
