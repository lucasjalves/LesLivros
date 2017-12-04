package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import finalDominio.Cupom;
import finalDominio.CupomPromocional;
import finalDominio.CupomTroca;
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
		if(entidade instanceof CupomPromocional)
		{
			CupomPromocional cup = (CupomPromocional)entidade;
			if(cup.getCodigo() != null)
				sb.append("AND codigo = '" + cup.getCodigo() + "'");
		}
			
		if(entidade instanceof CupomTroca)
		{
			CupomTroca cup = (CupomTroca)entidade;
			if(cup.getCodigo() != null)
				sb.append("AND codigo = '" + cup.getCodigo() + "'");
		}

		System.out.println(sb.toString());
		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> cupons = new ArrayList<EntidadeDominio>();
			while(rs.next()){
				if(entidade instanceof CupomPromocional)
				{
					CupomPromocional c = new CupomPromocional();
					c.setId(rs.getInt("id_cupom"));
					c.setCodigo(rs.getString("codigo"));
					c.setDesconto(rs.getDouble("desconto"));
					c.setDtValidade(rs.getDate("dtVencimento"));	
					cupons.add(c);
				}
				if(entidade instanceof CupomTroca)
				{
					
				}
					CupomTroca c = new CupomTroca();
					c.setId(rs.getInt("id_cupom"));
					c.setCodigo(rs.getString("codigo"));
					c.setDesconto(rs.getDouble("desconto"));
					c.setDtValidade(rs.getDate("dtVencimento"));	
					cupons.add(c);				{
					
				}
				
				
			}
			return cupons;		
		
	}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}		


}
