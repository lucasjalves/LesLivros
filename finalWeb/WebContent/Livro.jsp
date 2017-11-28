<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
		Resultado resultado = (Resultado) session.getAttribute("resultadoConsultaLivroCompra");
		
		if(resultado == null)
		{
			pageContext.forward("Home.jsp");
		}
		List<EntidadeDominio> entidades = resultado.getEntidades();
		Livro l = (Livro)entidades.get(0);
	
		request.getSession().setAttribute("livro", l);
		
		
	%>
	
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Livro: <%out.print(l.getNome()); %></title>
	<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="css/shop-item.css">
	<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
	<script src="bootstrap/jquery-3.2.1.min.js"></script>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
    
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

    <!-- Page Content -->
    <div class="container">

      <div class="row">

        <!-- /.col-lg-3 -->

        <div class="col-lg-9">
		
          <div class="card mt-4">
            <img class="card-img-top img-fluid" src="http://placehold.it/900x400" alt="">
            <div class="card-body">
              <h3 class="card-title"><%out.print(l.getNome()); %></h3>
              <h4><%out.print(String.format("%.2f", l.getPreco())); %>R$</h4>
              <p class="card-text"><%out.print(l.getSinopse()); %></p>
              <%
              	if(l.getQtdeEstoque() == 0)
              	{
              		out.print("<a href='' class='btn btn-danger' disabled>Esgotado</a>");
              	}
              	else
              	{
              		out.print("<a href='SalvarCarrinho?operacao=VERIFICAR' class='btn btn-success' " +
              				" style='background-color: #C63D0F; hover: #C63D0F;border: #C63D0F;'>Adicionar ao carrinho" 
              		+ "<i class='fa fa-shopping-cart' aria-hidden='true'></i></a>");
              	}
              %>
			
            </div>
          </div>
          <!-- /.card -->

          <div class="card card-outline-secondary my-4">
            <div class="card-header">
              Características do Livro
            </div>
            <div class="card-body">
				<div class="table-responsive">
					<table class="table table-striped">
					<thead>
						<th><h3>Características</h3></th>
					</thead>
					<tbody>
						
							<tr><td>Ano</td>
							<td><%out.print(l.getAno()); %></td></tr>
						
						
							<tr><td>Editora</td>
							<td><%out.print(l.getEditora()); %></td></tr>
						
						
							<tr><td>Edição</td>
							<td><%out.print(l.getEdicao()); %></td></tr>
						
						
							<tr><td>ISBN</td>
							<td><%out.print(l.getISBN()); %></td></tr>
						
						
							<tr><td>Nº Páginas</td>
							<td><%out.print(l.getNpaginas()); %></td></tr>
						
						
							<tr><td>Altura</td>
							<td><%out.print(l.getAltura()); %></td></tr>
						
						
							<tr><td>Largura</td>
							<td><%out.print(l.getLargura()); %></td></tr>
						
						
							<tr><td>Profundidade</td>
							<td><%out.print(l.getProfundidade()); %></td></tr>
						
							<tr><td>Peso</td>
							<td><%out.print(l.getPeso()); %></td></tr>
																																																			
					</tbody>
					</table>
				</div>
              
            </div>
          </div>
          <!-- /.card -->

        </div>
        <!-- /.col-lg-9 -->

      </div>

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5" style="background-color: #3B3738; " >
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
      </div>
      <!-- /.container -->
    </footer>
</body>
</html>