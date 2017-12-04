package finalCore.negocio;

import finalCore.core.IStrategy;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;

public class ValidarPagamentoCartoes implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Pedido p = (Pedido)entidade;
		if(p.getCartoesCompra() != null)
		{
			
			if(p.getCartoesCompra().size() > 1)
			{
				for(int i = 0; i < p.getCartoesCompra().size(); i ++)
				{
					double precoPagoCartao = p.getCartoesCompra().get(i).getValorPago();
					if(precoPagoCartao < 10 && p.getCupomTroca().size() == 0 || p.getCupomPromocional() == null)
					{
						return "O valor mínimo para cada cartão é de 10R$";
					}
				}
			}
			
		}
		return null;
	}

}
