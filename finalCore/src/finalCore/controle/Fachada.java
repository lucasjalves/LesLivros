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
import finalCore.dao.SubCategoriaDAO;
import finalCore.dao.TelefoneDAO;
import finalCore.negocio.ValidarDadosObrigatoriosLivro;
import finalDominio.Cartao;
import finalDominio.Categoria;
import finalDominio.Cupom;
import finalDominio.Endereco;
import finalDominio.EntidadeDominio;
import finalDominio.GrupoPrecificacao;
import finalDominio.Livro;
import finalDominio.PessoaFisica;
import finalDominio.SubCategoria;
import finalDominio.Telefone;

public class Fachada implements IFachada{
	/** 
	 * Mapa de DAOS, ser� indexado pelo nome da entidade 
	 * O valor � uma inst�ncia do DAO para uma dada entidade; 
	 */
	private Map<String, IDAO> daos;
	
	/**
	 * Mapa para conter as regras de neg�cio de todas opera��es por entidade;
	 * O valor � um mapa que de regras de neg�cio indexado pela opera��o
	 */
	private Map<String, Map<String, List<IStrategy>>> rns;
	
	private Resultado resultado;

	public Fachada(){
		/* Int�nciando o Map de DAOS */
		daos = new HashMap<String, IDAO>();
		/* Int�nciando o Map de Regras de Neg�cio */
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		
		/* Criando inst�ncias dos DAOs a serem utilizados*/
		LivroDAO livroDAO = new LivroDAO();
		EnderecoDAO	endDAO = new EnderecoDAO();
		ClienteDAO cliDAO = new ClienteDAO();
		TelefoneDAO telDAO = new TelefoneDAO();
		CartaoDAO carDAO = new CartaoDAO();
		CategoriaDAO catDAO = new CategoriaDAO();
		SubCategoriaDAO subcatDAO = new SubCategoriaDAO();
		GrupoPrecificacaoDAO gpDAO = new GrupoPrecificacaoDAO();
		CupomDAO cupDAO = new CupomDAO();
		
		/* Adicionando cada dao no MAP indexando pelo nome da classe */
		daos.put(Livro.class.getName(), livroDAO);
		daos.put(Endereco.class.getName(), endDAO);
		daos.put(PessoaFisica.class.getName(), cliDAO);
		daos.put(Telefone.class.getName(), telDAO);
		daos.put(Cartao.class.getName(), carDAO);
		daos.put(Categoria.class.getName(), catDAO);
		daos.put(SubCategoria.class.getName(), subcatDAO);
		daos.put(GrupoPrecificacao.class.getName(), gpDAO);
		daos.put(Cupom.class.getName(), cupDAO);
		
		
		/* Criando inst�ncias de regras de neg�cio a serem utilizados*/
		ValidarDadosObrigatoriosLivro vdObrigatoriosLivro = new ValidarDadosObrigatoriosLivro();		
		/* Criando uma lista para conter as regras de neg�cio de fornencedor
		 * quando a opera��o for salvar
		 */
		List<IStrategy> rnsSalvarLivro = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na opera��o salvar do fornecedor*/
		rnsSalvarLivro.add(vdObrigatoriosLivro);
		
		/* Cria o mapa que poder� conter todas as listas de regras de neg�cio espec�fica 
		 * por opera��o  do fornecedor
		 */
		Map<String, List<IStrategy>> rnsLivro = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na opera��o salvar no mapa do fornecedor (lista criada na linha 70)
		 */
		rnsLivro.put("SALVAR", rnsSalvarLivro);	
		
		/* Adiciona o mapa(criado na linha 79) com as regras indexadas pelas opera��es no mapa geral indexado 
		 * pelo nome da entidade
		 */
		rns.put(Livro.class.getName(), rnsLivro);
	
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
				resultado.setMsg("N�o foi poss�vel realizar o registro!");
				
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
				resultado.setMsg("N�o foi poss�vel realizar a consulta!");
				
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
				resultado.setMsg("N�o foi poss�vel realizar o registro!");
				
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
		Resultado resultado = new Resultado();
		String msg = executarRegras(entidade, "VERIFICAR");
		resultado.setMsg(msg);
		return resultado;
	}
}
