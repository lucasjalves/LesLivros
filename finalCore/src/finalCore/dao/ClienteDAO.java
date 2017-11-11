package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import finalDominio.Cartao;
import finalDominio.Endereco;
import finalDominio.EntidadeDominio;
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
		Endereco end = p.getEndereco();
		Telefone tel = p.getTelefone();
		Cartao c = p.getCartao();
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
		sb.append("SELECT * FROM cliente INNER JOIN "
				+ "endereco ON (cliente.id_cliente = endereco.pk_cliente) "
				+ "INNER JOIN telefone ON (cliente.id_cliente = telefone.fk_cliente) "
				+ "INNER JOIN cartao ON (cliente.id_cliente = cartao.pk_cliente) WHERE 1=1 ");
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
		System.out.println(sb.toString());
		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> pessoas = new ArrayList<EntidadeDominio>();
			while(rs.next()){
				PessoaFisica p = new PessoaFisica();
				Endereco e = new Endereco();
				Telefone t = new Telefone();
				Cartao c = new Cartao();
				
				p.setEndereco(e);
				p.setTelefone(t);
				p.setCartao(c);
				
				p.setId(rs.getInt("id_cliente"));
				p.setGenero(rs.getString("genero"));
				p.setNome(rs.getString("nome_cliente"));
				p.setDtNascimento(rs.getDate("dtNascimento"));
				p.setCpf(rs.getString("cpf"));
				p.setEmail(rs.getString("email"));
				p.setSenha(rs.getString("senha"));
				
				p.getEndereco().setId(rs.getInt("id_endereco"));
				p.getEndereco().setTipoRes(rs.getString("tipo_res"));
				p.getEndereco().setTipoLog(rs.getString("tipo_log"));
				p.getEndereco().setLogradouro(rs.getString("logradouro"));
				p.getEndereco().setNumCasa(rs.getString("num_casa"));
				p.getEndereco().setBairro(rs.getString("bairro"));
				p.getEndereco().setCep(rs.getString("cep"));
				p.getEndereco().setCidade(rs.getString("cidade"));
				p.getEndereco().setEstado(rs.getString("estado"));
				p.getEndereco().setPais(rs.getString("pais"));
				p.getEndereco().setNome(rs.getString("nome_id"));
				p.getEndereco().setFk_pessoa(rs.getInt("pk_cliente"));
				
				p.getTelefone().setId(rs.getInt("id_telefone"));
				p.getTelefone().setDdd(rs.getString("ddd"));
				p.getTelefone().setNumero(rs.getString("numero"));
				p.getTelefone().setTipo(rs.getString("tipo_telefone"));
				p.getTelefone().setFk_cliente(rs.getInt("fk_cliente"));
				
				p.getCartao().setId(rs.getInt("id_cartao"));
				p.getCartao().setBandeira(rs.getString("bandeira"));
				p.getCartao().setNumero(rs.getString("numero"));
				p.getCartao().setCodSeg(rs.getString("codigo_seg"));
				p.getCartao().setDtVencimento(rs.getDate("dtVencimento"));
				p.getCartao().setPkUsuario(rs.getInt("pk_cliente"));
				
				pessoas.add(p);
				
			}
			return pessoas;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	

}
