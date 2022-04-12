package br.com.jogodaforca.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.jogodaforca.models.Endereco;

public class EnderecoDao {
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	public Endereco buscarPorCep(String cep) {
		String jpql = "select e from Endereco e where e.cep like :cep";
		List<Endereco> query = 
				em.createQuery(jpql, Endereco.class).
					setParameter("cep", cep).getResultList();
		
		if(query.size()>0) {
			return query.get(0);
		} else {
			return null;
		}
	}
}
