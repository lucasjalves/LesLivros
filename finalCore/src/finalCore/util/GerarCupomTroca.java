package finalCore.util;

import java.util.Calendar;
import java.util.Date;

import finalDominio.CupomTroca;
import finalDominio.ItemTroca;
import finalDominio.PedidoTroca;

public class GerarCupomTroca {
	public static CupomTroca gerarCupomTroca(PedidoTroca pedido)
	{
		ItemTroca i = pedido.getItensTroca().get(0);
		CupomTroca c = new CupomTroca();
		c.setCodigo(GeradorCodigoCupom.generateString());
		
		c.setDesconto(i.getPrecoLivro());
		Calendar cal = Calendar.getInstance();
		Date dtCriacao = cal.getTime();
		c.setDtCriacao(dtCriacao);
		Date dtValidade = dtCriacao;
		
		dtValidade.setMonth(dtValidade.getMonth() + 1);
		c.setDtValidade(dtValidade);
		c.setIdCliente(pedido.getIdCliente());
		
		return c;
		
	}
}
