package finalWeb.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.EntidadeDominio;
import finalDominio.Item;
import finalDominio.Livro;
import finalWeb.vh.IViewHelper;

public class CarrinhoViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Resultado resultado = (Resultado) request.getAttribute("resultadoConsultaLivroCompra");
		Resultado carrinho = (Resultado) request.getAttribute("carrinho"); 
		List<Item> itens;
		List<EntidadeDominio> livros = resultado.getEntidades();
		Livro livro = (Livro) livros.get(0);
		
		if(carrinho == null)
		{
			itens = new ArrayList<Item>();
			Item i = new Item();
			i.setLivro(livro);
			itens.add(i);
			request.getSession().setAttribute("carrinho", itens);
			return i;
		}
		else
		{
			List<Item> entidadesCarrinho =  (List<Item>) request.getAttribute("carrinho");
			Item i = new Item();
			i.setLivro(livro);
			entidadesCarrinho.add(i);		
			return i;
		}
	}

	@Override
	public void setView(Resultado resultadoConsulta, Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		RequestDispatcher d = null;
		String operacao = (String)request.getAttribute("operacao");
		if(operacao.equals("INFORMACOESLIVRO")){
			request.getSession().setAttribute("resultadoLivro", resultado);
			d= request.getRequestDispatcher("Carrinho.jsp");  
		}			
		
		d.forward(request,response);
		
	}
	

}
