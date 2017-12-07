package finalDominio;

import java.util.Date;
import java.util.List;

public class PedidoTroca extends EntidadeDominio {
	private List<ItemTroca> itensTroca;
	private Integer idCliente;
	private Integer idPedido;
	private String status;
	private Date dtTroca;
	
	
	public Date getDtTroca() {
		return dtTroca;
	}
	public void setDtTroca(Date dtTroca) {
		this.dtTroca = dtTroca;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public List<ItemTroca> getItensTroca() {
		return itensTroca;
	}
	public void setItensTroca(List<ItemTroca> itensTroca) {
		this.itensTroca = itensTroca;
	}
}
