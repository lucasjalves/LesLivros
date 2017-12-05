package finalCore.negocio;

import finalCore.core.IStrategy;
import finalCore.util.ValidarCartao;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;

public class AprovarCompraCartao implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Pedido p = (Pedido)entidade;
		if(p.getId() != null)
			if(p.getStatus().equals("EM PROCESSAMENTO"))
				return ValidarCartao.validarCompra(p);
		return null;
	}

}
