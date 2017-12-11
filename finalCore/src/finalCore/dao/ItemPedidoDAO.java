package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import finalCore.util.GerarCupomTroca;
import finalDominio.CupomTroca;
import finalDominio.EntidadeDominio;
import finalDominio.Item;
import finalDominio.Pedido;
import finalDominio.PedidoTroca;

public class ItemPedidoDAO extends AbstractJdbcDAO {

	public ItemPedidoDAO() {
		super("table", "idTable");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		PreparedStatement pst = null;
		Pedido p = (Pedido)entidade;
		Item i = p.getItem().get(0);
		try {
			connection.setAutoCommit(false);

	
			int qtde = 0;
			pst = connection.prepareStatement("SELECT * FROM item_pedido where PK_PEDIDO = " + p.getId());
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				qtde = rs.getInt("qtde_livros_restantes");
			}
			rs.close();
			
			int qtdeItem = qtde - p.getItem().get(0).getQtde();
			
			String sql = "UPDATE `qtde_item_pedido` "
					+ " SET qtde="+ qtdeItem +" WHERE fk_pedido="+ i.getId() +"";


	
			pst = connection.prepareStatement(sql.toString());
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
