package finalCore.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import finalDominio.CupomTroca;
import finalDominio.EntidadeDominio;

public class CupomTrocaClienteDAO extends AbstractJdbcDAO{

	public CupomTrocaClienteDAO() {
		super("", "");
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		CupomTroca c = (CupomTroca)entidade;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO cupom_troca_cliente (codigo_cupom_troca, desconto_cupom_troca, dtCriacao, dtExpiracao");
		sql.append(", fk_cliente) VALUES (?,?,?,?,?)");
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, c.getCodigo());
			pst.setDouble(2, c.getDesconto());
			pst.setDate(3, new Date(c.getDtCriacao().getTime()));
			pst.setDate(4, new Date(c.getDtValidade().getTime()));
			pst.setInt(5, c.getIdCliente());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
