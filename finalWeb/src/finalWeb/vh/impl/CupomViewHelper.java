package finalWeb.vh.impl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.Cupom;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;
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
	public void setView(Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		RequestDispatcher d=null;
		
		String operacao = request.getParameter("operacao");
		
		if(operacao.equals("AdicionarCupom"))
		{
			if(resultado.getMsg() == null)
			{
				String idTxt = (String)request.getSession().getAttribute("userid");
				int id = Integer.parseInt(idTxt);
				Map<Integer, Pedido> mapaUsuarios = (Map<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
				
				
			}
			request.getSession().setAttribute("resultadoCupom", resultado);
			d= request.getRequestDispatcher("Carrinho.jsp");  
		}

		
		d.forward(request,response);
	}		
	

}
