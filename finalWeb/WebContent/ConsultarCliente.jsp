<%@page import="finalCore.util.ConverteDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consultar Clientes</title>
</head>
<body>
	<%
		Resultado resultado = (Resultado) session.getAttribute("resultado");
	
		
	%>
		<form action="SalvarCliente" method="post">
		<table>
			<tr>
				<td>Nome Cliente: </td>
				<td><input type="text" id="txtNomeCli" name="txtNomeCli" /></td>			
				<td>Gênero: </td> 
				<td><select name="txtGenero">
					<option value="M">M</option>
					<option value="F">F</option>
				</select></td>
				<td>Data de Nascimento: </td>
				<td><input type="text" id="txtDtNascimento" name="txtDtNascimento" /></td>
				<td>CPF: </td>
				<td><input type="text" id="txtCpf" name="txtCpf" /></td>
				<td>Email: </td>
				<td><input type="text" id="txtEmail" name="txtEmail" /></td>								
			</tr>
			<tr>
			
			</tr>
		</table>
		<input type="submit" id="operacao" name="operacao" value="CONSULTARCLIENTE" />
	</form>
	
		<%
	
	if(resultado !=null && resultado.getMsg() != null){
		out.print(resultado.getMsg());
	}
	
	%>
<BR>

<TABLE BORDER="5"    WIDTH="50%"   CELLPADDING="4" CELLSPACING="3">
   <TR>
      <TH COLSPAN="26"><BR>
      	<H3>CLIENTES</H3>
      </TH>
   </TR>
   <TR>
      <TH>ID</TH>
      <TH>Genero</TH>
      <TH>Nome</TH>
      <TH>data de Nascimento</TH>
      <TH>CPF</TH>
      <TH>Email</TH>
      <TH>Senha</TH>
      
      <TH>Tipo Res.</TH>
      <TH>Tipo log.</TH>
      <TH>Número</TH>
      <TH>Bairro</TH>
      <TH>CEP</TH>
      <TH>Cidade</TH>
      <TH>Estado</TH>
      <TH>Pais</TH>
      <TH>Apelido Endereço</TH>
      
      <TH>DDD</TH>
      <TH>Número</TH>
      <TH>Tipo</TH>
      
      <TH>Número Cartão</TH>
      <TH>Bandeira</TH>
      <TH>Data Vencimento</TH>
      <TH>Código de Segurança</TH>
      <th></th>
   </TR>
   <%
   if (resultado != null) {
		List<EntidadeDominio> entidades = resultado.getEntidades();
		StringBuilder sbRegistro = new StringBuilder();
		StringBuilder sbLink = new StringBuilder();
		if(entidades != null){
			for (int i = 0; i < entidades.size(); i++) {
				PessoaFisica p = (PessoaFisica) entidades.get(i);
				if(i != 0)
				{
					p = (PessoaFisica) entidades.get(i);
					PessoaFisica pBack = (PessoaFisica) entidades.get(i-1);
					if(pBack.getId() != p.getId())
					{
						sbRegistro.setLength(0);
						sbLink.setLength(0);
						sbRegistro.append("<TR ALIGN='CENTER'>");
						
						
//		 				sbLink.append("<a href=SalvarCliente?");
//		 					sbLink.append("txtId=");
//		 					sbLink.append(String.valueOf(p.getId()));						
//		 					sbLink.append("&");
//		 					sbLink.append("operacao=");
//		 					sbLink.append("VISUALIZAR");
							
//		 				sbLink.append(">");
				

						sbRegistro.append("<TD>");
						sbRegistro.append(String.valueOf(p.getId()));		
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");
						sbRegistro.append(p.getGenero());			
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");
						sbRegistro.append(p.getNome());			
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");			
						sbRegistro.append(ConverteDate.converteDateString(p.getDtNascimento()));				
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");				
						sbRegistro.append(p.getCpf());			
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");				
						sbRegistro.append(p.getEmail());			
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");			
						sbRegistro.append(p.getSenha());			
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");		
						sbRegistro.append(p.getEndereco().getTipoRes());			
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");			
						sbRegistro.append(p.getEndereco().getTipoLog());				
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");			
						sbRegistro.append(p.getEndereco().getNumCasa());		
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");			
						sbRegistro.append(p.getEndereco().getBairro());		
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");			
						sbRegistro.append(p.getEndereco().getCep());			
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");				
						sbRegistro.append(p.getEndereco().getCidade());			
						sbRegistro.append("</TD>");

						sbRegistro.append("<TD>");			
						sbRegistro.append(p.getEndereco().getEstado());			
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");				
						sbRegistro.append(p.getEndereco().getPais());				
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");			
						sbRegistro.append(p.getEndereco().getNome());		
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");		
						sbRegistro.append(p.getTelefone().getDdd());		
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");				
						sbRegistro.append(p.getTelefone().getNumero());			
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");			
						sbRegistro.append(p.getTelefone().getTipo());				
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");			
						sbRegistro.append(p.getCartao().getNumero());		
						sbRegistro.append("</TD>");

						sbRegistro.append("<TD>");			
						sbRegistro.append(p.getCartao().getBandeira());			
						sbRegistro.append("</TD>");

						sbRegistro.append("<TD>");			
						sbRegistro.append(ConverteDate.converteDateString(p.getCartao().getDtVencimento()));			
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");				
						sbRegistro.append(p.getCartao().getCodSeg());			
						sbRegistro.append("</TD>");				
						
						sbRegistro.append("<td><form action=\"SalvarCliente\"method=\"post\"> "+
						"<input type=\"hidden\" name=\"txtId\" value=\""+ p.getId() + "\"> " +
						"<input type=\"submit\" id=\"operacao\" name=\"operacao\" value=\"VISUALIZAR\"/></form></td>");
						
						sbRegistro.append("</TR>");
						
						out.print(sbRegistro.toString());						
					}
					
				}
				else
				{
					sbRegistro.setLength(0);
					sbLink.setLength(0);
					sbRegistro.append("<TR ALIGN='CENTER'>");
					
					
//	 				sbLink.append("<a href=SalvarCliente?");
//	 					sbLink.append("txtId=");
//	 					sbLink.append(String.valueOf(p.getId()));						
//	 					sbLink.append("&");
//	 					sbLink.append("operacao=");
//	 					sbLink.append("VISUALIZAR");
						
//	 				sbLink.append(">");
			

					sbRegistro.append("<TD>");
					sbRegistro.append(String.valueOf(p.getId()));		
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");
					sbRegistro.append(p.getGenero());			
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");
					sbRegistro.append(p.getNome());			
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");			
					sbRegistro.append(ConverteDate.converteDateString(p.getDtNascimento()));				
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");				
					sbRegistro.append(p.getCpf());			
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");				
					sbRegistro.append(p.getEmail());			
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");			
					sbRegistro.append(p.getSenha());			
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");		
					sbRegistro.append(p.getEndereco().getTipoRes());			
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");			
					sbRegistro.append(p.getEndereco().getTipoLog());				
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");			
					sbRegistro.append(p.getEndereco().getNumCasa());		
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");			
					sbRegistro.append(p.getEndereco().getBairro());		
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");			
					sbRegistro.append(p.getEndereco().getCep());			
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");				
					sbRegistro.append(p.getEndereco().getCidade());			
					sbRegistro.append("</TD>");

					sbRegistro.append("<TD>");			
					sbRegistro.append(p.getEndereco().getEstado());			
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");				
					sbRegistro.append(p.getEndereco().getPais());				
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");			
					sbRegistro.append(p.getEndereco().getNome());		
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");		
					sbRegistro.append(p.getTelefone().getDdd());		
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");				
					sbRegistro.append(p.getTelefone().getNumero());			
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");			
					sbRegistro.append(p.getTelefone().getTipo());				
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");			
					sbRegistro.append(p.getCartao().getNumero());		
					sbRegistro.append("</TD>");

					sbRegistro.append("<TD>");			
					sbRegistro.append(p.getCartao().getBandeira());			
					sbRegistro.append("</TD>");

					sbRegistro.append("<TD>");			
					sbRegistro.append(ConverteDate.converteDateString(p.getCartao().getDtVencimento()));			
					sbRegistro.append("</TD>");
					
					sbRegistro.append("<TD>");				
					sbRegistro.append(p.getCartao().getCodSeg());			
					sbRegistro.append("</TD>");				
					
					sbRegistro.append("<td><form action=\"SalvarCliente\"method=\"post\"> "+
					"<input type=\"hidden\" name=\"txtId\" value=\""+ p.getId() + "\"> " +
					"<input type=\"submit\" id=\"operacao\" name=\"operacao\" value=\"VISUALIZAR\"/></form></td>");
					
					sbRegistro.append("</TR>");
					
					out.print(sbRegistro.toString());									
				}
			}
		}
   }
      
			%>
   </TABLE>	
</body>
</html>