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
		if(operacao.equals("CONSULTAR")) 
		{			
			return new PessoaFisica();

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

		
		if(operacao.equals("LOGIN"))
		{
			List<EntidadeDominio> entidades = resultado.getEntidades();
			PessoaFisica p = (PessoaFisica) entidades.get(0);
			request.getSession().setAttribute("userid", p.getId());
			@SuppressWarnings("unchecked")
			Map<Integer, Pedido> mapaUsuarios = (Map<Integer, Pedido>) request.getSession().getAttribute("mapaUsuarios");
			System.out.println(p.getId());

						
			if(mapaUsuarios == null)
			{
				Pedido pedido = new Pedido();
				pedido.setItem(new ArrayList<Item>());	
				mapaUsuarios = new HashMap<Integer, Pedido>();
				
				mapaUsuarios.put(p.getId(), pedido);	
				request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
				
			}if(!mapaUsuarios.containsKey(p.getId()))
			{
				Pedido pedido = new Pedido();
				pedido.setItem(new ArrayList<Item>());

				mapaUsuarios = new HashMap<Integer, Pedido>();
				mapaUsuarios.put(p.getId(), pedido);	
				request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
			}if(mapaUsuarios.containsKey(0)){
				Pedido pedido = mapaUsuarios.get(0);
				
				mapaUsuarios.remove(0);
				mapaUsuarios.put(p.getId(), pedido);
				request.getSession().setAttribute("mapaUsuarios", mapaUsuarios);
			}


			
			
			String local = request.getParameter("local");		
			if(local == null)d = request.getRequestDispatcher("Conta.jsp");local = "";
			if(local.equals("carrinho"))d = request.getRequestDispatcher("Carrinho.jsp");  		
			if(local.equals("compra"))d = request.getRequestDispatcher("Compra.jsp");
				
			
			
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
				
		if(operacao.equals("CONSULTAR")){

			if(request.getParameter("local") == null)
			{
				request.getSession().setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("ConsultarCliente.jsp");  
				d.forward(request, response);
			}				
			else
			{
				request.getSession().setAttribute("resultadoTodosClientes", resultado);
				d = request.getRequestDispatcher("/iframes/listapedidos.jsp");	
				d.forward(request, response);
			}

		}
				
		if(resultado.getMsg() != null){
			if(operacao.equals("SALVAR")){
				request.getSession().setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("ConsultarCliente.jsp");  	
			}
		}
			
	}		
	

}
