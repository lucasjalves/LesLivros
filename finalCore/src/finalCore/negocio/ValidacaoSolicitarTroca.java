package finalCore.negocio;

import finalCore.core.IStrategy;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;

public class ValidacaoSolicitarTroca implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Pedido p = (Pedido)entidade;
		if(p != null && p.getStatus() != null)
			if(p.getStatus().trim().equals("ENTREGUE"))
				return "Somente pedidos entregues podem ser trocados";
		return null;
	}

}
