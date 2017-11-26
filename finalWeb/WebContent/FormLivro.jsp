<%@page import="finalWeb.vh.impl.SubCategoriaViewHelper"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Cadastro de livros</title>
			<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<script src="bootstrap/jquery-3.2.1.min.js"></script>
			<script src="bootstrap/popper.js"></script>
			<script src="bootstrap/bootstrap.min.js"></script>
			<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
			<script>
			
			$(document).ready(function()		
					{
						
						$("#ddlCategoria").change(function(){
							var idCategoria = $(this).val();
							var operacao = "CONSULTAR";
							/*
							$.ajax({
								url: "SalvarSubCategoria",
								method: "post",																							
								data: {txtId : idCategoria , operacao : operacao}, // AJAX
								sucess:function(data){
								}
							});
							window.location.reload();
							*/
							window.location.href = "SalvarSubCategoria?txtId=" + idCategoria + "&operacao=CONSULTAR";
							
						});
						
					});
			</script>
	</head>	
	
	<body>
		<%
			
			Resultado resultadoCategorias = (Resultado) session.getAttribute("resultadoCategorias");	
			Resultado resultadoGrupos = (Resultado) session.getAttribute("resultadoGrupos");
			Resultado resultadoSubCategorias = (Resultado) session.getAttribute("resultadoSubCategorias");
			Resultado resultado = (Resultado) session.getAttribute("resultado");
			Livro livro = null;
			List<EntidadeDominio> entidadesCategorias = null;
			List<EntidadeDominio> entidadesGrupos = null;
			List<EntidadeDominio> subcategorias = null;
			if(resultadoCategorias == null){
				pageContext.forward("SalvarCategoria?operacao=CONSULTAR");
				return;}
			
			else if(resultadoCategorias != null){
				entidadesCategorias = resultadoCategorias.getEntidades();
			}
			
			
			if(resultadoGrupos == null){
				pageContext.forward("SalvarGrupos?operacao=CONSULTAR");
				return;
			}
			
			else if(resultadoGrupos != null){
				entidadesGrupos = resultadoGrupos.getEntidades();
			}
			
			Resultado resultadoLivros = (Resultado) session.getAttribute("resultadoConsultaLivro");
			if(resultadoLivros != null){
				livro = (Livro)resultadoLivros.getEntidades().get(0);
			}						
		%>	
		
		<form action="SalvarLivro" method="post" id="frmSalvarLivro">
			<table>
				
					<tr><td>Nome do livro</td>
					<td><input type="text"class="form-control" id="txtNome" name="txtNome"  value=
					"<%if(livro != null) out.print(livro.getNome());%>" maxlength="100" required/>
					</td></tr>
				
				
					<tr><td>Autor do livro</td><td><input type="text"class="form-control" id="txtAutor" name="txtAutor" 
					value="<%if(livro != null) out.print(livro.getAutor());%>" maxlength="100" required /></td></tr>
				
				
					<tr><td>Categoria do livro</td>
					<td>
						<select id="ddlCategoria" name="ddlCategoria" class="ddlCategoria" required>
							<option>SELECIONE</option>
							<%
							if(resultadoCategorias != null)
							{								
								for(int i = 0; i < entidadesCategorias.size(); i ++)
								{
									Categoria cat = (Categoria)entidadesCategorias.get(i);
									if(resultadoSubCategorias != null)
									{
										subcategorias = resultadoSubCategorias.getEntidades();
										SubCategoria s = (SubCategoria)subcategorias.get(0);
										if(cat.getId() == s.getFkCategoria())
											out.print("<option value='" + cat.getId() + "' selected>" + cat.getNome() + "</option>");
										else
										{
											out.print("<option value='" + cat.getId() + "'>" + cat.getNome() + "</option>");
										}
									}
									else
									{
										out.print("<option value='" + cat.getId() + "'>" + cat.getNome() + "</option>");
									}
								}
							}//se resultado !=null
							%>

						</select>
					</td></tr>
				
				
					<tr><td>Subcategoria do livro</td>
					<td>
						<select id="ddlsCategoria" name="ddlsCategoria" required>
							<%
							if(resultadoSubCategorias != null)
							{
								
								subcategorias = resultadoSubCategorias.getEntidades();
								for(int i = 0; i < subcategorias.size(); i ++)
								{
									SubCategoria subcat = (SubCategoria)subcategorias.get(i);
									out.print("<option value='" + subcat.getId() + "'>" + subcat.getNome() + "</option>");
								}
							}
							%>						
						</select>
					</td></tr>
				
				
					<tr><td>Grupo Precificação</td>
					<td>
						<select id="ddlsCategoria" name="ddlGrupoPrecificacao" required>
							<%
							if(resultadoGrupos != null)
							{
								
								for(int i = 0; i < entidadesGrupos.size(); i ++) 
								{
									GrupoPrecificacao gp = (GrupoPrecificacao)entidadesGrupos.get(i);
									out.print("<option value='" + gp.getId() + "'>" + gp.getNome() + "</option>");
								}
							}
							%>						
						</select>
					</td></tr>
								
				
					<tr><td>Ano do livro</td>
					<td><input type="text"class="form-control" id="txtAno" name="txtAno" maxlength="4" required/></td></tr>
				
				
					<tr><td>Titulo do livro</td>
					<td><input type="text"class="form-control" id="txtTitulo" name="txtTitulo"  maxlength="100" required/></td></tr>
				
				
					<tr><td>Editora do livro
					<input type="text"class="form-control" id="txtEditora" name="txtEditora" maxlength="40" required/></td></tr>
				
				
					<tr><td>Edição do livro</td>
					<td><input type="text"class="form-control" id="txtEdicao" name="txtEdicao" maxlength="4" required/></td>
				
				
					<tr><td>ISBN do livro</td>
					<td><input type="text"class="form-control" id="txtISBN" name="txtISBN"  maxlength="14" required/></td></tr>
				
				
					<tr><td>Número de páginas do livro</td>
					<td><input type="text"class="form-control" id="txtPaginas" name="txtPaginas"  maxlength="5" required/></td></tr>
				
				
					<tr><td>Sinopse do livro</td>
					<td><input type="text"class="form-control" id="txtSinopse" name="txtSinopse"  require/></td></tr>
				
				
					<tr><td>Altura</td>
					<td><input type="text"class="form-control" id="txtAltura" name="txtAltura"  maxlength="5" required/></td></tr>
				
				
					<tr><td>Peso</td>
					<td><input type="text"class="form-control" id="txtPeso" name="txtPeso" maxlength="5" required /></td></tr>
				
				
					<tr><td>Profundidade</td>
					<td><input type="text"class="form-control" id="txtProfundidade" name="txtProfundidade"  maxlength="5" required/></td></tr>
																
			</table>
			<input type="submit" id="operacao" name="operacao"
									value="SALVAR" class="btn btn-default" />
		</form>
		<% 
			if(resultado != null)
			{
				if(resultado.getMsg() != null)
				{
					out.print(resultado.getMsg());
				}
			}
		%>
	</body>
	<% 
		session.setAttribute("resultadoCategorias", null);		
		session.setAttribute("resultadoGrupos", null);
		
	%>
</html>