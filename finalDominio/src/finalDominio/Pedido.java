package finalDominio;

import java.util.Date;
import java.util.List;

public class Pedido extends EntidadeDominio{
	
	private List<Item> item;
	private Date dtPedido;
	private String status;
	private double precoTotal;
	private double frete;
	private List<CupomTroca> cupomTroca;
	private List<CartoesCompra> cartoesCompra;
	private CupomPromocional cupomPromocional;
	private Endereco endereco;
	private int idCliente;
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public CupomPromocional getCupomPromocional() {
		return cupomPromocional;
	}
	public void setCupomPromocional(CupomPromocional cupomPromocional) {
		this.cupomPromocional = cupomPromocional;
	}
	public List<CupomTroca> getCupomTroca() {
		return cupomTroca;
	}
	public void setCupomTroca(List<CupomTroca> cupomTroca) {
		this.cupomTroca = cupomTroca;
	}
	
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

	public double getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
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
