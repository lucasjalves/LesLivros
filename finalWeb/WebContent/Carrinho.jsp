<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<% 
		/*
    	if(request.getSession().getAttribute("redirecionar") != null)
    	{
    		request.getSession().setAttribute("redirecionar", null);
    		response.sendRedirect("Carrinho.jsp");
    		return;
   		}
		request.getSession().setAttribute("redirecionar", "redirecionar");
		*/
		List<Livro> livros = (List<Livro>)request.getSession().getAttribute("livros");	
		Map<Integer, Integer> map = (Map<Integer, Integer>) request.getSession().getAttribute("mapaCarrinho");
		Resultado cupom = (Resultado)request.getSession().getAttribute("resultadoCupom");
		Resultado res = (Resultado)request.getSession().getAttribute("resultadoLivro");
		if(map != null)
		{
			if(map.size() != 0)
			{
				Map<Integer, Resultado> mapaResultado = (Map<Integer, Resultado>)request.getSession().getAttribute("mapaResultado");
				Set<Integer> chaves = mapaResultado.keySet();
				for(Integer chave: chaves)
				{
					if(mapaResultado.get(chave) == null)
					{
						String url = "SalvarCarrinho?operacao=validar&idLivro="+chave;
						pageContext.forward(url);
						return;
					}
				}	
			}
				
		}
	%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Meu Carrinho</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="css/shop-item.css">
	<script src="bootstrap/jquery-3.2.1.min.js"></script>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">Start Bootstrap</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="Home.jsp">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Services</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="Index.jsp">Login</a>
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
						double desconto = 0;
						double precoTotal = 0;
						double precoFrete = 0;				
						if(livros != null)
						{
							Map<Integer, Resultado> mapaResultado = (Map<Integer, Resultado>)request.getSession().getAttribute("mapaResultado");
							StringBuilder sb = new StringBuilder();
							
							for(int i = 0; i < map.size(); i ++)
							{
								sb.setLength(0);
								Livro l = livros.get(i);
								sb.append("<tr>");
								sb.append("<td>");
								sb.append(l.getNome());
								sb.append("</td>");
								
								sb.append("<td>");
								sb.append(String.format("%.2f", l.getPreco()));
								sb.append("R$</td>");
								
								double freteLivro = (Double.parseDouble(l.getAltura()) * 
										Double.parseDouble(l.getProfundidade()) * 
										Double.parseDouble(l.getLargura())) / 200;
								precoFrete = precoFrete + freteLivro;
								
								sb.append("<td style='text-align: center;'>");
								sb.append("<form action='SalvarCarrinho' method='POST' style=' float: left;' >" );
								
								if(map.get(l.getId()) <= 1){
									sb.append("<button type='button' style='float: left;' class='btn btn-danger' disabled>");
								}		
								else{
									sb.append("<button type='submit' name='operacao' value='subtrairItem' style='float: left;' class='btn btn-danger'>");
								}
								sb.append("<span>-</span>");
								sb.append("</button>");   
								sb.append("<input type='hidden' name='txtId' value='"+ l.getId() +"'>");             
								sb.append("</form>");		
								
								sb.append(map.get(l.getId()));
								
								sb.append("<form action='SalvarCarrinho' method='POST' style=' float: right;' >" );
								if(map.get(l.getId()) == 0){
									sb.append("<button type='button' style='float: left; size: 80%' class='btn btn-success' disabled> ");
								}
								else{
									sb.append("<button type='submit' name='operacao' value='AdicionarItem' style='float: left; size: 80%' class='btn btn-success' > ");
								}
								
								sb.append("<span>+</span>");
								sb.append("</button>");   
								sb.append("<input type='hidden' name='txtId' value='"+ l.getId() +"'>");             
								sb.append("</form>");
								
								sb.append("</td>");
								


								
								sb.append("<td id='subtotal" + i +"'>");
								sb.append("<script>$('#subtotal" + i + "').html('" + String.format("%.2f" , map.get(l.getId()) * l.getPreco()) + "R$');</script>");
								
								precoTotal = precoTotal + map.get(l.getId()) * l.getPreco();
								
								sb.append("</td>");
								if(map.get(l.getId()) == 0){
									map.remove(l.getId());
									livros.remove(i);
									request.getSession().setAttribute("mapaCarrinho", map);
									request.getSession().setAttribute("livros", livros);
									sb.append("<td><p style='color: red'>Este item será removido por indisponibilidade no estoque!</p></td>");
								}
								else{
									sb.append("<td><a href='SalvarCarrinho?operacao=removerItem&id=" + l.getId() +"'>Remover</a></td>");
								}
								sb.append("</tr>");	
								out.print(sb.toString());	
								mapaResultado.replace(l.getId(), null);
								
							}
							precoTotal = precoTotal + precoFrete;
							
						}
						if(livros == null || livros.size() == 0)
						{
							precoFrete = 0;
							precoTotal = 0;
							out.print("<tr><td>Não há itens no seu carrinho</td></tr>");
						}
				
						
						%>																																				
					</tbody>
				</table>
				</form>
			</div>
         </div>
     </div>
            <div class="col-lg-4 col-md-6 mb-4" style="position: relative; float: right;">
              <div class="card h-100">
                <div class="card-body">
                  <h4 class="card-title">
                    Total da compra
                  </h4>
                  
                  <%
                 
                  if(cupom != null) 
                  {
                	  if(cupom.getMsg() == null && cupom.getEntidades().size() != 0)
                	  {
                		  List<EntidadeDominio> listCupom = cupom.getEntidades();
                		  Cupom c = (Cupom)listCupom.get(0);
                		  desconto = (c.getDesconto()) / 100;
                		  precoTotal = precoTotal - (precoTotal * desconto);
                		  request.getSession().setAttribute("resultadoCupom", cupom);
                	  }
                  }
                  %>
                  <h6>Frete: <%out.print(String.format("%.2f", precoFrete)); %>R$ </h6>
                  <h6 style="color: green"> <%
                  if(cupom != null){
                	  out.print("Desconto: -" + String.format("%.2f", (precoTotal * desconto)) + "R$" );
                  }
                  
                  %></h6>
                  <h5>Total: <%out.print(String.format("%.2f", precoTotal)); %>R$</h5>
                  <form action="ValidarCupom" method="POST">
                  	<input type="text" name="txtCodigo" placeholder="Código do cupom" maxlength="6">
                  	<button type="submit" name="operacao" value="AdicionarCupom" class="btn btn-success" />	
                  			<span>Adicionar Cupom</span>
                  	</button>
                  </form>
                  <br>
                  <form action="ComprarItens" method="POST">
                  	 <input type="hidden" name="txtValor" value="<% out.print(precoTotal);%>">
                  	 <button type="submit" name="operacao" value="ComprarItens" class="btn btn-primary" />
                  	 	<span>Finalizar Compra</span>	
                  </form>
                   <%if(cupom != null) 
                   {
                		if(cupom.getMsg() !=null)
                		{
                			out.print(cupom.getMsg());
                		}
                   }
                  %>                 
                </div>
              </div>
            </div>  
            <%
            
			if( res != null)
			{
				if(res.getMsg() != null)
				{
					out.print("<tr><td><p style='color: red'>" + res.getMsg() +"</p></td></tr>");
				}
			}
            request.removeAttribute("resultadoCarrinho");
            %>
     </div> 
</body>
</html>