package finalDominio;

import java.util.List;

public class Categoria extends EntidadeDominio {
	private List<SubCategoria> subcategorias;

	public List<SubCategoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<SubCategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}
	
	
}
