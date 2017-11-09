package finalWeb.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.Endereco;
import finalDominio.EntidadeDominio;
import finalWeb.vh.IViewHelper;

public class EnderecoViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String operacao = request.getParameter("operacao");
		Endereco e = null;
		if(operacao.equals("ALTERAR"))
		{
			e = new Endereco();
			
			String idTxt = request.getParameter("txtIdEndereco");
			Integer id = Integer.parseInt(idTxt);
			String tipoRes = request.getParameter("txtTipoRes");
			String tipoLog = request.getParameter("txtTipoLog");
			String logradouro = request.getParameter("txtLogradouro");
			String numCasa = request.getParameter("txtNumCasa");
			String bairro = request.getParameter("txtBairro");
			String cep = request.getParameter("txtCep");
			String cidade = request.getParameter("txtCidade");
			String estado = request.getParameter("txtEstado");
			String pais = request.getParameter("txtPais");
			String nome_end = request.getParameter("txtNome");
			
			
			e.setTipoRes(tipoRes);
			e.setTipoLog(tipoLog);
			e.setLogradouro(logradouro);
			e.setNumCasa(numCasa);
			e.setBairro(bairro);
			e.setCep(cep);
			e.setCidade(cidade);
			e.setEstado(estado);
			e.setPais(pais);
			e.setNome(nome_end);
			e.setId(id);
			return e;
		}
		
		if(operacao.equals("SALVAR"))
		{
			e = new Endereco();
			
			String idTxt = request.getParameter("txtIdFkEndereco");
			Integer id = Integer.parseInt(idTxt);
			String tipoRes = request.getParameter("txtTipoRes");
			String tipoLog = request.getParameter("txtTipoLog");
			String logradouro = request.getParameter("txtLogradouro");
			String numCasa = request.getParameter("txtNumCasa");
			String bairro = request.getParameter("txtBairro");
			String cep = request.getParameter("txtCep");
			String cidade = request.getParameter("txtCidade");
			String estado = request.getParameter("txtEstado");
			String pais = request.getParameter("txtPais");
			String nome_end = request.getParameter("txtNome");
			
			
			e.setTipoRes(tipoRes);
			e.setTipoLog(tipoLog);
			e.setLogradouro(logradouro);
			e.setNumCasa(numCasa);
			e.setBairro(bairro);
			e.setCep(cep);
			e.setCidade(cidade);
			e.setEstado(estado);
			e.setPais(pais);
			e.setNome(nome_end);
			e.setFk_pessoa(id);
			
			return e;			
		}
		return null;
	}

	@Override
	public void setView(Resultado resultadoConsulta, Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d=null;
		
		String operacao = request.getParameter("operacao");

		if(operacao.equals("ALTERAR") || operacao.equals("VISUALIZAR") || operacao.equals("SALVAR"))
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
 
		}
		
		d.forward(request,response);
	}

}
