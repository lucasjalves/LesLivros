package finalCore.negocio;

import java.util.Calendar;
import java.util.Date;

import finalCore.core.IStrategy;
import finalCore.util.ConverteDate;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;

public class ValidarCupomPromocionalData implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		Pedido p = (Pedido)entidade;
		if(p.getCupom() != null)
		{
			for(int i = 0; i < p.getCupom().size(); i++)
			{
				Date dtVencimento = p.getCupom().get(i).getDtValidade();
				Calendar cal = Calendar.getInstance();
				Date dtHoje = cal.getTime();
				System.out.println(cal.getTime().toString());
				System.out.println(ConverteDate.converteDateString(dtVencimento));
				if(dtVencimento.before(dtHoje))
				{
					return "O cupom está fora do prazo de validade";
				}
			}
		}
		
		return null;
	}

}
