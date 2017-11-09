package finalDominio;

public class Endereco extends EntidadeDominio {
	private String tipoRes;
	private String tipoLog;
	private String logradouro;
	private String numCasa;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private String pais;
	private Integer pk_pessoa;
	
	public String getTipoRes() {
		return tipoRes;
	}
	public void setTipoRes(String tipoRes) {
		this.tipoRes = tipoRes;
	}
	public String getTipoLog() {
		return tipoLog;
	}
	public void setTipoLog(String tipoLog) {
		this.tipoLog = tipoLog;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumCasa() {
		return numCasa;
	}
	public void setNumCasa(String numCasa) {
		this.numCasa = numCasa;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public Integer getPk_pessoa() {
		return pk_pessoa;
	}
	public void setFk_pessoa(Integer pk_pessoa) {
		this.pk_pessoa = pk_pessoa;
	}
	
	
}
