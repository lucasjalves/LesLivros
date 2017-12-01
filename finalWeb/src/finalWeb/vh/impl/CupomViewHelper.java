package finalWeb.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.Cupom;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;
import finalDominio.PessoaFisica;
import finalWeb.vh.IViewHelper;

public class CupomViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String txtCodigo = request.getParameter("txtCodigo");

		Cupom cupom = new Cupom();
		cupom.setCodigo(txtCodigo);
		request.getSession().setAttribute("cupomvalidado", "a");
		if(request.getSession().getAttribute("c") != null)
		{
			request.getSession().removeAttribute("cupomvalidado");
			Cupom cu = (Cupom)request.getSession().getAttribute("c");
			request.getSession().removeAttribute("c");
			return cu;
		}
		else	
			return cupom;
			
		
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		RequestDispatcher d=null;
		
		if(request.getSession().getAttribute("cupomvalidado") != null && request.getSession().getAttribute("c") == null)
		{
			List<EntidadeDominio> e = resultado.getEntidades();
			Cupom c = null;
			
			if(e.size() == 0)
				c = new Cupom();
			else
				c = (Cupom)e.get(0);
			
			request.getSession().setAttribute("c", c);
			d = request.getRequestDispatcher("ValidarCupom?operacao=AdicionarCupom");
			d.forward(request, response);
			return;
		}
		
		String operacao = request.getParameter("operacao");
		System.out.println(resultado.getMsg());
		if(operacao.equals("AdicionarCupom"))
		{
			request.getSession().removeAttribute("cupomvalidado");
			request.getSession().removeAttribute("c");
			if(resultado.getMsg() == null && resultado.getEntidades() != null)
			{
				Integer id = (Integer)request.getSession().getAttribute("userid");
				@SuppressWarnings("unchecked")
				Map<Integer, PessoaFisica> mapaUsuarios = (Map<Integer, PessoaFisica>)request.getSession().getAttribute("mapaUsuarios");
				PessoaFisica pf = mapaUsuarios.get(id);
				Pedido pedido = pf.getPedidos().get(0);
				List<EntidadeDominio> e = resultado.getEntidades();
				Cupom cupomResultado = (Cupom)e.get(0);
				
				if(pedido.getCupom() == null)
				{
					pedido.setCupom(new ArrayList<Cupom>());
					if(resultado.getMsg() == null)
						pedido.getCupom().add(cupomResultado);
				}
				else
				{
					if(resultado.getMsg() == null)
						pedido.getCupom().add(cupomResultado);
				}
				pf.getPedidos().set(0, pedido);
				mapaUsuarios.replace(id, pf);
				request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
			}

			d= request.getRequestDispatcher("Carrinho.jsp");  
		}

		request.getSession().setAttribute("resultadoCupom", resultado);
		d.forward(request,response);
	}		
	

}
