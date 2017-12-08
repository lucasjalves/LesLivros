<%@page import="finalCore.util.ConverteDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
							System.out.println(p.getId());
							out.print("<form action='../RealizarTroca' method='POST'>");
							out.print("<input type='hidden' name='idPedidoTroca' value='" + p.getId() +"'>");
							out.print("<input type='hidden' name='desconto' value='" + it.getPrecoLivro()+ "'>");
							out.print("<input type='hidden' name='fk_cliente' value='" + p.getIdCliente()+ "'>");
							out.print("<input type='hidden' name='atualizarStatus' value='trocarItens'>");
							out.print("<tr>");
							out.print("<td><p>" + l.getNome() + "</p></td>");
							out.print("<td><p>" + it.getQtde()  + "</p></td>");
							out.print("<td><p>" + String.format("%.2f",it.getPrecoLivro()) + "R$ </p></td>");
							out.print("<td><p>" + p.getStatus() + "</p></td>");
							if(p.getStatus().trim().equals("EM TROCA"))
							{
								out.print("<td><button type='submit' class='btn btn-success'" + 
										" name='operacao' value='ALTERAR'><span>Confirmar</span></button></td>") ;								
							}

							out.print("</tr>");
							out.print("</form>");
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