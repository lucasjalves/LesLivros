<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%
	
		Map<Integer, Pedido> mapaUsuario = (Map<Integer, Pedido>)request.getSession().getAttribute("mapaUsuarios");
		String id = (String)request.getSession().getAttribute("userid");
		int idUsuario = Integer.parseInt(id);
	%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comprar</title>
</head>
<body>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
              <a class="nav-link" href="Home.jsp">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="Conta.jsp">Minha conta</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container">
    	<div class="row">
    	<div class="col-lg-2"></div>	    
		      <div class="col-lg-8">
		      		<div class="card mt-4">
			            <div class="card-body">

			            </div>
			        </div>
		      </div>
		</div>
	</div>

</body>
</html>