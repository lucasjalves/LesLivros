package finalCore.negocio;

import finalCore.core.IStrategy;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;
import finalDominio.PedidoTroca;

public class AtualizarStatusPedidoTroca implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Pedido)
		{
			Pedido p = (Pedido)entidade;
			if(p.getStatus().trim().equals("trocar"))	
				return "EM TROCA"; 
		}
		if(entidade instanceof PedidoTroca)
		{
			PedidoTroca p = (PedidoTroca)entidade;
			if(p.getStatus().trim().equals("trocar"))
				return "EM TROCA";
		}
		return null;
	}

}
