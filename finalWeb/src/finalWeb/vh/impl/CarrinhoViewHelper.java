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
import finalDominio.Pedido;
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
			//List<Item> livros = (List<Item>)request.getSession().getAttribute("livros");
			Livro l = (Livro) request.getSession().getAttribute("livro");
			//Map<Integer, Integer> m = (Map<Integer, Integer>)request.getSession().getAttribute("mapaCarrinho");
			Map<Integer, Pedido> mapaUsuarios = (Map<Integer, Pedido>) request.getSession().getAttribute("mapaUsuarios");
			
			
			String txtId = (String)request.getSession().getAttribute("userid");
			Integer id = Integer.parseInt(txtId);
			if(mapaUsuarios == null)
			{
				mapaUsuarios = new HashMap<Integer, Pedido>();
			}
			String msg1 = "Nao ha mais livros restantes no estoque";
			msg1.trim();
			if(resultado.getMsg() == null || resultado.getMsg().trim().equals(msg1))
			{
				if(mapaUsuarios.containsKey(id)) //se o usuário já existe
				{
					List<EntidadeDominio> e = resultado.getEntidades();  //pegando o resultado que retorna da fachada
														// cast da lista de entidade dominio para item(só é possível ter um item na lista
					Item item = (Item)e.get(0);//pega o único item que retornou da fachada
					Pedido p = mapaUsuarios.get(id); //pega o pedido que está associado com a id do usuário
					if(p.getItem().size() == 0)      //se não existe uma lista de itens no pedido
					{
						p.setItem(new ArrayList<Item>()); 
						p.getItem().add(item);  
					}
					
					else // se o pedido já tem um item nele
					{
						for(int i = 0; i < p.getItem().size(); i++ )
						{
							int idLivroFachada = p.getItem().get(i).getLivro().getId(); 
							int idItemLivro = item.getLivro().getId();
							if(idLivroFachada == idItemLivro)
							{
								p.getItem().get(i).setQtde(p.getItem().get(i).getQtde() + 1);
								break;
							}
							else //se não existe
							{
								p.getItem().add(item); //adiciona na lista
								break;
							}
							
						}//for
						mapaUsuarios.replace(id, p);  //pega o id atual do usuário e insero pedido para ele
						request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
					}// if lista de item maior q 0
				}//if contains key
				
				if(!mapaUsuarios.containsKey(id))
				{
					List<EntidadeDominio> e = resultado.getEntidades();  //pegando o resultado que retorna da fachada

					Item item = (Item)e.get(0);//pega o único item que retornou da fachada
					Pedido p = mapaUsuarios.get(id); //pega o pedido que está associado com a id do usuário
					
					if(p == null)
					{
						p = new Pedido();
					}
					
					p.setItem(new ArrayList<Item>()); 
					p.getItem().add(item);  
					
					if(mapaUsuarios.size() == 0 || !mapaUsuarios.containsKey(id))
					{
						mapaUsuarios.put(id, p);  
					}
					else
					{
						mapaUsuarios.replace(id, p); 
					}
					request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
				} // if !containsKey
			}// if getMsg == null
			
			
			//request.getSession().setAttribute("livros", livros);
			request.getSession().setAttribute("resultadoLivro", resultado);
			request.getSession().setAttribute("mapaResultado", mapaResultado);
			
			d= request.getRequestDispatcher("Carrinho.jsp");  
		} //operacação == VERIFICAR


		
		
		if(operacao.equals("AdicionarItem"))
		{
			Map<Integer, Pedido> mapaUsuarios = (HashMap<Integer,Pedido>)request.getSession().getAttribute("mapaUsuarios");
			//mapaResultado = (Map<Integer, Resultado>)request.getSession().getAttribute("mapaResultado");
			String txtIdLivro = (String) request.getParameter("txtId");
			Integer idLivro = Integer.parseInt(txtIdLivro);
			String txtIdUsuario = (String)request.getSession().getAttribute("userid");
			Integer idUsuario = Integer.parseInt(txtIdUsuario);
			Pedido p = mapaUsuarios.get(idUsuario);
			if(resultado.getMsg() == null)
			{
				for(int i = 0; i < p.getItem().size(); i ++)
				{
					Livro l = p.getItem().get(i).getLivro();
					if(l.getId() == idLivro)
					{
						Integer qtde = p.getItem().get(i).getQtde() + 1;
						p.getItem().get(i).setQtde(qtde);
						break;
					}
				}
				
			}
			
			if(resultado.getMsg() != null)
			{

				List<EntidadeDominio> ed = resultado.getEntidades();
				Item item = (Item)ed.get(0);
				p = mapaUsuarios.get(idUsuario);
				Integer qtdeLivrosRestantes = item.getQtde();
				for(int i = 0; i < p.getItem().size(); i ++)
				{
					Livro l = p.getItem().get(i).getLivro();
					if(l.getId() == idLivro)
					{
						p.getItem().get(i).setQtde(qtdeLivrosRestantes);
						break;					
					}
				}
				
							
			}
			//List<Livro> livros = (List<Livro>)request.getSession().getAttribute("livros");
			//request.getSession().setAttribute("mapaCarrinho", m);
			request.getSession().setAttribute("resultadoLivro", resultado);
			request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
			d = request.getRequestDispatcher("Carrinho.jsp");
			
		}
		
		
		
		
		
		
		if(operacao.equals("subtrairItem"))
		{
			
			Map<Integer, Pedido> mapaUsuarios = (HashMap<Integer,Pedido>)request.getSession().getAttribute("mapaUsuarios");
			//mapaResultado = (Map<Integer, Resultado>)request.getSession().getAttribute("mapaResultado");
			String txtIdLivro = (String) request.getParameter("txtId");
			Integer idLivro = Integer.parseInt(txtIdLivro);
			String txtIdUsuario = (String)request.getSession().getAttribute("userid");
			Integer idUsuario = Integer.parseInt(txtIdUsuario);
			Pedido p = mapaUsuarios.get(idUsuario);
			if(resultado.getMsg() == null)
			{
				for(int i = 0; i < p.getItem().size(); i ++)
				{
					Livro l = p.getItem().get(i).getLivro();
					if(l.getId() == idLivro)
					{
						Integer qtde = p.getItem().get(i).getQtde() - 1;
						p.getItem().get(i).setQtde(qtde);
						break;
					}
				}
				
			}
			
			if(resultado.getMsg() != null)
			{

				List<EntidadeDominio> ed = resultado.getEntidades();
				Item item = (Item)ed.get(0);
				p = mapaUsuarios.get(idUsuario);
				Integer qtdeLivrosRestantes = item.getQtde();
				for(int i = 0; i < p.getItem().size(); i ++)
				{
					Livro l = p.getItem().get(i).getLivro();
					if(l.getId() == idLivro)
					{
						p.getItem().get(i).setQtde(qtdeLivrosRestantes);
						break;					
					}
				}
				
							
			}
			//List<Livro> livros = (List<Livro>)request.getSession().getAttribute("livros");
			//request.getSession().setAttribute("mapaCarrinho", m);
			request.getSession().setAttribute("resultadoLivro", resultado);
			request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
			d = request.getRequestDispatcher("Carrinho.jsp");
		}
		
		
		
		
		
		
		if(operacao.equals("removerItem"))
		{
			Map<Integer, Pedido> mapaUsuarios = (HashMap<Integer,Pedido>)request.getSession().getAttribute("mapaUsuarios");
			String txtIdLivro = (String) request.getParameter("id");
			Integer idLivro = Integer.parseInt(txtIdLivro);
			String txtIdUsuario = (String)request.getSession().getAttribute("userid");
			Integer idUsuario = Integer.parseInt(txtIdUsuario);
			Pedido p = mapaUsuarios.get(idUsuario);
			
			for(int i = 0; i < p.getItem().size(); i ++)
			{
				Livro l = p.getItem().get(i).getLivro();
				if(l.getId() == idLivro)
				{
					p.getItem().remove(i);
					break;
				}
			}
			
			request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
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
	
