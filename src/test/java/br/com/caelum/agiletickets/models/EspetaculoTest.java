package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void DeveInserir10SessoesDiariasEntreDia1eDia10() {
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(9);
		LocalTime horario = new LocalTime();
		
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(10,sessoes.size());
	}

	@Test
	public void DeveInserir2SessoesSemanaisEntreDia1eDia10() {
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(9);
		LocalTime horario = new LocalTime();
		
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		
		Assert.assertEquals(2,sessoes.size());
	}
	@Test
	public void DeveInserir1SessaoDiariasEmDiasIguais() {
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio;
		LocalTime horario = new LocalTime();
		
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(1,sessoes.size());
	}

	@Test
	public void DeveInserir1SessaoSemanalEmDiasIguais() {
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio;
		LocalTime horario = new LocalTime();
		
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		
		Assert.assertEquals(1,sessoes.size());
	}

	@Test
	public void NaoDeveInserirSessoesDiariasSeDataFinalMenorQueInicial() {
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.minusDays(1);
		LocalTime horario = new LocalTime();
		
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(0,sessoes.size());
	}

	@Test
	public void NaoDeveInserirSessoesSemanaisSeDataFinalMenorQueInicial() {
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.minusDays(1);
		LocalTime horario = new LocalTime();
		
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		
		Assert.assertEquals(0,sessoes.size());
	}	
	
}
