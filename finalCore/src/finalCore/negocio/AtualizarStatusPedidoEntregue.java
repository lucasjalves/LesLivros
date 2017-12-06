package finalCore.negocio;

import finalCore.core.IStrategy;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;

public class AtualizarStatusPedidoEntregue implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Pedido p = (Pedido)entidade;
			if(p != null)
				if(p.getStatus().trim().equals("entregado"))
					return "ENTREGUE";
		return null;
	}

}
