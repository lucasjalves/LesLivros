package finalCore.negocio;

import finalCore.core.IStrategy;
import finalDominio.EntidadeDominio;
import finalDominio.Livro;

public class ValidarDadosObrigatoriosLivro implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if(entidade instanceof Livro)
		{
			Livro l = (Livro)entidade;
			
			if(l.getAltura() == null || l.getAno() == null || l.getAutor() == null)
				return "Altura, ano e autor são obrigatórios!";
			
			if(l.getCategoria() == null || l.getEditora() == null || l.getGp() == null)
				return "Categoria obrigatória, editora e grupo de precificação são obrigatórios!";
			
			if(l.getISBN() == null|| l.getNome() == null || l.getNpaginas() == null)		
				return "ISBN, nome e quantidade de páginas são obrigatórios!";
			
			if(l.getPeso() == null || l.getProfundidade() == null || l.getSinopse() == null  )
				return "Peso, profundidade e sinopse são obrigatórios!";		
			
			if(l.getStatus() == null || l.getSubcategoria() == null || l.getTitulo() == null)
				return "Status, subcategoria e títulos são obrigatórios!";	
			

			
			
			
			
			
			if(l.getAltura().trim().equals("") || l.getAno().trim().equals("") || l.getAutor().trim().equals(""))
				return "Altura, ano e autor são obrigatórios!";
			
			if(l.getCategoria() == null || l.getEditora().trim().equals("") || l.getGp() == null)
				return "Categoria obrigatória, editora e grupo de precificação são obrigatórios!";
			
			if(l.getISBN().trim().equals("") || l.getNome().trim().equals("") || l.getNpaginas().trim().equals(""))		
				return "ISBN, nome e quantidade de páginas são obrigatórios!";
			
			if(l.getPeso().trim().equals("") || l.getProfundidade().trim().equals("") || l.getSinopse().trim().equals("") )
				return "Peso, profundidade e sinopse são obrigatórios!";		
			
			if(l.getStatus().trim().equals("") || l.getSubcategoria() == null || l.getTitulo().trim().equals(""))
				return "Status, subcategoria e títulos são obrigatórios!";
			
			
			return null;
		}
		return null;
	}

}
