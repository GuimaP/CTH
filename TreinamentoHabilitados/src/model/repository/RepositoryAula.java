package model.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import model.Aula;
import model.Cliente;

public class RepositoryAula extends Repository<Aula> {

	private Cliente c;
	private List<Aula> listAula = new ArrayList<Aula>();

	public void adicionar(Aula aula) throws Exception {
		EntityManager sessiom = ConnectionFactoryRepository.getManager();
		try {

			sessiom.getTransaction().begin();
			sessiom.persist(aula);
			sessiom.getTransaction().commit();
		} catch (Exception e) {
			sessiom.getTransaction().rollback();
			throw new Exception(e);
		}

	}

public List<Aula> buscaAulaPorCliente(Cliente c) throws Exception {
		
		try {
			List<Aula> list = new ArrayList<Aula>();
			this.c = c;
			if (c.getCpf() != null) {
				EntityManager session = ConnectionFactoryRepository
						.getManager();
				listAula = session.createQuery("from Aula").getResultList();
				for (Aula a : listAula) {
					if (c.getCpf().equals(a.getPacote().getCliente().getCpf())) {
						list.add(a);
					}
				}
				
			}
			return list;
		} catch (Exception e) {
			throw new Exception(e);
		}
		

	}
	
	
	
	
	
	
	
	
	public List<Aula> buscarTodos() throws Exception {

		try {
			EntityManager session = ConnectionFactoryRepository.getManager();
			return session.createQuery("from Aula").getResultList();
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	

}
