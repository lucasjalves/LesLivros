package finalDominio;

import java.util.List;

public class Pessoa extends EntidadeDominio{
	private String email;
	private String senha;
	private String status;
	private String nome;
	private List<Endereco> endereco;
	private List<Cartao> cartao;
	private List<Pedido> pedidos;
	private List<Telefone> telefones;
	
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
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

	
}
