package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import finalDominio.Cartao;
import finalDominio.EntidadeDominio;
import finalDominio.PessoaFisica;

public class CartaoDAO extends AbstractJdbcDAO{

	public CartaoDAO() {
		super("", "");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		PreparedStatement pst = null;
		Cartao c = (Cartao)entidade;
		try {
			
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO `cartao`(`numero`, `bandeira`, "
					+ "`dtVencimento`, `codigo_seg`, "
					+ "`pk_cliente`) VALUES (?,?,?,?,?)");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, c.getNumero());
			pst.setString(2, c.getBandeira());
			Timestamp time = new Timestamp(c.getDtVencimento().getTime());
			pst.setTimestamp(3, time);
			pst.setString(4, c.getCodSeg());
			pst.setInt(5, c.getPkUsuario());

			
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
		openConnection();
		PreparedStatement pst = null;
		Cartao c = (Cartao)entidade;		
		try
		{
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			
			sql.append("UPDATE cartao SET numero = ?,"
					+ "bandeira = ?, dtVencimento = ?, codigo_seg = ?"
					+ "WHERE id_cartao = ? AND pk_cliente = ?");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, c.getNumero());
			pst.setString(2, c.getBandeira());
			Timestamp time = new Timestamp(c.getDtVencimento().getTime());
			pst.setTimestamp(3, time);
			pst.setString(4,  c.getCodSeg());
			pst.setInt(5, c.getId());
			pst.setInt(6, c.getPkUsuario());
			
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
		sb.append("SELECT * FROM cartao ");
		Cartao cartao = (Cartao)entidade;
		if(cartao.getId() != null && cartao.getPkUsuario() != null)
		{
			sb.append("WHERE id_cartao = '"+ cartao.getId() + "' AND pk_cliente = '" + cartao.getPkUsuario() + "'");
			
		}
		try 
		{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> cartoes = new ArrayList<EntidadeDominio>();
			while(rs.next())
			{
				Cartao c = new Cartao();
				System.out.println(sb.toString());
				c.setId(rs.getInt("id_cartao"));
				c.setBandeira(rs.getString("bandeira"));
				c.setNumero(rs.getString("numero"));
				c.setDtVencimento(rs.getDate("dtVencimento"));
				c.setCodSeg(rs.getString("codigo_seg"));
				c.setPkUsuario(rs.getInt("pk_cliente"));
				
				cartoes.add(c);
			}
			return cartoes;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

}
