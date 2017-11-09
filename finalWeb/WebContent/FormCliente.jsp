<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CADASTRO CLIENTE</title>
</head>
<body>
	<form action="SalvarCliente" method="post" id="frmSalvarLivro">
		<table>
			<tr>
				<td>Nome Cliente: </td>
				<td><input type="text" id="txtNomeCli" name="txtNomeCli" /></td>
			</tr>
			<tr>
				<td>Genero: </td>
				<td><input type="text" id="txtGenero" name="txtGenero" /></td>
			</tr>			
			<tr>
				<td>Data de Nascimento: </td>
				<td><input type="text" id="txtDtNascimento" name="txtDtNascimento" /></td>
			</tr>
			<tr>
				<td>CPF: </td>
				<td><input type="text" id="txtCpf" name="txtCpf" /></td>
			</tr>
				
			<tr>
				<td>Email: </td>
				<td><input type="text" id="txtEmail" name="txtEmail" /></td>
			</tr>
			
			<tr>
				<td>Senha: </td>
				<td><input type="text" id="txtSenha" name="txtSenha" /></td>
			</tr>

			<tr>
				<td>CNPJ: </td>
				<td><input type="text" id="txtCNPJ" name="txtCNPJ" /></td>
			</tr>																					
		</table>
		<br>			
		<table>
			<tr>
				<td>Apelido Endereço: </td>
				<td><input type="text" id="txtNome" name="txtNome" /></td>
			</tr>		
			<tr>
				<td>Tipo Residencia: </td>
				<td><input type="text" id="txtTipoRes" name="txtTipoRes" /></td>
			</tr>
			<tr>
				<td>Tipo Logradouro: </td>
				<td><input type="text" id="txtTipoLog" name="txtTipoLog" /></td>
			</tr>		
			<tr>
				<td>Logradouro: </td>
				<td><input type="text" id="txtLogradouro" name="txtLogradouro" /></td>
			</tr>	
			<tr>
				<td>Número da Casa: </td>
				<td><input type="text" id="txtNumCasa" name="txtNumCasa" /></td>
			</tr>
			<tr>
				<td>Bairro: </td>
				<td><input type="text" id="txtBairro" name="txtBairro" /></td>
			</tr>
			<tr>
				<td>CEP: </td>
				<td><input type="text" id="txtCep" name="txtCep" /></td>
			</tr>
			<tr>
				<td>Cidade: </td>
				<td><input type="text" id="txtCidade" name="txtCidade" /></td>
			</tr>
			<tr>
				<td>Estado: </td>
				<td><input type="text" id="txtEstado" name="txtEstado" /></td>
			</tr>
			<tr>
				<td>Pais: </td>
				<td><input type="text" id="txtPais" name="txtPais" /></td>
			</tr>																							
		</table>
		<br>
		<table>
			<tr>
				<td>DDD Telefone: </td>
				<td><input type="text" id="txtDdd" name="txtDdd" /></td>
			</tr>				
			<tr>
				<td>Número Telefone: </td>
				<td><input type="text" id="txtNumeroTel" name="txtNumeroTel" /></td>
			</tr>
			<tr>
				<td>Tipo Telefone: </td>
				<td><select name="ddlTipoTel">
					<option value="Telefone">Telefone</option>
					<option value="Celular">Celular</option>
				</select></td>
			</tr>								
		</table>
		<br>
		<table>
			<tr>
				<td>Número do Cartão: </td>
				<td><input type="text" id="txtNumCartao" name="txtNumCartao" /></td>
			</tr>				
			<tr>
				<td>Bandeira: </td>
				<td><select name="ddlBandeira">
					<option value="MasterCard">MasterCard</option>
      				<option value="VISA">VISA</option>
      				<option value="American Express">American Express</option>
				</select></td>
			</tr>
			<tr>
				<td>Data de Vencimento: </td>
				<td><input type="text" id="txtDtVencimento" name="txtDtVencimento" /></td>
			</tr>
			<tr>
				<td>Código de Segurança: </td>
				<td><input type="text" id="txtCodSeg" name="txtCodSeg" /></td>
			</tr>												
		</table>		
		<input type="submit" id="operacao" name="operacao" value="SALVARCLIENTE"  />	
	</form>
</body>
</html>