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
import finalDominio.Pedido;

import finalDominio.PessoaFisica;
import finalWeb.vh.IViewHelper;

public class CarrinhoViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Integer id = (Integer)request.getSession().getAttribute("userid");
		String qtdeLivrosTxt = request.getParameter("qtdeLivros");
		int qtdeLivros = 0;
		if(qtdeLivrosTxt != null)
			qtdeLivros = Integer.parseInt(qtdeLivrosTxt);
		
		if(id == null)
		{
			request.getSession().setAttribute("userid", 0);
			id = 0;
			Pedido pedido = new Pedido();
			
			pedido.setItem(new ArrayList<Item>());
			
			Map<Integer, Pedido> mapaUsuarios = new HashMap<Integer, Pedido>();		
			mapaUsuarios.put(id, pedido);	
			request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
			
		}
		
		
		
		Livro l = (Livro) request.getSession().getAttribute("livro");
		@SuppressWarnings("unchecked")
		Map<Integer, Pedido> mapaUsuarios = (Map<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
		String operacao = (String)request.getParameter("operacao");		
		
		
		if(operacao.equals("AdicionarItem") || operacao.equals("subtrairItem"))
		{
			String txtIdLivro = request.getParameter("txtId");
			int idLivro = Integer.parseInt(txtIdLivro);
			Pedido pedido = mapaUsuarios.get(id);
			for(int i = 0; i < pedido.getItem().size(); i++)
			{
				int idLivroPedido = pedido.getItem().get(i).getLivro().getId();
				if(idLivroPedido == idLivro)
				{	
					int qtde = pedido.getItem().get(i).getQtde();
					if(operacao.equals("AdicionarItem"))
						 qtde = qtde + 1;
					else
						qtde = qtde - 1;
					pedido.getItem().get(i).setQtde(qtde);
					break;
				}
			}
			
			return pedido;
		}
		
		if(operacao.equals("VERIFICAR"))
		{
			
			Item i = new Item();
			i.setLivro(l);
			i.setQtde(qtdeLivros);
			
			Pedido pedido = mapaUsuarios.get(id);
			if(pedido == null)
				pedido = new Pedido();
			
			if(pedido.getItem() == null)
				pedido.setItem(new ArrayList<Item>());
			
			List<Item> itens = pedido.getItem();
			
			itens.add(i);
			pedido.setItem(itens);
			
			return pedido;
		}
		Pedido pedido = mapaUsuarios.get(id);
		
		return pedido;
		
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		request.getSession().removeAttribute("resultadoConsultaLivro");

		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
	
		if(operacao.equals("VERIFICAR")){
			@SuppressWarnings("unchecked")
			Map<Integer, Pedido> mapaUsuarios = (Map<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
			Integer id = (Integer)request.getSession().getAttribute("userid");

			List<EntidadeDominio> e = resultado.getEntidades();  
			
			Pedido p = (Pedido)e.get(0);
			if(resultado.getMsg() != null)
			{
				String[] msg = resultado.getMsg().split("\\s");
				String indice = msg[msg.length-1];
				String qtde = msg[msg.length-2];
				
				int i = Integer.parseInt(indice);
				int qt = Integer.parseInt(qtde);

				p.getItem().get(i).setQtde(qt);
			}
			
			if(mapaUsuarios.containsKey(id))
				mapaUsuarios.replace(id, p);  
			
			if(!mapaUsuarios.containsKey(id))
				mapaUsuarios.put(id, p); 
			
			request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);								
			request.getSession().setAttribute("resultadoCarrinho", resultado);			
			d= request.getRequestDispatcher("Carrinho.jsp");  
		} 


		
		
		if(operacao.equals("AdicionarItem") || operacao.equals("subtrairItem"))
		{
			@SuppressWarnings("unchecked")
			Map<Integer, Pedido> mapaUsuarios = (Map<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
			Integer id = (Integer)request.getSession().getAttribute("userid");

			List<EntidadeDominio> e = resultado.getEntidades();  
													
			Pedido p = (Pedido)e.get(0);


			if(resultado.getMsg() != null)
			{
				String[] msg = resultado.getMsg().split("\\s");
				String indice = msg[msg.length-1];
				String qtde = msg[msg.length-2];
				
				int i = Integer.parseInt(indice);
				int qt = Integer.parseInt(qtde);

				p.getItem().get(i).setQtde(qt);	

			}
			mapaUsuarios.replace(id, p);
			
			request.getSession().setAttribute("resultadoCarrinho", resultado);		
			request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
			
			
		}

		if(operacao.equals("removerItem"))
		{
			@SuppressWarnings("unchecked")
			Map<Integer, Pedido> mapaUsuarios = (Map<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
			Integer id = (Integer)request.getSession().getAttribute("userid");
			String indice = request.getParameter("indice");
			
			int i = Integer.parseInt(indice);
			
			Pedido pedido = mapaUsuarios.get(id); 
			
			pedido.getItem().remove(i);
			
			mapaUsuarios.replace(id, pedido);
			request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
					
			
		}
		d = request.getRequestDispatcher("Carrinho.jsp");
		d.forward(request,response);
	}
}