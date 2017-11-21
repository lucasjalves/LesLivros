package finalCore.dao;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;


import finalDominio.EntidadeDominio;
import finalDominio.Item;
import finalDominio.Pedido;

public class PedidoDAO extends AbstractJdbcDAO{
	public PedidoDAO()
	{
		super("livros", "id_livro");
	}
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		PreparedStatement pst = null;
		Pedido p = (Pedido)entidade;	
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO pedido (dtPedido, status, pk_cliente, precoTotal)");
			pst = connection.prepareStatement(sql.toString());

			Timestamp dtPedido = new Timestamp(p.getDtPedido().getTime());
			pst.setTimestamp(1, dtPedido);
			pst.setString(2, p.getStatus());
			pst.setInt(3, p.getUsuario().getId());
			pst.setDouble(4, p.getPrecoTotal());
			
			int qtdeItem = p.getItem().size();
			
			pst.executeUpdate();			
			connection.commit();
			for(int i = 0; i < qtdeItem; i ++)
			{
				Item item = p.getItem().get(i);
				ItemPedidoDAO pedidoDAO = new ItemPedidoDAO();
				pedidoDAO.salvar(item);
			}
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
		
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		
		return null;
	}

}
