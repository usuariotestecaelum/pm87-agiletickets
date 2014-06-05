package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		TipoDeEspetaculo tipoEspetaculo;
		tipoEspetaculo= sessao.getEspetaculo().getTipo();
		preco= tipoEspetaculo.getPreco(getTotalDeIngressoPorcentagem(sessao),sessao.getDuracaoEmMinutos(), sessao.getPreco());
		
		return preco.multiply(BigDecimal.valueOf(quantidade));
		
	/*	if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			if(getTotalDeIngressoPorcentagem(sessao) <= 0.05) { 
				preco =  getPrecoDoIngresso(sessao, 0.10);
			} else {
				preco = sessao.getPreco();
			}
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)||(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET))) {
			if(getTotalDeIngressoPorcentagem(sessao) <= 0.50) { 
				preco = getPrecoDoIngresso(sessao, 0.20);
			} else {
				preco = sessao.getPreco();
			}

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		}  else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
*/	}

	private static double getTotalDeIngressoPorcentagem(Sessao sessao) {
		return getTotalIngressosDisponiveis(sessao) / getTotalDeIngressos(sessao);
	}

	private static double getTotalDeIngressos(Sessao sessao) {
		return sessao.getTotalIngressos().doubleValue();
	}

	private static int getTotalIngressosDisponiveis(Sessao sessao) {
		return sessao.getTotalIngressos() - sessao.getIngressosReservados();
	}
	private static BigDecimal getPrecoDoIngresso(Sessao sessao,Double valor){
		return sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(valor)));
	}

}