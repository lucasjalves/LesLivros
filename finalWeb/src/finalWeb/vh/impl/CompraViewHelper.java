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
			String txtIdLivro = request.getParameter("id");
			if(txtIdLivro != null)
			{
				int idLivro = Integer.parseInt(txtIdLivro);
				String qtdeTxt = request.getParameter("qtde");
				int qtde = Integer.parseInt(qtdeTxt);
				Item i = new Item();
				i.setQtde(qtde);
				Livro l = new Livro();
				l.setId(id);
				i.setLivro(l);
				return i;
			}
			else
			{
				Item item = p.getItem().get(0);
				return item;
			}
			  //apenas o primeiro livro item é envidado para fachada, os livros restantes irão ser tratados na setview
			
		}
		return null;
	}

	@Override
	public void setView(Resultado resultadoConsulta, Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		Integer qtdeLivrosVerificados = (Integer)request.getSession().getAttribute("livrosVerificados");
		if(operacao.equals("ComprarItens"))
		{
			if(qtdeLivrosVerificados == null)
			{
				qtdeLivrosVerificados = 0;
				request.getSession().setAttribute("livrosVerificados", qtdeLivrosVerificados);
			}
			if(resultado.getMsg() != null)
			{
				Map<Integer, Pedido> mapaUsuarios = (HashMap<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
				String txtId = (String)request.getSession().getAttribute("userid");
				int id = Integer.parseInt(txtId);
				Pedido p = mapaUsuarios.get(id);
				List<EntidadeDominio> e = resultado.getEntidades();
				Item item = (Item)e.get(0);
				Livro livroFachada = item.getLivro();
				if(qtdeLivrosVerificados < p.getItem().size())
				{	
					for(int i = 0; i < p.getItem().size(); i ++)
					{
						Livro l = p.getItem().get(i).getLivro();
						if(item.getQtde() < p.getItem().get(i).getQtde() && l.getId() == livroFachada.getId())
						{
							int idLivroPedido = p.getItem().get(i).getLivro().getId();
							String url = "ComprarItens?id=" + idLivroPedido + "&qtde=" + p.getItem().get(i).getQtde() + "&operacao=ComprarItens"; 
							d = request.getRequestDispatcher(url); 
							p.getItem().get(i).setQtde(item.getQtde()); 
							Integer qtdeLivros = (Integer)request.getSession().getAttribute("livrosVerificados");
							request.getSession().setAttribute("livrosVerificados", qtdeLivros + 1);
							request.getSession().setAttribute("falha", true);
							break;
						}
						else
						{
							int idLivroPedido = p.getItem().get(i).getLivro().getId();
							String url = "ComprarItens?id=" + idLivroPedido + "&qtde=" + p.getItem().get(i).getQtde() + "&operacao=ComprarItens"; 
							d = request.getRequestDispatcher(url); 	
							break;
						}
					

					}
					d.forward(request,response);
					return;
				}
				if((boolean)request.getSession().getAttribute("falha") == true)  //deu merda e volta pro carrinho
				{
					request.getSession().removeAttribute("falha");
					d = request.getRequestDispatcher("Carrinho.jsp"); 
					d.forward(request,response);
					return;
				}
				else
				{
					request.getSession().removeAttribute("falha");
					d = request.getRequestDispatcher("Compra.jsp"); 
					d.forward(request,response);
					return;	
				}
			}
			else
			{
				d = request.getRequestDispatcher("Compra.jsp"); 
				d.forward(request,response);	
				return;
			}
		}
		
	}

}
