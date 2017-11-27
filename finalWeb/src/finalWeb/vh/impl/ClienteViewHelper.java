package finalWeb.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import finalDominio.Pedido;
import finalDominio.Pessoa;
import finalDominio.Item;

public class ClienteViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		System.out.println(operacao);
		PessoaFisica p = null;
		System.out.println(operacao);
		if(operacao.equals("LOGIN"))
		{
			p = new PessoaFisica();
			String email = request.getParameter("txtEmail");
			String senha = request.getParameter("txtPwd");
			
			p.setEmail(email);
			p.setSenha(senha);
			if(request.getSession().getAttribute("userid") != null)
			{
				request.getSession().removeAttribute("userid");
			}
		
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
		
		else {
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
			c.setDtVencimento(dtVencimentoTxt);
			c.setNumero(numCartao);
			/*
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
			*/
			}
		
		
		return p;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		RequestDispatcher d=null;

		String operacao = request.getParameter("operacao");
		if(operacao.equals("SALVARCLIENTE"))
		{
			request.getSession().setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("ConsultarCliente.jsp"); 	
			resultado.setMsg("Cliente cadastrado com sucesso!");
		}


		if(operacao.equals("EXCLUIR")){
			
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
			List<EntidadeDominio> entidades = resultado.getEntidades();
			PessoaFisica p = (PessoaFisica) entidades.get(0);
			String txtId = String.valueOf(p.getId());
			request.getSession().setAttribute("userid", txtId);
			
			if(request.getSession().getAttribute("mapaUsuarios") != null)
			{
				if(request.getSession().getAttribute("usuariodeslogado") != null)
				{
					Map<Integer, Pedido> mapaUsuarios = (Map<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
					Pedido pedido = mapaUsuarios.get(0);
					pedido.setUsuario(p);
					mapaUsuarios.put(p.getId(), pedido);
					mapaUsuarios.remove(0);
					request.getSession().removeAttribute("usuariodeslogado");
					request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
				}
				else
				{
					Map<Integer, Pedido> mapaUsuarios = (Map<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
					Pedido pedido = mapaUsuarios.get(p.getId());
					pedido.setUsuario(p);
					mapaUsuarios.replace(p.getId(), pedido);
					request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);			
				}
				
			}
			
			if(request.getSession().getAttribute("mapaUsuarios") == null)
			{
				Map<Integer, Pedido>mapaUsuarios = new HashMap<Integer, Pedido>();
				Pedido pedido = new Pedido();
				pedido.setUsuario(p);
				List<Item> itens = new ArrayList<Item>();
				pedido.setItem(itens);
				mapaUsuarios.put(p.getId(), pedido);
				request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
			}
			String local = request.getParameter("local");
			
			if(local == null)
			{
				d = request.getRequestDispatcher("Conta.jsp");
				local = "";
			}
				  
			
			if(local.equals("carrinho"))
				d = request.getRequestDispatcher("Carrinho.jsp");  
			
			if(local.equals("compra"))
				d = request.getRequestDispatcher("Compra.jsp");
			
			
			request.getSession().setAttribute("resultadoLogin", resultado);  
			d.forward(request, response);
		}
		
		if(operacao.equals("ALTERAR") || operacao.equals("VISUALIZAR"))
		{
			Resultado res = (Resultado)request.getSession().getAttribute("resultadoLogin");
			
			List<EntidadeDominio> e = res.getEntidades();
			
			Pessoa p = (Pessoa)e.get(0);
			
			String email = p.getEmail();
			String senha = p.getSenha();

			
			String url = "SalvarCliente?txtEmail=" + email + "&txtPwd=" +senha +"&operacao=LOGIN&";
			d = request.getRequestDispatcher(url); 		
			d.forward(request, response);
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
			
	}		
	

}
