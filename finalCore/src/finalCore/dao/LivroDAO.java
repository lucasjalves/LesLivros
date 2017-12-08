package finalCore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");		
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
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
			pst.setInt(14, livro.getGp().getId());
			ResultSet rs = pst.getGeneratedKeys();
			pst.executeUpdate();	
			int id = 0;
			if(rs.next())
				id = rs.getInt(1);
			
			for(int i = 0; i < livro.getCategoria().size(); i ++ )
			{
				Categoria c = livro.getCategoria().get(i);
				SubCategoria s = c.getSubcategorias().get(0);
				pst = connection.prepareStatement("INSERT INTO `categoria_livro`(`id_livro`, `id_categoria`) VALUES ("+livro.getId()+"," +c.getId() +", " +s.getId()+")");		
				pst.executeUpdate();
			}
				

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
				+ "INNER JOIN grupoprecificacao ON "
				+ "(livros.pk_grupo = grupoprecificacao.id_grupo) WHERE 1=1 ");
		Livro livro = (Livro)entidade;
		if(livro.getId() != null)
		{
			sb.append("AND livros.id = '" + livro.getId() + "'");
		}
		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> livros = new ArrayList<EntidadeDominio>();
			while(rs.next()){
				Livro l = new Livro();

				GrupoPrecificacao gp = new GrupoPrecificacao();

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
				l.setPreco(rs.getDouble("preco_livro"));
				l.setQtdeEstoque(rs.getInt("qtde_estoque"));
				l.setLargura(rs.getString("largura"));
			
				l.getGp().setNome(rs.getString("nome_grupo"));
				l.getGp().setMargemLucro(rs.getDouble("taxa"));
				l.setCategoria(new ArrayList<Categoria>());
			
				pst = connection.prepareStatement("SELECT nome_categoria, categoria_livro.id_categoria FROM "
						+ "categoria_livro JOIN categoria on (categoria_livro.id_livro = " + l.getId() + " "
						+ "and categoria_livro.id_categoria = categoria.id_categoria)");
				ResultSet rsCategoria = pst.executeQuery();
				while(rsCategoria.next())
				{
					Categoria c = new Categoria();
					c.setNome(rsCategoria.getString("nome_categoria"));
					c.setId(rsCategoria.getInt("categoria_livro.id_categoria"));
					c.setSubcategorias(new ArrayList<SubCategoria>());
					
					pst = connection.prepareStatement("SELECT * "
							+ "FROM categoria_livro JOIN subcategoria on (categoria_livro.id_livro = " + l.getId() + " "
							+ "and categoria_livro.id_subcategoria = subcategoria.id_subcategoria)");
					ResultSet rsSubCategoria = pst.executeQuery();
					while(rsSubCategoria.next())
					{
						SubCategoria subc = new SubCategoria();
						subc.setId(rsSubCategoria.getInt("id_subcategoria"));
						subc.setNome(rsSubCategoria.getString("nome_subcategoria"));	
						c.getSubcategorias().add(subc);
					}					
					l.getCategoria().add(c);
				}
				livros.add(l);
			}
			return livros;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

}
