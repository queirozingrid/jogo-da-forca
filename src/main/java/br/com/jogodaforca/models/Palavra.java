package br.com.jogodaforca.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Palavra {
	
	@Id
	private String palavra;
	private String dica;
	
	public String getPalavra() {
		return palavra;
	}
	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}
	public String getDica() {
		return dica;
	}
	public void setDica(String dica) {
		this.dica = dica;
	}
	
	
}
