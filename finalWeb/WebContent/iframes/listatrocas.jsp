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
		
<title>Insert title here</title>
</head>
<body>
	<%
		
		if(request.getSession().getAttribute("resultadoTrocas") == null)
		{
			pageContext.forward("/RealizarTroca?operacao=CONSULTAR");
			return;
		}

	Resultado resultado = (Resultado)request.getSession().getAttribute("resultadoTrocas");
		List<EntidadeDominio> trocas = resultado.getEntidades();
		
	%>
	
	<div class="container">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">
					Pedidos de Troca
				</h4>

				<table class="table table-hover table-bordered">
					<thead>
						<th>Livro</th>
						<th>Quantidade</th>
						<th>Preço unitário</th>
						<th>Status</th>
						<th>Operação</th>
					</thead>
					
					<%
					if(trocas != null)
					{
						for(int i = 0; i < trocas.size(); i ++)
						{
							PedidoTroca p = (PedidoTroca)trocas.get(i);
							ItemTroca it  = p.getItensTroca().get(0);
							Livro l = p.getItensTroca().get(0).getLivro();

							out.print("<tr>");
							out.print("<td><p>" + l.getNome() + "</p></td>");
							out.print("<td><p>" + it.getQtde()  + "</p></td>");
							out.print("<td><p>" + String.format("%.2f",it.getPrecoLivro()) + "R$ </p></td>");
							out.print("<td><p>" + p.getStatus() + "</p></td>");
							if(p.getStatus().trim().equals("EM TROCA"))
							{
								out.print("<form action='../RealizarTroca' method='POST'>");	
								out.print("<input type='hidden' name='idLivro' value='"+l.getId()+"'/>");
								out.print("<input type='hidden' name='idPedidoTroca' value='"+p.getId()+"'/>");
								out.print("<input type='hidden' name='desconto' value='" + it.getPrecoLivro()+ "'/>");
								out.print("<input type='hidden' name='fk_cliente' value='" + p.getIdCliente()+ "'/>");
								out.print("<input type='hidden' name='status' value='EM TROCA'/>");
								out.print("<input type='hidden' name='qtde' value='" + it.getQtde() +"'/>");
								out.print("<input type='hidden' local='/iframes/listatrocas.jsp'/>");
								out.print("<td><button type='submit' class='btn btn-success'" + 
										" name='operacao' value='ALTERAR'><span>Confirmar</span></button></td>") ;									
								out.print("</form>");
							}

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
	</div>
</body>
<% 
	request.getSession().removeAttribute("resultadoTrocas");
%>
</html>