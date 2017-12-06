package finalDominio;

public class Item extends EntidadeDominio {
	private Livro livro;
	private Integer qtde;
	private double precoLivro;
	
	
	public double getPrecoLivro() {
		return precoLivro;
	}

	public void setPrecoLivro(double precoLivro) {
		this.precoLivro = precoLivro;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
}
