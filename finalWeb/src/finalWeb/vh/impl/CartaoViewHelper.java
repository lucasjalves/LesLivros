package finalWeb.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalCore.util.ConverteDate;
import finalDominio.Cartao;
import finalDominio.EntidadeDominio;
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
			c.setDtVencimento(ConverteDate.converteStringDate(dtVencimentoTxt));
			c.setCodSeg(codSeg);
			c.setId(Integer.parseInt(txtId));
			c.setPkUsuario(Integer.parseInt(txtFkId));
			
			return c;
		}
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void setView(Resultado resultadoConsulta, Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		RequestDispatcher d=null;
		
		String operacao = request.getParameter("operacao");	
		
		if(operacao.equals("ALTERAR"))
		{
			if(resultadoConsulta != null)
			{
				request.getSession().setAttribute("resultado", resultadoConsulta);
				d = request.getRequestDispatcher("Conta.jsp"); 
			}
			else
			{
				request.getSession().setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("Conta.jsp"); 							
			}
			
			d.forward(request,response);
 
		}
		
		

	}

}
