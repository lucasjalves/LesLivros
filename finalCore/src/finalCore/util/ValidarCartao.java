package finalCore.util;


import java.util.Random;

import finalDominio.Pedido;

public class ValidarCartao {
	public static String validarCompra(Pedido pedido)
	{
		Random r = new Random();
		int n = r.nextInt(10);
		if(n == 0)
			return "REPROVADO";
		else
			return "APROVADO";
	}
}
