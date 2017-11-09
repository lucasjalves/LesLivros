package finalWeb.vh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.EntidadeDominio;

public interface IViewHelper {
	public EntidadeDominio getEntidade(HttpServletRequest request);
	
	public void setView(Resultado resultadoConsulta, Resultado resultado,  
			HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException;
}
