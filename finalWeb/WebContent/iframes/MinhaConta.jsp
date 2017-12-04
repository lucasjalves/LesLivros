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
	List<Cartao> cartoes = p.getCartao();
	Integer id = (Integer)request.getSession().getAttribute("userid");
%>
</head>
<body>
      <div class="container">
	      <div class="row">
              
	      	<div class="col-lg-5">
		          <div class="card mt-4">
			             	 <div class="card-body">
			             	 	<h4 class="card-title">Meus dados</h4>
								<p>Email: <%out.print(p.getEmail()); %></p>
								<p>Nome: <%out.print(p.getNome()); %></p>
								<p>Data de Nascimento: <%out.print(ConverteDate.converteDateString(p.getDtNascimento())); %></p>
								<p>CPF: <%out.print(p.getCpf()); %></p>
								<p><a href="">Alterar</a></p>
			              </div>
			    	</div>
			   </div>
			</div>	

		</div>
</body>
</html>