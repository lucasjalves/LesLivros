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
			
			List<Cartao> c = p.getCartao();
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
							for(int i = 0; i < c.size(); i++)
							{
								sb.setLength(0);
								sb.append("<tr style='border-bottom: 1px solid #000; border-top: 1px solid #000'><td>");
								sb.append("<p>" + c.get(i).getBandeira() + "</p>");
								sb.append("<p>Número do cartão: "  + c.get(i).getNumero());
								sb.append("     Código Seg: " + c.get(i).getCodSeg());
								sb.append("</p><p>Data de Validade: " + c.get(i).getDtVencimento() + "</p>");
								sb.append("<p><a href='#'>Editar</a></p></td></tr>");
								out.print(sb.toString());
							}
						%>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>