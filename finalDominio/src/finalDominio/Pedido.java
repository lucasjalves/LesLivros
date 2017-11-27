package finalDominio;

import java.util.Date;
import java.util.List;

public class Pedido extends EntidadeDominio{
	private List<Item> item;
	private Date dtPedido;

	private String status;
	private Pessoa usuario;
	private double precoTotal;
	private double frete;
	private List<Cupom> cupom;
	private List<CartoesCompra> cartoesCompra;
	private Endereco endereco;
	
	
	public List<CartoesCompra> getCartoesCompra() {
		return cartoesCompra;
	}
	public void setCartoesCompra(List<CartoesCompra> cartoesCompra) {
		this.cartoesCompra = cartoesCompra;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public double getFrete() {
		return frete;
	}
	public void setFrete(double frete) {
		this.frete = frete;
	}
	public List<Cupom> getCupom() {
		return cupom;
	}
	public void setCupom(List<Cupom> cupom) {
		this.cupom = cupom;
	}
	
	
	public double getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}
	public Pessoa getUsuario() {
		return usuario;
	}
	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}
	public Date getDtPedido() {
		return dtPedido;
	}
	public void setDtPedido(Date dtPedido) {
		this.dtPedido = dtPedido;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
