package finalCore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import finalDominio.Categoria;
import finalDominio.EntidadeDominio;
import finalDominio.SubCategoria;

public class SubCategoriaDAO extends AbstractJdbcDAO {

	public SubCategoriaDAO() {
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
		sb.append("SELECT * FROM `subcategoria` "
				+ "INNER JOIN categoria ON "
				+ "(categoria.id_categoria = subcategoria.pk_categoria) WHERE 1=1 ");
		System.out.println(sb.toString());
		SubCategoria subc = (SubCategoria)entidade;
		if(subc.getId() != null)
		{
			sb.append("AND categoria.id_categoria = '" + subc.getId() + "'");
		}
		System.out.println(sb.toString());
		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> subcategorias = new ArrayList<EntidadeDominio>();
			while(rs.next()){
				SubCategoria sc = new SubCategoria();
				//SubCategoria subc = new SubCategoria();
				//c.setSubcategoria(subc);
				sc.setId(rs.getInt("id_subcategoria"));
				sc.setNome((rs.getString("nome_subcategoria")));
				sc.setFkCategoria(rs.getInt("id_categoria"));
				//c.getSubcategoria().setNome(rs.getString("nome_subcategoria"));
				//c.getSubcategoria().setId(rs.getInt("id_subcategoria"));
				subcategorias.add(sc);
				
			}
			return subcategorias;		
		
	}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	

}
