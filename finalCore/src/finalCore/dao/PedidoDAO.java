package finalCore.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import finalDominio.CartoesCompra;
import finalDominio.Cupom;
import finalDominio.Endereco;
import finalDominio.EntidadeDominio;
import finalDominio.Livro;
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
		Pedido pedido = (Pedido)entidade;	
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO pedido (dtPedido, status, pk_cliente, precoTotal, frete, fk_endereco) VALUES (?,?,?,?,?,?)");
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);	
		
			
			
			
			pst.setDate(1, new Date(pedido.getDtPedido().getTime()));
			pst.setString(2, "EM PROCESSAMENTO");
			pst.setInt(3, pedido.getIdCliente());
			pst.setDouble(4, pedido.getPrecoTotal());
			pst.setDouble(5, pedido.getFrete());
			pst.setInt(6, pedido.getEndereco().getId());
			
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			int idPedido = 0;
			if(rs.next())
				idPedido = rs.getInt(1);
			pedido.setId(idPedido);

			for(int i = 0; i < pedido.getItem().size(); i++)
			{
				Livro l = pedido.getItem().get(i).getLivro();
				pst = connection.prepareStatement("INSERT INTO item_pedido (fk_livro, quantidade,preco, PK_PEDIDO) VALUES (?,?,?,?)");
				pst.setInt(1, l.getId());
				pst.setInt(2, pedido.getItem().get(i).getQtde());
				pst.setDouble(3, l.getPreco());
				pst.setInt(4, idPedido);
				pst.executeUpdate();
				
			}
			
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
		
		openConnection();
		PreparedStatement pst = null;
		Pedido pedido = (Pedido)entidade;
		int idPedido = pedido.getId();
		try {
			connection.setAutoCommit(false);

			pst = connection.prepareStatement("UPDATE PEDIDO SET status = ?");
			pst.setString(1, pedido.getStatus());
			pst.executeUpdate();
			if(pedido.getCartoesCompra() != null)
			{
				for(int i = 0; i < pedido.getCartoesCompra().size() ; i ++)
				{
					CartoesCompra c = pedido.getCartoesCompra().get(i);
					pst = connection.prepareStatement("INSERT INTO pagamento_cartao (fk_cartao, fk_pedido, valor) VALUES(?,?,?)");
					pst.setInt(1, c.getCartao().getId());
					pst.setInt(2, idPedido);
					pst.setDouble(3, c.getValorPago());
					pst.executeUpdate();
				}
			}
			if(pedido.getCupomPromocional() != null)
			{

				Cupom c = pedido.getCupomPromocional();
				pst = connection.prepareStatement("INSERT INTO cupom_pedido (fk_cupom, fk_pedido) VALUES(?,?)");
				pst.setInt(1, c.getId());
				pst.setInt(2, idPedido);
				pst.executeUpdate();
								
			}		
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
		
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {

		return null;
	}

}
