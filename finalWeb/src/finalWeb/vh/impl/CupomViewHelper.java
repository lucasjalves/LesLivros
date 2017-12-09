package finalWeb.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.Cupom;
import finalDominio.CupomPromocional;
import finalDominio.CupomTroca;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;
import finalDominio.PedidoTroca;
import finalDominio.PessoaFisica;
import finalWeb.vh.IViewHelper;

public class CupomViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		if(operacao == null)
			operacao = "";
		
		if(operacao.equals("SALVAR"))
		{
			String tipoCupom = request.getParameter("tipoCupom");
			if(tipoCupom.equals("troca"))
			{
				CupomTroca cupomTroca = new CupomTroca();
				PedidoTroca pt = (PedidoTroca)request.getSession().getAttribute("pedidoTroca");
				
				cupomTroca.setDesconto(pt.getItensTroca().get(0).getPrecoLivro());
				cupomTroca.setIdCliente(pt.getIdCliente());
				Calendar cal = Calendar.getInstance();
				Date dtCadastro = cal.getTime();
				
				cupomTroca.setDtCriacao(dtCadastro);
				dtCadastro.setMonth(dtCadastro.getMonth() + 1);
				
				
				cupomTroca.setDtValidade(dtCadastro);
				
				return cupomTroca;
								
			}
			return null;
		}
		else
		{
			String txtCodigo = request.getParameter("txtCodigo");
			CupomPromocional cupomPromocional = new CupomPromocional();
			cupomPromocional.setCodigo(txtCodigo);
			request.getSession().setAttribute("cupomvalidado", "a");
			if(request.getSession().getAttribute("c") != null)
			{
				request.getSession().removeAttribute("cupomvalidado");
				CupomPromocional cu = (CupomPromocional)request.getSession().getAttribute("c");
				request.getSession().removeAttribute("c");
				return cu;
			}
			else	
				return cupomPromocional;			
		}

			
		
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		RequestDispatcher d=null;
		
		if(request.getSession().getAttribute("cupomvalidado") != null && request.getSession().getAttribute("c") == null)
		{
			List<EntidadeDominio> e = resultado.getEntidades();
			CupomPromocional c = null;
			
			if(e.size() == 0)
				c = new CupomPromocional();
			else
				c = (CupomPromocional)e.get(0);
			
			request.getSession().setAttribute("c", c);
			d = request.getRequestDispatcher("ValidarCupom?operacao=CONSULTAR");
			d.forward(request, response);
			return;
		}
		
		String operacao = request.getParameter("operacao");
		if(operacao.equals("CONSULTAR"))
		{
			request.getSession().removeAttribute("cupomvalidado");
			request.getSession().removeAttribute("c");
			if(resultado.getMsg() == null && resultado.getEntidades() != null)
			{
				Integer id = (Integer)request.getSession().getAttribute("userid");
				@SuppressWarnings("unchecked")
				Map<Integer, Pedido> mapaUsuarios = (Map<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
				Pedido pedido = mapaUsuarios.get(id);
				List<EntidadeDominio> e = resultado.getEntidades();
				CupomPromocional cupomResultado = (CupomPromocional)e.get(0);
				
				pedido.setCupomPromocional(cupomResultado);
				mapaUsuarios.replace(id, pedido);
				request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
			}

			d= request.getRequestDispatcher("Carrinho.jsp");  
		}
		if(operacao.equals("SALVAR"))
			d = request.getRequestDispatcher("iframes/listatrocas.jsp");

		request.getSession().setAttribute("resultadoCupom", resultado);
		d.forward(request,response);
	}		
	

}
