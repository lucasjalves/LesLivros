package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import finalDominio.Categoria;
import finalDominio.EntidadeDominio;
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
		sb.append("SELECT * FROM CATEGORIA");
		
		Categoria cat = (Categoria)entidade;
		if(cat.getId() != null)
		{
			sb.append(" WHERE id_categoria = " + cat.getId());
		}
		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			ResultSet rs = pst.executeQuery();
			
			List<Integer> ids = new ArrayList<Integer>();
			Map<Integer, Categoria> mapaCategorias = new HashMap<Integer, Categoria>();
			List<EntidadeDominio> categorias = new ArrayList<EntidadeDominio>();
			
			while(rs.next()){
				Categoria c = new Categoria();
				c.setId(rs.getInt("id_categoria"));
				c.setNome((rs.getString("nome_categoria")));
				
				if(!mapaCategorias.containsKey(c.getId()))
				{
					c.setSubcategorias(new ArrayList<SubCategoria>());
					mapaCategorias.put(c.getId(), c);
					ids.add(c.getId());
				}			
			}
			for(int i = 0; i < ids.size(); i++)
			{
				pst = connection.prepareStatement("SELECT * FROM subcategoria WHERE pk_categoria = " + ids.get(i));
				ResultSet rsSubCategoria = pst.executeQuery();
				while(rsSubCategoria.next())
				{
					SubCategoria s = new SubCategoria();
					Categoria c = mapaCategorias.get(ids.get(i));
					s.setId(rsSubCategoria.getInt("id_subcategoria"));
					s.setNome((rsSubCategoria.getString("nome_subcategoria")));
					c.getSubcategorias().add(s);
				}	
				categorias.add(mapaCategorias.get(ids.get(i)));
			}		
			
			return categorias;		
		
	}catch(SQLException e)
		{
			e.printStackTrace();
	}finally{
		connection.close();
	}
		return null;
	}
}
