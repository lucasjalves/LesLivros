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
			
			PessoaFisica pf = (PessoaFisica) entidades.get(0);
			Integer id = (Integer)request.getSession().getAttribute("userid");
			
			List<Pedido> pedidos = pf.getPedidos();
			String indicePedido = request.getParameter("indicepedido");
			
			int index = Integer.parseInt(indicePedido);
			Pedido p = pedidos.get(index);
			/*
			Map<Integer, Integer> mapIds = new HashMap<Integer, Integer>();
			for(int i = 0; i < p.getItem().size(); i ++)
			{
				Item item = p.getItem().get(i);
				if(mapIds.containsKey(item.getId()))
					mapIds.replace(item.getId(), item.getQtde());
				
				if(mapIds.containsKey(item.getId()))
					mapIds.put(item.getId(), item.getQtde());
				
			}
			*/
		%>
	</head>
<body>
	<div class="container">
		<div class="card-body">
			<h4 class="card-title">
				Informações do pedido 
			</h4>
			
				<table class="table table-hover table-bordered">
					<thead>
						<th>Livro</th>
						<th>Preço</th>
						<th>Quantidade</th>						
						<% 
							if(p.getStatus().trim().equals("ENTREGUE"))
							{
								out.print("<th>Quantiade para troca</th>");
								out.print("<th>Operação</th>");
							}
						%>
						
						
					</thead>
					<%
						for(int i = 0; i < p.getItem().size(); i ++)
						{
							Integer qtde = null;
							Item item = p.getItem().get(i);
							Livro l  = item.getLivro();
							out.print("<form action='../RealizarTroca' method='POST'>");
							out.print("<tr>");
							out.print("<td><p>" + l.getNome() + "</p></td>");
							out.print("<td><p>" + String.format("%.2f", item.getPrecoLivro()) + "R$ </p></td>");
							out.print("<td><p>" + item.getQtde() + "<p></td>");

							if(p.getStatus().trim().equals("ENTREGUE"))
							{
								out.print("<input type='hidden' name='idPedido' value='" + p.getId() + "'/>");
								out.print("<input type='hidden' name='idLivro' value='" + l.getId() + "'/>");
								out.print("<input type='hidden' name='status' value='ENTREGUE'/>");
								out.print("<input type='hidden' name='idUsuario' value='" + id + "'/>");
								out.print("<input type='hidden' name='preco' value='"+item.getPrecoLivro()+"'/>");
								out.print("<td><input type='number' name='qtdeLivrosTroca' /></td>");
								out.print("<td><button type='submit' class='btn btn-success' name='operacao' value='SALVAR'>" + 
											"<span>Trocar</span></button></td>");	
								out.print("</tr>");
							}
							else
								out.print("</tr>");
							
							out.print("</form>");
						}
				%>
			
			</table>
		</div>
	</div>
</body>
</html>