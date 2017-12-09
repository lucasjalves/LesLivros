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
import finalCore.util.ConverteDate;
import finalDominio.Cartao;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;
import finalDominio.Pessoa;
import finalDominio.PessoaFisica;
import finalDominio.Item;
import finalWeb.vh.IViewHelper;

public class CartaoViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Cartao c = null;
		if(operacao.equals("CONSULTAR")) {
			c = new Cartao();
			
			String txtId = request.getParameter("txtIdCartao");
			String txtFkId = request.getParameter("txtIdCartaoFk");	
			
			c.setId(Integer.parseInt(txtId));
			c.setPkUsuario(Integer.parseInt(txtFkId));
			
			return c;
		}
		
		if(operacao.equals("ALTERAR"))
		{
			c = new Cartao();
			String numCartao = request.getParameter("txtNumCartao");
			String bandeira = request.getParameter("ddlBandeira");
			String dtVencimentoTxt = request.getParameter("txtDtVencimento");
			String codSeg = request.getParameter("txtCodSeg");
			String txtId = request.getParameter("txtIdCartao");
			String txtFkId = request.getParameter("txtIdCartaoFk");
			
			c.setBandeira(bandeira);
			c.setNumero(numCartao);
			c.setDtVencimento(dtVencimentoTxt);
			c.setCodSeg(codSeg);
			c.setId(Integer.parseInt(txtId));
			c.setPkUsuario(Integer.parseInt(txtFkId));
			
			return c;
		}
		
		if(operacao.equals("SALVAR"))
		{
			c = new Cartao();
			String numCartao = request.getParameter("txtNumCartao");
			String bandeira = request.getParameter("ddlBandeira");
			String dtVencimentoTxt = request.getParameter("txtDtVencimento");
			String codSeg = request.getParameter("txtCodSeg");
			String txtFkId = request.getParameter("txtIdCartaoFk");
			
		
			
			c.setBandeira(bandeira);
			c.setNumero(numCartao);
			c.setDtVencimento(dtVencimentoTxt);
			c.setCodSeg(codSeg);
			c.setPkUsuario(Integer.parseInt(txtFkId));
			
			return c;
		}		
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		RequestDispatcher d=null;

		
		
		String operacao = request.getParameter("operacao");	
		if(operacao.equals("SALVAR") || operacao.equals("ALTERAR"))
		{
			Resultado res = (Resultado)request.getSession().getAttribute("resultadoLogin");
			
			List<EntidadeDominio> e = res.getEntidades();
			
			Pessoa p = (Pessoa)e.get(0);
			
			String email = p.getEmail();
			String senha = p.getSenha();

			
			String url = "SalvarCliente?txtEmail=" + email + "&txtPwd=" +senha +"&operacao=LOGIN&";
			d = request.getRequestDispatcher(url); 		
			d.forward(request, response);
			
		}

		
		
	}

}
