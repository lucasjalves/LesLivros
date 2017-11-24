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
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Livro: <%out.print(l.getNome()); %></title>
	<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="css/shop-item.css">
	
	<script src="bootstrap/jquery-3.2.1.min.js"></script>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">Start Bootstrap</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="#">Home
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
              <a class="nav-link" href="#">Contact</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Page Content -->
    <div class="container">

      <div class="row">

        <div class="col-lg-3">
          <h1 class="my-4">Shop Name</h1>
          <div class="list-group">
            <a href="#" class="list-group-item active">Category 1</a>
            <a href="#" class="list-group-item">Category 2</a>
            <a href="#" class="list-group-item">Category 3</a>
          </div>
        </div>
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
              		out.print("<a href='SalvarCarrinho?operacao=VERIFICAR' class='btn btn-success'>Adicionar ao carrinho</a>");
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
						<tr>
							<td>Ano</td>
							<td><%out.print(l.getAno()); %></td>
						</tr>
						<tr>
							<td>Editora</td>
							<td><%out.print(l.getEditora()); %></td>
						</tr>
						<tr>
							<td>Edição</td>
							<td><%out.print(l.getEdicao()); %></td>
						</tr>
						<tr>
							<td>ISBN</td>
							<td><%out.print(l.getISBN()); %></td>
						</tr>
						<tr>
							<td>Nº Páginas</td>
							<td><%out.print(l.getNpaginas()); %></td>
						</tr>
						<tr>
							<td>Altura</td>
							<td><%out.print(l.getAltura()); %></td>
						</tr>
						<tr>
							<td>Largura</td>
							<td><%out.print(l.getLargura()); %></td>
						</tr>
						<tr>
							<td>Profundidade</td>
							<td><%out.print(l.getProfundidade()); %></td>
						<tr>
							<td>Peso</td>
							<td><%out.print(l.getPeso()); %></td>
						</tr>																																													
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
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
      </div>
      <!-- /.container -->
    </footer>
</body>
</html>