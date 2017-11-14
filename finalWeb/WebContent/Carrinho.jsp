<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
		List<Item> itensCarrinho = (List<Item>)request.getSession().getAttribute("carrinho");	
	%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Meu Carrinho</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="css/shop-item.css">
	<script src="bootstrap/jquery-3.2.1.min.js"></script>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
	<script>
		function validarQtde(qtde, max, preco, i)
		{
			if(parseInt(qtde) > parseInt(max))
			{
				alert("O seu pedido ultrapassou a quantidade de itens no estoque!");
				 document.getElementById('qtde' + i).value = max;
				$('#subtotal' + i).html(parseInt(qtde) * parseFloat(preco));
			}
			else
			{
				$('#subtotal' + i).html(parseInt(qtde) * parseFloat(preco));
			}
		}
		
		function atualizarCarrinho(id)
		{
			//window.location.replace("SalvarCarrinho?id=" + id + "&operacao=ADICIONARITEM");			
		}
	</script>
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
						</tr>
						<%
						
						if(itensCarrinho != null)
						{
							List<Livro> livros;
							Map<Integer, Integer> map = (HashMap<Integer,Integer>)request.getSession().getAttribute("mapaCarrinho");
							StringBuilder sb = new StringBuilder();
							if(map == null)
							{
								map = new HashMap<Integer, Integer>();
								livros = new ArrayList<Livro>();
								for(int i = 0; i < itensCarrinho.size(); i++)	
								{
									Item item = itensCarrinho.get(i);
									Livro l = item.getLivro();
									if(!map.containsKey(l.getId()))
									{
										map.put(l.getId(), 1);
										livros.add(l);
									}
									
								}
								request.getSession().setAttribute("livros", livros);
								request.getSession().setAttribute("mapaCarrinho", map);
							}
							else
							{
								livros = (List<Livro>)request.getSession().getAttribute("livros");
								for(int i = 0; i < itensCarrinho.size(); i++)	
								{
									Item item = itensCarrinho.get(i);
									Livro l = item.getLivro();
									if(!map.containsKey(l.getId()))
									{
										map.put(l.getId(), 1);
										livros.add(l);
									}

								}
								request.getSession().setAttribute("livros", livros);
							}
							livros = (List<Livro>)request.getSession().getAttribute("livros");
							for(int i = 0; i < map.size(); i ++)
							{
								sb.setLength(0);
								Livro l = livros.get(i);
								Integer qtdeEstoque = l.getQtdeEstoque() - map.get(l.getId());
								sb.append("<tr>");
								sb.append("<td>");
								sb.append(l.getNome());
								sb.append("</td>");
								
								sb.append("<td>");
								sb.append(l.getPreco().toString());
								sb.append("</td>");
	
								
								sb.append("<td>");
								
								sb.append("<form action='SalvarCarrinho' method='POST'>");
								sb.append("<button type='submit' name='operacao' value='removerItem'>");
								sb.append("<span>-</span>");
								sb.append("</button>");
								sb.append("<input type='hidden' name='txtId' value='"+ l.getId() +"'>");        
								sb.append("</form><div>");			
								
								sb.append(map.get(l.getId()));
								
								sb.append("</div><form action='SalvarCarrinho' method='POST'>");
								sb.append("<button type='submit' name='operacao' value='AdicionarItem'> ");
								sb.append("<span>+</span>");
								sb.append("</button>");   
								sb.append("<input type='hidden' name='txtId' value='"+ l.getId() +"'>");             
								sb.append("</form>");
								
								sb.append("</td>");
								

	

								
								sb.append("<td id='subtotal" + i +"'>");
								sb.append("<script>$('#subtotal" + i + "').html('" + map.get(l.getId()) * l.getPreco() + "');</script>");
								
								sb.append("</td>");
								sb.append("</tr>");	
								out.print(sb.toString());			
								
							}
							request.getSession().setAttribute("mapaCarrinho", map);
						}
						else
						{
							out.print("<tr><td>Não há itens no seu carrinho</td></tr>");
						}
						%>																																				
					</tbody>
				</table>
				</form>
			</div>
         </div>
     </div>  
     </div> 
</body>
</html>