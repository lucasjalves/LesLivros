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
import finalWeb.vh.IViewHelper;

public class TrocaViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		if(operacao.equals("CONSULTAR"))
		{
			return new PedidoTroca();
		}
		if(operacao.equals("ALTERAR"))
		{
			ItemTroca it = new ItemTroca();
			PedidoTroca pt = new PedidoTroca();
			Livro l = new Livro();
			List<ItemTroca> listTroca = new ArrayList<ItemTroca>();
			
			String qtdeLivro = request.getParameter("qtdeLivrosTroca");
			String idPedido = request.getParameter("idPedido");
			String idLivro = request.getParameter("idLivro");
			String idUsuario = request.getParameter("idUsuario");
			String precoTxt = request.getParameter("preco");
			
			int qtde = Integer.parseInt(qtdeLivro);
			int idP = Integer.parseInt(idPedido);
			int idL = Integer.parseInt(idLivro);
			int idC = Integer.parseInt(idUsuario);
			double preco = Double.parseDouble(precoTxt);
			
			pt.setIdCliente(idC);
			pt.setIdPedido(idP);
		
			l.setId(idL);
			it.setLivro(l);
			it.setPrecoLivro(preco);
			
			
			it.setQtde(qtde);
			listTroca.add(it);
			
			pt.setItensTroca(listTroca);
			Calendar cal = Calendar.getInstance();
			Date date;
			
			date = cal.getTime();
			pt.setDtTroca(date);
			
			String idPedidoTxt = request.getParameter("idPedidoTroca");
			String status = request.getParameter("atualizarStatus");
			if(status != null)
				pt.setStatus(status);
			int id = Integer.parseInt(idPedidoTxt);
			pt.setIdPedido(id);
			request.getSession().setAttribute("pedidoTroca", pt);
			return pt;
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
				d = request.getRequestDispatcher("RealizarTroca?operacao=SALVAR&validar=true&resultado="+resultado.getMsg());
			else
				d = request.getRequestDispatcher("iframes/pedidos.jsp");
			
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
			if(resultado.getMsg() != null)
				d = request.getRequestDispatcher("RealizarTroca?operacao=ALTERAR&atualizarStatus="+resultado.getMsg());
			else
				d = request.getRequestDispatcher("ValidarCupom?operacao=SALVAR&tipoCupom=troca");
		}

		
	}
	
}
