package finalCore.core;

import finalCore.aplicacao.Resultado;
import finalDominio.EntidadeDominio;

public interface IFachada {
	public Resultado salvar(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
	public Resultado visualizar(EntidadeDominio entidade);
	public Resultado verificarCarrinho(EntidadeDominio entidade);
}
