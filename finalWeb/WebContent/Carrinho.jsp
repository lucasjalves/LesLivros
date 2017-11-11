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
				<table class="table table-striped">
					<thead>
						<th><h3>Meu carrinho</h3></th>
					</thead>
					<tbody>
						<tr>
							<td>Item</td>
							<td>Pre�o</td>
							<td>Quantidade</td>
							<td>Subtotal</td>
						</tr>
						<%
						if(itensCarrinho != null)
						{
							Map<Integer, Integer> map = new HashMap<Integer, Integer>();
							List<Livro> livros = new ArrayList<Livro>();
							StringBuilder sb = new StringBuilder();
							for(int i = 0; i < itensCarrinho.size(); i++)	
							{
								sb.setLength(0);
								Item item = itensCarrinho.get(i);
								Livro l = item.getLivro();
								if(!map.containsKey(l.getId()))
								{
									map.put(l.getId(), 0);
									livros.add(l);
								}
								if(map.containsKey(l.getId()))
								{
									map.put(l.getId(), map.get(l.getId()) + 1);
								}
								
														
							}
							
							for(int i = 0; i < livros.size(); i ++)
							{
								sb.setLength(0);
								Livro l = livros.get(i);
								System.out.println(livros.size());
								sb.append("<tr>");
								sb.append("<td>");
								sb.append(l.getNome());
								sb.append("</td>");
								sb.append("<td>");
								sb.append(l.getPreco().toString());
								sb.append("</td>");
								sb.append("<td>");
								sb.append(map.get(l.getId()));
								sb.append("</td>");
								sb.append("<td>");
								sb.append(map.get(l.getId()) * l.getPreco());
								sb.append("</td>");
								sb.append("</tr>");	
								out.print(sb.toString());										
							}
						}
						else
						{
							out.print("<tr><td>N�o h� itens no seu carrinho</td></tr>");
						}
						%>																																				
					</tbody>
				</table>
			</div>
         </div>
     </div>  
     </div> 
</body>
</html>