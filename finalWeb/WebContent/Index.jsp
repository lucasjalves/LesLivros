<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página Inicial</title>
</head>
<body>
	<%
		Resultado resultado = (Resultado) session.getAttribute("resultado");
	%>
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