package finalWeb.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.EntidadeDominio;
import finalDominio.ItemTroca;
import finalDominio.Livro;
import finalDominio.PedidoTroca;
import finalDominio.PessoaFisica;
import finalWeb.vh.IViewHelper;

public class TrocaViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		if(operacao.equals("CONSULTAR"))
		{
			return new PedidoTroca();
		}
		if(operacao.equals("SALVAR"))
		{
			if(request.getSession().getAttribute("pedidoTroca") != null)
			{
				String status = request.getParameter("status");
				PedidoTroca pt = (PedidoTroca)request.getSession().getAttribute("pedidoTroca");
				pt.setStatus(status);
				request.getSession().removeAttribute("pedidoTroca");
				return pt;
			}
			else
			{
				ItemTroca itemTroca = new ItemTroca();
				PedidoTroca pedidoTroca = new PedidoTroca();
				Livro l = new Livro();
				List<ItemTroca> listTroca = new ArrayList<ItemTroca>();
				
				String idPedidoTxt = request.getParameter("idPedido");
				String idUsuarioTxt = request.getParameter("idUsuario");
				String precoTxt = request.getParameter("preco");
				String status = request.getParameter("status");
				String idLivroTxt = request.getParameter("idLivro");
				String qtdeLivrosTxt = request.getParameter("qtdeLivrosTroca");
				
				int idPedido = Integer.parseInt(idPedidoTxt);
				int idUsuario = Integer.parseInt(idUsuarioTxt);
				double preco = Double.parseDouble(precoTxt);
				int idLivro = Integer.parseInt(idLivroTxt);
				int qtdeLivros = Integer.parseInt(qtdeLivrosTxt);
				
				l.setId(idLivro);
				
				itemTroca.setQtde(qtdeLivros);
				itemTroca.setLivro(l);
				itemTroca.setPrecoLivro(preco);
				listTroca.add(itemTroca);
				
				pedidoTroca.setItensTroca(listTroca);
				
				Calendar cal = Calendar.getInstance();
				Date date;			
				date = cal.getTime();
				
				pedidoTroca.setDtTroca(date);			
				pedidoTroca.setIdCliente(idUsuario);
				pedidoTroca.setIdPedido(idPedido);
				pedidoTroca.setStatus(status);

				request.getSession().setAttribute("pedidoTroca", pedidoTroca);
				return pedidoTroca;				
			}

		}
		if(operacao.equals("ALTERAR"))
		{
			if(request.getSession().getAttribute("pedidoTroca") != null)
			{
				String status = request.getParameter("status");
				PedidoTroca pt = (PedidoTroca)request.getSession().getAttribute("pedidoTroca");
				pt.setStatus(status);
				request.getSession().removeAttribute("pedidoTroca");
				return pt;
			}
			String idPedidoTroca = request.getParameter("idPedidoTroca");
			String descontoTxt = request.getParameter("desconto");
			String fkClienteTxt = request.getParameter("fk_cliente");
			String qtdeTxt = request.getParameter("qtde");
			String status = request.getParameter("status");
			
			int id = Integer.parseInt(idPedidoTroca);
			double valor = Double.parseDouble(descontoTxt);
			int idCliente = Integer.parseInt(fkClienteTxt);
			int qtde = Integer.parseInt(qtdeTxt);

			
			ItemTroca i  = new ItemTroca();
			i.setPrecoLivro(valor);
			i.setQtde(qtde);
			
			List<ItemTroca> lista = new ArrayList<ItemTroca>();
			lista.add(i);
			
			PedidoTroca pedidoTroca = new PedidoTroca();	
			pedidoTroca.setIdPedido(id);
			pedidoTroca.setIdCliente(idCliente);
			pedidoTroca.setItensTroca(lista);
			pedidoTroca.setStatus(status);
			request.getSession().setAttribute("pedidoTroca", pedidoTroca);
			return pedidoTroca;
		}
		return null;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		if(operacao.equals("SALVAR"))
		{
			
			if(resultado.getMsg() != null)
				d = request.getRequestDispatcher("RealizarTroca?operacao=SALVAR&status="+resultado.getMsg());
			else
			{
				Resultado r = (Resultado)request.getSession().getAttribute("resultadoLogin");
				List<EntidadeDominio> e = r.getEntidades();
				PessoaFisica pf = (PessoaFisica)e.get(0);
				String email = pf.getEmail();
				String senha = pf.getSenha();
				String url = "SalvarCliente?operacao=LOGIN&txtEmail="+email+"&txtPwd="+senha+"&local=/iframes/pedidos.jsp";
				d = request.getRequestDispatcher(url);
			}
				
			
			d.forward(request, response);			
		}
		if(operacao.equals("CONSULTAR"))
		{
			request.getSession().setAttribute("resultadoTrocas", resultado);
			d = request.getRequestDispatcher("iframes/listatrocas.jsp");
			d.forward(request, response);
		}
		
		if(operacao.equals("ALTERAR"))
		{
	
			String local = request.getParameter("local");
			if(resultado.getMsg() != null)	
				d = request.getRequestDispatcher("RealizarTroca?operacao=ALTERAR&status="+resultado.getMsg()+"&local="+local);	
			
			else
				{
				Resultado r = (Resultado)request.getSession().getAttribute("resultadoLogin");
				List<EntidadeDominio> e = r.getEntidades();
				PessoaFisica pf = (PessoaFisica)e.get(0);
				String email = pf.getEmail();
				String senha = pf.getSenha();
				String url = "SalvarCliente?operacao=LOGIN&txtEmail="+email+"&txtPwd="+senha+"&local="
						+ "/iframes/listatrocas.jsp&local="+local;
				d = request.getRequestDispatcher(url);
				}
			
			
			d.forward(request, response);
				
		}
		
	}
	
}
