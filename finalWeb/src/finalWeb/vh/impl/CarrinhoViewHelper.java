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
		String stringId = (String)request.getSession().getAttribute("userid");
		
		if(stringId == null)
		{
			request.getSession().setAttribute("userid", "0");
			request.getSession().setAttribute("usuariodeslogado", true);
			stringId = "0";
		}
		
		Livro l = (Livro) request.getSession().getAttribute("livro");
		Map<Integer, Pedido> mapaUsuarios = (Map<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
		Map<Integer, Resultado> mapaResultado = (Map<Integer, Resultado>)request.getSession().getAttribute("mapaResultado");
		String operacao = (String)request.getParameter("operacao");		
		
		
		if(mapaUsuarios == null)
		{
			mapaUsuarios = new HashMap<Integer, Pedido>();
			Pedido p = new Pedido();
			p.setItem(new ArrayList<Item>());
			Item i = new Item();
			i.setLivro(l);
			i.setQtde(1);
			p.getItem().add(i);
			int idUsuario = Integer.parseInt(stringId);
			mapaUsuarios.put(idUsuario, p);
			return i;
		}
		
		if(operacao.equals("AdicionarItem") || operacao.equals("subtrairItem"))
		{
			String txtIdUsuario = (String)request.getSession().getAttribute("userid");
			int idUsuario = Integer.parseInt(txtIdUsuario);
			String txtIdLivro = request.getParameter("txtId");
			int idLivro = Integer.parseInt(txtIdLivro);
			Pedido p = mapaUsuarios.get(idUsuario);
			Livro liv;
			Item i = new Item();
			for(int k = 0; k < p.getItem().size(); k++)
			{
				if(p.getItem().get(k).getLivro().getId() == idLivro)
				{
					liv = p.getItem().get(k).getLivro();
					i = p.getItem().get(k);
					
				}
			}
			
			return i;
		}
		
		if(operacao.equals("removerItem"))
		{
			
		}
		/*
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
		*/
		if(mapaUsuarios != null)
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
		String stringId = (String)request.getSession().getAttribute("userid");
		
		if(!stringId.trim().equals("0"))
		{
			if(request.getSession().getAttribute("usuariodeslogado") != null)
			{
				Map<Integer, Pedido> mapaUsuarios = (Map<Integer, Pedido>) request.getSession().getAttribute("mapaUsuarios");
				Pedido p = mapaUsuarios.get(0);
				mapaUsuarios.put(Integer.parseInt(stringId), p);
				mapaUsuarios.remove(0);
				request.getSession().removeAttribute("usuariodeslogado");
				request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
			}	
		}
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		/*
		Map<Integer, Resultado> mapaResultado = (Map<Integer, Resultado>)request.getSession().getAttribute("mapaResultado");
		if(mapaResultado == null)
		{
			mapaResultado = new HashMap<Integer, Resultado>();
			request.getSession().setAttribute("mapaResultado", mapaResultado);
		}
		*/
		
		
		
		
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
													
					Item item = (Item)e.get(0);//pega o único item que retornou da fachada
					Pedido p = mapaUsuarios.get(id); //pega o pedido que está associado com a id do usuário
					List<Integer> listaIds= new ArrayList<Integer>();
					int indice = 0;
					if(p.getItem().size() == 0)      //se não existe uma lista de itens no pedido
					{
						p.setItem(new ArrayList<Item>()); 
						p.getItem().add(item);  
					}
					else
					{
						for(int i = 0; i < p.getItem().size(); i++)
						{
							if(item.getLivro().getId() == p.getItem().get(i).getLivro().getId())
								indice = i;
							
							listaIds.add(p.getItem().get(i).getLivro().getId()); 
						}
						
						if(!listaIds.contains(item.getLivro().getId()))
							p.getItem().add(item); 
						else
							p.getItem().get(indice).setQtde(p.getItem().get(indice).getQtde() + 1);
						
						mapaUsuarios.replace(id, p);  
						request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
					}
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
			//request.getSession().setAttribute("mapaResultado", mapaResultado);
			
			d= request.getRequestDispatcher("Carrinho.jsp");  
		} //operacação == VERIFICAR


		
		
		if(operacao.equals("AdicionarItem") || operacao.equals("subtrairItem"))
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
						Integer qtde;
						if(operacao.equals("subtrairItem"))
						{
							qtde = p.getItem().get(i).getQtde() - 1;
						}
						else
						{
							qtde = p.getItem().get(i).getQtde() + 1;
						}
						
						p.getItem().get(i).setQtde(qtde);
						break;
					}
				}
				
				mapaUsuarios.replace(idUsuario, p);
				
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
						if(operacao.equals("subtrairItem") && qtdeLivrosRestantes == p.getItem().get(i).getQtde())
						{
							System.out.println(qtdeLivrosRestantes);
							System.out.println(p.getItem().get(i).getQtde());
							Integer qtdeItem = p.getItem().get(i).getQtde() - 1;
							p.getItem().get(i).setQtde(qtdeItem);
						}
						else
						{
							p.getItem().get(i).setQtde(qtdeLivrosRestantes);
						}
						
						break;					
					}
				}
				
				mapaUsuarios.replace(idUsuario, p);
							
			}
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
			
			mapaUsuarios.replace(idUsuario, p);
			request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
			request.getSession().setAttribute("resultadoLivro", resultado);
			d = request.getRequestDispatcher("Carrinho.jsp");
			
			
		}
		/*
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
		*/
		d.forward(request,response);
	}
}
	
