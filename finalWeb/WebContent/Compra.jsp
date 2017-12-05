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
	<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
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
		$('#myModalAdicionarCartao').on('shown.bs.modal', function () {
			  $('#myInput').focus()
			})				
	</script>
	<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
	<link href="css/shop-homepage.css" rel="stylesheet">
	

	<%
		
		Map<Integer, PessoaFisica> mapaUsuario = (Map<Integer, PessoaFisica>)request.getSession().getAttribute("mapaUsuarios");
		Integer id = (Integer)request.getSession().getAttribute("userid");
		if(id == null)
		{
			pageContext.forward("Index.jsp");
			return;
		}
		String txtIndiceEndereco = request.getParameter("txtIndiceEnderecos");
		String indicePedido = request.getParameter("pedido");
		
		
		Resultado resultado = (Resultado) session.getAttribute("resultadoLogin");
		Map<Integer, Pedido> map = (Map<Integer, Pedido>) request.getSession().getAttribute("mapaUsuarios");
		List<EntidadeDominio> entidades = null;
		PessoaFisica pf = null;
		Pedido pedido = null;
		Endereco enderecoSelecionado = null;
		
		
		if(resultado != null)
		{
			entidades = resultado.getEntidades();
			pf = (PessoaFisica) entidades.get(0);			
		}
		if(indicePedido != null)
		{
			int ind = Integer.parseInt(indicePedido);
			entidades = resultado.getEntidades();
			pf = (PessoaFisica)entidades.get(0);
			int iPedido = Integer.parseInt(indicePedido);
			pedido = pf.getPedidos().get(ind);
			enderecoSelecionado = pedido.getEndereco();
		}
		else
		{
			int indiceEndereco = Integer.parseInt(txtIndiceEndereco);
			enderecoSelecionado = pf.getEndereco().get(indiceEndereco);
			pedido = map.get(id);
			pedido.setEndereco(enderecoSelecionado);			
		}

		
		List<Cartao> cartoes = pf.getCartao();
		


	%>
	<script>
		function colocarInput(indice, precoTotal, id)
		{

			
			var formulario = document.getElementById("formulario");
			
			var hidden = document.createElement('input');
			hidden.type = "hidden";
			hidden.setAttribute("name", "cartaoHidden" + indice);
			hidden.setAttribute("id", "cartaoHidden" + indice);
			hidden.setAttribute("value", id)


			var chkbox = document.getElementById("chkbox" + indice).checked;
			if(!chkbox)
			{
				
				var formInputHidden = document.getElementById("cartaoHidden"+indice);
				var formInputNumber = document.getElementById("input"+indice);

				
				var qtdeCartoesInput = document.getElementById("qtdeCartoes").value;
				var qtdeCartoesInt = parseInt(qtdeCartoesInput);
				
				qtdeCartoesInt = qtdeCartoesInt - 1;
				document.getElementById("qtdeCartoes").value = qtdeCartoesInt;
				
				var qtdeCartoesInput = document.getElementById("qtdeCartoes").value;
				qtdeCartoesInput = parseInt(qtdeCartoesInput);
				if(qtdeCartoesInput == 0)
					qtdeCartoesInput = 1;
				precoTotal = parseFloat(precoTotal);
				precoTotal = precoTotal / qtdeCartoesInput;
				document.getElementById("precoPorCartao").innerText = precoTotal.toFixed(2)+" R$";
				document.getElementById("precoCartaoCompra").value = precoTotal.toFixed(2);
			}
			else
			{

				formulario.appendChild(hidden);

				
				var qtdeCartoesInput = document.getElementById("qtdeCartoes").value;
				var qtdeCartoesInt = parseInt(qtdeCartoesInput);
				
				qtdeCartoesInt = qtdeCartoesInt + 1;
				qtdeCartoesInput = document.getElementById("qtdeCartoes").value = qtdeCartoesInt;

				var qtdeCartoesInput = document.getElementById("qtdeCartoes").value;
				qtdeCartoesInput = parseInt(qtdeCartoesInput);
				precoTotal = parseFloat(precoTotal);
				precoTotal = precoTotal / qtdeCartoesInput;
				document.getElementById("precoPorCartao").innerText = precoTotal.toFixed(2)+" R$";
				document.getElementById("precoCartaoCompra").value = precoTotal.toFixed(2);
			}
			
		}
	</script>
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
    <nav class="navbar navbar-expand-lg fixed-top" style="background-color: #3B3738; height: 75px;">
      <div class="container" >
        <a class="navbar-brand" href="#" style="color: white;" >ECommerce LES</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive" >
          <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
		    <div class="input-group">
		    <div class="input-group-btn">
		    	<button type="button" class="btn btn-secondary dropdown-toggle"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color: white;color: black;">
		    		<span class="sr-only">Toggle Dropdown</span>
		    		Filtros
		    	</button>
		        <div class="dropdown-menu">
		          <a class="dropdown-item" href="#">Autor</a>
		          <a class="dropdown-item" href="#">Edição</a>
		          <a class="dropdown-item" href="#">Título</a>
		        </div>		    	
		    </div>
		      <input type="text" class="form-control" style="width: 500px;">
		      <div class="input-group-btn">
		        <button type="button" class="btn btn-secondary" style="background-color: #C63D0F; hover:#C63D0F; border: #C63D0F;"><i class="fa fa-search" aria-hidden="true"></i></button> 

		      </div>
		      </div>          
          </li>
            <li class="nav-item active">
              <a class="nav-link" href="Home.jsp" style="color: white;">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="Conta.jsp" style="color: white;">Minha conta</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="Carrinho.jsp" style="color: white;">Meu Carrinho</a>
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
							<p>RUA: <%out.print(pedido.getEndereco().getLogradouro()); %>,								
							<%out.print(pedido.getEndereco().getNumCasa()); %></p>	
							
								
							<p>CIDADE: <%out.print(pedido.getEndereco().getCidade()); %>,
							<%out.print(pedido.getEndereco().getEstado()); %>,	
							CEP: <%out.print(pedido.getEndereco().getCep()); %>,
							<%out.print(pedido.getEndereco().getPais()); %></p>	
							<p>T: (<%out.print(pf.getTelefone().getDdd()); %>) <%out.print(pf.getTelefone().getNumero());%></p>
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
								
								double frete = pedido.getFrete();
								
								double total = pedido.getPrecoTotal();
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
									"id='btnEndereco' style='background-color: #2B7D77; hover: #2B7D77;border: #2B7D77;'>Visualizar</button></td>");
							out.print("<td><input type='checkbox' id='chkbox"+ i +"' onclick='colocarInput("+ i +",\"" + String.format("%.2f", pedido.getPrecoTotal()) +"\"," + c.getId() +")'></td>");
							out.print("<td id='cartao" + i +"'></td>"); 
							out.print("</tr>");
			     		}
			     	%>
					<tr>
						<td>
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalAdicionarCartao" style='background-color: #C63D0F; hover: #C63D0F;border: #C63D0F;'>Adicionar</button>
							<td><p>Valor a pagar com cada cartão: </p></td>
							<td><p id="precoPorCartao"></p></td>
						</td>
					</tr>
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
						modals.append("<button type='button' class='btn btn-secondary' data-dismiss='modal'>Fechar</button>");
						modals.append("</div>");
						modals.append("</div>");
						modals.append("</div>");
						modals.append("</div>");
						
						out.print(modals.toString());
					}
					if(request.getSession().getAttribute("resultadoCompra") != null)
					{
						Resultado r = (Resultado)request.getSession().getAttribute("resultadoCompra");
						out.print(r.getMsg());
					}
			     %>	
			     
			       <form action="ComprarItens" method="POST" id="formulario">
			 			<input type='hidden' name='qtdeCartoes' id = 'qtdeCartoes' value='0'/>
			 			<%
			 				if(indicePedido != null)
			 					out.print("<input type='hidden' name='indicePedido' value='" + indicePedido + "'/>");
			 			%>
			 			<button type='submit' name='operacao' value='CONSULTAR'  class ="btn btn-primary">
			 				<span>Finalizar Compra</span>
			 			</button>
			 			<input type='hidden' name="precoCartaoCompra" value="" />
			       </form>
		      	</div>
		  	</div>	
		  </div>
	</div>
	<form action="SalvarCartao" method="post" id="frmSalvarCartao">
		<div class="modal fade" id="myModalAdicionarCartao" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="myModalLabel">Adicionar Cartão</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
			<table>
					<tr><td>Número Cartão: </td><td><input type='text' id='txtNumCartao' name='txtNumCartao'/></td></tr>
					<tr><td>Bandeira: 			<td><select name="ddlBandeira">
					<option value="MasterCard">MasterCard</option>
      				<option value="VISA">VISA</option>
      				<option value="American Express">American Express</option>
					</select></td></tr>
					<tr><td>Data de Vencimento: </td><td><input type='text' id='txtDtVencimento' name='txtDtVencimento' /></td></tr>
					<tr><td>Código de Segurança: </td><td><input type='text' id='txtCodSeg' name='txtCodSeg' /></td></tr>
					<tr><td><input type="hidden" name="txtIdCartaoFk" value="<%out.print(id); %>" />	</td></tr>
					<tr><td><input type="hidden" name="local" value="compra" />	</td></tr>																				
			</table>
		      </div>
		      <div class="modal-footer">
		        <input type="submit" class ="btn btn-primary" id="operacao" name="operacao" value="SALVAR"  style='background-color: #C63D0F; hover: #C63D0F;border: #C63D0F;'/>		      
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
		      </div>
		    </div>
		  </div>
		</div>
	</form>

 </div>
    <footer class="py-5" style="background-color: #3B3738;">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
      </div>
      <!-- /.container -->
    </footer>
</div>
</body>
</html>