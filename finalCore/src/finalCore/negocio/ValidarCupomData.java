package finalCore.negocio;


import java.util.Calendar;
import java.util.Date;


import finalCore.core.IStrategy;

import finalDominio.Cupom;
import finalDominio.EntidadeDominio;


public class ValidarCupomData implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		Cupom c = (Cupom)entidade;
		if(c.getId() != null)
		{

			Date dtVencimento = c.getDtValidade();
			Calendar cal = Calendar.getInstance();
			Date dtHoje = cal.getTime();
			if(dtVencimento.before(dtHoje))
			{
					return "O cupom está fora do prazo de validade";
			}		
		}
		
		return null;
	}

}
