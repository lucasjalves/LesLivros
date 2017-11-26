package finalWeb.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.EntidadeDominio;
import finalDominio.SubCategoria;
import finalWeb.vh.IViewHelper;

public class SubCategoriaViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String operacao = request.getParameter("operacao");
		if(operacao.equals("CONSULTAR"))
		{
			String txtId = request.getParameter("txtId");
			Integer id = Integer.parseInt(txtId);
			SubCategoria subc = new SubCategoria();
			subc.setId(id);
			
			return subc;
		}
		
		return null;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d=null;
		
		String operacao = request.getParameter("operacao");
			
		if(operacao.equals("CONSULTAR")){
			request.getSession().setAttribute("resultadoSubCategorias", resultado);
			d= request.getRequestDispatcher("FormLivro.jsp");  
		}
		d.forward(request,response);		
	}

}
