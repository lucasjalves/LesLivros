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
			
				<tr><td>Nome Cliente: </td>
				<td><input type="text" id="txtNomeCli" name="txtNomeCli" /></td></tr>
			
			
				<tr><td>Genero: </td>
				<td><input type="text" id="txtGenero" name="txtGenero" /></td></tr>
						
			
				<tr><td>Data de Nascimento: </td>
				<td><input type="text" id="txtDtNascimento" name="txtDtNascimento" /></td></tr>
			
			
				<tr><td>CPF: </td>
				<td><input type="text" id="txtCpf" name="txtCpf" /></td></tr>
			
				
			
				<td>Email: </td>
				<td><input type="text" id="txtEmail" name="txtEmail" /></td>
			
			
			
				<td>Senha: </td>
				<td><input type="text" id="txtSenha" name="txtSenha" /></td>
			

			
				<td>CNPJ: </td>
				<td><input type="text" id="txtCNPJ" name="txtCNPJ" /></td>
																								
		</table>
		<br>			
		<table>
			
				<td>Apelido Endereço: </td>
				<td><input type="text" id="txtNome" name="txtNome" /></td>
					
			
				<td>Tipo Residencia: </td>
				<td><input type="text" id="txtTipoRes" name="txtTipoRes" /></td>
			
			
				<td>Tipo Logradouro: </td>
				<td><input type="text" id="txtTipoLog" name="txtTipoLog" /></td>
					
			
				<td>Logradouro: </td>
				<td><input type="text" id="txtLogradouro" name="txtLogradouro" /></td>
				
			
				<td>Número da Casa: </td>
				<td><input type="text" id="txtNumCasa" name="txtNumCasa" /></td>
			
			
				<td>Bairro: </td>
				<td><input type="text" id="txtBairro" name="txtBairro" /></td>
			
			
				<td>CEP: </td>
				<td><input type="text" id="txtCep" name="txtCep" /></td>
			
			
				<td>Cidade: </td>
				<td><input type="text" id="txtCidade" name="txtCidade" /></td>
			
			
				<td>Estado: </td>
				<td><input type="text" id="txtEstado" name="txtEstado" /></td>
			
			
				<td>Pais: </td>
				<td><input type="text" id="txtPais" name="txtPais" /></td>
																										
		</table>
		<br>
		<table>
			
				<td>DDD Telefone: </td>
				<td><input type="text" id="txtDdd" name="txtDdd" /></td>
							
			
				<td>Número Telefone: </td>
				<td><input type="text" id="txtNumeroTel" name="txtNumeroTel" /></td>
			
			
				<td>Tipo Telefone: </td>
				<td><select name="ddlTipoTel">
					<option value="Telefone">Telefone</option>
					<option value="Celular">Celular</option>
				</select></td>
											
		</table>
		<br>
		<table>
			
				<td>Número do Cartão: </td>
				<td><input type="text" id="txtNumCartao" name="txtNumCartao" /></td>
							
			
				<td>Bandeira: </td>
				<td><select name="ddlBandeira">
					<option value="MasterCard">MasterCard</option>
      				<option value="VISA">VISA</option>
      				<option value="American Express">American Express</option>
				</select></td>
			
			
				<td>Data de Vencimento: </td>
				<td><input type="text" id="txtDtVencimento" name="txtDtVencimento" /></td>
			
			
				<td>Código de Segurança: </td>
				<td><input type="text" id="txtCodSeg" name="txtCodSeg" /></td>
															
		</table>		
		<input type="submit" id="operacao" name="operacao" value="SALVARCLIENTE"  />	
	</form>
</body>
</html>