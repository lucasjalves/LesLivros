package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import finalDominio.Cartao;
import finalDominio.CupomTroca;
import finalDominio.Endereco;
import finalDominio.EntidadeDominio;
import finalDominio.Item;
import finalDominio.Livro;
import finalDominio.Pedido;
import finalDominio.PedidoTroca;
import finalDominio.PessoaFisica;
import finalDominio.Telefone;

public class ClienteDAO extends AbstractJdbcDAO {

	public ClienteDAO() {
		super("livros", "id_livro");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		PreparedStatement pst = null;
		PessoaFisica p = (PessoaFisica)entidade;
		//Endereco end = p.getEndereco();
		//Telefone tel = p.getTelefone();
		//Cartao c = p.getCartao();
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO `cliente`(`genero`, `nome_cliente`, "
					+ "`dtNascimento`, `cpf`, `email`, `senha`, `status`)"
					+ "VALUES (?,?,?,?,?,?,?)");		
			
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, p.getGenero());
			pst.setString(2, p.getNome());
			Timestamp time = new Timestamp(p.getDtNascimento().getTime());
			pst.setTimestamp(3, time);
			pst.setString(4, p.getCpf());
			pst.setString(5, p.getEmail());
			pst.setString(6, p.getSenha());
			pst.setString(7, p.getStatus());
			
