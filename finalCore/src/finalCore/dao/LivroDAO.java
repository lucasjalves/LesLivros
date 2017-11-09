package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import finalDominio.Categoria;
import finalDominio.EntidadeDominio;
import finalDominio.GrupoPrecificacao;
import finalDominio.Livro;
import finalDominio.SubCategoria;

public class LivroDAO extends AbstractJdbcDAO{
	public LivroDAO() {
		super("livros", "id_livro");
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst = null;
		Livro livro = (Livro)entidade;
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO livros(nome, autor, ano, status, titulo, ");
			sql.append("editora, edicao, isbn, num_paginas, sinopse, altura, peso, ");
			sql.append("profundidade, pk_categoria, pk_subcategoria, pk_grupo)");
			sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");		
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, livro.getNome());
			pst.setString(2, livro.getAutor());
			pst.setString(3, livro.getAno());
			pst.setString(4, livro.getStatus());
			pst.setString(5, livro.getTitulo());
			pst.setString(6, livro.getEditora());
			pst.setString(7, livro.getEdicao());
			pst.setString(8, livro.getISBN());
			pst.setString(9, livro.getNpaginas());
			pst.setString(10, livro.getSinopse());
			pst.setString(11, livro.getAltura());
			pst.setString(12, livro.getPeso());
			pst.setString(13, livro.getProfundidade());
			pst.setInt(14, livro.getCategoria().getId());
			pst.setInt(15, livro.getSubcategoria().getId());
			pst.setInt(16, livro.getGp().getId());
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
			try 
			{
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
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM `livros` "
				+ "INNER JOIN categoria ON "
				+ "(livros.pk_categoria = categoria.id_categoria) "
				+ "INNER JOIN subcategoria ON "
				+ "(livros.pk_subcategoria = subcategoria.id_subcategoria) "
				+ "INNER JOIN grupoprecificacao ON "
				+ "(livros.pk_grupo = grupoprecificacao.id_grupo) WHERE 1=1 ");
		Livro livro = (Livro)entidade;
		if(livro.getId() != null)
		{
			sb.append("AND livros.id = '" + livro.getId() + "'");
		}
		System.out.println(sb.toString());
		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> livros = new ArrayList<EntidadeDominio>();
			while(rs.next()){
				Livro l = new Livro();
				Categoria c = new Categoria();
				SubCategoria subc = new SubCategoria();
				GrupoPrecificacao gp = new GrupoPrecificacao();
				l.setCategoria(c);
				l.setSubcategoria(subc);
				l.setGp(gp);
				
				
				l.setNome(rs.getString("nome"));
				l.setAutor(rs.getString("autor"));
				l.setAno(rs.getString("ano"));
				l.setTitulo(rs.getString("titulo"));
				l.setEditora(rs.getString("editora"));
				l.setEdicao(rs.getString("edicao"));
				l.setISBN(rs.getString("isbn"));
				l.setNpaginas(rs.getString("num_paginas"));
				l.setSinopse(rs.getString("sinopse"));
				l.setAltura(rs.getString("altura"));
				l.setPeso(rs.getString("peso"));
				l.setProfundidade(rs.getString("profundidade"));
				l.setId(rs.getInt("id"));
				
				l.getCategoria().setNome(rs.getString("nome_categoria"));
				l.getSubcategoria().setNome(rs.getString("nome_subcategoria"));
				l.getGp().setNome(rs.getString("nome_grupo"));
				l.getGp().setMargemLucro(rs.getDouble("taxa"));
				
				
				livros.add(l);
			}
			return livros;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

}
