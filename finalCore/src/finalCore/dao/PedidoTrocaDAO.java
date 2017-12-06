package finalCore.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import finalDominio.EntidadeDominio;
import finalDominio.ItemTroca;
import finalDominio.Livro;
import finalDominio.PedidoTroca;

public class PedidoTrocaDAO extends AbstractJdbcDAO {

	public PedidoTrocaDAO() {
		super("table", "idTable");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		PreparedStatement pst = null;
		PedidoTroca pt = (PedidoTroca)entidade;
		ItemTroca itemTroca = pt.getItensTroca().get(0);
		try
		{
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO pedido_troca (fk_pedido_troca, fk_usuario_troca, dtTroca, status) VALUES (?,?,?,?)");
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, pt.getIdPedido());
			pst.setInt(2, pt.getIdCliente());
			pst.setDate(3, new Date(pt.getDtTroca().getTime()));
			pst.setString(4, pt.getStatus());
			pst.executeUpdate();
			int id = 0;
			ResultSet rs = pst.getGeneratedKeys();
			while(rs.next())
				id = rs.getInt(1);
			rs.close();
			
			pst.close();
			Livro l = itemTroca.getLivro();
			pst = connection.prepareStatement("INSERT INTO pedido_troca_itens (id_pedido_troca_itens, fk_livro_troca, qtde_livro_troca)");
			pst.setInt(1, id);
			pst.setInt(2, l.getId());
			pst.setInt(3, itemTroca.getQtde());
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
