package finalCore.negocio;

import finalCore.core.IStrategy;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;

public class ValidarPagamentoCartoes implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Pedido p = (Pedido)entidade;
		double precoPagoCartao = 0;
		if(p.getCartoesCompra() != null)
		{
			
			if(p.getCartoesCompra().size() > 1)
			{
				for(int i = 0; i < p.getCartoesCompra().size(); i ++)
				{
					precoPagoCartao = precoPagoCartao + p.getCartoesCompra().get(i).getValorPago();
					if(p.getCupomPromocional() == null && precoPagoCartao < 10)
						return "O valor mínimo de compra para cada cartão é de 10R$";
				}
			}
			
		}
		return null;
	}

}
