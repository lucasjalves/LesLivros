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
			
			List<Pedido> pedidos = p.getPedidos();
		%>
	</head>
	<body>
		<div class="container">
			<div class="card">
				<div class="card-body">
                  <h4 class="card-title">
                    Meus Pedidos
                  </h4>				
					<table class="table">
						<% 
							if(p.getPedidos() != null)
							{
								if(p.getPedidos().size() > 0 )
									
								{
									StringBuilder sb = new StringBuilder();
									for(int i = 0; i < pedidos.size(); i++)
									{
										
									}									
								}
								else
									out.print("<tr><td>Voc� n�o tem pedidos ainda</td</tr>");

							}
							else
								out.print("<tr><td>Voc� n�o tem pedidos ainda</td</tr>");
								
						%>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>