package finalWeb.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.Categoria;
import finalDominio.EntidadeDominio;
import finalDominio.SubCategoria;
import finalWeb.vh.IViewHelper;

public class CategoriaViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String operacao = request.getParameter("operacao");
		if(operacao.equals("CONSULTAR"))
		{
			Categoria c = new Categoria();
			c.setSubcategoria(new SubCategoria());
			
			return c;
		}
		return null;
	}

	@Override
	public void setView(Resultado resultadoConsulta, Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		
		RequestDispatcher d=null;
		
		String operacao = request.getParameter("operacao");
			
		if(operacao.equals("CONSULTAR")){
			request.getSession().setAttribute("resultadoCategorias", resultado);
			
			d= request.getRequestDispatcher("FormLivro.jsp");  	
		}
		d.forward(request,response);
	}	

}
