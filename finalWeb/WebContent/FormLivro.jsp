<%@page import="finalWeb.vh.impl.SubCategoriaViewHelper"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
		
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title>Cadastro de Livros</title>
			<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
			<link rel="stylesheet" href="css/shop-item.css">
			<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
			<script src="bootstrap/jquery-3.2.1.min.js"></script>
		    <script src="bootstrap/bootstrap.bundle.min.js"></script>
		    <%
				Resultado resultadoCategorias = (Resultado) session.getAttribute("resultadoCategorias");	
				Resultado resultadoGrupos = (Resultado) session.getAttribute("resultadoGrupos");
				Resultado resultado = (Resultado) session.getAttribute("resultado");
				
				List<EntidadeDominio> entidadesCategorias = null;
				List<EntidadeDominio> entidadesGrupos = null;
				if(resultadoCategorias == null){
					pageContext.forward("SalvarCategoria?operacao=CONSULTAR");
					return;}
				
				else if(resultadoCategorias != null){
					entidadesCategorias = resultadoCategorias.getEntidades();
				}
				
				
				if(resultadoGrupos == null){
					pageContext.forward("SalvarGrupos?operacao=CONSULTAR");
					return;
				}
				
				else if(resultadoGrupos != null){
					entidadesGrupos = resultadoGrupos.getEntidades();
				}
				Livro livro = null;
									
			%>	
		</head>
		<script>
		function selecionarCategoria()
		{
			alert("asdas");
			var form = document.getElementById("form1");
			form.action = "/SalvarCategoria";
			form.submit();
		}
		</script>
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
	
        <div class="col-lg-5">
		<form action"SalvarLivro" method="POST" id="form1">
          <div class="card mt-4">
            <div class="card-body">
              <h3 class="card-title">Cadastrar Livro</h3>
				<table>
					<tr><td>Titulo: </td>
					<td><input type="text"class="form-control" id="txtTitulo" name="txtTitulo"  maxlength="100" required/></td></tr>
					
					<tr><td>Autor:: </td><td><input type="text"class="form-control" id="txtAutor" name="txtAutor" 
					value="" maxlength="100" required /></td></tr>	
					
					<tr><td>Ano do livro: </td>
					<td><input type="text"class="form-control" id="txtAno" name="txtAno" maxlength="4" required/></td></tr>	
					
					<tr><td>Editora do livro: </td>
					<td><input type="text"class="form-control" id="txtEditora" name="txtEditora" maxlength="40" required/></td></tr>
					
					<tr><td>Edição do livro: </td>
					<td><input type="text"class="form-control" id="txtEdicao" name="txtEdicao" maxlength="4" required/></td>
					
					
					<tr><td>Nº de Páginas: </td>
					<td><input type="text"class="form-control" id="txtPaginas" name="txtPaginas"  maxlength="5" required/></td></tr>		
					
					<tr><td>Sinopse do livro: </td>
					<td><input type="text"class="form-control" id="txtSinopse" name="txtSinopse"  required/></td></tr>		
					

					<tr><td>ISBN do livro: </td>
					<td><input type="text"class="form-control" id="txtISBN" name="txtISBN"  maxlength="14" required/></td></tr>							
				</table>
				<br><br>
            </div>
          </div>


        </div>

        <div class="col-lg-5">
		
          <div class="card mt-4">
            <div class="card-body">
              <h3 class="card-title">Cadastrar Livro</h3>
				<table>

					<tr><td>Altura: </td>
					<td><input type="text"class="form-control" id="txtAltura" name="txtAltura"  maxlength="5" required/></td></tr>
				
				
					<tr><td>Peso: </td>
					<td><input type="text"class="form-control" id="txtPeso" name="txtPeso" maxlength="5" required /></td></tr>
				
				
					<tr><td>Profundidade: </td>
					<td><input type="text"class="form-control" id="txtProfundidade" name="txtProfundidade"  maxlength="5" required/></td></tr>
					
					<tr><td>Largura: </td>
					<td><input type="text"class="form-control" id="txtLargura" name="txtLargura"  maxlength="5" required/></td></tr>	
					
				
					<tr><td>Grupo Precificação</td>
					<td>
						<select id="ddlsCategoria" name="ddlGrupoPrecificacao" class="form-control" style="color: black;" required>
							<%
								List<EntidadeDominio> e = resultadoGrupos.getEntidades();
								for(int i = 0; i < e.size(); i++)
								{
									GrupoPrecificacao gp = (GrupoPrecificacao)e.get(i);
									out.print("<option>" + gp.getNome()+ "-- " +gp.getMargemLucro() +"</option>");
								}
							%>
						</select>
					</td></tr>
					<tr><td>Categoria: </td>
					<td>
						<select id="ddlCategoria" name="ddlCategoria" class="form-control" style="color: black;" onclick='selecionarCategoria()' required>
							<% 

								for(int i = 0; i < entidadesCategorias.size(); i ++)
								{
									Categoria c = (Categoria)entidadesCategorias.get(i);
									out.print("<option>" +c.getNome() + "</option>");
								}
							%>
						</select>
					</td></tr>		
					
					<tr><td>Subcategoria: </td>
					<td>
						<select id="ddlsSubCategoria" name="ddlsSubCategoria" class="form-control" style="color: black;" required>
						<%
							for(int i = 0; i < entidadesCategorias.size(); i ++)
							{
								Categoria c = (Categoria)entidadesCategorias.get(i);
								for(int k = 0; k < c.getSubcategorias().size(); k++)
								{
									SubCategoria sb = c.getSubcategorias().get(k);
									out.print("<option>" + sb.getNome()  + "</option>");
								}

							}					
						%>			
						</select>
																			
				</table>
					<br>
  						<button type="submit" id="operacao" name="operacao" value="LOGIN" class="btn btn-lg btn-primary btn-block" 
						style='background-color: #C63D0F; hover:#C63D0F; border: #C63D0F; ' >
							<span>Cadastrar Livro</span>
						</button>
       				<br>
            </div>
          </div>


        </div>


      </div>
	
		</form>
        </div>
		</div>
    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5" style="background-color: #3B3738; ">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
      </div>
      <!-- /.container -->
    </footer>
</body>
	<% 
		request.getSession().removeAttribute("resultadoCategorias");
	%>
	
</html>