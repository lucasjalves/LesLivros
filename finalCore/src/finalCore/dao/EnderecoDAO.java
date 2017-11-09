package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import finalDominio.Endereco;
import finalDominio.EntidadeDominio;
import finalDominio.PessoaFisica;

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
		return null;
	}

}
