package finalWeb.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Livro l = (Livro) request.getSession().getAttribute("livro");
		List<Item> carrinho = (List<Item>) request.getSession().getAttribute("carrinho"); 
		List<Item> itens;

		if(request.getSession().getAttribute("mapaCarrinho") != null)
		{
			Map<Integer, Integer> m = (HashMap<Integer,Integer>)request.getSession().getAttribute("mapaCarrinho");
			String txtId = (String) request.getAttribute("id");
			Integer id = Integer.parseInt(txtId);
			if(m.containsKey(id))
			{
				Integer qtde = m.get(id);
				m.replace(id, qtde + 1);
				
				return null;
			}
		}
		if(carrinho == null)
		{
			itens = new ArrayList<Item>();
			Item i = new Item();
			i.setLivro(l);
			itens.add(i);
			request.getSession().setAttribute("carrinho", itens);
			
			return i;
		}
		else
		{
			Item i = new Item();
			i.setLivro(l);
			carrinho.add(i);		
			return i;
		}
		
	}

	@Override
	public void setView(Resultado resultadoConsulta, Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		if(operacao.equals("VERIFICAR")){
			request.getSession().setAttribute("resultadoLivro", resultado);
			d= request.getRequestDispatcher("Carrinho.jsp");  
		}			
		
		if(operacao.equals("adicionarItemCarrinho"))
		{
			d = request.getRequestDispatcher("Carrinho.jsp");
		}
		d.forward(request,response);
		
	}
	

}
