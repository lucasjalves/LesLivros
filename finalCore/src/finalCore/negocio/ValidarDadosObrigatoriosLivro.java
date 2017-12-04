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
				return "Altura, ano e autor s�o obrigat�rios!";
			
			if(l.getCategoria() == null || l.getEditora() == null || l.getGp() == null)
				return "Categoria obrigat�ria, editora e grupo de precifica��o s�o obrigat�rios!";
			
			if(l.getISBN() == null|| l.getNome() == null || l.getNpaginas() == null)		
				return "ISBN, nome e quantidade de p�ginas s�o obrigat�rios!";
			
			if(l.getPeso() == null || l.getProfundidade() == null || l.getSinopse() == null  )
				return "Peso, profundidade e sinopse s�o obrigat�rios!";		
			
			if(l.getStatus() == null || l.getTitulo() == null)
				return "Status e t�tulos s�o obrigat�rios!";	
			

			
			
			
			
			
			if(l.getAltura().trim().equals("") || l.getAno().trim().equals("") || l.getAutor().trim().equals(""))
				return "Altura, ano e autor s�o obrigat�rios!";
			
			if(l.getCategoria() == null || l.getEditora().trim().equals("") || l.getGp() == null)
				return "Categoria obrigat�ria, editora e grupo de precifica��o s�o obrigat�rios!";
			
			if(l.getISBN().trim().equals("") || l.getNome().trim().equals("") || l.getNpaginas().trim().equals(""))		
				return "ISBN, nome e quantidade de p�ginas s�o obrigat�rios!";
			
			if(l.getPeso().trim().equals("") || l.getProfundidade().trim().equals("") || l.getSinopse().trim().equals("") )
				return "Peso, profundidade e sinopse s�o obrigat�rios!";		
			
			if(l.getStatus().trim().equals("") || l.getTitulo().trim().equals(""))
				return "Status e t�tulos s�o obrigat�rios!";
			
			
			return null;
		}
		return null;
	}

}
