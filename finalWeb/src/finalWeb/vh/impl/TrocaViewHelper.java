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
			
			String idPedido = request.getParameter("idPedidoTroca");
			String idUsuario = request.getParameter("fk_cliente");
			String precoTxt = request.getParameter("desconto");
			String status = request.getParameter("atualizarStatus");
			
			int idP = Integer.parseInt(idPedido);
			int idC = Integer.parseInt(idUsuario);
			double preco = Double.parseDouble(precoTxt);
			
			pt.setIdCliente(idC);
			pt.setIdPedido(idP);
		
			it.setLivro(l);
			it.setPrecoLivro(preco);
			
			listTroca.add(it);
			
			pt.setItensTroca(listTroca);
			Calendar cal = Calendar.getInstance();
			Date date;
			
			date = cal.getTime();
			pt.setDtTroca(date);
			pt.setStatus("trocarItens");
			String idPedidoTxt = request.getParameter("idPedidoTroca");
			pt.setStatus(status);
			int id = Integer.parseInt(idPedidoTxt);
			pt.setIdPedido(id);
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
		if(operacao.equals("ALTERAR"))
		{
			
			if(resultado.getMsg() != null)
				d = request.getRequestDispatcher("RealizarTroca?operacao=ALTERAR&atualizarStatus="+resultado.getMsg());
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
		
	}
	
}
