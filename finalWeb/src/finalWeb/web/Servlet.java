package finalWeb.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import finalCore.aplicacao.Resultado;
import finalDominio.EntidadeDominio;
import finalDominio.PessoaFisica;
import finalWeb.command.ICommand;
import finalWeb.command.impl.AlterarCommand;
import finalWeb.command.impl.ConsultarCommand;
import finalWeb.command.impl.SalvarCommand;
import finalWeb.command.impl.VerificarCarrinhoCommand;
import finalWeb.command.impl.VisualizarCommand;
import finalWeb.vh.IViewHelper;
import finalWeb.vh.impl.CarrinhoViewHelper;
import finalWeb.vh.impl.CartaoViewHelper;
import finalWeb.vh.impl.CategoriaViewHelper;
import finalWeb.vh.impl.ClienteViewHelper;
import finalWeb.vh.impl.CompraViewHelper;
import finalWeb.vh.impl.CupomViewHelper;
import finalWeb.vh.impl.EnderecoViewHelper;
import finalWeb.vh.impl.GrupoPrecificacaoViewHelper;
import finalWeb.vh.impl.LivroViewHelper;
import finalWeb.vh.impl.SubCategoriaViewHelper;

public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;
	
	
    /**
     * Default constructor. 
     * @throws ClassNotFoundException 
     */
    public Servlet(){
    	
    	/* Utilizando o command para chamar a fachada e indexando cada command 
    	 * pela opera��o garantimos que esta servelt atender� qualquer opera��o */
    	commands = new HashMap<String, ICommand>();
    	commands.put("SALVAR", new SalvarCommand());
    	commands.put("SALVARCLIENTE", new SalvarCommand());
    	commands.put("ALTERAR", new AlterarCommand());
    	commands.put("CONSULTAR", new ConsultarCommand());
    	commands.put("CONSULTARCLIENTE", new ConsultarCommand());
    	commands.put("CONSULTARLIVRO", new ConsultarCommand());
    	commands.put("CONSULTARLIVROHOME", new ConsultarCommand());
    	commands.put("INFORMACOESLIVRO", new ConsultarCommand());
    	commands.put("LOGIN", new ConsultarCommand());
    	commands.put("VISUALIZAR", new VisualizarCommand());
    	commands.put("VERIFICAR", new VerificarCarrinhoCommand());
    	commands.put("AdicionarItem", new VerificarCarrinhoCommand());
    	commands.put("subtrairItem", new VerificarCarrinhoCommand());
    	commands.put("removerItem", new VerificarCarrinhoCommand());
    	commands.put("ComprarItens", new VerificarCarrinhoCommand());
    	commands.put("AdicionarCupom", new ConsultarCommand());
    	/* Utilizando o ViewHelper para tratar especifica��es de qualquer tela e indexando 
    	 * cada viewhelper pela url em que esta servlet � chamada no form
    	 * garantimos que esta servelt atender� qualquer entidade */
    	
    	vhs = new HashMap<String, IViewHelper>();
    	/*A chave do mapa � o mapeamento da servlet para cada form que 
    	 * est� configurado no web.xml e sendo utilizada no action do html
    	 */
    	vhs.put("/finalWeb/SalvarLivro", new LivroViewHelper());
    	vhs.put("/finalWeb/SalvarCliente", new ClienteViewHelper());
    	vhs.put("/finalWeb/SalvarCartao", new CartaoViewHelper());
    	vhs.put("/finalWeb/SalvarEndereco", new EnderecoViewHelper());
    	vhs.put("/finalWeb/SalvarCategoria", new CategoriaViewHelper());
    	vhs.put("/finalWeb/SalvarSubCategoria", new SubCategoriaViewHelper());
    	vhs.put("/finalWeb/SalvarGrupos", new GrupoPrecificacaoViewHelper());
    	vhs.put("/finalWeb/SalvarCarrinho", new CarrinhoViewHelper());
    	vhs.put("/finalWeb/ValidarCupom", new CupomViewHelper());
    	vhs.put("/finalWeb/ComprarItens", new CompraViewHelper());
    }
    
    
    /**
     * TODO Descri��o do M�todo
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
    		IOException {
    	doProcessRequest(request, response);

    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessRequest(request, response);
	}
	
	
	protected void doProcessRequest(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String operacao = request.getParameter("operacao");

		IViewHelper vh = vhs.get(uri);		
		EntidadeDominio entidade =  vh.getEntidade(request);
		
		ICommand command = commands.get(operacao);	
		Resultado resultado = command.execute(entidade);


		vh.setView(resultado, request, response);
	
	}
}