package finalWeb.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		Map<Integer, Resultado> mapaResultado = (Map<Integer, Resultado>)request.getSession().getAttribute("mapaResultado");
		String operacao = (String)request.getParameter("operacao");		
		
		if(carrinhoLivros == null)
		{
			Item i = new Item();
			i.setQtde(1);
			i.setLivro(l);
			return i;
		}

		if(operacao.equals("AdicionarItem"))
		{
			String txtId = request.getParameter("txtId");
			int id = Integer.parseInt(txtId);
			Item i = new Item();
			Livro livro = new Livro();
			livro.setId(id);
			i.setLivro(livro);
			i.setQtde(m.get(id));
			return i;
		}
		
		if(operacao.equals("subtrairItem"))
		{
			String txtId = request.getParameter("txtId");
			int id = Integer.parseInt(txtId);
			Item i = new Item();
			Livro livro = new Livro();
			livro.setId(id);
			i.setLivro(livro);
			i.setQtde(m.get(id));
			return i;
		}
		
		if(operacao.equals("removerItem"))
		{
			String txtId = request.getParameter("id");
			int id = Integer.parseInt(txtId);

			m.remove(id, m.get(id));
			mapaResultado.remove(id, null);
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
				request.getSession().setAttribute("mapaResultado", mapaResultado);	
				return new Item();
		}
		
		if(operacao.equals("validar"))
		{
			String txtId = request.getParameter("idLivro");
			int id = Integer.parseInt(txtId);
			Livro liv = new Livro();
			liv.setId(id);
			Item it = new Item();
			it.setQtde(m.get(id));
			it.setLivro(liv);
			return it;
			
		}
		if(carrinhoLivros != null)
		{
			Item i = new Item();
			i.setQtde(1);
			i.setLivro(l);
			return i;
		}
		
		return new Item();
		
	}

	@Override
	public void setView(Resultado resultadoConsulta, Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		request.getSession().removeAttribute("resultadoConsultaLivro");
		
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		Map<Integer, Resultado> mapaResultado = (Map<Integer, Resultado>)request.getSession().getAttribute("mapaResultado");
		if(mapaResultado == null)
		{
			mapaResultado = new HashMap<Integer, Resultado>();
			request.getSession().setAttribute("mapaResultado", mapaResultado);
		}
		
		
		
		
		
		if(operacao.equals("VERIFICAR")){
			List<Livro> livros = (List<Livro>)request.getSession().getAttribute("livros");
			Livro l = (Livro) request.getSession().getAttribute("livro");
			Map<Integer, Integer> m = (Map<Integer, Integer>)request.getSession().getAttribute("mapaCarrinho");
			
			String msg = resultado.getMsg();
			if(m == null)
			{
				m = new HashMap<Integer, Integer>();
			}
				
			if(request.getSession().getAttribute("livros") == null)
			{
				livros = new ArrayList<Livro>();
			}
				
			if(msg == null)
			{
				if(m.containsKey(l.getId()))
				{
					m.replace(l.getId(), m.get(l.getId()) + 1);
					
				}
				if(!m.containsKey(l.getId()))
				{
					
					livros.add(l);
					m.put(l.getId(), 1);
					mapaResultado.put(l.getId(), resultado);
				
				}
			}
			if(msg != null)
			{
				if(resultado.getMsg().equals(msg))
				{
					List<EntidadeDominio> ed = resultado.getEntidades();
					Item i = (Item)ed.get(0);
					
					if(m.containsKey(l.getId()))
					{
						m.replace(l.getId(), i.getQtde());	
						
					}
					if(!m.containsKey(l.getId()))
					{
						livros.add(l);
						m.put(l.getId(), i.getQtde());	
						mapaResultado.put(l.getId(), resultado);
					
					}
								
				}				
			}

			
			request.getSession().setAttribute("livros", livros);
			request.getSession().setAttribute("mapaCarrinho", m);
			request.getSession().setAttribute("resultadoLivro", resultado);
			request.getSession().setAttribute("mapaResultado", mapaResultado);
			
			d= request.getRequestDispatcher("Carrinho.jsp");  
		}
		
		
		
		
		
		
		if(operacao.equals("AdicionarItem"))
		{
			Map<Integer, Integer> m = (HashMap<Integer,Integer>)request.getSession().getAttribute("mapaCarrinho");
			mapaResultado = (Map<Integer, Resultado>)request.getSession().getAttribute("mapaResultado");
			String txtId = (String) request.getParameter("txtId");
			Integer id = Integer.parseInt(txtId);
			String msg = resultado.getMsg();
			if(msg == null)
			{
				m.replace(id, m.get(id) + 1);
				
			}
			
			if(msg != null)
			{
				if(resultado.getMsg().equals(msg))
				{
					List<EntidadeDominio> ed = resultado.getEntidades();
					Item i = (Item)ed.get(0);
					m.replace(id, i.getQtde());
					mapaResultado.remove(id, resultado);
					
				}				
			}
			List<Livro> livros = (List<Livro>)request.getSession().getAttribute("livros");
			request.getSession().setAttribute("mapaCarrinho", m);
			request.getSession().setAttribute("resultadoLivro", resultado);
			request.getSession().setAttribute("mapaResultado", mapaResultado);
			d = request.getRequestDispatcher("Carrinho.jsp");
			
		}
		
		
		
		
		
		
		if(operacao.equals("subtrairItem"))
		{
			
			Map<Integer, Integer> m = (HashMap<Integer,Integer>)request.getSession().getAttribute("mapaCarrinho");
			mapaResultado = (Map<Integer, Resultado>)request.getSession().getAttribute("mapaResultado");
			String txtId = (String) request.getParameter("txtId");
			Integer id = Integer.parseInt(txtId);
			
			if(resultado.getMsg() == null)
			{
				m.replace(id, m.get(id) - 1);
				
			}
			else
			{
				String msgResultado = resultado.getMsg().trim();
				String erro1 = "Livro nao disponivel no estoque";
				String erro2 = "Nao ha mais livros restantes no estoque";
				erro1.trim();
				erro2.trim();
				if(msgResultado.equals(erro1))
				{
					m.replace(id, 0);			
					m.remove(id, null);
				}
				if(msgResultado.equals(erro2))
				{
					List<EntidadeDominio> ed = resultado.getEntidades();
					Item i = (Item)ed.get(0);
					
					if(m.get(id) > i.getQtde())
					{
						m.replace(id, i.getQtde());
					}
					else
					{
						m.replace(id, m.get(id) - 1);
						resultado.setMsg(null);
					}
				}				
			}
			request.getSession().setAttribute("mapaCarrinho", m);
			request.getSession().setAttribute("resultadoLivro", resultado);
			request.getSession().setAttribute("mapaResultado", mapaResultado);
			d = request.getRequestDispatcher("Carrinho.jsp");
		}
		
		
		
		
		
		
		if(operacao.equals("removerItem"))
		{
			request.getSession().setAttribute("resultadoLivro", resultado);
			d = request.getRequestDispatcher("Carrinho.jsp");
			
		}
		
		if(operacao.equals("validar"))
		{
			mapaResultado =  (Map<Integer, Resultado>) request.getSession().getAttribute("mapaResultado");
			List<EntidadeDominio> e = resultado.getEntidades();
			Item i = (Item)e.get(0);
			Livro l = i.getLivro();
			mapaResultado.replace(l.getId(), resultado);
			request.getSession().setAttribute("mapaResultado", mapaResultado);
			if(resultado.getMsg() != null)
			{
				Map<Integer, Integer> m = (HashMap<Integer,Integer>)request.getSession().getAttribute("mapaCarrinho");
				m.replace(l.getId(), i.getQtde());
				request.getSession().setAttribute("mapaCarrinho", m);
			}

			d = request.getRequestDispatcher("Carrinho.jsp");			
		}
		
		d.forward(request,response);
	}
	
}
