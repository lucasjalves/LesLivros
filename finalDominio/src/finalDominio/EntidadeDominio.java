package finalDominio;

public class EntidadeDominio implements IEntidade{
	private String nome;
	private Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public String getNome() 
	{
		return nome;
	}
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
}
