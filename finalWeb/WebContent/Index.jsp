<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="css/shop-item.css">
	<script src="bootstrap/jquery-3.2.1.min.js"></script>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
	<title>Login</title>
</head>
<body>
	<%
		Resultado resultado = (Resultado) session.getAttribute("resultado");
	%>
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
    	
	<form action="SalvarCliente" method="post" id="frmSalvarLivro">
		<table>
			<tr>
				<td>E-mail: </td>
				<td><input type="text" name="txtEmail">
			</tr>
			<tr>
				<td>Senha: </td>
				<td><input type="password" name="txtPwd">
			</tr>
			<tr>
				<td><input type="submit" id="operacao" name="operacao" value="LOGIN"  /></td>
			</tr>			
		</table>
	</form>
	<%
		if(resultado != null)
		{
			if(resultado.getMsg() != null)
			{
				out.print(resultado.getMsg());
			}
			
		}
	%>
	<form action="SalvarCategoria" method="post" id="frmSalvarLivro">
		<input type="submit" id="operacao" name="operacao" value="CONSULTAR"  />
	</form>	
</body>
</html>