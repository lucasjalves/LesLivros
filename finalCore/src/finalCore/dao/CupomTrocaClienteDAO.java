package finalCore.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import finalDominio.CupomTroca;
import finalDominio.EntidadeDominio;
import finalDominio.GrupoPrecificacao;

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
		sql.append(", fk_cliente,status) VALUES (?,?,?,?,?,?)");
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, c.getCodigo());
			pst.setDouble(2, c.getDesconto());
			pst.setDate(3, new Date(c.getDtCriacao().getTime()));
			pst.setDate(4, new Date(c.getDtValidade().getTime()));
			pst.setInt(5, c.getIdCliente());
			pst.setInt(6, 1);
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
		PreparedStatement pst = null;
		StringBuilder sb = new StringBuilder();
		CupomTroca ct = (CupomTroca)entidade;
		sb.append("SELECT * FROM cupom_troca_cliente WHERE 1=1 ");
		if(ct.getIdCliente() != null)
		{
			sb.append("AND fk_cliente = " + ct.getIdCliente());
		}
		

		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> cuponsTroca = new ArrayList<>();
			while(rs.next()){
				CupomTroca cupom = new CupomTroca();
				cupom.setId(rs.getInt("id_cupom_troca"));
				cupom.setCodigo(rs.getString("codigo_cupom_troca"));
				cupom.setDesconto(rs.getDouble("desconto_cupom_troca"));
				cupom.setDtCriacao(rs.getDate("dtCriacao"));
				cupom.setDtValidade(rs.getDate("dtExpiracao"));
				cupom.setIdCliente(rs.getInt("fk_cliente"));
				cupom.setStatus(rs.getInt("status"));
				cuponsTroca.add(cupom);
			}
			return cuponsTroca;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

}
