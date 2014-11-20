package model.repository;

import java.sql.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;

import model.Cliente;
import model.Financeiro;
import model.enums.EnumTipoDeRelatorio;
import model.enums.EnumTipoDespesa;

public class RepositoryFinanceiro {
	public void adicionar(Financeiro financeiro) throws Exception {
		try {
			EntityManager session = ConnectionFactoryRepository.getManager();
			session.getTransaction().begin();
			session.persist(financeiro);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public ObservableList<Financeiro> buscarTodos() throws Exception {
		try {
			EntityManager session = ConnectionFactoryRepository.getManager();
			List<Financeiro>list = session.createQuery("from Financeiro").getResultList();
			
			return FXCollections.observableArrayList(list); 
		} catch (Exception e) {
			throw new Exception(e);
		}

	}
	
	public List<Financeiro> buscarTodos1() throws Exception {
		try {
			EntityManager session = ConnectionFactoryRepository.getManager();
			List<Financeiro>list = session.createQuery("from Financeiro").getResultList();
			
			return list; 
		} catch (Exception e) {
			throw new Exception(e);
		}

	}
	
	public List<Financeiro> buscarPorData(Date dtInicial, Date dtFinal) throws Exception {
		try {
			EntityManager session = ConnectionFactoryRepository.getManager();
			Query query = session.createQuery("from Financeiro where (dtLancamento BETWEEN :dtInicial AND :dtFinal )");
			query.setParameter("dtInicial",dtInicial );
			query.setParameter("dtFinal",dtFinal);
			List<Financeiro>list = query.getResultList();
			
			return list; 
		} catch (Exception e) {
			throw new Exception(e);
		}

	}
	
	public List<Financeiro> buscarPorDataTipo(Date dtInicial, Date dtFinal,EnumTipoDespesa tipo) throws Exception {
		try {
			EntityManager session = ConnectionFactoryRepository.getManager();
			Query query = session.createQuery("from Financeiro where (dtLancamento BETWEEN :dtInicial AND :dtFinal ) AND tipoDespesa = :tipo");
			query.setParameter("dtInicial",dtInicial );
			query.setParameter("dtFinal",dtFinal);
			query.setParameter("tipo", tipo);
			List<Financeiro>list = query.getResultList();
			
			return list; 
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	public void deleteDespesa(Financeiro financeiro) {
		EntityManager em = ConnectionFactoryRepository.getManager();
		try{
			em.getTransaction().begin();
			em.remove(financeiro);
			em.getTransaction().commit();
		}catch(Exception er){
			try{
				em.getTransaction().rollback();
			}catch (Exception er1){
				throw er1;
			}
			throw er;
			
		}
		
	}

	public void atualizar(Financeiro financeiro) {
		EntityManager em = ConnectionFactoryRepository.getManager();
		try{
			em.getTransaction().begin();
			em.merge(financeiro);
			em.getTransaction().commit();
		}catch(Exception er){
			try{
				em.getTransaction().rollback();
			}catch (Exception er1){
				throw er1;
			}
			throw er;
			
		}
		
	}
	
}
