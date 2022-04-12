package br.com.jogodaforca.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.jogodaforca.models.Palavra;

public class PalavraDao {
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	public List<Palavra> listaPalavras() {
		String jpql = "select p from Palavra p";
		return em.createQuery(jpql, Palavra.class).getResultList();
		
	}
}
