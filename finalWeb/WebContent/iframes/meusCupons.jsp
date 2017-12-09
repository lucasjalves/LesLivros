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
			
			List<CupomTroca> cuponsTroca = p.getCuponsTroca();
		%>
	</head>
<body>
	<div class="container">
		<div class="card">
			<div class="card-body">
			<h4 class="card-title">
				Meus cupons de Troca
			</h4>
			<table class="table table-hover table-striped">
				<thead>
					<th>Código Cupom</th>
					<th>Data de geração</th>
					<th>Data de expiração</th>
					<th>Valor</th>
					<th>Status</th>
				</thead>
					<tbody>
					<%
						if(cuponsTroca != null)
						{
							for(int i = 0; i < cuponsTroca.size(); i++)
							{
								CupomTroca c = cuponsTroca.get(i);
								out.print("<tr>");
								out.print("<td><p> " + c.getCodigo() + "</p></td>");
								out.print("<td><p> " + ConverteDate.converteDateString(c.getDtCriacao()) + "</p></td>");
								out.print("<td><p> " + ConverteDate.converteDateString(c.getDtValidade()) + "</p></td>");
								out.print("<td><p> " + String.format("%.2f", c.getDesconto()) + "</p></td>");
								if(c.getStatus() == 1)
									out.print("<td><p>DISPONÍVEL PARA USO</p></td>");
								else
									out.print("<td><p>INDISPONÍVEL PARA USO</p></td>");
								
								out.print("</tr>");
							}
						}
					%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>