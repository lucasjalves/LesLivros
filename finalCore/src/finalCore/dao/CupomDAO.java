package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import finalDominio.Cupom;
import finalDominio.EntidadeDominio;

public class CupomDAO extends AbstractJdbcDAO {

	public CupomDAO() {
		super("table", "idTable");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
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
		sb.append("SELECT * FROM `cupom` WHERE 1=1 ");
		Cupom cup = (Cupom)entidade;
		if(cup.getCodigo() != null)
		{
			sb.append("AND codigo = '" + cup.getCodigo() + "'");
		}
		System.out.println(sb.toString());
		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> cupons = new ArrayList<EntidadeDominio>();
			while(rs.next()){
				Cupom c = new Cupom();

				c.setId(rs.getInt("id_cupom"));
				c.setCodigo(rs.getString("codigo"));
				c.setDesconto(rs.getInt("desconto"));
				cupons.add(c);
			}
			return cupons;		
		
	}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}		


}
