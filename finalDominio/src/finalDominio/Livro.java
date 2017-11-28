package finalDominio;

import java.util.List;

public class Livro extends EntidadeDominio{
	private String autor; 
	private String ano; 
	private String titulo; 
	private String editora; 
	private String edicao; 
	private String ISBN; 
	private String npaginas;
	private String sinopse;
	private String status; 
	private String altura; 
	private String peso; 
	private String profundidade; 
	private String largura;
	private List<Categoria> categoria;
	
	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}

	private GrupoPrecificacao gp;
	private Integer qtdeEstoque;
	private Double preco;
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getNpaginas() {
		return npaginas;
	}

	public void setNpaginas(String npaginas) {
		this.npaginas = npaginas;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public GrupoPrecificacao getGp() {
		return gp;
	}

	public void setGp(GrupoPrecificacao gp) {
		this.gp = gp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(String profundidade) {
		this.profundidade = profundidade;
	}

	public Integer getQtdeEstoque() {
		return qtdeEstoque;
	}

	public void setQtdeEstoque(Integer qtdeEstoque) {
		this.qtdeEstoque = qtdeEstoque;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getLargura() {
		return largura;
	}

	public void setLargura(String largura) {
		this.largura = largura;
	}

}
