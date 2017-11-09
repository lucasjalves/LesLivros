package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import finalDominio.EntidadeDominio;
import finalDominio.Telefone;

public class TelefoneDAO extends AbstractJdbcDAO{

	public TelefoneDAO() {
		super("table", "idTable");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		PreparedStatement pst = null;
		Telefone t = (Telefone)entidade;
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO `telefone`(`ddd`, `numero`, `tipo_telefone`, `fk_cliente`) "
					+ "VALUES(?,?,?,?)");		
			
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, t.getDdd());
			pst.setString(2, t.getNumero());
			pst.setString(3, t.getTipo());
			pst.setInt(4, t.getFk_cliente());			
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
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
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
