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
					<table class="table table-hover table-bordered">
					<th>Data</th>
					<th>Valor</th>
					<th>Status</th>
					<th>Opera��o</th>
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
										if(pe.getStatus().trim().equals("ENTREGUE"))
										{
											out.print("<td><a href='pedidoTroca.jsp?indicepedido=" + i + "' " +
													"class='btn btn-danger' style='background-color: #C63D0F; hover: #C63D0F;border: #C63D0F'>Solicitar Troca</a></td>");											
										}
										out.print("</tr>");
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