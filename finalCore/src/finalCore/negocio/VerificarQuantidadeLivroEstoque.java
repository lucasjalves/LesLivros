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
		Livro l = itemCarrinho.getLivro();

		if(l.getQtdeEstoque() == 0)
		{
			return "Livro n�o dispon�vel no estoque";
		}
		if(itemCarrinho.getQtde() >= l.getQtdeEstoque())
		{
			return "N�o h� mais livros restantes no estoque";
		}

		return null;
	}

}
