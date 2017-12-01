package finalCore.negocio;

import finalCore.core.IStrategy;
import finalDominio.Cupom;
import finalDominio.EntidadeDominio;

public class ValidarCupomVeridico implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Cupom c = (Cupom)entidade;
		if(c.getCodigo() == null)
		{
			return "O Cupom digitado não existe";
		}
		return null;
	}

}
