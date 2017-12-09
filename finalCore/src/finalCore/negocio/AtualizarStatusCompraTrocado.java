package finalCore.negocio;

import finalCore.core.IStrategy;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;
import finalDominio.PedidoTroca;

public class AtualizarStatusCompraTrocado implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof PedidoTroca)
		{
			PedidoTroca p = (PedidoTroca)entidade;
			if(p.getStatus() != null)
				if(p.getStatus().equals("EM TROCA"))
					return "TROCADO";
			return null;			
		}
		if(entidade instanceof Pedido)
		{
			Pedido p = (Pedido)entidade;
			if(p.getStatus() != null)
				if(p.getStatus().equals("EM TROCA"))
					return "TROCADO";
			return null;			
		}
		
		return null;
	
	}
}
