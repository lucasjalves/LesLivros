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
			PessoaFisica pessoa = new PessoaFisica();
			Pedido pedido = new Pedido();
			
			pedido.setItem(new ArrayList<Item>());
			List<Pedido> pedidos = new ArrayList<Pedido>();
			
			pedidos.add(pedido);			
			pessoa.setPedidos(pedidos);
			
			Map<Integer, PessoaFisica> mapaUsuarios = new HashMap<Integer, PessoaFisica>();		
			mapaUsuarios.put(id, pessoa);	
			request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
			
		}
		
		
		
		Livro l = (Livro) request.getSession().getAttribute("livro");
		@SuppressWarnings("unchecked")
		Map<Integer, PessoaFisica> mapaUsuarios = (Map<Integer, PessoaFisica>)request.getSession().getAttribute("mapaUsuarios");
		String operacao = (String)request.getParameter("operacao");		
		
		
		if(operacao.equals("AdicionarItem") || operacao.equals("subtrairItem"))
		{
			String txtIdLivro = request.getParameter("txtId");
			int idLivro = Integer.parseInt(txtIdLivro);
			PessoaFisica pf = mapaUsuarios.get(id);
			Pedido pedido = pf.getPedidos().get(0);
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
			
			PessoaFisica p = mapaUsuarios.get(id);
			Pedido pedido = p.getPedidos().get(0);
				
	
			List<Item> itensPedido = pedido.getItem();
			itensPedido.add(i);
		
			pedido.setItem(itensPedido);
			
			return pedido;
		}
		PessoaFisica pf = mapaUsuarios.get(id);
		Pedido pedido = pf.getPedidos().get(0);
		
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
			Map<Integer, PessoaFisica> mapaUsuarios = (Map<Integer, PessoaFisica>)request.getSession().getAttribute("mapaUsuarios");
			Integer id = (Integer)request.getSession().getAttribute("userid");

			List<EntidadeDominio> e = resultado.getEntidades();  
			
			Pedido p = (Pedido)e.get(0);
			PessoaFisica pf = mapaUsuarios.get(id); 
			if(resultado.getMsg() != null)
			{
				String[] msg = resultado.getMsg().split("\\s");
				String indice = msg[msg.length-1];
				String qtde = msg[msg.length-2];
				
				int i = Integer.parseInt(indice);
				int qt = Integer.parseInt(qtde);

				p.getItem().get(i).setQtde(qt);
			}
			pf.getPedidos().set(0, p);
			mapaUsuarios.replace(id, pf);  
			
			request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);								
			request.getSession().setAttribute("resultadoCarrinho", resultado);			
			d= request.getRequestDispatcher("Carrinho.jsp");  
		} 


		
		
		if(operacao.equals("AdicionarItem") || operacao.equals("subtrairItem"))
		{
			@SuppressWarnings("unchecked")
			Map<Integer, PessoaFisica> mapaUsuarios = (Map<Integer, PessoaFisica>)request.getSession().getAttribute("mapaUsuarios");
			Integer id = (Integer)request.getSession().getAttribute("userid");

			List<EntidadeDominio> e = resultado.getEntidades();  
													
			Pedido p = (Pedido)e.get(0);
			PessoaFisica pf = mapaUsuarios.get(id); 

			if(resultado.getMsg() == null)
				pf.getPedidos().set(0, p);
			else
			{
				String[] msg = resultado.getMsg().split("\\s");
				String indice = msg[msg.length-1];
				String qtde = msg[msg.length-2];
				
				int i = Integer.parseInt(indice);
				int qt = Integer.parseInt(qtde);

				p.getItem().get(i).setQtde(qt);	
				pf.getPedidos().set(0, p);
			}
			mapaUsuarios.replace(id, pf);
			
			request.getSession().setAttribute("resultadoCarrinho", resultado);		
			request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
			
			
		}

		if(operacao.equals("removerItem"))
		{
			@SuppressWarnings("unchecked")
			Map<Integer, PessoaFisica> mapaUsuarios = (Map<Integer, PessoaFisica>)request.getSession().getAttribute("mapaUsuarios");
			Integer id = (Integer)request.getSession().getAttribute("userid");
			String indice = request.getParameter("indice");
			
			int i = Integer.parseInt(indice);
			
			PessoaFisica pf = mapaUsuarios.get(id); 
			Pedido pedido = pf.getPedidos().get(0);
			
			pedido.getItem().remove(i);
			
			pf.getPedidos().set(0, pedido);
			mapaUsuarios.replace(id, pf);
			request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
					
			
		}
		d = request.getRequestDispatcher("Carrinho.jsp");
		d.forward(request,response);
	}
}
	
