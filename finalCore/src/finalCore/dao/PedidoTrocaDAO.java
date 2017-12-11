package finalCore.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import finalCore.util.GerarCupomTroca;
import finalDominio.Cartao;
import finalDominio.CupomTroca;
import finalDominio.Endereco;
import finalDominio.EntidadeDominio;
import finalDominio.Item;
import finalDominio.ItemTroca;
import finalDominio.Livro;
import finalDominio.Pedido;
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
			sql.append("INSERT INTO pedido_troca (fk_pedido, fk_usuario, dtTroca, status) VALUES (?,?,?,?)");
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
			pst = connection.prepareStatement("INSERT INTO pedido_troca_itens "
					+ "(fk_pedido_troca, fk_livro, qtde_livro, preco_troca) VALUES (?,?,?,?)");
			pst.setInt(1, id);
			pst.setInt(2, l.getId());
			pst.setInt(3, itemTroca.getQtde());
			pst.setDouble(4, itemTroca.getPrecoLivro());
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
		openConnection();
		PreparedStatement pst = null;
		PedidoTroca p = (PedidoTroca)entidade;
		ItemTroca it = p.getItensTroca().get(0);
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE `pedido_troca` "
					+ "SET `status`= ? WHERE id = ?");		
			
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, p.getStatus());
			pst.setInt(2, p.getIdPedido());
			
			pst.executeUpdate();		
			CupomTrocaClienteDAO cupTrocaDAO = new CupomTrocaClienteDAO();
			CupomTroca c = GerarCupomTroca.gerarCupomTroca(p);
			cupTrocaDAO.salvar(c);
			
			int id = 0;
			pst = connection.prepareStatement("SELECT * FROM pedido_troca where id = " + p.getIdPedido());
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				id = rs.getInt("fk_pedido");
			}
			rs.close();
						
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
		openConnection();
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		
		try {
			openConnection();
			PedidoTroca p = (PedidoTroca)entidade;
			PreparedStatement pst = null;
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM pedido_troca WHERE 1=1 ");
			if(p.getIdCliente() != null)
			{
				sql.append("AND fk_usuario = ");
				sql.append(p.getIdCliente());
			}
			
			pst = connection.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> pedidos = new ArrayList<EntidadeDominio>();
			while(rs.next())
			{
				PedidoTroca pedido = new PedidoTroca();
				pedido.setId(rs.getInt("id"));
				pedido.setIdPedido(rs.getInt("fk_pedido"));
				pedido.setIdCliente(rs.getInt("fk_usuario"));
				pedido.setDtTroca(rs.getDate("dtTroca"));
				pedido.setStatus(rs.getString("status"));
				
				pst = connection.prepareStatement("SELECT * FROM pedido_troca_itens INNER JOIN "
						+ "livros on (fk_livro = livros.id) WHERE fk_pedido_troca = " + pedido.getId());
				ResultSet itensPedido = pst.executeQuery();
				List<ItemTroca> itens = new ArrayList<ItemTroca>();
				while(itensPedido.next())
				{
					Livro l = new Livro();
					ItemTroca i = new ItemTroca();
					l.setId(itensPedido.getInt("id"));
					i.setQtde(itensPedido.getInt("qtde_livro"));
					l.setNome(itensPedido.getString("nome"));
					i.setPrecoLivro(itensPedido.getDouble("preco_troca"));
					i.setLivro(l);
					itens.add(i);
				}
				pedido.setItensTroca(itens);
				pedidos.add(pedido);
			}
			return pedidos;
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;

	}
	
	

}
