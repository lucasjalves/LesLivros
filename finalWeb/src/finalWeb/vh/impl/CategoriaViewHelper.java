package finalWeb.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalCore.aplicacao.Resultado;
import finalDominio.Categoria;
import finalDominio.EntidadeDominio;
import finalDominio.Livro;
import finalDominio.SubCategoria;
import finalWeb.vh.IViewHelper;

public class CategoriaViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String operacao = request.getParameter("operacao");
		if(operacao.equals("CONSULTAR"))
			return new Categoria();
		if(operacao.equals("CONSULTARLIVRO"))
		{
			String nome = request.getParameter("txtNome");
			String autor = request.getParameter("txtAutor");
			String categoria = request.getParameter("ddlCategoria");
			String subcategoria = request.getParameter("ddlsCategoria");
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
			
			
			Livro l = new Livro();
			
			l.setNome(nome);
			l.setAutor(autor);
			l.setAno(ano);
			l.setTitulo(titulo);
			l.setEditora(editora);
			l.setEdicao(edicao);
			l.setISBN(isbn);
			l.setNpaginas(npaginas);
			l.setAltura(altura);
			l.setPeso(peso);
			l.setProfundidade(profundidade);
			l.setLargura(largura);
			l.setSinopse(sinopse);
			
			request.getSession().setAttribute("formLivro", l);
			
			
			Categoria c = new Categoria();
			
			
			String idCategoriaTxt = request.getParameter("idCategoria");
			int idCategoria = Integer.parseInt(idCategoriaTxt);
			
			c.setId(idCategoria);
			return c;
		}
		return null;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		
		RequestDispatcher d=null;
		
		String operacao = request.getParameter("operacao");
			
		if(operacao.equals("CONSULTAR")){
			request.getSession().setAttribute("resultadoTodasCategorias", resultado);
			
			d= request.getRequestDispatcher("FormLivro.jsp");  	
		}
		if(operacao.equals("CONSULTARLIVRO"))
		{
			request.getSession().setAttribute("resultadoCategorias", resultado);
			d= request.getRequestDispatcher("FormLivro.jsp");  	
		}
		d.forward(request,response);
		
	}	

}
