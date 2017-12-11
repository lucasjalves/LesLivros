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
		
		%>
		
</head>

<body>
	<div class="container">
		<div class="card">	
			<div class="card-body">
				<h4 class="card-title">
					Meus dados cadastrais
				</h4>
				<form action="../SalvarCliente" method="POST" target="_parent">
				<table class="table table-bordered">
						<tr>
							<td>Email: </td>
							<td><input type='text' name='txtEmail' value='<%out.print(p.getEmail());%>'/></td>
						</tr>
						<tr>
							<td>Senha: </td>
							<td><input type='text' name='txtSenha' value='<%out.print(p.getSenha());%>'/></td>
						</tr>
						<tr>
							<td>Nome: </td>
							<td><input type='text' name='txtNomeCli' value='<%out.print(p.getNome());%>'/></td>
						</tr>
						<tr>
							<td>Data de Nascimento: </td>
							<td><input type='text' name='txtDtNascimento' value='<%out.print(ConverteDate.converteDateString(p.getDtNascimento()));%>'/></td>
						</tr>
						<tr>
							<td>CPF: </td>
							<td><input type='text' name='txtCpf' value='<%out.print(p.getCpf());%>'/></td>
						</tr>
						<tr>
							<td>Gênero: </td>
							<td><input type='text' name='txtGenero' value='<%out.print(p.getGenero());%>'/></td>						
						<tr>
							<td><button type='submit' class='btn btn-success' name='operacao' value='ALTERAR'>
										<span>Alterar dados</span></button></td>
						</tr>														
					<% 
						out.print("<input type='hidden' name='txtId' value='" + p.getId() +"'/>");
					%>								
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>