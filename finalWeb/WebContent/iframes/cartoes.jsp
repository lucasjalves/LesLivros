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