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
		if(operacao.equals("SALVAR"))
		{
			ItemTroca it = new ItemTroca();
			PedidoTroca pd = new PedidoTroca();
			Livro l = new Livro();
			List<ItemTroca> listTroca = new ArrayList<ItemTroca>();
			
			String qtdeLivro = request.getParameter("qtdeLivrosTroca");
			String idPedido = request.getParameter("idPedido");
			String idLivro = request.getParameter("idLivro");
			String idUsuario = request.getParameter("idUsuario");
			
			int qtde = Integer.parseInt(qtdeLivro);
			int idP = Integer.parseInt(idPedido);
			int idL = Integer.parseInt(idLivro);
			int idC = Integer.parseInt(idUsuario);
			
			pd.setIdCliente(idC);
			pd.setIdPedido(idP);
			
			l.setId(idL);
			it.setLivro(l);
			
			it.setQtde(qtde);
			listTroca.add(it);
			
			pd.setItensTroca(listTroca);
			pd.setStatus("trocar");
			String sts = request.getParameter("resultado");
			if(sts != null)
				pd.setStatus(sts);
			
			
			Calendar cal = Calendar.getInstance();
			Date date;
			
			date = cal.getTime();
			pd.setDtTroca(date);
			return pd;
			
			
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
			
			String validar = request.getParameter("valdidar");
			if(validar == null)
				d = request.getRequestDispatcher("RealizarTroca?operacao=SALVAR&validar=true&resultado="+resultado.getMsg());
			else
				d = request.getRequestDispatcher("pedidoTroca.jsp");
			
			d.forward(request, response);			
		}

		
	}
	
}
