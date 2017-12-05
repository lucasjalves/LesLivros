package finalCore.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import finalCore.aplicacao.Resultado;
import finalCore.core.IDAO;
import finalCore.core.IFachada;
import finalCore.core.IStrategy;
import finalCore.dao.CartaoDAO;
import finalCore.dao.CategoriaDAO;
import finalCore.dao.ClienteDAO;
import finalCore.dao.CupomDAO;
import finalCore.dao.EnderecoDAO;
import finalCore.dao.GrupoPrecificacaoDAO;
import finalCore.dao.LivroDAO;
import finalCore.dao.PedidoDAO;
import finalCore.negocio.AprovarCompraCartao;
import finalCore.negocio.AtualizarStatusPedidoEntrega;
import finalCore.negocio.ValidarCupomData;
import finalCore.negocio.ValidarCupomVeridico;
import finalCore.negocio.ValidarDadosObrigatoriosLivro;
import finalCore.negocio.ValidarPagamentoCartoes;
import finalCore.negocio.VerificarQtdeLivroEstoque;
import finalDominio.Cartao;
import finalDominio.Categoria;
import finalDominio.Cupom;
import finalDominio.CupomPromocional;
import finalDominio.Endereco;
import finalDominio.EntidadeDominio;
import finalDominio.GrupoPrecificacao;
import finalDominio.Livro;
import finalDominio.Pedido;
import finalDominio.PessoaFisica;

public class Fachada implements IFachada{

	private Map<String, IDAO> daos;
	

	private Map<String, Map<String, List<IStrategy>>> rns;
	
	private Resultado resultado;

	public Fachada(){

		daos = new HashMap<String, IDAO>();

		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		

		LivroDAO livroDAO = new LivroDAO();
		EnderecoDAO	endDAO = new EnderecoDAO();
		ClienteDAO cliDAO = new ClienteDAO();
		CartaoDAO carDAO = new CartaoDAO();
		CategoriaDAO catDAO = new CategoriaDAO();
		GrupoPrecificacaoDAO gpDAO = new GrupoPrecificacaoDAO();
		CupomDAO cupDAO = new CupomDAO();
		PedidoDAO pedidoDAO = new PedidoDAO();
		

		daos.put(Livro.class.getName(), livroDAO);
		daos.put(Endereco.class.getName(), endDAO);
		daos.put(PessoaFisica.class.getName(), cliDAO);
		daos.put(Cartao.class.getName(), carDAO);
		daos.put(Categoria.class.getName(), catDAO);
		daos.put(GrupoPrecificacao.class.getName(), gpDAO);
		daos.put(CupomPromocional.class.getName(), cupDAO);
		daos.put(Pedido.class.getName(),pedidoDAO);
		

		ValidarDadosObrigatoriosLivro vdObrigatoriosLivro = new ValidarDadosObrigatoriosLivro();	
		VerificarQtdeLivroEstoque vQtdeEstoqueLivro = new VerificarQtdeLivroEstoque();
		ValidarCupomVeridico vCupVeridico = new ValidarCupomVeridico();
		AprovarCompraCartao aCompraCartao = new AprovarCompraCartao();
		ValidarCupomData vCupomData = new ValidarCupomData();
		ValidarPagamentoCartoes vPagamentoCartao = new ValidarPagamentoCartoes();
		AtualizarStatusPedidoEntrega aStatusPedidoEntrega = new AtualizarStatusPedidoEntrega();
		
		List<IStrategy> rnsValidarCupom = new ArrayList<IStrategy>();
		List<IStrategy> rnsValidarPedido = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarLivro = new ArrayList<IStrategy>();
		List<IStrategy> rnsAprovarCompra = new ArrayList<IStrategy>();
		
		rnsSalvarLivro.add(vdObrigatoriosLivro);
		

		rnsAprovarCompra.add(aCompraCartao);
		rnsAprovarCompra.add(aStatusPedidoEntrega);
		
		rnsValidarPedido.add(vPagamentoCartao);
		rnsValidarPedido.add(vQtdeEstoqueLivro);
		
		
		rnsValidarCupom.add(vCupVeridico);
		rnsValidarCupom.add(vCupomData);
		
		Map<String, List<IStrategy>> rnsLivro = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsPedido = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsCupom = new HashMap<String, List<IStrategy>>();
		

		rnsLivro.put("SALVAR", rnsSalvarLivro);	
		rnsPedido.put("ValidarCarrinho", rnsValidarPedido);
		rnsCupom.put("CONSULTAR", rnsValidarCupom);
		rnsPedido.put("CONSULTAR", rnsAprovarCompra);
		
		
		rns.put(Livro.class.getName(), rnsLivro);
		rns.put(Pedido.class.getName(), rnsPedido);
		rns.put(CupomPromocional.class.getName(), rnsCupom);
	
	}
	
	
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "SALVAR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
				
			}
		}else{
			resultado.setMsg(msg);
		}
		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		String msg = executarRegras(entidade, "CONSULTAR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				
				resultado.setEntidades(dao.consultar(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar a consulta!");
				
			}
		}else{
			resultado.setMsg(msg);
			
		}
		
		return resultado;

	}
	
	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();
		resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
		resultado.getEntidades().add(entidade);		
		return resultado;

	}

	
	private String executarRegras(EntidadeDominio entidade, String operacao){
		String nmClasse = entidade.getClass().getName();		
		StringBuilder msg = new StringBuilder();
		
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);
		
		
		if(regrasOperacao != null){
			List<IStrategy> regras = regrasOperacao.get(operacao);
			
			if(regras != null){
				for(IStrategy s: regras){			
					String m = s.processar(entidade);			
					
					if(m != null){
						msg.append(m);
						msg.append("\n");
					}			
				}	
			}			
			
		}
		
		if(msg.length()>0)
			return msg.toString();
		else
			return null;
	}


	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "ALTERAR");
	
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.alterar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
				
			}
		}else{
			resultado.setMsg(msg);
		}
		return resultado;
	}
	
	
	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Resultado verificarCarrinho(EntidadeDominio entidade) {
		resultado = new Resultado();	
		String msg = executarRegras(entidade, "ValidarCarrinho");
		Pedido p = (Pedido)entidade;
		List<EntidadeDominio> e = new ArrayList<EntidadeDominio>();
		e.add(p);
		resultado.setMsg(msg);
		resultado.setEntidades(e);
		return resultado;
	}
}
