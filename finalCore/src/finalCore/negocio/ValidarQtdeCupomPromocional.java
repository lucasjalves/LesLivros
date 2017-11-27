package finalCore.negocio;

import finalCore.core.IStrategy;
import finalDominio.Cupom;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;

public class ValidarQtdeCupomPromocional implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Pedido p = (Pedido)entidade;
		int qtdeCupomPromocional = 0;
		if(p.getCupom() != null)
		{
			for(int i = 0; i < p.getCupom().size(); i++)
			{
				Cupom c = p.getCupom().get(i);
				if(c.getTipo() == 1) {
					qtdeCupomPromocional = qtdeCupomPromocional + 1;
				}
				if(qtdeCupomPromocional > 1)
				{
					return "Só é permitido utilizar apenas um cupom promocional por pedido";
				}				
			}			
		}

		return null;
	}
	
	
}
