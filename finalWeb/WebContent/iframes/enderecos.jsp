<%@page import="finalCore.util.ConverteDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<link rel="stylesheet" href="../bootstrap/bootstrap.min.css">
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<script src="../bootstrap/popper.js"></script>
			<script src="../bootstrap/jquery-3.2.1.min.js"></script>
			<script src="../bootstrap/bootstrap.min.js"></script>
			<link rel="stylesheet" href="../bootstrap/bootstrap.min.css">
		
		<title>Minha conta</title>
		<%
			Resultado resultado = (Resultado) session.getAttribute("resultadoLogin");
			List<EntidadeDominio> entidades = resultado.getEntidades();
			PessoaFisica p = (PessoaFisica) entidades.get(0);
			
			
			List<Endereco> end= p.getEndereco();
			Integer id = (Integer)request.getSession().getAttribute("userid");
		%>
	</head>
	<body>
		<div class="container">
			<div class="card">
				<div class="card-body">
                  <h4 class="card-title">
                    Meus Endereços
                  </h4>				
					<table class="table">
						<% 
							StringBuilder sb = new StringBuilder();
							for(int i = 0; i < end.size(); i++)
							{
								sb.setLength(0);
								sb.append("<tr style='border-bottom: 1px solid #000; border-top: 1px solid #000'><td>");
								sb.append("<p>RUA: ");
								sb.append(end.get(i).getLogradouro() + ", ");
								sb.append(end.get(i).getNumCasa() + "</p>");
								sb.append("<p>" + end.get(i).getCidade() +", ");
								sb.append(end.get(i).getEstado() + ", " + end.get(i).getCep() + "</p>");
								sb.append("<p>" + end.get(i).getPais() + "</p>");
								sb.append("<a href='#'>Editar</a></p></td></tr>");
								out.print(sb.toString());
							}
						%>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>