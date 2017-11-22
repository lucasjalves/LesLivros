package finalDominio;

public class Categoria extends EntidadeDominio {
	private SubCategoria subcategoria;

	public SubCategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(SubCategoria subcategoria) {
		this.subcategoria = subcategoria;
	}
	
}
