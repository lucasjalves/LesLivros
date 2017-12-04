<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<% 
		Integer id = (Integer)request.getSession().getAttribute("userid");

		
    	if(request.getSession().getAttribute("redirecionar") == null)
    	{
    		request.getSession().setAttribute("redirecionar", "1");
    		response.sendRedirect("Carrinho.jsp");
    		return;
   		}
		request.getSession().setAttribute("redirecionar", null);
	
		Resultado resultado = (Resultado) session.getAttribute("resultadoLogin");
		Map<Integer, Pedido> map = (Map<Integer, Pedido>) request.getSession().getAttribute("mapaUsuarios");
		List<EntidadeDominio> entidades = null;
		PessoaFisica pf = null;
		if(resultado != null)
		{
			entidades = resultado.getEntidades();
			pf = (PessoaFisica) entidades.get(0);			
		}


		
	%>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Meu Carrinho</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="css/shop-item.css">
	<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
	<script src="bootstrap/jquery-3.2.1.min.js"></script>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
	<script>
		$('#myModal').on('shown.bs.modal', function () {
			  $('#myInput').focus()
			})
		$('#myModalAdicionarEnderecos').on('shown.bs.modal', function () {
			  $('#myInput').focus()
			})	
			

		window.onload = function()
		{
			var freteLivro = document.getElementById("precoFreteLivro").value;
			freteLivro = parseFloat(freteLivro);
			
			
			var precoTotal = document.getElementById("precoTotalInput").value;
			precoTotal = parseFloat(precoTotal);
			if(document.getElementById("cepp") != null)
				calcularCEP(1, document.getElementById("cepp").value, 0);
			else	
				calcularCEP(null, null, 0);
						
		}
		function calcularCEP(radio, cep, i)
		{
	
			var freteCep = 0;
			
			var freteLivro = document.getElementById("precoFreteLivro").value;
			freteLivro = parseFloat(freteLivro);
			if(cep != null)
			{
				var cep = cep.slice(0,4);
				var numero = parseInt(cep);
				if(numero <= 3000)
				{
					freteCep = freteCep + (numero / 85);
				}
				else
				{
					freteCep = freteCep + (numero / 150);
				}
			}
			freteCep += freteLivro;
			var precoTotal = document.getElementById("precoTotalInput").value;
			precoTotal = parseFloat(precoTotal);
			precoTotal = precoTotal + freteCep;
			
			document.getElementById('txtIndiceEnderecos').value = i;
			document.getElementById("precoFretestring").innerText = "Frete: " + (freteCep + freteLivro).toFixed(2) + "R$ ";
			document.getElementById("precoTotal").innerText = "Preço: " + precoTotal.toFixed(2) + "R$ ";
			document.getElementById("txtValor").value = precoTotal;
			document.getElementById("txtIndiceEnderecos").value = i;
			
			document.getElementById("txtFrete").value = freteCep + freteLivro;
			for(i = 0; i < (document.radio.ra.length); i++)
			{
				document.radio.ra[i].checked = false;
			}
			radio.checked = true;
			
		}
	</script>
