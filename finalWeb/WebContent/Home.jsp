<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
		Resultado resultado = (Resultado) session.getAttribute("resultadoConsultaLivro");
	    
		if(resultado == null)
		{
			pageContext.forward("SalvarLivro?operacao=CONSULTARLIVROHOME");
			return;
		}
	%>
		
	<%
		List<EntidadeDominio> entidades = resultado.getEntidades();
		Livro livro = (Livro)entidades.get(6);
	%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Compra de livros</title>
	<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
	<link href="css/shop-homepage.css" rel="stylesheet">
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
              <a class="nav-link" href="Index.jsp">Login</a>
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
            <a href="#" class="list-group-item">Category 1</a>
            <a href="#" class="list-group-item">Category 2</a>
            <a href="#" class="list-group-item">Category 3</a>
          </div>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

          <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
            <ol class="carousel-indicators">
              <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
              <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
              <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner" role="listbox">
              <div class="carousel-item active">
                <img class="d-block img-fluid" src="http://placehold.it/900x350" alt="First slide">
              </div>
              <div class="carousel-item">
                <img class="d-block img-fluid" src="http://placehold.it/900x350" alt="Second slide">
              </div>
              <div class="carousel-item">
                <img class="d-block img-fluid" src="http://placehold.it/900x350" alt="Third slide">
              </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>

          <div class="row">
			<% 
				StringBuilder sb = new StringBuilder();
				List<EntidadeDominio> entidadesLivro = resultado.getEntidades();
				for(int i = 0; i < entidadesLivro.size(); i ++)
				{
					sb.setLength(0);
					Livro l = (Livro)entidadesLivro.get(i);
					sb.append("<div class='col-lg-4 col-md-6 mb-4'>");
					sb.append("<div class='card h-100'>");
					sb.append("<a href='#'><img class='card-img-top' src='http://placehold.it/700x400' alt=''></a>");
					sb.append("<div class='card-body'>");
					
					sb.append("<h4 class='card-title'>");
					sb.append("<a href='SalvarLivro?operacao=INFORMACOESLIVRO&txtId=");
					sb.append(l.getId());
					sb.append("'>");
					sb.append(l.getTitulo());
					sb.append("</a>");
					sb.append("</h4>");
					
					sb.append("<h5>");
					sb.append(String.format("%.2f", l.getPreco()));
					sb.append("R$</h5>");
					
					sb.append("<p class='card-text'>");
					sb.append(l.getSinopse());
					sb.append("</p>");
					sb.append("</div>");
					sb.append("</div>");
					sb.append("</div>");
					
					out.print(sb.toString());
				}
			
			%>

          </div>
          <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="bootstrap/jquery-3.2.1.min.js"></script>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>