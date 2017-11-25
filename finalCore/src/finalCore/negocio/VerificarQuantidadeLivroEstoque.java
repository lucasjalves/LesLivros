package finalCore.negocio;



import finalCore.core.IStrategy;

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
			return "Livro não disponivel no estoque";
		}
		if(itemCarrinho.getQtde() >= l.getQtdeEstoque())
		{
			return "Não há mais livros restantes no estoque";
		}

		return null;
	}

}
