package model.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.bridj.cpp.std.list;
import org.hibernate.Criteria;
import org.hibernate.Session;

import model.Cliente;

public class RepositoryCliente extends Repository<Cliente> {

	public void adicionar(Cliente cliente) throws Exception {
		try {
			EntityManager sessiom = ConnectionFactoryRepository.getManager();
			sessiom.getTransaction().begin();
			sessiom.persist(cliente);
			sessiom.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<Cliente> buscarTodos() throws Exception {
		try {
			EntityManager session = ConnectionFactoryRepository.getManager();

			return session.createQuery("from Cliente").getResultList();
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

}
