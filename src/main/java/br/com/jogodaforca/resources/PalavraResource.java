package br.com.jogodaforca.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.jogodaforca.daos.PalavraDao;
import br.com.jogodaforca.models.Palavra;

@Path("palavras")
public class PalavraResource {
	
	@Inject
	private PalavraDao palavraDao;
	
	@GET
	@Path("listaPalavras")
	@Produces({javax.ws.rs.core.MediaType.APPLICATION_JSON})
	public List<Palavra> listaPalavras() {
		return palavraDao.listaPalavras();
	}
	
}
