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
		List<Livro> carrinhoLivros = (List<Livro>) request.getSession().getAttribute("livros"); 
		Map<Integer, Integer> m = (Map<Integer, Integer>)request.getSession().getAttribute("mapaCarrinho");
		
		String operacao = (String)request.getParameter("operacao");

			
		if(carrinhoLivros == null)
		{
			Map<Integer, Integer> mapLivros = new HashMap<Integer, Integer>();
			List<Livro> livros = new ArrayList<Livro>();
			livros.add(l);
			request.getSession().setAttribute("livros", livros);
			mapLivros.put(l.getId(), 1);
			request.getSession().setAttribute("mapaCarrinho", mapLivros);
			Item i = new Item();
			i.setLivro(l);
			return i;
		}
		

		
		if(m != null && operacao.equals("AdicionarItem"))
		{
			
			m = (HashMap<Integer,Integer>)request.getSession().getAttribute("mapaCarrinho");
			String txtId = (String) request.getParameter("txtId");
			Integer id = Integer.parseInt(txtId);
			if(m.containsKey(id))
			{
				m.replace(id, m.get(id) + 1);
				request.getSession().setAttribute("mapaCarrinho", m);
				Item i = new Item();
				i.setLivro(l);
				return i;
			}
		}
		
		if(m != null && operacao.equals("subtrairItem"))
		{
			m = (HashMap<Integer,Integer>)request.getSession().getAttribute("mapaCarrinho");
			String txtId = (String) request.getParameter("txtId");
			Integer id = Integer.parseInt(txtId);
			if(m.containsKey(id))
			{
				m.replace(id, m.get(id) - 1);
				request.getSession().setAttribute("mapaCarrinho", m);
				Item i = new Item();
				return i;
			}
		}
		
		if(m != null && operacao.equals("removerItem"))
		{
			String txtId = request.getParameter("id");
			int id = Integer.parseInt(txtId);

			m.remove(id, m.get(id));
			for(int i = 0; i < carrinhoLivros.size(); i ++)
			{
				if(carrinhoLivros.get(i).getId() == id)
				{
					carrinhoLivros.remove(i);
					break;
				}
			}
				request.getSession().setAttribute("livros", carrinhoLivros);
				request.getSession().setAttribute("mapaCarrinho", m);		
				return new Item();
		}
		
		if(carrinhoLivros != null)
		{
			if(m.containsKey(l.getId()))
			{
				m.put(l.getId(), m.get(l.getId()) + 1);
				request.getSession().setAttribute("mapaCarrinho", m);
			}
			if(!m.containsKey(l.getId()))
			{
				carrinhoLivros.add(l);
				m.put(l.getId(), 1);
				request.getSession().setAttribute("livros", carrinhoLivros);
				request.getSession().setAttribute("mapaCarrinho", m);	
			}
			return new Item();
		}
		
		return new Item();
		
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
		if(operacao.equals("removerItem") || operacao.equals("AdicionarItem") || operacao.equals("subtrairItem"))
		{
			d = request.getRequestDispatcher("Carrinho.jsp");
		}
		d.forward(request,response);
		
	}
	

}
