<%@page import="finalCore.util.ConverteDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page
	import="finalCore.aplicacao.Resultado, finalDominio.*, java.util.*, finalCore.util.ConverteDate"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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

		
		List<Endereco> end= p.getEndereco();
		List<Cartao> cartoes = p.getCartao();
		
		Integer id = (Integer)request.getSession().getAttribute("userid");


	%>
	<%
	for(int i = 0; i < end.size(); i ++)
	{
		out.print("<script>$('#myModalEndereco"+ i + "').on('shown.bs.modal', function () {$('#myInput').focus()})</script>");
	}
	for(int i = 0; i < cartoes.size(); i ++)
	{
		out.print("<script>$('#myModalCartao"+ i + "').on('shown.bs.modal', function () {$('#myInput').focus()})</script>");
	}
	%>	
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
									<tr><td>Genero: <%out.print(p.getGenero()); %></td></tr>
									
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
									<table>
									<tr>
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
								        <input type="submit" class ="btn btn-primary" id="operacao" name="operacao" value="ALTERAR"  style='background-color: #2B7D77; hover: #2B7D77;border: #2B7D77;'/>		      
								        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
								      </div>
								    </div>
								  </div>
								</div>
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" style='background-color: #C63D0F; hover: #C63D0F;border: #C63D0F;'>Alterar</button>	
								</form>								 
			              </div>
			            </div>
			    	</div>
			   </div>
			</div>
		</div>            
		    <!--                               MODAL DOS ENDERECOS                              -->
      		<!--                               MODAL DOS ENDERECOS	                            -->
      	<div class="row">
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
							out.print("<td><button type='button' " +
									"class='btn btn-primary' " +
									"data-toggle='modal' "+
									"data-target='#myModalEnderecos" + i + "'"+
									"id='btnEndereco' style='background-color: #7E8F7C; hover: #7E8F7C;border: #7E8F7C;'>Alterar</button></td>");
							out.print("</tr>");
						}
						out.print("</table>");
						
						StringBuilder modals = new StringBuilder();
						for(int i = 0; i < end.size(); i++)
						{
							Endereco e = end.get(i);			
							modals.setLength(0);
							modals.append("<form action='SalvarEndereco' method='post' id='frmSalvarLivro'>");
							modals.append("<div class='modal fade' id='myModalEnderecos" + i + "' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>");
							modals.append("<div class='modal-dialog'role='document'>");
							modals.append("<div class='modal-content'>");
							modals.append("<div class='modal-header'>");
							modals.append("<h5 class='modal-title' id='myModalLabel'>Endereços</h5>");
							modals.append("<button type='button' class='close' data-dismiss='modal' aria-label='Close'>");
							modals.append("<span aria-hidden='true'>&times;</span>");
							modals.append("</button>");
							modals.append("</div>");
							modals.append("<div class='modal-body' id='detalhesEnderecos'>");
							modals.append("<table>");
							modals.append("<tr><td><input type='hidden' id='txtIdEndereco' name='txtIdEndereco' value='" + String.valueOf(e.getId())+ "'/></td></tr>");
							modals.append("<tr><td>Apelido Endereco: </td><td><input type='text' id='txtNome' name='txtNome' value='" + e.getNome() + "'/></td></tr>");
							modals.append("<tr><td>Tipo Residencia: </td><td><input type='text' id='txtTipoRes' name='txtTipoRes' value='" + e.getTipoRes() + "'/></td></tr>");
							modals.append("<tr><td>Logradouro: </td><td><input type='text' id='txtLogradouro' name='txtLogradouro' value='" + e.getLogradouro() + "'/></td></tr>");
							modals.append("<tr><td>Tipo Logradouro: </td><td><input type='text' id='txtTipoLog' name='txtTipoLog' value='" + e.getTipoLog() + "'/></td></tr>");
							modals.append("<tr><td>Número da Casa: </td><td><input type='text' id='txtNumCasa' name='txtNumCasa' value='" + e.getNumCasa() + "'/></td></tr>");
							modals.append("<tr><td>Bairro: </td><td><input type='text' id='txtBairro' name='txtBairro' value='" + e.getBairro() + "'/></td></tr>");
							modals.append("<tr><td>CEP: </td><td><input type='text' id='txtCep' name='txtCep' value='" + e.getCep() + "'/></td></tr>");
							modals.append("<tr><td>Cidade: </td><td><input type='text' id='txtCidade' name='txtCidade' value='" + e.getCidade() + "'/></td></tr>");
							modals.append("<tr><td>Estado: </td><td><input type='text' id='txtEstado' name='txtEstado' value='" + e.getEstado() + "'/></td></tr>");
							modals.append("<tr><td>Pais: </td><td><input type='text' id='txtPais' name='txtPais' value='" + e.getPais() + "'/></td></tr>");
							modals.append("</table>");
							modals.append("</div>");
							modals.append("<div class='modal-footer'>");
							modals.append("<input type='submit' class ='btn btn-primary' id='operacao' name='operacao' value='ALTERAR'  style='background-color: #2B7D77; hover: #2B7D77;border: #2B7D77;'/>");
							modals.append("<button type='button' class='btn btn-danger' data-dismiss='modal'>Cancelar</button>");
							modals.append("</div>");
							modals.append("</div>");
							modals.append("</div>");
							modals.append("</div>");
							modals.append("</form>");
							
							out.print(modals.toString());
							}
							
						}
					%>
						
				</table>
				</div>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalAdicionarEnderecos" style='background-color: #C63D0F; hover: #C63D0F;border: #C63D0F;'>Adicionar</button>	
                </div>
              </div>
        
     
      		<!--                               MODAL DOS ENDERECOS  (FIM)                            -->
      		<!--                               MODAL DOS ENDERECOS	(FIM)                            -->
      		
      		
      		
      		<!--                               MODAL DOS CARTOES                               -->
      		<!--                               MODAL DOS CARTOES                               -->
      		
           <div class="col-lg-4 col-md-6 mb-4">
              <div class="card h-100">
                <div class="card-body">
                  <h4 class="card-title">
                    Cartões
                  </h4>
                  
					<table class="table table">
					<% 
					if(resultado != null)
					{
						entidades = resultado.getEntidades();
							
						for(int i = 0; i < cartoes.size(); i++)
						{
							
							Cartao c = cartoes.get(i);
							out.print("<tr>");
							out.print("<td><p class='card-text'>" + c.getBandeira() + "</p></td>");
							out.print("<td><button type='button' " +
									"class='btn btn-primary' " +
									"data-toggle='modal' "+
									"data-target='#myModalCartao" + i + "'"+
									"id='btnEndereco' style='background-color: #7E8F7C; hover: #7E8F7C;border: #7E8F7C; '>Alterar</button></td>");
							out.print("</tr>");
						}
						out.print("</table>");
						
						StringBuilder modals = new StringBuilder();
						for(int i = 0; i < cartoes.size(); i++)
						{
							Cartao c = cartoes.get(i);			
							modals.setLength(0);
							modals.append("<form action='SalvarCartao' method='post' id='frmSalvarLivro'>");
							modals.append("<div class='modal fade' id='myModalCartao" + i + "' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>");
							modals.append("<div class='modal-dialog'role='document'>");
							modals.append("<div class='modal-content'>");
							modals.append("<div class='modal-header'>");
							modals.append("<h5 class='modal-title' id='myModalLabel'>Cartões</h5>");
							modals.append("<button type='button' class='close' data-dismiss='modal' aria-label='Close'>");
							modals.append("<span aria-hidden='true'>&times;</span>");
							modals.append("</button>");
							modals.append("</div>");
							modals.append("<div class='modal-body' id='detalhesCartoes'>");
							modals.append("<table>");
							modals.append("<tr><td><input type='hidden' id='txtIdCartao' name='txtIdCartao' value='" + String.valueOf(c.getId())+ "'/></td></tr>");
							modals.append("<tr><td>Número Cartão: </td><td><input type='text' id='txtNumCartao' name='txtNumCartao' value='" + c.getNumero() + "'/></td></tr>");
							modals.append("<tr><td>Bandeira: </td><td><input type='text' id='ddlBandeira' name='ddlBandeira' value='" + c.getBandeira() + "'/></td></tr>");
							modals.append("<tr><td>Data de Vencimento: </td><td><input type='text' id='txtDtVencimento' name='txtDtVencimento' value='" + c.getDtVencimento() + "'/></td></tr>");
							modals.append("<tr><td>Código de Segurança: </td><td><input type='text' id='txtCodSeg' name='txtCodSeg' value='" + c.getCodSeg() + "'/></td></tr>");
							modals.append("<tr><td><input type='hidden' id='txtIdCartaoFk' name='txtIdCartaoFk' value='" + c.getPkUsuario() + "'/></td></tr>");
							modals.append("</table>");
							modals.append("</div>");
							modals.append("<div class='modal-footer'>");
							modals.append("<input type='submit' class ='btn btn-primary' id='operacao' name='operacao' value='ALTERAR' style='background-color: #2B7D77; hover: #2B7D77;border: #2B7D77;' />");
							modals.append("<button type='button' class='btn btn-danger' data-dismiss='modal'>Cancelar</button>");
							modals.append("</div>");
							modals.append("</div>");
							modals.append("</div>");
							modals.append("</div>");
							modals.append("</form>");
							
							out.print(modals.toString());
							}
							
						}
					%>
					
						
						</table>
					</div>	
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalCartao" style='background-color: #C63D0F; hover: #C63D0F;border: #C63D0F;'>Adicionar</button>	
                </div>
                
              </div>
           </div>
                 <!--                               MODAL DOS CARTOES  FIM                            -->   
      			<!--                               MODAL DOS CARTOES  FIM                            -->   
      

	<form action="SalvarCartao" method="post" id="frmSalvarCartao">
		<div class="modal fade" id="myModalCartao" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="myModalLabel">Adicionar cartão de crédito</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
			<table>
					<tr><td>Número Cartão: </td><td><input type='text' id='txtNumCartao' name='txtNumCartao'/></td></tr>
					<tr><td>Bandeira: 			<td><select name="ddlBandeira">
					<option value="MasterCard">MasterCard</option>
      				<option value="VISA">VISA</option>
      				<option value="American Express">American Express</option>
					</select></td></tr>
					<tr><td>Data de Vencimento: </td><td><input type='text' id='txtDtVencimento' name='txtDtVencimento' /></td></tr>
					<tr><td>Código de Segurança: </td><td><input type='text' id='txtCodSeg' name='txtCodSeg' /></td></tr>
					<tr><td><input type="hidden" name="txtIdCartaoFk" value="<%out.print(id); %>" />	</td></tr>
					<tr><td><input type="hidden" name="local" value="conta" />	</td></tr>																				
			</table>
		      </div>
		      <div class="modal-footer">
		        <input type="submit" class ="btn btn-primary" id="operacao" name="operacao" value="SALVAR"  style='background-color: #C63D0F; hover: #C63D0F;border: #C63D0F;'/>		      
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
		      </div>
		    </div>
		  </div>
		</div>
	</form>
	
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
					<td><input type="text" id="txtPais" name="txtPais" /></td></tr>
				
				<input type="hidden" name="txtIdFkEndereco" value="<%out.print(p.getId()); %>">																							
			</table>
		      </div>
		      <div class="modal-footer">
		        <input type="submit" class ="btn btn-primary" id="operacao" name="operacao" value="SALVAR"  style='background-color: #C63D0F; hover: #C63D0F;border: #C63D0F;'/>		      
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
		      </div>
		    </div>
		  </div>
		</div>
	</form>
</div>   
	
    <footer class="py-5" style="background-color: #3B3738;">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
      </div>
    </footer>	
</body>
</html>