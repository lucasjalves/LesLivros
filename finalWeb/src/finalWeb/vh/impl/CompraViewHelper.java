package finalWeb.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.Pedido;
import finalDominio.Item;
import finalDominio.Livro;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import finalDominio.EntidadeDominio;
import finalWeb.vh.IViewHelper;

public class CompraViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<Integer, Pedido> mapaUsuarios = (HashMap<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
		String operacao = request.getParameter("operacao");
		System.out.println(operacao);
		if(operacao.equals("ComprarItens"))
		{
			String txtId = (String)request.getSession().getAttribute("userid");
			int id = Integer.parseInt(txtId);
			Pedido p = mapaUsuarios.get(id);
			Integer indiceLivro = (Integer)request.getSession().getAttribute("indice");
			if(indiceLivro != null)
			{
				Item i = p.getItem().get(indiceLivro);
				request.getSession().setAttribute("qtdeLivrosPedido", i.getQtde());
				return i;
			}
			else
			{
				request.getSession().setAttribute("indice", 0);
				Item item = p.getItem().get(0);
				request.getSession().setAttribute("qtdeLivrosPedido", item.getQtde());
				return item;
			}

			
		}
		return null;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");

		if(operacao.equals("ComprarItens"))
		{
			Map<Integer, Pedido> mapaUsuarios = (HashMap<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
			String txtId = (String)request.getSession().getAttribute("userid");
			
			int id = Integer.parseInt(txtId);
			Pedido p = mapaUsuarios.get(id);
			
			List<EntidadeDominio> e = resultado.getEntidades();
			Item item = (Item)e.get(0);
					
			Integer indice = (Integer)request.getSession().getAttribute("indice"); 
			
			int qtdeLivroRestantes = item.getQtde();
			int qtdeLivroPedido = (int)request.getSession().getAttribute("qtdeLivrosPedido");
			
			
			if(qtdeLivroRestantes < qtdeLivroPedido)
			{
				request.getSession().setAttribute("falha", true);
			}
			int index = indice + 1;
			int qtde = p.getItem().size();
			System.out.println(index);
			System.out.println(qtde);
			if(index == qtde)
			{
				
				if(request.getSession().getAttribute("falha") != null)  //deu merda e volta pro carrinho
				{
					request.getSession().removeAttribute("falha");
					request.getSession().removeAttribute("indice");
					request.getSession().removeAttribute("qtdeLivrosPedido");
					d = request.getRequestDispatcher("Carrinho.jsp"); 
					d.forward(request,response);
					return;
				}
				else

					request.getSession().removeAttribute("falha");
					request.getSession().removeAttribute("indice");
					request.getSession().removeAttribute("qtdeLivrosPedido");
					d = request.getRequestDispatcher("Compra.jsp"); 
					d.forward(request,response);
					return;						
				}
			else
			{
				String url = "ComprarItens?indice=" + indice;
				request.getSession().setAttribute("indice", indice + 1);
				d = request.getRequestDispatcher(url);
				d.forward(request,response);
				return;						
			}
				
		}
		
	}

}
