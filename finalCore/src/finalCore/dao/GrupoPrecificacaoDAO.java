package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import finalDominio.EntidadeDominio;
import finalDominio.GrupoPrecificacao;
import finalDominio.Livro;

public class GrupoPrecificacaoDAO extends AbstractJdbcDAO{

	public GrupoPrecificacaoDAO() {
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
		sb.append("SELECT * FROM grupoprecificacao WHERE 1=1");

		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			System.out.println(sb.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> grupos = new ArrayList<>();
			while(rs.next()){
				GrupoPrecificacao gp = new GrupoPrecificacao();
				gp.setId(rs.getInt("id_grupo"));
				gp.setNome(rs.getString("nome_grupo"));
				gp.setMargemLucro(rs.getDouble("taxa"));
				grupos.add(gp);
			}
			return grupos;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	

}