			pst.executeUpdate();			
			ResultSet rs = pst.getGeneratedKeys();
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
			/*
			end.setFk_pessoa(id);
			tel.setFk_cliente(id);
			c.setPkUsuario(id);
			connection.commit();
			
			EnderecoDAO endDAO = new EnderecoDAO();
			endDAO.salvar(end);
			
			TelefoneDAO telDAO = new TelefoneDAO();
			telDAO.salvar(tel);
			
			CartaoDAO carDAO = new CartaoDAO();
			carDAO.salvar(c);
			*/
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		PreparedStatement pst = null;
		PessoaFisica p = (PessoaFisica)entidade;
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE `cliente` SET `genero`=?,`nome_cliente`=?,"
					+ "`dtNascimento`=?,`cpf`=?,`email`=?,`senha`=? WHERE id_cliente=?");		
			
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, p.getGenero());
			pst.setString(2, p.getNome());
			Timestamp time = new Timestamp(p.getDtNascimento().getTime());
			pst.setTimestamp(3, time);
			pst.setString(4, p.getCpf());
			pst.setString(5, p.getEmail());
			pst.setString(6, p.getSenha());
			pst.setInt(7, p.getId());
			
			pst.executeUpdate();			
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM cliente WHERE 1=1 ");
		PessoaFisica pf = (PessoaFisica)entidade;
		if(pf.getEmail() != null  && pf.getSenha() != null)
		{
			sb.append("AND cliente.email = '" + pf.getEmail() + "' AND cliente.senha = '" + pf.getSenha() + "'");
		}
		if(pf.getId() != null)
		{
			sb.append("AND id_cliente = '" + pf.getId() + "'");
		}
		if(pf.getNome() != null && pf.getNome().length() > 0)
		{
			sb.append("AND nome_cliente LIKE '%" + pf.getNome() + "%'");
		}
		if(pf.getCpf() != null && pf.getCpf().length() > 0)
		{
			sb.append("AND cpf LIKE '%"  + pf.getCpf() + "%'");
		}
		if(pf.getGenero() != null && pf.getGenero().length() > 0)
		{
			sb.append("AND genero LIKE '%" + pf.getGenero() + "%'");
		}
		/*
		if(pf.getEmail() != null || pf.getEmail().length() > 0);
		{
			sb.append("AND email LIKE '%" + pf.getEmail() + "%'");
		}	
		*/	
		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> pessoas = new ArrayList<EntidadeDominio>();
			while(rs.next()){
				PessoaFisica p = new PessoaFisica();
				
				
				p.setId(rs.getInt("id_cliente"));
				int idCliente = p.getId();
				p.setGenero(rs.getString("genero"));
				p.setNome(rs.getString("nome_cliente"));
				p.setDtNascimento(rs.getDate("dtNascimento"));
				p.setCpf(rs.getString("cpf"));
				p.setEmail(rs.getString("email"));
				p.setSenha(rs.getString("senha"));
				p.setTipo(rs.getInt("tipo"));
				
				
				pst = connection.prepareStatement("SELECT * FROM endereco WHERE pk_cliente = " + idCliente);
				ResultSet enderecosCliente = pst.executeQuery();
				List<Endereco> enderecos = new ArrayList<Endereco>();
				while(enderecosCliente.next())
				{
					Endereco e = new Endereco();
					
					e.setId(enderecosCliente.getInt("id_endereco"));
					e.setTipoRes(enderecosCliente.getString("tipo_res"));
					e.setTipoLog(enderecosCliente.getString("tipo_log"));
					e.setLogradouro(enderecosCliente.getString("logradouro"));
					e.setNumCasa(enderecosCliente.getString("num_casa"));
					e.setBairro(enderecosCliente.getString("bairro"));
					e.setCep(enderecosCliente.getString("cep"));
					e.setCidade(enderecosCliente.getString("cidade"));
					e.setEstado(enderecosCliente.getString("estado"));
					e.setPais(enderecosCliente.getString("pais"));
					e.setNome(enderecosCliente.getString("nome_id"));
					e.setFk_pessoa(enderecosCliente.getInt("pk_cliente"));
					
					enderecos.add(e);
				}
				enderecosCliente.close();
				
				pst = connection.prepareStatement("SELECT * FROM TELEFONE WHERE fk_cliente = " + idCliente);
				ResultSet telefoneCliente = pst.executeQuery();
				List<Telefone> telefones = new ArrayList<Telefone>();
				while(telefoneCliente.next())
				{
					Telefone t = new Telefone();

					t.setId(telefoneCliente.getInt("id_telefone"));
					t.setDdd(telefoneCliente.getString("ddd"));
					t.setNumero(telefoneCliente.getString("numero"));
					t.setTipo(telefoneCliente.getString("tipo_telefone"));
					t.setFk_cliente(telefoneCliente.getInt("fk_cliente"));
					telefones.add(t);
				}
				telefoneCliente.close();
				p.setTelefones(telefones);
				
				pst = connection.prepareStatement("SELECT * FROM cartao WHERE pk_cliente = " + idCliente);
				ResultSet cartoesCliente = pst.executeQuery();
				List<Cartao> cartoes = new ArrayList<Cartao>();
				while(cartoesCliente.next())
				{
					Cartao c = new Cartao();
					
					c.setId(cartoesCliente.getInt("id_cartao"));
					c.setBandeira(cartoesCliente.getString("bandeira"));
					c.setNumero(cartoesCliente.getString("numero"));
					c.setCodSeg(cartoesCliente.getString("codigo_seg"));
					c.setDtVencimento(cartoesCliente.getString("dtVencimento"));
					c.setPkUsuario(cartoesCliente.getInt("pk_cliente"));
					cartoes.add(c);
				}				
				cartoesCliente.close();
				
				PedidoDAO daoPedido = new PedidoDAO();
				Pedido ped = new Pedido();
				
				ped.setIdCliente(p.getId());
				List<EntidadeDominio> pedidos = daoPedido.consultar(ped);
				if(pedidos != null)
				{
					List<Pedido> listaPedidos = new ArrayList<Pedido>();
					for(int i = 0; i < pedidos.size(); i++)
					{
						ped = (Pedido)pedidos.get(i);
						listaPedidos.add(ped);
					}
					p.setPedidos(listaPedidos);
				
				}
				
				PedidoTrocaDAO daoTroca = new PedidoTrocaDAO();
				PedidoTroca pt = new PedidoTroca();
				pt.setIdCliente(p.getId());
				List<EntidadeDominio> trocas = daoTroca.consultar(pt);
				if(trocas != null)
				{
					List<PedidoTroca> listaTrocas = new ArrayList<PedidoTroca>();
					for(int i = 0; i < trocas.size(); i++)
					{
						pt = (PedidoTroca)trocas.get(i);
						listaTrocas.add(pt);
					}
					p.setTrocas(listaTrocas);
				}
				p.setCartao(cartoes);
				p.setEndereco(enderecos);
				
				CupomTrocaClienteDAO cupTrocaDAO = new CupomTrocaClienteDAO();
				CupomTroca ct = new CupomTroca();
				ct.setIdCliente(p.getId());
				List<EntidadeDominio> cuponsTroca = cupTrocaDAO.consultar(ct);
				if(cuponsTroca != null)
				{
					List<CupomTroca> cupons = new ArrayList<CupomTroca>();
					for(int i = 0; i < cuponsTroca.size(); i ++)
					{
						ct = (CupomTroca)cuponsTroca.get(i);
						cupons.add(ct);
					}
					p.setCuponsTroca(cupons);
				}
				pessoas.add(p);
			}
			
			return pessoas;
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}
	

}
