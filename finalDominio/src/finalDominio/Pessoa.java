package finalDominio;

import java.util.List;

public class Pessoa extends EntidadeDominio{
	private String email;
	private String senha;
	private String status;
	private Telefone telefone;
	private String nome;

	private List<Endereco> endereco;
	private List<Cartao> cartao;
	
	
	public List<Cartao> getCartao() {
		return cartao;
	}
	public void setCartao(List<Cartao> cartao) {
		this.cartao = cartao;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}
	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
}
