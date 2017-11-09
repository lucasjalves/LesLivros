package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import finalDominio.Categoria;
import finalDominio.EntidadeDominio;
import finalDominio.Livro;
import finalDominio.PessoaFisica;
import finalDominio.SubCategoria;

public class CategoriaDAO extends AbstractJdbcDAO{

	public CategoriaDAO() {
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
		sb.append("SELECT * FROM `categoria`");
		System.out.println(sb.toString());
		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> categorias = new ArrayList<EntidadeDominio>();
			while(rs.next()){
				Categoria c = new Categoria();
				//SubCategoria subc = new SubCategoria();
				//c.setSubcategoria(subc);
				c.setId(rs.getInt("id_categoria"));
				c.setNome((rs.getString("nome_categoria")));
				//c.getSubcategoria().setNome(rs.getString("nome_subcategoria"));
				//c.getSubcategoria().setId(rs.getInt("id_subcategoria"));
				categorias.add(c);
				
			}
			return categorias;		
		
	}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
