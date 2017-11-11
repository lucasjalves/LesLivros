package finalWeb.vh.impl;

import java.io.IOException;
import java.util.Date;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalCore.util.ConverteDate;
import finalDominio.Cartao;
import finalDominio.Endereco;
import finalDominio.EntidadeDominio;
import finalDominio.PessoaFisica;
import finalDominio.Telefone;
import finalWeb.vh.IViewHelper;

public class ClienteViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		System.out.println(operacao);
		PessoaFisica p = null;
		if(operacao.equals("LOGIN"))
		{
			p = new PessoaFisica();
			String email = request.getParameter("txtEmail");
			String senha = request.getParameter("txtPwd");
			
			p.setEmail(email);
			p.setSenha(senha);
			
		
			return p;
		}
		if(operacao.equals("ALTERAR"))
		{
			p = new PessoaFisica();
			
			String idString = request.getParameter("txtId");
			int id = Integer.parseInt(idString);
			String nome_cli = request.getParameter("txtNomeCli");
			String genero = request.getParameter("txtGenero");
			String dtNascimentoTxt = request.getParameter("txtDtNascimento");
			Date dtNascimento = ConverteDate.converteStringDate(dtNascimentoTxt);
			String cpf = request.getParameter("txtCpf");
			String email = request.getParameter("txtEmail");
			String senha = request.getParameter("txtSenha");		
			
			p.setId(id);
			p.setNome(nome_cli);
			p.setGenero(genero);
			p.setDtNascimento(dtNascimento);
			p.setCpf(cpf);
			p.setEmail(email);
			p.setSenha(senha);
			
			return p;
			
		}
		if(operacao.equals("CONSULTARCLIENTE")) 
		{
			p = new PessoaFisica();
			
			String nome_cli = request.getParameter("txtNomeCli");
			String genero = request.getParameter("txtGenero");
			String dtNascimentoTxt = request.getParameter("txtDtNascimento");
			Date dtNascimento = ConverteDate.converteStringDate(dtNascimentoTxt);
			String cpf = request.getParameter("txtCpf");
			String email = request.getParameter("txtEmail");	
			
			p.setNome(nome_cli);
			p.setGenero(genero);
			p.setDtNascimento(dtNascimento);
			p.setCpf(cpf);
			p.setEmail(email);
			
			return p;

		}
		if(operacao.equals("VISUALIZAR"))
		{
			p = new PessoaFisica();
			String txtId = request.getParameter("txtId");
			p.setId(Integer.parseInt(txtId));
			request.getSession().setAttribute("userid", txtId);
			
			return p;
			
		}
		
		else {/*
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("resultado");
			for(EntidadeDominio e: resultado.getEntidades()){
					p = (PessoaFisica)e;
			*/
			p = new PessoaFisica();
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
			
			String nome_cli = request.getParameter("txtNomeCli");
			String genero = request.getParameter("txtGenero");
			String dtNascimentoTxt = request.getParameter("txtDtNascimento");
			Date dtNascimento = ConverteDate.converteStringDate(dtNascimentoTxt);
			String cpf = request.getParameter("txtCpf");
			String email = request.getParameter("txtEmail");
			String senha = request.getParameter("txtSenha");
			//String cnpj = request.getParameter("txtCnpj");
			
			String ddd = request.getParameter("txtDdd");
			String numeroTel = request.getParameter("txtNumeroTel");
			String tipoTel = request.getParameter("ddlTipoTel");
			
			String numCartao = request.getParameter("txtNumCartao");
			String bandeira = request.getParameter("ddlBandeira");
			String dtVencimentoTxt = request.getParameter("txtDtVencimento");
			String codSeg = request.getParameter("txtCodSeg");
			
			Date dtVencimento = ConverteDate.converteStringDate(dtVencimentoTxt);
			
			Endereco e = new Endereco();
			
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
			
			Telefone t = new Telefone();
			t.setDdd(ddd);
			t.setNumero(numeroTel);
			t.setTipo(tipoTel);
			
			
			Cartao c = new Cartao();
			
			c.setBandeira(bandeira);
			c.setCodSeg(codSeg);
			c.setDtVencimento(dtVencimento);
			c.setNumero(numCartao);
			
			p.setNome(nome_cli);
			p.setGenero(genero);
			p.setDtNascimento(dtNascimento);
			p.setCpf(cpf);
			p.setEmail(email);
			p.setSenha(senha);
			p.setStatus("1");
			p.setEndereco(e);
			p.setTelefone(t);
			p.setCartao(c);
			}
		
		
		return p;
	}

	@Override
	public void setView(Resultado resultadoConsulta, Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		RequestDispatcher d=null;
		
		String operacao = request.getParameter("operacao");
		System.out.println(operacao);
		if(operacao.equals("SALVARCLIENTE"))
		{
			request.getSession().setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("ConsultarCliente.jsp"); 	
			resultado.setMsg("Cliente cadastrado com sucesso!");
		}


		if(resultado.getMsg() == null && operacao.equals("EXCLUIR")){
			
			request.getSession().setAttribute("resultado", null);
			d= request.getRequestDispatcher("ConsultarCliente.jsp");  
		}
		
		if(resultado.getMsg() != null && operacao.equals("LOGIN"))
		{
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("Index.jsp");  
		}
		
		if(resultado.getMsg() == null && operacao.equals("LOGIN"))
		{
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("Conta.jsp");  
		}
		
		if(operacao.equals("ALTERAR") || operacao.equals("VISUALIZAR"))
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
		
		if(operacao.equals("CONSULTARCLIENTE")){
			
			request.getSession().setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("ConsultarCliente.jsp");  
		}
				
		if(resultado.getMsg() != null){
			if(operacao.equals("SALVAR")){
				request.getSession().setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("ConsultarCliente.jsp");  	
			}
		}
		
		
		d.forward(request,response);
	}		
	

}
