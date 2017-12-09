<%@page import="finalCore.util.ConverteDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
			if(request.getSession().getAttribute("resultadoTodosClientes") == null)
			{
				pageContext.forward("/SalvarCliente?operacao=CONSULTAR&local=listapedidos");
			}
			Resultado resultado = (Resultado)request.getSession().getAttribute("resultadoTodosClientes");
		%>
	</head>
<body>
	<div class="container">	
		<div class="card">
			<div class="card-body">
				<h4 class="card-tyle">
					Todos os Pedidos
				</h4>
				<table class="table-striped" width="50%" cellpadding="10" cellspacing="4">
				<thead class="thead-inverse">
					<th style="text-align: center;">ID</th>
					<th style="text-align: center;">Data</th>
					<th style="text-align: center;">Valor</th>
					<th style="text-align: center;">CPF cliente</th>
					<th style="text-align: center;">Status pedido</th>
					<th style="text-align: center;">Operação</th>
				</thead>
				<tbody>
				<%
					if(resultado != null)
					{
						List<EntidadeDominio> e = resultado.getEntidades();
						StringBuilder sb = new StringBuilder();
						if( e != null)
						{
							for(int i = 0; i < e.size(); i++)
							{
								PessoaFisica p = (PessoaFisica)e.get(i);
								if(p.getPedidos().size() > 0)
								{
								
									for(int j = 0; j < p.getPedidos().size(); j++)
									{
										sb.setLength(0);	
										sb.append("<tr>");
										Pedido pe = p.getPedidos().get(j);
										sb.append("<th scope='row'>");
										sb.append(pe.getId());
										sb.append("</th>");
										
										sb.append("<td>");
										sb.append(ConverteDate.converteDateString(pe.getDtPedido()));
										sb.append("</td>");
										
										sb.append("<td>");
										sb.append(String.format("%.2f", pe.getPrecoTotal()));
										sb.append("</td>");
										
										sb.append("<td>");
										sb.append(p.getCpf());
										sb.append("</td>");
										
										sb.append("<td>");
										sb.append(pe.getStatus());
										sb.append("</td>");
										if(pe.getStatus().trim().equals("APROVADO"))	
										{
											sb.append("<td>");
											sb.append("<a href='../ComprarItens?operacao=CONSULTAR" +
													"&local=listapedidos&id=" + pe.getId()+"&status="+pe.getStatus());
											sb.append("' class='btn btn-success' target='_parent'>Confirmar Transporte</a>");
											sb.append("</td>");
										}
										if(pe.getStatus().trim().equals("EM TRANSPORTE"))
										{
											sb.append("<td>");
											sb.append("<a href='../ComprarItens?operacao=CONSULTAR&" +
													"local=listapedidos&id=" + pe.getId() + "&status="+pe.getStatus());
											sb.append("' class='btn btn-success' target='_parent'>Confirmar Entrega</a>");
											sb.append("</td>");											
										}
										sb.append("</tr>");
										out.print(sb.toString());
									}
									
								}
							}
						}
						
					}
				%>
				</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
<%request.getSession().removeAttribute("resultadoTodosClientes"); %>
</html>