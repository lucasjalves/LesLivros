<%@page import="finalCore.util.ConverteDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*, finalCore.util.ConverteDate"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="bootstrap/popper.js"></script>
	<script src="bootstrap/jquery-3.2.1.min.js"></script>
	<script src="bootstrap/bootstrap.min.js"></script>
	<script>
		$('#myModal').on('shown.bs.modal', function () {
			  $('#myInput').focus()
			})
		$('#myModalAdicionarEnderecos').on('shown.bs.modal', function () {
			  $('#myInput').focus()
			})	
	</script>
	<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="css/shop-item.css">	
<title>Minha conta</title>
</head>
<body>
	<%
		Resultado resultado = (Resultado) session.getAttribute("resultado");

		List<EntidadeDominio> entidades = resultado.getEntidades();
		PessoaFisica p = (PessoaFisica) entidades.get(0);
		String txtId = String.valueOf(p.getId());
		

		Integer idInicial = p.getEndereco().getId();
		session.setAttribute("userid", txtId);
		List<Endereco> end= new ArrayList<Endereco>();

		// List<Integer> idEnderecos = new ArrayList<Integer>();
		// idEnderecos.add(p.getEndereco().getId());
		Integer primeiroId = p.getEndereco().getId();
		end.add(p.getEndereco());
		for(int i = 0; i < entidades.size(); i ++)
		{
			
			PessoaFisica pf = (PessoaFisica) entidades.get(i);
			
			if(pf.getEndereco().getId() != primeiroId)
			{
				if(primeiroId <= pf.getEndereco().getId())
				{
					primeiroId = pf.getEndereco().getId();
					end.add(pf.getEndereco());					
				}

				
			}

		}
		
	%>
	<%
	for(int i = 0; i < end.size(); i ++)
	{
		System.out.println(end.size());
		out.print("<script>$('#myModalEndereco"+ i + "').on('shown.bs.modal', function () {$('#myInput').focus()})</script>");
	}
	%>	
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
      <div class="container">
	      <div class="row">
		      <div class="col-lg-9">
		          <div class="card mt-4">
			            <div class="card-body">
			              <h3 class="card-title">Minha Conta</h3>
			             	 <div class="card-body">
			              		<div class="table-responsive">
								<table class="table table-striped">
									<tr><td>Email: <%out.print(p.getEmail()); %></td></tr>
									<tr><td>Nome: <%out.print(p.getNome()); %></td></tr>
									<tr><td>Genero: <%out.print(p.getGenero()); %></td>
									</tr>
									<tr><td>CPF: <%out.print(p.getCpf()); %></td></tr>
									<tr><td>Data de Nascimento: <%out.print(ConverteDate.converteDateString(p.getDtNascimento())); %></td></tr>
								</table>
								<form action="SalvarCliente" method="post" id="frmSalvarLivro">
								<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								  <div class="modal-dialog" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="myModalLabel">Dados Cadastrais</h5>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
								      </div>
								      <div class="modal-body">
									<table><tr>
											<td>Nome Cliente: </td>
											<td><input type="text" id="txtNomeCli" name="txtNomeCli" value="<%out.print(p.getNome()); %>" /></td>
										</tr>
										<tr>
											<td>Genero: </td>
											<td><input type="text" id="txtGenero" name="txtGenero" value="<%out.print(p.getGenero()); %>"/></td>
										</tr>			
										<tr>
											<td>Data de Nascimento: </td>
											<td><input type="text" id="txtDtNascimento" name="txtDtNascimento" value="<%out.print(ConverteDate.converteDateString(p.getDtNascimento())); %>" /></td>
										</tr>
										<tr>
											<td>CPF: </td>
											<td><input type="text" id="txtCpf" name="txtCpf" value="<%out.print(p.getCpf()); %>"/></td>
										</tr>
											
										<tr>
											<td>Email: </td>
											<td><input type="text" id="txtEmail" name="txtEmail" value="<%out.print(p.getEmail()); %>"/></td>
										</tr>
										
										<tr>
											<td>Senha: </td>
											<td><input type="text" id="txtSenha" name="txtSenha" value="<%out.print(p.getSenha()); %>" /></td>
										</tr>
											<input type="hidden" name="txtId" value="<%out.print(p.getId()); %>">																							
									</table>
								      </div>
								      <div class="modal-footer">
								        <input type="submit" class ="btn btn-primary" id="operacao" name="operacao" value="ALTERAR"  />		      
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
								      </div>
								    </div>
								  </div>
								</div>
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Alterar</button>	
								</form>								 
			              </div>
			            </div>
			    	</div>
			   </div>
			</div>
		</div>            
           <div class="col-lg-4 col-md-6 mb-4">
              <div class="card h-100">
                <div class="card-body">
                  <h4 class="card-title">
                    Meus Endereços
                  </h4>
					<table class="table table">
					<% 
					if(resultado != null)
					{
						entidades = resultado.getEntidades();
						
						for(int i = 0; i < end.size(); i++)
						{
							Endereco e = end.get(i);
							out.print("<tr>");
							out.print("<td><p class='card-text'>" + e.getNome() + "</p></td>");
							out.print("<td><button type=\"button\" " +
									"class=\"btn btn-primary\" " +
									"data-toggle=\"modal\" "+
									"data-target=\"#myModalEnderecos" + i + "\" "+
									"id=\"btnEndereco\">Alterar</button></td>");
							out.print("<tr>");
						}
						out.print("</table>");
						
						StringBuilder modals = new StringBuilder();
						for(int i = 0; i < end.size(); i++)
						{
							Endereco e = end.get(i);			
							modals.setLength(0);
							modals.append("<form action=\"SalvarEndereco\" method=\"post\" id=\"frmSalvarLivro\">");
							modals.append("<div class=\"modal fade\" id=\"myModalEnderecos" + i + "\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">");
							modals.append("<div class=\"modal-dialog\" role=\"document\">");
							modals.append("<div class=\"modal-content\">");
							modals.append("<div class=\"modal-header\">");
							modals.append("<h5 class=\"modal-title\" id=\"myModalLabel\">Endereços</h5>");
							modals.append("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">");
							modals.append("<span aria-hidden=\"true\">&times;</span>");
							modals.append("</button>");
							modals.append("</div>");
							modals.append("<div class=\"modal-body\" id=\"detalhesCartao\">");
							modals.append("<table>");
							modals.append("<tr><td><input type=\"hidden\" id=\"txtIdEndereco\" name=\"txtIdEndereco\" value=\"" + String.valueOf(e.getId())+ "\"/></td></tr>");
							modals.append("<tr><td>Apelido Endereco: </td><td><input type=\"text\" id=\"txtNome\" name=\"txtNome\" value=\"" + e.getNome() + "\"/></td></tr>");
							modals.append("<tr><td>Tipo Residencia: </td><td><input type=\"text\" id=\"txtTipoRes\" name=\"txtTipoRes\" value=\"" + e.getTipoRes() + "\"/></td></tr>");
							modals.append("<tr><td>Logradouro: </td><td><input type=\"text\" id=\"txtLogradouro\" name=\"txtLogradouro\" value=\"" + e.getLogradouro() + "\"/></td></tr>");
							modals.append("<tr><td>Tipo Logradouro: </td><td><input type=\"text\" id=\"txtTipoLog\" name=\"txtTipoLog\" value=\"" + e.getTipoLog() + "\"/></td></tr>");
							modals.append("<tr><td>Número da Casa: </td><td><input type=\"text\" id=\"txtNumCasa\" name=\"txtNumCasa\" value=\"" + e.getNumCasa() + "\"/></td></tr>");
							modals.append("<tr><td>Bairro: </td><td><input type=\"text\" id=\"txtBairro\" name=\"txtBairro\" value=\"" + e.getBairro() + "\"/></td></tr>");
							modals.append("<tr><td>CEP: </td><td><input type=\"text\" id=\"txtCep\" name=\"txtCep\" value=\"" + e.getCep() + "\"/></td></tr>");
							modals.append("<tr><td>Cidade: </td><td><input type=\"text\" id=\"txtCidade\" name=\"txtCidade\" value=\"" + e.getCidade() + "\"/></td></tr>");
							modals.append("<tr><td>Estado: </td><td><input type=\"text\" id=\"txtEstado\" name=\"txtEstado\" value=\"" + e.getEstado() + "\"/></td></tr>");
							modals.append("<tr><td>Pais: </td><td><input type=\"text\" id=\"txtPais\" name=\"txtPais\" value=\"" + e.getPais() + "\"/></td></tr>");
							modals.append("</table>");
							modals.append("</div>");
							modals.append("<div class=\"modal-footer\">");
							modals.append("<input type=\"submit\" class =\"btn btn-primary\" id=\"operacao\" name=\"operacao\" value=\"ALTERAR\"  />");
							modals.append("<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Cancelar</button>");
							modals.append("</div>");
							modals.append("</div>");
							modals.append("</div>");
							modals.append("</div>");
							modals.append("</form>");
							
							out.print(modals.toString());
							}
							
						}
					%>
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalAdicionarEnderecos">Adicionar</button>	
                </div>
              </div>
           </div>
      </div>
	<form action="SalvarEndereco" method="post" id="frmSalvarLivro">
		<div class="modal fade" id="myModalAdicionarEnderecos" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="myModalLabel">Adicionar Endereco</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
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
				<input type="hidden" name="txtIdFkEndereco" value="<%out.print(p.getId()); %>">																							
			</table>
		      </div>
		      <div class="modal-footer">
		        <input type="submit" class ="btn btn-primary" id="operacao" name="operacao" value="SALVAR"  />		      
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
		      </div>
		    </div>
		  </div>
		</div>
	</form>
	
</body>
</html>