package finalWeb.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;

import finalDominio.Pedido;
import finalDominio.PessoaFisica;


import java.util.Map;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import finalDominio.Cartao;
import finalDominio.CartoesCompra;
import finalDominio.Endereco;
import finalDominio.EntidadeDominio;
import finalWeb.vh.IViewHelper;

public class CompraViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<Integer, Pedido> mapaUsuarios = (HashMap<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
		String operacao = request.getParameter("operacao");
		
		String local = request.getParameter("local");
		System.out.println(local);
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
			
			request.getSession().setAttribute("pedidoUser", pedido);
			mapaUsuarios.remove(id);
			return pedido;
		}
		if(operacao.equals("ALTERAR"))
		{
			
			Pedido p = (Pedido)request.getSession().getAttribute("pedido");
			request.getSession().removeAttribute("pedido");
			String status = request.getParameter("status");
			String idTxt = request.getParameter("id");
			if(idTxt != null)
			{
				if(p == null)
					p = new Pedido();
				int id = Integer.parseInt(idTxt);
				p.setId(id);
			}
			p.setStatus(status);
			
			return p;
		}
		if(operacao.equals("CONSULTAR") && local == null)
		{
			Resultado resultado = (Resultado) request.getSession().getAttribute("resultadoLogin");
			List<EntidadeDominio> e = resultado.getEntidades();
			PessoaFisica pf = (PessoaFisica)e.get(0);
			
			
			Pedido pedido = (Pedido)request.getSession().getAttribute("pedidoUser");
			
			String qtdeCartoesTxt = request.getParameter("qtdeCartoes");
			int qtdeCartoes = Integer.parseInt(qtdeCartoesTxt);
			
			List<CartoesCompra> cartoes = new ArrayList<CartoesCompra>();
			for(int i = 0; i < qtdeCartoes; i++)
			{
				double v = pedido.getPrecoTotal() / qtdeCartoes;
				Cartao c = new Cartao();
				CartoesCompra cc = new CartoesCompra();
				
				String txtId = request.getParameter("cartaoHidden" + i);
				int id = Integer.parseInt(txtId);
				c.setId(id);
				cc.setValorPago(v);
				cc.setCartao(c);
				cartoes.add(cc);
				pedido.setCartoesCompra(cartoes);
			}
			request.getSession().setAttribute("pedido", pedido);
			if(pedido.getStatus() == null)
				pedido.setStatus("EM PROCESSAMENTO");
			return pedido;
		}
		
		if(operacao.equals("CONSULTAR") && local != null)
		{
			Pedido p = new Pedido();
			String idTxt = request.getParameter("id");
			int id = Integer.parseInt(idTxt);
			
			p.setId(id);
			String status = request.getParameter("status");
			p.setStatus(status);
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
		String local = request.getParameter("local");
		
		if(operacao.equals("SALVAR"))
		{		
			request.getSession().setAttribute("resultadoCompra", resultado);
			if(resultado.getMsg() == null)
				d = request.getRequestDispatcher("Compra.jsp");
			else
				d = request.getRequestDispatcher("Carrinho.jsp");
			d.forward(request, response);
		}
		
		if(operacao.equals("CONSULTAR"))
		{	

			if(local == null)
			{
				if(resultado.getMsg() != null)
				{

					if(resultado.getMsg().trim().equals("APROVADOO valor mínimo de compra para cada cartão é de 10R$"))
					{
						d = request.getRequestDispatcher("Compra.jsp");
						request.getSession().setAttribute("resultadoMsg", resultado.getMsg());
						
					}
					else
						d = request.getRequestDispatcher("ComprarItens?operacao=ALTERAR&status="+resultado.getMsg());
				
				}
				else
					d = request.getRequestDispatcher("ComprarItens?operacao=ALTERAR&status="+resultado.getMsg());
			}
				
			else
			{
				String idTxt = request.getParameter("id");
				if(resultado.getMsg() != null)
				{
					if(resultado.getMsg().trim().equals("APROVADO O valor mínimo de compra para cada cartão é de 10R$"))
					{
						d = request.getRequestDispatcher("Compra.jsp");
						request.getSession().setAttribute("resultadoMsg", resultado.getMsg());
						
					}
					else
					{
						d = request.getRequestDispatcher("ComprarItens?operacao=ALTERAR"
								+ "&status="+resultado.getMsg()+"&id="+idTxt+"&local="+local);	
						request.getSession().removeAttribute("pedidoUser");
					}
				}
				else
				{
					d = request.getRequestDispatcher("ComprarItens?operacao=ALTERAR"
							+ "&status="+resultado.getMsg()+"&id="+idTxt+"&local="+local);	
					request.getSession().removeAttribute("pedidoUser");
				}
			
			}
	
			d.forward(request, response);
		}
		if(operacao.equals("ALTERAR"))
		{	
			if(local == null)
				local = "Conta.jsp";
			Resultado r = (Resultado)request.getSession().getAttribute("resultadoLogin");
			List<EntidadeDominio> e = r.getEntidades();
			PessoaFisica pf = (PessoaFisica)e.get(0);
			String email = pf.getEmail();
			String senha = pf.getSenha();
			String url = "SalvarCliente?operacao=LOGIN&txtEmail="+email+"&txtPwd="+senha+"&local="+local;
			
			d = request.getRequestDispatcher(url);
			d.forward(request, response);
		}
		
		
	}

}
