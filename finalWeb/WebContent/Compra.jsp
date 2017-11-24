<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%
		Resultado resultado = (Resultado)request.getSession().getAttribute("resultado");
		if(resultado == null)
		{
			pageContext.forward("Index.jsp?operacao=comprar");
			return;
		}
	%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comprar</title>
</head>
<body>
	<h1>deu certo</h1>
</body>
</html>