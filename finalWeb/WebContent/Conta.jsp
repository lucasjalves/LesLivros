<%@page import="finalCore.util.ConverteDate"%>
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
	<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="css/shop-item.css">	
	<script>
	function mudarIframe(link){
		document.getElementById("iframesite").src = link;
	}
	</script>
<title>Minha conta</title>
</head>
<body>
	<%
		if(request.getSession().getAttribute("redirecionar") == null)
		{
			request.getSession().setAttribute("redirecionar", "redirecionar");
			response.sendRedirect("Conta.jsp");
			return;
		}
		request.getSession().setAttribute("redirecionar", null);
		
		Resultado resultado = (Resultado) session.getAttribute("resultadoLogin");
		if(resultado == null)
		{
			pageContext.forward("Index.jsp");
			return;
		}
		
		List<EntidadeDominio> entidades = resultado.getEntidades();
		PessoaFisica p = (PessoaFisica) entidades.get(0);


	%>
     <nav class="navbar navbar-expand-lg fixed-top" style="background-color: #3B3738; height: 75px;">
      <div class="container" style="width: 100%;">
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
        <div class="col-lg-3">

          <div class="card mt-4">
	          	<div class="card-body">
		          <div class="list-group">
		            <a href="#" class="list-group-item nav-link" onclick="mudarIframe('iframes/MinhaConta.jsp')">Minha Conta</a>
					<a href="#" class="list-group-item nav-link" onclick="mudarIframe('iframes/enderecos.jsp')">Meus Endereços</a>
					<a href="#" class="list-group-item nav-link" onclick="mudarIframe('iframes/cartoes.jsp')">Meus Cartões</a>	
					<a href="#" class="list-group-item nav-link" onclick="mudarIframe('iframes/pedidos.jsp')">Meus Pedidos</a>
					<a href="#" class="list-group-item nav-link" onclick="mudarIframe('iframes/minhastrocas.jsp')">Minhas Trocas</a>							
					<%
						if(p.getTipo() == 1)
						{
							out.print("<p class='list-group-item'>Opções administrativas</p>");
							out.print("<a href='#' class='list-group-item' "
									+ "onclick=\"mudarIframe('iframes/listapedidos.jsp')\">Todos os Pedidos</a>");
							
							out.print("<a href='#' class='list-group-item nav-link' " +
									"onclick=\"mudarIframe('iframes/listatrocas.jsp')\">Todas as Trocas</a>");
						}
					%>	
		          </div>
	          </div>
		</div>
        </div>	      
	      	<div class="col-lg-9">
		          <div class="card mt-4">
			             	 <div class="card-body">
								<div class="embed-responsive embed-responsive-4by3">
	  								<iframe id="iframesite" class="embed-responsive-item" src="iframes/MinhaConta.jsp" onclick="mudarIframe('iframes/MinhaConta.jsp"></iframe>
								</div>
			              </div>
			    	</div>
			   </div>
			</div> 	
	</div>
    <footer class="py-5" style="background-color: #3B3738;">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
      </div>
    </footer>	
</body>
</html>