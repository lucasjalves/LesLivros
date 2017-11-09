package finalWeb.command.impl;

import finalCore.aplicacao.Resultado;
import finalDominio.EntidadeDominio;

public class VerificarCarrinhoCommand extends AbstractCommand{

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		
		return fachada.verificarCarrinho(entidade);
	}

}
