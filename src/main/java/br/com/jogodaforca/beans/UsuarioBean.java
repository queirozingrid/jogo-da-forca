package br.com.jogodaforca.beans;

import java.io.IOException;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.jogodaforca.daos.EnderecoDao;
import br.com.jogodaforca.daos.UsuarioDao;
import br.com.jogodaforca.models.Endereco;
import br.com.jogodaforca.models.PassGenerator;
import br.com.jogodaforca.models.SystemRole;
import br.com.jogodaforca.models.Usuario;

@Model
public class UsuarioBean {
	
	// entidades
	private Usuario usuario = new Usuario();
	
	private Endereco endereco = new Endereco();
	
	// daos
	@Inject
	private UsuarioDao usuarioDao;	
	@Inject
	private EnderecoDao enderecoDao;
	
	
	// utilitarios
	@Inject
	private FacesContext context;
	private PassGenerator gerador = new PassGenerator();
	
	//atributos do bean
	private String senha;
	private String confirmaSenha;
	private String cep;
	
	
	public String salvar() throws IOException{
		this.endereco = enderecoDao.buscarPorCep(cep);
		//System.out.println(this.usuario.getCartao().getValidade());
		
		if(this.endereco != null) {
			this.usuario.setEndereco(endereco);
			this.usuarioDao.salvar(usuario);
			
			SystemRole role = new SystemRole("usuario");
			this.usuario.getRoles().add(role);
			
			this.usuario = new Usuario();
			cep = "";
			
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Não encontramos um endereço para o seu CEP",
							"Não encontramos um endereço para o seu CEP"));
		}
		return null;
		
	}
	
	@Transactional
	public String validaUsuario() throws IOException {
		boolean validacaoNome = validaNome();
		
		String cpf = this.usuario.getCpf();
		cpf = cpf.replaceAll("\\.", ""); // substitui todos os pontos do cpf
		cpf = cpf.replaceAll("\\-", ""); // substitui todos os traços do cpf
		this.usuario.setCpf(cpf);
		
		cep = cep.replaceAll("\\-", "");
		boolean validacaoCpf = validaCpf(cpf);
		boolean validacaoSenha = validaSenha();
		
		if(validacaoCpf && validacaoSenha && validacaoNome) {
			salvar();
			// se os campos acima são verdadeiros, quer dizer que posso adicionar esse novo usuario
			
			return "/jogo/forca?faces-redirect=true";
		} else {
			return "#?faces-redirect=true";
		}
				
	}
	
	public boolean validaCpf(String cpf) {
		if(this.usuarioDao.validaCpf(this.usuario.getCpf())) {
			System.out.println("não existe nenhum usuario com esse cpf, pode cadastrar");
			return true;
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Já existe um usuário cadastrado com este CPF!",
					"Já existe um usuário cadastrado com este CPF!!"));
				return false;
		  }
	}
	
	public boolean validaSenha() {
		if(this.senha.equals(confirmaSenha)) {
			this.usuario.setSenha(gerador.generate(senha));
			
			return true;
		} else {
			System.out.println("as senhas não coincidem");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"As senhas não coincidem!", "As senhas não coincidem!"));
			return false;
		}
	}
	
	public boolean validaNome() {
		int contaNumero = 0;
		if(this.usuario.getNome().isEmpty()) {
			return false;
		}
		
		for(int i = 0; i< this.usuario.getNome().length(); i++) {
			if(Character.isDigit(this.getUsuario().getNome().charAt(i))) {
				
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Digite apenas Letras", "Digite apenas Letras"));
				contaNumero++;				
			}
		}
		
		if(contaNumero > 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public void validaCartao() {
		
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	

}
