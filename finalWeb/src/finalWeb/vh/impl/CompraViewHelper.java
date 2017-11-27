package finalWeb.vh.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.Pedido;
import finalDominio.Item;
import finalDominio.Livro;

import java.util.Map;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import finalDominio.Cartao;
import finalDominio.CartoesCompra;
import finalDominio.Cupom;
import finalDominio.EntidadeDominio;
import finalWeb.vh.IViewHelper;

public class CompraViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<Integer, Pedido> mapaUsuarios = (HashMap<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
		String operacao = request.getParameter("operacao");
		if(operacao.equals("ComprarItens"))
		{
			String precoTotalTxt = request.getParameter("txtValor");
			String freteTxt = request.getParameter("txtFrete");
			Resultado resultadoCupom = (Resultado)request.getSession().getAttribute("resultadoCupom");

			String id = (String)request.getSession().getAttribute("userid");
			Map<Integer, Pedido> mapaUsuario = (Map<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");	
			int idUsuario = Integer.parseInt(id);
			Pedido pedido = mapaUsuario.get(idUsuario);
			
			double precoTotal = Double.parseDouble(precoTotalTxt);
			double frete = Double.parseDouble(freteTxt);
			
			pedido.setFrete(frete);
			pedido.setPrecoTotal(precoTotal);
		
			
			if(resultadoCupom != null)
			{
				List<EntidadeDominio> e = resultadoCupom.getEntidades();
				Cupom cupom = (Cupom)e.get(0);
				List<Cupom> cupons = new ArrayList<Cupom>();
				cupons.add(cupom);
				pedido.setCupom(cupons);;
			}
			return new Item();
		}
		if(operacao.equals("SALVAR"))
		{
			String qtdeCartoesTxt = request.getParameter("qtdeCartoes");

			
			int qtdeCartoes = Integer.parseInt(qtdeCartoesTxt);

			
			
			String id = (String)request.getSession().getAttribute("userid");
			int idUsuario = Integer.parseInt(id);
			
			Pedido p = mapaUsuarios.get(idUsuario);
			
			
			double precoPorCartao = (p.getPrecoTotal() / qtdeCartoes);
					
					
			List<CartoesCompra> cartoesCompra = new ArrayList<CartoesCompra>();
			for(int i = 0; i < qtdeCartoes; i ++)
			{
				String idCartaoTxt = request.getParameter("cartaoHidden"+i);
				int idCartao = Integer.parseInt(idCartaoTxt);
				Cartao c = new Cartao();
				c.setId(idCartao);
				CartoesCompra cc = new CartoesCompra();
				cc.setCartao(c);
				cc.setValorPago(precoPorCartao);
				cartoesCompra.add(cc);
			}
			p.setCartoesCompra(cartoesCompra);

			Calendar cal = Calendar.getInstance();
			Date date;
			
			date = cal.getTime();
			p.setDtPedido(date);
			
			return p;
			
		}
		return null;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");

		if(operacao.equals("ComprarItens"))
		{		
			d = request.getRequestDispatcher("Compra.jsp");
			d.forward(request, response);
		}
	}

}
