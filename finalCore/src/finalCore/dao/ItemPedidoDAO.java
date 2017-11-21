package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import finalDominio.EntidadeDominio;
import finalDominio.Item;

public class ItemPedidoDAO extends AbstractJdbcDAO {
	public ItemPedidoDAO()
	{
		super("livros", "id_livro");
	}
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		PreparedStatement pst = null;
		Item i = (Item)entidade;
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO item_pedido (pk_livro, quantidade, preco)");
			pst = connection.prepareStatement(sql.toString());

			pst.setInt(1, i.getLivro().getId());
			pst.setInt(2, i.getQtde());
			pst.setDouble(3, i.getLivro().getPreco());
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