</head>
<body>

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
    <div class="card card-outline-secondary my-4">
    	<div class="card-header">
              Carrinho de compras
        </div>
        <div class="card-body">
			<div class="table-responsive">
				<form action="SalvarCarrinho" method="POST" id="form1">
				<table class="table table-striped">
					<thead>
						<th><h3>Meu carrinho</h3></th>
					</thead>
					<tbody>
						<tr>
							<td>Item</td>
							<td>Preço</td>
							<td>Quantidade</td>
							<td>Subtotal</td>
							<td>Operação</td>
			
						</tr>
						<%
						
						double precoTotal = 0;
						double precoFrete = 0;			
						double preco = 0;
						double freteLivro = 0;
						if(map != null)
						{
						
							StringBuilder sb = new StringBuilder();
							Pedido pedido = map.get(id);
							List<Item> item = pedido.getItem();
							if(item.size() != 0)
							{
											
								for(int i = 0; i < item.size(); i ++)
								{
									sb.setLength(0);
									Item it = item.get(i);
									Livro l = it.getLivro();
									sb.append("<tr>");
									sb.append("<td>");
									sb.append(l.getNome());
									sb.append("</td>");
									
									sb.append("<td>");
									sb.append(String.format("%.2f", l.getPreco()));
									sb.append("R$</td>");
									
									freteLivro = (Double.parseDouble(l.getAltura()) * 
											Double.parseDouble(l.getProfundidade()) * 
											Double.parseDouble(l.getLargura())) / 200;
									precoFrete = precoFrete + freteLivro;
									
									sb.append("<td style='text-align: center;'>");
									sb.append("<form action='SalvarCarrinho' method='POST' style=' float: left;' >" );
									
									if(item.get(i).getQtde() <= 1){
										sb.append("<button type='button' style='float: left; background-color: #7E8F7C; hover:#7E8F7C; border: #7E8F7C;'  class='btn btn-danger' disabled>");
									}		
									else{
										sb.append("<button type='submit' name='operacao' value='subtrairItem' style='float: left;  background-color: #7E8F7C; hover:#7E8F7C; border: #7E8F7C;' class='btn btn-danger'>");
									}
									sb.append("<span>-</span>");
									sb.append("</button>");   
									sb.append("<input type='hidden' name='txtId' value='"+ l.getId() +"'>");             
									sb.append("</form>");		
									
									sb.append(it.getQtde());
									
									sb.append("<form action='SalvarCarrinho' method='POST' style=' float: right;' >" );
									if(it.getQtde() == 0){
										sb.append("<button type='button' style='float: left; size: 80%; " +
										" background-color: #C63D0F; hover:#C63D0F; border: #C63D0F;' " +
										"class='btn btn-success' disabled> ");
									}
									else{
										sb.append("<button type='submit' name='operacao' value='AdicionarItem' " +
												"class='btn btn-success' style='float: left; size: 80%; " + 
										"background-color: #C63D0F; hover:#C63D0F; border: #C63D0F;'> ");
									}
									
									sb.append("<span>+</span>");
									sb.append("</button>");   
									sb.append("<input type='hidden' name='txtId' value='"+ l.getId() +"'>");             
									sb.append("</form>");
									
									sb.append("</td>");
									
	
	
									int qtdeLivro = it.getQtde();
									preco = it.getLivro().getPreco();
	
									sb.append("<td id='subtotal" + i +"'>");
									sb.append(String.format("%.2f" , (qtdeLivro * preco)) + "R$");
									
									precoTotal = precoTotal + (qtdeLivro * preco);
									
									sb.append("</td>");
									if(qtdeLivro == 0){
										item.remove(i);
										pedido.setItem(item);
										sb.append("<tr><td><p style='color: red'>Este item será removido por indisponibilidade no estoque!</p></td>");
										map.replace(id, pedido);
										request.getSession().setAttribute("mapUsuarios", map);
									}
									else{
										sb.append("<td><a href='SalvarCarrinho?operacao=removerItem&indice=" + i +"'>Remover</a></td>");

									}


									sb.append("</tr>");	
									out.print(sb.toString());	
									//mapaResultado.replace(l.getId(), null);
									
								}
								precoTotal = precoTotal + precoFrete;
								
							}
						}
						if(map == null || map.size() == 0)
						{
							precoFrete = 0;
							precoTotal = 0;
							out.print("<td>Não há itens no seu carrinho</td>");									
						}
				
						
						%>																																				
							</tbody>
						</table>
						</form>
					</div>
		         </div>
		     </div>
     					<% 

						%>
            <div class="col-lg-4 col-md-6 mb-4" style="position: relative; float: right;">
              <div class="card h-100">
                <div class="card-body">
                  <h4 class="card-title">
                    Total da compra
                  </h4>
                  
                  <%
	
                  Resultado msgcupom = (Resultado)request.getSession().getAttribute("resultadoCupom");
                  if(msgcupom != null)
                  {
                	  if(msgcupom.getMsg() != null)	
                		  out.print("<p style='color: red;'>" + msgcupom.getMsg() +"</p>");
                  }
                  
                  %>
                  <h6 id="precoFretestring"></h6>
                  <input type='hidden' value='<%out.print(precoTotal); %>' id='precoTotalInput'>
                  <input type='hidden' value='<%out.print(precoFrete); %>' id='precoFreteLivro'>
                  
                  <h6 style="color: green" id="desconto">
                  	<%
                  	if(map != null)
                  	{
                  		Pedido pedido = map.get(id);
                      	CupomPromocional c = pedido.getCupomPromocional();
                      	if(c != null)
                      	{
    						double desconto = c.getDesconto();
    						desconto = 0 - desconto;
                      		out.print("Desconto: " + String.format("%.2f", desconto) + "R$");

                      	}                  		
                  	}

                  	%>
                  </h6>
                  
                  <h5 id="precoTotal"></h5>
                  
                  <form action="ValidarCupom" method="POST">
                  	<input type="text" name="txtCodigo" placeholder="Código do cupom" maxlength="6">
                  	<button type="submit" name="operacao" value="CONSULTAR" class="btn btn-success" style='background-color: #C63D0F; hover:#C63D0F; border: #C63D0F;'/>	
                  			<span>Adicionar Cupom</span>
                  	</button>
                  </form>
                  <br>
                  
                  <form action="ComprarItens" method="POST">
                  	 <input type="hidden" id="txtValor" name="txtValor" value="">
                  	 <input type="hidden" id='txtIndiceEnderecos' name="txtIndiceEnderecos" value="0">
                  	 <input type="hidden" id='txtFrete' name="txtFrete" value="">
                  	 <%
                  	 	if(map != null)
                  	 	{
                  	 		Pedido pedido = map.get(id);
                      	 	List<Item> item = pedido.getItem();
                      		if(item.size() > 0 || id != 0)
                      	 	{
                      		 	out.print("<button type='submit' name='operacao' value='SALVAR' class='btn btn-primary' " +
                      		 			"style='background-color: #C63D0F; hover:#C63D0F; border: #C63D0F;'/> " +
                      		 			" <span>Finalizar Compra <i class='fa fa-shopping-cart' aria-hidden='true'></i></span></button>");
                      	 	}
                      	           	 		
                  	 		}else
                  	 		out.print("<button type='button' class='btn btn-secondary' /><span>Finalizar Compra</span>");

                  	%>   
                  	 	
                  </form>
                                
                </div>
              </div>
            </div>
    
           <div class="col-lg-4 col-md-6 mb-4" style="position: relative; float: left;">
              <div class="card h-100">
                <div class="card-body">
                  <h4 class="card-title">
                    Endereços
                  </h4>
                  	<%
                  	if(request.getSession().getAttribute("userid") == null)
                  	{
                  		out.print("Você deve estar logado para realizar o calculo do frete!");
                  		out.print("Clique <a href='Index.jsp?redirect=carrinho'>AQUI</a> para logar");
                  	}
                  	if(request.getSession().getAttribute("userid") != null)
                  	{
                  		if(request.getSession().getAttribute("userid").equals(0))
                  		{
                      		out.print("Você deve estar logado para realizar o calculo do frete!");
                      		out.print("Clique <a href='Index.jsp?redirect=carrinho'>AQUI</a> para logar");                 			
                  		}
                  		else
                  		{
                  			out.print("<table class='table table'>");
                  			if(id != 0)
                  			{
                  				
	                  			List<Endereco> end = pf.getEndereco();
	    						for(int i = 0; i < end.size(); i++)
	    						{
	    							
	    							Endereco e = end.get(i);
	    							
									out.print("<tr><td>" + end.get(i).getNome() +"</td>");
	    							if(i == 0)
	    								out.print("<td><input type='radio' onclick='calcularCEP(this,  \""+ e.getCep() + "\","+ i +")' name='ra' checked></td></tr>");
	    							else
	    								out.print("<td><input type='radio' onclick='calcularCEP(this,  \""+ e.getCep() + "\","+ i +")' name='ra'></td></tr>");
	    							
	    						}
	    						out.print("<input type='hidden' value='" + end.get(0).getCep() +"' id='cepp'>" )	;
	    						out.print("</table>");
	                      		out.print("<button type='button' class='btn btn-primary' "+
	                      				"data-toggle='modal' data-target='#myModalAdicionarEnderecos' "+
	                      				"style='background-color: #C63D0F; hover: #C63D0F;border: #C63D0F;'>Adicionar</button>	");  							
	    					}
	
	                  	}
                  	}
                  	%>
     	      	
                </div>
              </div>
            </div>                
            <%
            

            request.removeAttribute("resultadoCarrinho");
            %>
       
            
  	<form action="SalvarEndereco" method="post" id="frmSalvarLivro">
		<div class="modal fade" id="myModalAdicionarEnderecos" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="myModalLabel">Adicionar Endereco</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
			<table>
				<tr>
					<td>Apelido Endereço: </td>
					<td><input type="text" id="txtNome" name="txtNome" /></td>
				</tr>
				<tr>
					<td>Tipo Residencia: </td>
					<td><input type="text" id="txtTipoRes" name="txtTipoRes" /></td>
				<tr>
					<td>Tipo Logradouro: </td>
					<td><input type="text" id="txtTipoLog" name="txtTipoLog" /></td>
				</tr>					
				<tr>		
					<td>Logradouro: </td>
					<td><input type="text" id="txtLogradouro" name="txtLogradouro" /></td>
				</tr>					
				<tr>	
					<td>Número da Casa: </td>
					<td><input type="text" id="txtNumCasa" name="txtNumCasa" /></td>
					</tr>
				<tr>
					<td>Bairro: </td>
					<td><input type="text" id="txtBairro" name="txtBairro" /></td>
				</tr>
				<tr>
					<td>CEP: </td>
					<td><input type="text" id="txtCep" name="txtCep" /></td>
				</tr>
				<tr>
					<td>Cidade: </td>
					<td><input type="text" id="txtCidade" name="txtCidade" /></td>
				</tr>

				<tr>
					<td>Estado: </td>
					<td><input type="text" id="txtEstado" name="txtEstado" /></td>
				</tr>
				<tr>
					<td>Pais: </td>
					<td><input type="text" id="txtPais" name="txtPais" /></td></tr>
				
				<input type="hidden" name="txtIdFkEndereco" value="<%out.print(id); %>"/>				
				<input type="hidden" name="local" value="carrinho">																				
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
       
    

</body>
</html>