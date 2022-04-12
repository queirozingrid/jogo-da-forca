package br.com.jogodaforca.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.jogodaforca.models.Cartao;

public class CartaoDao {
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	private String jpql;
	
	public Cartao salvar(Cartao cartao) {
		em.persist(cartao);
		return cartao;
	}
	
	public List<Cartao> listar(){
		this.jpql = "select Cartao c from Cartao";
		return em.createQuery(jpql, Cartao.class).getResultList();
	}
}
