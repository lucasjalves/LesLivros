package finalDominio;


public class Cartao extends EntidadeDominio{
	private String numero;
	private String dtVencimento;
	private String codSeg;
	private String bandeira;
	private Integer pkUsuario;
	
	public Integer getPkUsuario() {
		return pkUsuario;
	}
	public void setPkUsuario(Integer pkUsuario) {
		this.pkUsuario = pkUsuario;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDtVencimento() {
		return dtVencimento;
	}
	public void setDtVencimento(String dtVencimento) {
		this.dtVencimento = dtVencimento;
	}
	public String getCodSeg() {
		return codSeg;
	}
	public void setCodSeg(String codSeg) {
		this.codSeg = codSeg;
	}
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	
}
