package finalDominio;

public class Telefone extends EntidadeDominio {
	private String ddd;
	private String numero;
	private String tipo;
	private Integer fk_cliente;
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getFk_cliente() {
		return fk_cliente;
	}
	public void setFk_cliente(Integer fk_cliente) {
		this.fk_cliente = fk_cliente;
	}
}
