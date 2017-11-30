package finalCore.negocio;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import finalCore.core.IStrategy;
import finalCore.dao.CupomDAO;
import finalCore.util.ConverteDate;
import finalDominio.Cupom;
import finalDominio.EntidadeDominio;
import finalDominio.Pedido;

public class ValidarCupomPromocionalData implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		Pedido p = (Pedido)entidade;
		if(p.getCupom() != null)
		{
			CupomDAO cupDAO = new CupomDAO();
			List<EntidadeDominio> e = null;

			for(int i = 0; i < p.getCupom().size(); i++)
			{
				try {
					e = cupDAO.consultar(entidade);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(e.size() == 0)	
					return "O cupom não existe";
				
				Date dtVencimento = p.getCupom().get(i).getDtValidade();
				Calendar cal = Calendar.getInstance();
				Date dtHoje = cal.getTime();
				if(dtVencimento.before(dtHoje))
				{
					return "O cupom está fora do prazo de validade";
				}
			}
		}
		
		return null;
	}

}
