package finalCore.negocio;



import java.util.List;

import finalCore.core.IStrategy;
import finalCore.dao.LivroDAO;
import finalDominio.EntidadeDominio;
import finalDominio.Item;
import finalDominio.Livro;
import finalDominio.Pedido;

public class VerificarQtdeLivroEstoque implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
		Pedido pedido = (Pedido)entidade;
		
		Item itemCarrinho = pedido.getItem().get(pedido.getItem().size() - 1);
		LivroDAO ld = new LivroDAO();
		Livro livroPedido = itemCarrinho.getLivro();
		List<EntidadeDominio> e = ld.consultar(livroPedido);
		
		Livro l = (Livro)e.get(0);
		int id = l.getId();
		int qtde = l.getQtdeEstoque();
		int indice =  pedido.getItem().size() - 1;
		
		if(l.getQtdeEstoque() == 0)
		{
			return "Livro não disponivel no estoque " + id + " " + qtde + " " + indice;
		}
		if(itemCarrinho.getQtde() > l.getQtdeEstoque())
		{
			return "Não há mais livros restantes no estoque " + id + " " + qtde + " " + indice;
		}

		return null;
	}

}
