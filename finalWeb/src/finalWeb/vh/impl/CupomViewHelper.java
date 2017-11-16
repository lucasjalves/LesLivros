package finalWeb.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.Cupom;
import finalDominio.EntidadeDominio;
import finalWeb.vh.IViewHelper;

public class CupomViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String txtCodigo = request.getParameter("txtCodigo");
		Cupom c = new Cupom();
		c.setCodigo(txtCodigo);
		return c;
	}

	@Override
	public void setView(Resultado resultadoConsulta, Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		RequestDispatcher d=null;
		
		String operacao = request.getParameter("operacao");
		
		if(operacao.equals("AdicionarCupom") && resultado.getMsg() != null)
		{
			request.getSession().setAttribute("resultadoCupom", resultado);
			d= request.getRequestDispatcher("Carrinho.jsp");  
		}
		else
		{
			request.getSession().setAttribute("resultadoCupom", resultado);
			d= request.getRequestDispatcher("Carrinho.jsp");  
		}
		
		d.forward(request,response);
	}		
	

}
