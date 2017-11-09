<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consultar livro</title>
</head>
<body>

	<%
		Resultado resultado = (Resultado) session.getAttribute("resultado");
	%>

	<form action="SalvarLivro" method="post">

		<label for="txtId">Id:</label> <input type="text" id="txtId" name="txtId" /> <br> 
		<label for="txtDescricao">DESCRIÇÃO:</label> 
		<input type="text" id="txtDescricao" name="txtDescricao" /> 
		<input type="submit" id="operacao" name="operacao" value="CONSULTAR" />
	</form>

	


	<%
	
	if(resultado !=null && resultado.getMsg() != null){
		out.print(resultado.getMsg());
	}
	
	%>
	<BR>
	
	<TABLE BORDER="5"    WIDTH="50%"   CELLPADDING="4" CELLSPACING="3">
	   <TR>
	      <TH COLSPAN="11"><BR>
	      	<H3>LIVROS</H3>
	      </TH>
	   </TR>
	   <TR>
	      <TH>Nome</TH>
	      <TH>Autor</TH>
	      <TH>Categoria</TH>
	      <TH>Subcategoria</TH>
	      <TH>Ano</TH>
	      <TH>Titulo</TH>
	      <TH>Editora</TH>
	      <TH>Edicao</TH>
	      <TH>ISBN</TH>
	      <TH>Paginas</TH>
	      <TH>Sinopse</TH>
	      <TH>Altura</TH>
	      <TH>Peso</TH>
	      <TH>Profundidade</TH>
	      <TH>Grupo</TH>
	      <TH>Margem Grupo</TH>
	      <TH>Operação</TH>
	   </TR>
   
   <%
   if (resultado != null) {
		List<EntidadeDominio> entidades = resultado.getEntidades();
		StringBuilder sbRegistro = new StringBuilder();
		StringBuilder sbLink = new StringBuilder();
		
		if(entidades != null){
			for (int i = 0; i < entidades.size(); i++) {
				Livro l = (Livro) entidades.get(i);
				if(l.getId() == null)
				{
					pageContext.forward("SalvarLivro?operacao=CONSULTAR");
					return;					
				}
				sbRegistro.setLength(0);
				sbLink.setLength(0);
				
				
			//	<a href="nome-do-lugar-a-ser-levado">descrição</a>
				
				sbRegistro.append("<TR ALIGN='CENTER'>");
				
				
				//sbLink.append("<a href=SalvarLivro?");
				//	sbLink.append("txtId=");
				//	sbLink.append(l.getNome());						
				//	sbLink.append("&");
				//	sbLink.append("operacao=");
				//	sbLink.append("VISUALIZAR");
					
				//sbLink.append(">");
				
				sbRegistro.append("<TD>");
				sbRegistro.append(l.getNome());		
				sbRegistro.append("</TD>");
				
				sbRegistro.append("<TD>");			
				sbRegistro.append(l.getAutor());				
				sbRegistro.append("</TD>");
				
				sbRegistro.append("<TD>");			
				sbRegistro.append(l.getCategoria().getNome());			
				sbRegistro.append("</TD>");
				
				sbRegistro.append("<TD>");				
				sbRegistro.append(l.getSubcategoria().getNome());		
				sbRegistro.append("</TD>");
				
				sbRegistro.append("<TD>");			
				sbRegistro.append(l.getAno());			
				sbRegistro.append("</TD>");
				
				sbRegistro.append("<TD>");				 
				sbRegistro.append(l.getTitulo());				 			
				sbRegistro.append("</TD>");
				
				sbRegistro.append("<TD>");				 			
				sbRegistro.append(l.getEditora());				 			
				sbRegistro.append("</TD>");
				
				sbRegistro.append("<TD>");				 			
				sbRegistro.append(l.getEdicao());				 			
				sbRegistro.append("</TD>");
				
				sbRegistro.append("<TD>");				 			
				sbRegistro.append(l.getISBN());			 			
				sbRegistro.append("</TD>");
				
				sbRegistro.append("<TD>");				 			
				sbRegistro.append(l.getNpaginas());			 			
				sbRegistro.append("</TD>");
				
				sbRegistro.append("<TD>");				 			
				sbRegistro.append(l.getSinopse());			 			
				sbRegistro.append("</TD>");
				
				sbRegistro.append("<TD>");			 			
				sbRegistro.append(l.getAltura());			 			
				sbRegistro.append("</TD>");				
				
				sbRegistro.append("<TD>");			 			
				sbRegistro.append(l.getPeso());			 			
				sbRegistro.append("</TD>");				
				
				sbRegistro.append("<TD>");			 			
				sbRegistro.append(l.getProfundidade());			 			
				sbRegistro.append("</TD>");	
				
				sbRegistro.append("<TD>");			 			
				sbRegistro.append(l.getGp().getNome());			 			
				sbRegistro.append("</TD>");				

				sbRegistro.append("<TD>");			 			
				sbRegistro.append(Double.toString(l.getGp().getMargemLucro()));				 			
				sbRegistro.append("</TD>");				
				
				sbRegistro.append("<TD>");			 			
				sbRegistro.append("<a href=SalvarLivro?txtId=" + Integer.toString(l.getId()) + "&operacao=CONSULTARLIVRO>Editar</a>");		
				sbRegistro.append("</TD>");		
				
				out.print(sbRegistro.toString());
				
				//System.out.println(sbRegistro.toString());
			}
		}
	}
   
   %>
</TABLE>
</body>
</html>