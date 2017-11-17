package finalCore.negocio;

import java.util.List;

import finalCore.core.IStrategy;
import finalCore.dao.LivroDAO;
import finalDominio.EntidadeDominio;
import finalDominio.Item;
import finalDominio.Livro;

public class VerificarQuantidadeLivroEstoque implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
		Item itemCarrinho = (Item)entidade;
		Livro livroCarrinho = itemCarrinho.getLivro();
		LivroDAO dao = new LivroDAO();
		List<EntidadeDominio> entidadeLivro = dao.consultar(livroCarrinho);
		
		Livro l = (Livro)entidadeLivro.get(0);
		if(l.getQtdeEstoque() == 0)
		{
			return "Livro não disponível no estoque";
		}
		
		

		return null;
	}

}
