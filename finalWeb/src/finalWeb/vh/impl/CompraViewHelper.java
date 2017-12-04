package finalWeb.vh.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.Pedido;
import finalDominio.PessoaFisica;
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
import finalDominio.Endereco;
import finalDominio.EntidadeDominio;
import finalWeb.vh.IViewHelper;

public class CompraViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<Integer, Pedido> mapaUsuarios = (HashMap<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
		String operacao = request.getParameter("operacao");
		if(operacao.equals("SALVAR"))
		{
			String precoTotalTxt = request.getParameter("txtValor");
			String freteTxt = request.getParameter("txtFrete");

			Integer id = (Integer)request.getSession().getAttribute("userid");
			Pedido pedido = mapaUsuarios.get(id);
			
			
			double precoTotal = Double.parseDouble(precoTotalTxt);
			double frete = Double.parseDouble(freteTxt);
			
			String txtIndiceEndereco = request.getParameter("txtIndiceEnderecos");
			int i = Integer.parseInt(txtIndiceEndereco);
			
			
			Resultado resultado = (Resultado)request.getSession().getAttribute("resultadoLogin");
			
			List<EntidadeDominio> e = resultado.getEntidades();
			PessoaFisica pessoa =(PessoaFisica) e.get(0);
			
			Endereco end = pessoa.getEndereco().get(i);			
			pedido.setEndereco(end);
			
			pedido.setFrete(frete);
			pedido.setPrecoTotal(precoTotal);
			
			Calendar cal = Calendar.getInstance();
			Date date;
			
			date = cal.getTime();
			pedido.setDtPedido(date);
			pedido.setIdCliente(id);
		
			return pedido;
		}
		return null;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");

		if(operacao.equals("SALVAR"))
		{		
			request.getSession().setAttribute("resultadoCompra", resultado);
			if(resultado.getMsg() == null)
				d = request.getRequestDispatcher("Compra.jsp");
			else
				d = request.getRequestDispatcher("Carrinho.jsp");
			d.forward(request, response);
		}
		
	}

}
