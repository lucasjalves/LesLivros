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
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
		
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
					<table class="table table-hover table-bordered">
					<th>Data</th>
					<th>Valor</th>
					<th>Status</th>
					<th>Operação</th>
						<% 
							if(p.getPedidos() != null)
							{
								if(p.getPedidos().size() > 0 )
									
								{
									StringBuilder sb = new StringBuilder();
									for(int i = 0; i < pedidos.size(); i++)
									{
										Pedido pe = p.getPedidos().get(i);
										out.print("<tr>");
										out.print("<td><p> " + 
											ConverteDate.converteDateString(pe.getDtPedido()) + "</p></td>");
										out.print("<td><p>" + String.format("%.2f", pe.getPrecoTotal()) + " R$ </p></td>");
										out.print("<td><p>" + pe.getStatus() + "</p></td>" );
										if(pe.getStatus().equals("EM PROCESSAMENTO"))	
										{
											out.print("<td><a href='../Compra.jsp?pedido=" + i + "' " +
											"class='btn btn-danger' style='background-color: #C63D0F; hover: #C63D0F;border: #C63D0F' target='_parent'>Realizar Pagamento</a></td>");
										}
										else
										{
											out.print("<td><a href='pedidoTroca.jsp?indicepedido=" + i + "' " +
													"class='btn btn-danger' style='background-color: #C63D0F; hover: #C63D0F;border: #C63D0F'>Detalhes</a></td>");											
										}
										out.print("</tr>");
									}									
								}
								else
									out.print("<tr><td>Você não tem pedidos ainda</td</tr>");

							}
							else
								out.print("<tr><td>Você não tem pedidos ainda</td</tr>");
								
						%>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>