<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="bootstrap/popper.js"></script>
	<script src="bootstrap/jquery-3.2.1.min.js"></script>
	<script src="bootstrap/bootstrap.min.js"></script>
	<script>
		$('#myModal').on('shown.bs.modal', function () {
			  $('#myInput').focus()
			})
		$('#myModalAdicionarEnderecos').on('shown.bs.modal', function () {
			  $('#myInput').focus()
			})	
	</script>
	<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
	<link href="css/shop-homepage.css" rel="stylesheet">
	

	<%
	
		Map<Integer, Pedido> mapaUsuario = (Map<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
		String id = (String)request.getSession().getAttribute("userid");
		if(id == null)
		{
			pageContext.forward("Index.jsp");
			return;
		}
		String txtIndiceEndereco = request.getParameter("txtIndiceEnderecos");
		int indiceEndereco = Integer.parseInt(txtIndiceEndereco);
		
		System.out.println(id);
		int idUsuario = Integer.parseInt(id);
		Pedido pedido = mapaUsuario.get(idUsuario);
		
		
		Pessoa cliente = pedido.getUsuario();
		Endereco enderecoSelecionado = cliente.getEndereco().get(indiceEndereco);
		List<Cartao> cartoes = cliente.getCartao();
		


	%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comprar</title>
</head>


<body>
	<% 
	for(int i = 0; i < cartoes.size(); i ++)
	{
			out.print("<script>$('#myModalCartao"+ i + "').on('shown.bs.modal', function () {$('#myInput').focus()})</script>");
	}
	%>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg fixed-top" style="background-color: #2B7D77;">
      <div class="container">
        <a class="navbar-brand" href="#" style="color: white;" >Start Bootstrap</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="Home.jsp" style="color: white;">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="Conta.jsp" style="color: white;">Minha conta</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container">
    	<div class="row">    
		      <div class="col-sm-5">
		      		<div class="card mt-5">
		      		      <div class="card-header">
              				<p class="h4" style="text-align: center;">Resumo do Pedido<p>	
           				 </div>
			            <div class="card-body">	
			         
			            	<p class="h6">Informações de entrega</p>
			            	<hr>			
							<p>RUA: <%out.print(enderecoSelecionado.getLogradouro()); %>,								
							<%out.print(enderecoSelecionado.getNumCasa()); %></p>	
							
								
							<p>CIDADE: <%out.print(enderecoSelecionado.getCidade()); %>,
							<%out.print(enderecoSelecionado.getEstado()); %>,	
							CEP: <%out.print(enderecoSelecionado.getCep()); %>,
							<%out.print(enderecoSelecionado.getPais()); %></p>	
							<p>T: (<%out.print(cliente.getTelefone().getDdd()); %>) <%out.print(cliente.getTelefone().getNumero());%></p>
							<hr>

							<p class="h5">Livros</p>
							<table class="table">
							<th></th>
							<th>Produto</th>
							<th>Subtotal</th>
							<%
								StringBuilder sb = new StringBuilder();
								for(int i = 0; i < pedido.getItem().size(); i ++)
								{
									sb.setLength(0);
									Item item = pedido.getItem().get(i);
									Livro l = item.getLivro();
									sb.append("<tr>");
									sb.append("<td><img src='//c1.staticflickr.com/1/466/19681864394_c332ae87df_t.jpg'></td>");
									sb.append("<td>");
									sb.append(item.getQtde());
									sb.append("x ");
									sb.append(l.getNome());
									sb.append("</td><td>");
									sb.append(String.format("%.2f", (l.getPreco() * item.getQtde())));
									sb.append("R$</td></tr>");
									out.print(sb.toString());
								}
								String txtFrete = request.getParameter("txtFrete");
								double frete = Double.parseDouble(txtFrete);
								
								String txtTotal = request.getParameter("txtValor");
								double total = Double.parseDouble(txtTotal);
							%>
							<tr>
							<td></td>
							<td><strong>Frete: <% out.print(String.format("%.2f", frete)); %>R$</strong></td>
							<td><strong>Total: <% out.print(String.format("%.2f", total)); %>R$</strong></td>
							</tr>
						</table>
			        </div>
		      </div>
		</div>
			<div class="col-sm-7" >
		      	<div class="card mt-5">
		      		     <div class="card-header">
              			<p class="h4" style="text-align: center;">Pagamento<p>	              			
           			</div>
			     <div class="card-body">		
			     <p class="h5" style="text-align: center;">Cartão de Crédito</p>
			     <table class="table">
			     	<%
			     		for(int i = 0; i < cartoes.size(); i++)
			     		{
							Cartao c = cartoes.get(i);
							out.print("<tr>");
							out.print("<td><p class='card-text'>" + c.getBandeira() + "</p></td>");
							out.print("<td><button type='button' " +
									"class='btn btn-primary' " +
									"data-toggle='modal' "+
									"data-target='#myModalCartao" + i + "'"+
									"id='btnEndereco' style='background-color: #2B7D77;'>Visualizar</button></td>");
							out.print("</tr>");
			     		}
			     	%>
			     </table>
			     <%
			     	StringBuilder modals = new StringBuilder();
					for(int i = 0; i < cartoes.size(); i++)
					{
						Cartao c = cartoes.get(i);			
						modals.setLength(0);
						modals.append("<div class='modal fade' id='myModalCartao" + i + "' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>");
						modals.append("<div class='modal-dialog'role='document'>");
						modals.append("<div class='modal-content'>");
						modals.append("<div class='modal-header'>");
						modals.append("<h5 class='modal-title' id='myModalLabel'>Cartões</h5>");
						modals.append("<button type='button' class='close' data-dismiss='modal' aria-label='Close'>");
						modals.append("<span aria-hidden='true'>&times;</span>");
						modals.append("</button>");
						modals.append("</div>");
						modals.append("<div class='modal-body' id='detalhesCartoes'>");
						modals.append("<table>");
						modals.append("<tr><td><input type='hidden' id='txtIdEndereco' name='txtIdEndereco' value='" + String.valueOf(c.getId())+ "' disabled/></td></tr>");
						modals.append("<tr><td>Número Cartão: </td><td><input type='text' id='txtNumCartao' name='txtNumCartao' value='" + c.getNumero() + "' disabled/></td></tr>");
						modals.append("<tr><td>Bandeira: </td><td><input type='text' id='ddlBandeira' name='ddlBandeira' value='" + c.getBandeira() + "' disabled/></td></tr>");
						modals.append("<tr><td>Data de Vencimento: </td><td><input type='text' id='txtDtVencimento' name='txtDtVencimento' value='" + c.getDtVencimento() + "' disabled/></td></tr>");
						modals.append("<tr><td>Código de Segurança: </td><td><input type='text' id='txtCodSeg' name='txtCodSeg' value='" + c.getCodSeg() + "' disabled/></td></tr>");
						modals.append("<tr><td><input type='hidden' id='txtIdCartaoFk' name='txtIdCartaoFk' value='" + c.getPkUsuario() + "' disabled/></td></tr>");
						modals.append("</table>");
						modals.append("</div>");
						modals.append("<div class='modal-footer'>");
						modals.append("<button type='button' class='btn btn-danger' data-dismiss='modal'>Fechar</button>");
						modals.append("</div>");
						modals.append("</div>");
						modals.append("</div>");
						modals.append("</div>");
						
						out.print(modals.toString());
					}
			     %>	
			        		
		      	</div>
		  	</div>	
		  </div>
	</div>




</div>
</body>
</html>