package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import finalDominio.Endereco;
import finalDominio.EntidadeDominio;


public class EnderecoDAO extends AbstractJdbcDAO{
	public EnderecoDAO() {
		super("endereco", "id_endereco");
	}


	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		PreparedStatement pst = null;
		Endereco end = (Endereco)entidade;		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO `endereco`(`tipo_res`, "
					+ "`tipo_log`, `logradouro`, `num_casa`, `bairro`, "
					+ "`cep`, `cidade`, `estado`, `pais`, `pk_cliente`, `nome_id`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, end.getTipoRes());
			pst.setString(2, end.getTipoLog());
			pst.setString(3, end.getLogradouro());
			pst.setString(4, end.getNumCasa());
			pst.setString(5, end.getBairro());
			pst.setString(6, end.getCep());
			pst.setString(7, end.getCidade());
			pst.setString(8, end.getEstado());
			pst.setString(9, end.getPais());
			pst.setInt(10, end.getPk_pessoa()); 
			pst.setString(11, end.getNome());
			
			pst.executeUpdate();			
			connection.commit();
		} catch (SQLException e) {
			try {
				pst.close();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
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
		Endereco en = (Endereco)entidade;
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE `endereco` "
					+ "SET `tipo_res`=?,`tipo_log`=?,"
					+ "`logradouro`=?,`num_casa`=?,"
					+ "`bairro`=?,`cep`=?,`cidade`=?,"
					+ "`estado`=?,`pais`=?,`nome_id`=? WHERE id_endereco = ?");		
			
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, en.getTipoRes());
			pst.setString(2, en.getTipoLog());
			pst.setString(3, en.getLogradouro());
			pst.setString(4, en.getNumCasa());
			pst.setString(5, en.getBairro());
			pst.setString(6, en.getCep());
			pst.setString(7, en.getCidade());
			pst.setString(8, en.getEstado());
			pst.setString(9, en.getPais());
			pst.setString(10, en.getNome());
			pst.setInt(11, en.getId());
			
			System.out.println(sql.toString());
			pst.executeUpdate();			
			connection.commit();
			pst.executeUpdate();			
			connection.commit();
		} catch (SQLException e) {
			try {
				pst.close();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
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
		Endereco end = (Endereco)entidade;
		sb.append("SELECT * FROM endereco WHERE pk_cliente = " + end.getPk_pessoa());
		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			ResultSet enderecosCliente = pst.executeQuery();
			List<EntidadeDominio> enderecos = new ArrayList<EntidadeDominio>();
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
			return enderecos;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
		
	}

}


