package finalCore.negocio;

import finalCore.core.IStrategy;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;

public class AtualizarStatusPedidoTransporte implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Pedido p = (Pedido)entidade;
		
		if(p.getStatus() != null)
			if(p.getStatus().equals("APROVADO"))
				return "EM TRANSPORTE";
		return null;
	}

}
