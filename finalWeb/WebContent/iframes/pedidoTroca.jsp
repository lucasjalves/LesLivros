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
			List<Pedido> pedidos = pf.getPedidos();
			String indicePedido = request.getParameter("indicepedido");
			int index = Integer.parseInt(indicePedido);
			Pedido p = pedidos.get(index);
		%>
	</head>
<body>
	<div class="container">
		<div class="card-body">
			<h4 class="card-title">
				Informações do pedido para troca
			</h4>
			
				<table class="table table-hover table-bordered">
					<thead>
						<th>Livro</th>
						<th>Quantidade</th>
						<th>Preço</th>
						<th>Qtde para troca</th>
					</thead>
					<%
						for(int i = 0; i < p.getItem().size(); i ++)
						{
							Item item = p.getItem().get(i);
							Livro l  = item.getLivro();
							out.print("<form action='../RealizarTroca' method='POST'");
							out.print("<tr>");
							out.print("<td><p>" + l.getNome() + "</p></td>");
							out.print("<td><p>" + item.getQtde() + "<p></td>");
							out.print("<td><p>" + String.format("%.2f", item.getPrecoLivro()) + "R$ </p></td>");
							out.print("<td><input type='number' class='form-control' size='4' name='qtdeLivrosTroca' max='"+ item.getQtde() +"'/></td>");
							out.print("<td><button type='submit' class='btn btn-success' name='operacao' value='SALVAR'>" + 
							"<span>Trocar</span></button></td>");
							out.print("</tr>");
							out.print("<input type='hidden' name='idPedido' value='" + p.getId() + "'/>");
							out.print("<input type='hidden' name='idLivro' value='" + l.getId() + "'/>");
							out.print("<input type='hidden' name='idUsuario' value='" + id + "'/>");
							out.print("<input type='hidden' name='preco' value='" + item.getPrecoLivro() +"'/>");
							out.print("</form>");
						}
				%>
			
			</table>
		</div>
	</div>
</body>
</html>