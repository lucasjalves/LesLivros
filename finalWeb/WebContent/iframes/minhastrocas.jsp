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
		
		<title>Minha conta</title>
		<%
			Resultado resultado = (Resultado) session.getAttribute("resultadoLogin");
			List<EntidadeDominio> entidades = resultado.getEntidades();
			PessoaFisica pf = (PessoaFisica) entidades.get(0);
			Integer id = (Integer)request.getSession().getAttribute("userid");
			List<PedidoTroca> trocas = pf.getTrocas();
		%>
	</head>
<body>
	<div class="container">
		<div class="card-body">
			<h4 class="card-title">
				Solicitações de troca
			</h4>
			
				<table class="table table-hover table-bordered">
					<thead>
						<th>Livro</th>
						<th>Quantidade</th>
						<th>Preço unitário</th>
						<th>Status</th>
					</thead>
					<%
					if(trocas != null)
					{
						for(int i = 0; i < trocas.size(); i ++)
						{
							PedidoTroca p = trocas.get(i);
							ItemTroca it  = p.getItensTroca().get(0);
							Livro l = p.getItensTroca().get(0).getLivro();
							out.print("<tr>");
							out.print("<td><p>" + l.getNome() + "</p></td>");
							out.print("<td><p>" + it.getQtde()  + "</p></td>");
							out.print("<td><p>" + String.format("%.2f",it.getPrecoLivro()) + "R$ </p></td>");
							out.print("<td><p>" + p.getStatus() + "</p></td>");
							out.print("</tr>");
						}
					}
					else
					{
						out.print("<tr><td><p>Não há pedidos de trocas</p></td></tr>");
					}
				%>
			
			</table>
		</div>
	</div>
</body>
</html>