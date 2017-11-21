package finalWeb.vh.impl;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.EntidadeDominio;
import finalDominio.Item;
import finalDominio.Pedido;
import finalWeb.vh.IViewHelper;

public class PedidoViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		if(operacao.equals("realizarPedido"))
		{
			Map<Integer, Integer> m = (Map<Integer, Integer>)request.getSession().getAttribute("mapaCarrinho");
			List<Item> itensCarrinho = (List<Item>)request.getSession().getAttribute("livros");
			Pedido p = new Pedido();
			String precoTxt = String.format("%.2f", request.getParameter("precoTotal"));
			Double precoTotal = Double.parseDouble(precoTxt);
			for(int i = 0; i < itensCarrinho.size(); i ++)
			{
				p.getItem().add(itensCarrinho.get(i));
				int qtde = itensCarrinho.get(i).getQtde();
				p.getItem().get(i).setQtde(qtde);
				p.setPrecoTotal(precoTotal);
			}
			return p;
		}
		return null;
	}

	@Override
	public void setView(Resultado resultadoConsulta, Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

}
