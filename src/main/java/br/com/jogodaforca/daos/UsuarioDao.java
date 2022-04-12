package br.com.jogodaforca.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.jogodaforca.models.Usuario;

@Stateful
public class UsuarioDao {
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	private String jpql;
	
	public void salvar(Usuario usuario) {
		em.persist(usuario);
	}
	
	public List<Usuario> listar(){
		this.jpql = "select u from Usuario u";
		System.out.println("chamei o metodo certin");
		return em.createQuery(jpql, Usuario.class).getResultList();
	}
	
	public boolean validaCpf(String cpf) {
		this.jpql = "select u from Usuario u where u.cpf like :cpf";
		if(em.createQuery(jpql, Usuario.class).setParameter("cpf", cpf).getResultList().size() < 1) {
			return true;
			/*
			 * se retornar true, quer dizer que não existe nenhum usuário cadastrado com
			 * esse email e o usuário poderá ser cadastrado
			 */
		} else {
			return false;
			/*
			 * se retornar false, quer dizer já existe um usuário cadastrado com
			 * esse email no banco
			 */
		}
	}	
	
}
