package br.com.jogodaforca.beans;

import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jogodaforca.daos.PalavraDao;
import br.com.jogodaforca.models.Palavra;

@Named
public class ForcaBean {
	private Palavra palavra = new Palavra();
	Random sorteador = new Random();
	
	@Inject
	private PalavraDao palavraDao;
	
//	@PostConstruct
//	public void sorteiaPalavra() {
//		List<Palavra> palavras = palavraDao.listaPalavras();
//		int i = sorteador.nextInt(palavras.size());
//		this.palavra = palavras.get(i);
//		
//	}

	public Palavra getPalavra() {
		return palavra;
	}

	public void setPalavra(Palavra palavra) {
		this.palavra = palavra;
	}
	
	
}
