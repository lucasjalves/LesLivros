package finalWeb.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import finalCore.aplicacao.Resultado;
import finalDominio.Categoria;
import finalDominio.EntidadeDominio;
import finalDominio.GrupoPrecificacao;
import finalDominio.Livro;
import finalDominio.SubCategoria;
import finalWeb.vh.IViewHelper;

public class LivroViewHelper implements IViewHelper{
	/** 
	 * TODO Descrição do Método
	 * @param request
	 * @param response
	 * @return
	 * @see lesWeb.vh.IViewHelper#getEntidade(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Livro l;
		
		if(operacao.equals("SALVAR"))
		{
			
			String nome = request.getParameter("txtNome");
			String autor = request.getParameter("txtAutor");
			String categoria = request.getParameter("ddlCategoria");
			String subcategoria = request.getParameter("ddlsSubCategoria");
			String grupo = request.getParameter("ddlGrupoPrecificacao");
			String ano = request.getParameter("txtAno");
			String titulo = request.getParameter("txtTitulo");
			String editora = request.getParameter("txtEditora");
			String edicao = request.getParameter("txtEdicao");
			String isbn = request.getParameter("txtISBN");
			String npaginas = request.getParameter("txtPaginas");
			String sinopse = request.getParameter("txtSinopse");
			String altura = request.getParameter("txtAltura");
			String peso = request.getParameter("txtPeso");
			String profundidade = request.getParameter("txtProfundidade");
			String largura = request.getParameter("txtLargura");
			String precoTxt = request.getParameter("txtPreco");
			String qtdeEstoqueTxt = request.getParameter("txtQtde");
			
			
			Categoria c = new Categoria();
			SubCategoria subc = new SubCategoria();
			GrupoPrecificacao gp = new GrupoPrecificacao();
			List<SubCategoria> listsub = new ArrayList<SubCategoria>();
			List<Categoria> listc = new ArrayList<Categoria>();
			
			
			
			
			l = new Livro();
			c.setId(Integer.parseInt(categoria));
			subc.setId(Integer.parseInt(subcategoria));
			gp.setId(Integer.parseInt(grupo));
			listsub.add(subc);
			c.setSubcategorias(listsub);
			listc.add(c);
			
			
			
			
			l.setNome(titulo);
			l.setAutor(autor);
			l.setAno(ano);
			l.setTitulo(titulo);
			l.setEditora(editora);
			l.setEdicao(edicao);
			l.setISBN(isbn);
			l.setNpaginas(npaginas);
			l.setSinopse(sinopse);
			l.setAltura(altura);
			l.setPeso(peso);
			l.setProfundidade(profundidade);
			l.setStatus("1");
			
			
			l.setCategoria(listc);
			
			l.setGp(gp);
	
			Integer qtde = Integer.parseInt(qtdeEstoqueTxt);
			Double preco = Double.parseDouble(precoTxt);
			
			l.setQtdeEstoque(qtde);
			l.setPreco(preco);
			l.setLargura(largura);
			return l;
		}
		
		if(operacao.equals("CONSULTARLIVRO") || operacao.equals("INFORMACOESLIVRO"))
		{
			l = new Livro();
			
			String txtId = request.getParameter("txtId");
			Integer id = 0;
			if(txtId != null || !txtId.trim().equals(""))
			{
				id = Integer.parseInt(txtId);
			}
			l.setId(id);
			return l;
			
		}
		else
		{
			 return new Livro();
		}
		
	}

	/** 
	 * TODO Descrição do Método
	 * @param request
	 * @param response
	 * @return
	 * @see lesWeb.controle.web.vh.IViewHelper#setView(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void setView(Resultado resultado, HttpServletRequest request, 
			HttpServletResponse response)  throws IOException, ServletException {
		RequestDispatcher d=null;
		
		String operacao = request.getParameter("operacao");
		
		if(operacao.equals("CONSULTAR")){
			request.getSession().setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("FormConsultaLivro.jsp");  
		}
		
		if(operacao.equals("CONSULTARLIVRO")){
			request.getSession().setAttribute("resultadoConsultaLivro", resultado);
			d= request.getRequestDispatcher("FormLivro.jsp");  
		}

		if(operacao.equals("CONSULTARLIVROHOME")){
			request.getSession().setAttribute("resultadoConsultaLivro", resultado);
			d= request.getRequestDispatcher("Home.jsp");  
		}
		if(operacao.equals("INFORMACOESLIVRO")){
			request.getSession().setAttribute("resultadoConsultaLivroCompra", resultado);
			d= request.getRequestDispatcher("Livro.jsp");  
		}		
		
		if(resultado.getMsg() == null && operacao.equals("SALVAR")){
			
			d= request.getRequestDispatcher("Home.jsp");  
		}
		System.out.println(resultado.getMsg());
		System.out.println(operacao);
		d.forward(request,response);
		
	}
}
