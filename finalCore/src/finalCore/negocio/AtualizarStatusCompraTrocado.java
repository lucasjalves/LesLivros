package finalCore.negocio;

import finalCore.core.IStrategy;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;

public class AtualizarStatusCompraTrocado implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Pedido p = (Pedido)entidade;
		if(p.getStatus() != null)
			if(p.getStatus().trim().equals("trocaritens"))
				return "TROCADO";
		return null;
	}

}
