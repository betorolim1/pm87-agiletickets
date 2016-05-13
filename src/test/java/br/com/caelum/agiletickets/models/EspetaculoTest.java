package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
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
	public void sessaoComMesmaDataPeriodicidadeDiariaDeveriaCriarApenasUmaSessao(){
		LocalDate dia = new LocalDate();
		LocalTime hora = new LocalTime();
		Espetaculo show = new Espetaculo();
		
		List<Sessao> sessoes = show.criaSessoes(dia, dia, hora, Periodicidade.DIARIA);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(show, sessoes.get(0).getEspetaculo());
		Assert.assertEquals(dia.toDateTime(hora), sessoes.get(0).getInicio());
		
	}
	
	@Test
	public void sessaoCom11DiasDeDiferencaPeriodicidadeDiariaDeveriaCriar10Sessoes(){
		LocalDate diaInicio = new LocalDate();
		LocalDate diaFim = new LocalDate(diaInicio.plusDays(10));
		LocalTime hora = new LocalTime();
		Espetaculo show = new Espetaculo();
		
		List<Sessao> sessoes = show.criaSessoes(diaInicio, diaFim, hora, Periodicidade.DIARIA);
		
		Assert.assertEquals(11, sessoes.size());
		int i = 0;
		for (Sessao sessao : sessoes) {
			Assert.assertEquals(show, sessao.getEspetaculo());
			Assert.assertEquals(diaInicio.plusDays(i).toDateTime(hora), sessao.getInicio());
			i++;
		}
	}
	
	@Test
	public void sessaoComInicioMaiorQueFimDeveriaNaoDeixar(){
		LocalDate diaInicio = new LocalDate();
		LocalDate diaFim = new LocalDate(diaInicio.plusDays(-3));
		LocalTime hora = new LocalTime();
		Espetaculo show = new Espetaculo();
		
		List<Sessao> sessoes = show.criaSessoes(diaInicio, diaFim, hora, Periodicidade.DIARIA);
		
		Assert.assertEquals(0, sessoes.size());
		
	}
	
	@Test
	public void sessaoComMesmaDataPeriodicidadeSemanalDeveriaCriarApenasUmaSessao(){
		LocalDate dia = new LocalDate();
		LocalTime hora = new LocalTime();
		Espetaculo show = new Espetaculo();
		
		List<Sessao> sessoes = show.criaSessoes(dia, dia, hora, Periodicidade.SEMANAL);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(show, sessoes.get(0).getEspetaculo());
		Assert.assertEquals(dia.toDateTime(hora), sessoes.get(0).getInicio());		
		
	}
	
	@Test
	public void sessaoCom10DiasDeDiferencaPeriodicidadeSemanalDeveriaCriar2Sessoes(){
		LocalDate diaInicio = new LocalDate();
		LocalDate diaFim = new LocalDate(diaInicio.plusDays(10));
		LocalTime hora = new LocalTime();
		Espetaculo show = new Espetaculo();
		
		List<Sessao> sessoes = show.criaSessoes(diaInicio, diaFim, hora, Periodicidade.SEMANAL);
		
		//////
		Assert.assertEquals(2, sessoes.size());
			Assert.assertEquals(show, sessoes.get(0).getEspetaculo());
			Assert.assertEquals(diaInicio.toDateTime(hora), sessoes.get(0).getInicio());
	}
	
	@Test
	public void sessaoCom20DiasDeDiferencaPeriodicidadeSemanalDeveriaCriar3Sessoes(){
		LocalDate diaInicio = new LocalDate();
		LocalDate diaFim = new LocalDate(diaInicio.plusDays(20));
		LocalTime hora = new LocalTime();
		Espetaculo show = new Espetaculo();
		
		List<Sessao> sessoes = show.criaSessoes(diaInicio, diaFim, hora, Periodicidade.SEMANAL);
		
		Assert.assertEquals(3, sessoes.size());
		int i = 0;
		for (Sessao sessao : sessoes) {
			Assert.assertEquals(show, sessao.getEspetaculo());
			Assert.assertEquals(diaInicio.plusWeeks(i).toDateTime(hora), sessao.getInicio());
			i++;
		}
		
	}
	
	@Test
	public void deveriaCriarApenasUmaSessaoSeDatasDeInicioEFimForemIguaisEPeriodicidadeForDiaria() {
		//ENTRADAS
		LocalDate hoje = new LocalDate();
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(hoje, hoje, agora, Periodicidade.DIARIA);
		
		//SAIDAS
		Assert.assertEquals(1, criadas.size());
		Assert.assertEquals(impeachment, criadas.get(0).getEspetaculo());
		Assert.assertEquals(hoje.toDateTime(agora), criadas.get(0).getInicio());
	}
	
	@Test
	public void deveriaCriarApenasUmaSessaoSeDatasDeInicioEFimForemIguaisEPeriodicidadeForSemanal() {
		//ENTRADAS
		LocalDate hoje = new LocalDate();
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(hoje, hoje, agora, Periodicidade.SEMANAL);
		
		//SAIDAS
		Assert.assertEquals(1, criadas.size());
		Assert.assertEquals(impeachment, criadas.get(0).getEspetaculo());
		Assert.assertEquals(hoje.toDateTime(agora), criadas.get(0).getInicio());
	}
	
	@Test
	public void deveriaCriar11SessoesSeIntervaloDeDatasForDe10DiasEPeriodicidadeForDiaria() {
		//ENTRADAS
		LocalDate hoje = new LocalDate();
		LocalDate daqui10Dias = hoje.plusDays(10);
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(hoje, daqui10Dias, agora, Periodicidade.DIARIA);
		
		//SAIDAS
		Assert.assertEquals(11, criadas.size());
		for (int i = 0; i < 11; i++) {
			Assert.assertEquals(impeachment, criadas.get(i).getEspetaculo());
			Assert.assertEquals(hoje.plusDays(i).toDateTime(agora), criadas.get(i).getInicio());
		}
	}
	
	@Test
	public void deveriaCriar4SessoesSeIntervaloDeDatasForDe3SemanasEPeriodicidadeForSemanal() {
		//ENTRADAS
		LocalDate hoje = new LocalDate();
		LocalDate daqui3Semanas= hoje.plusWeeks(3);
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(hoje, daqui3Semanas, agora, Periodicidade.SEMANAL);
		
		//SAIDAS
		Assert.assertEquals(4, criadas.size());
		for (int i = 0; i < 4; i++) {
			Assert.assertEquals(impeachment, criadas.get(i).getEspetaculo());
			Assert.assertEquals(hoje.plusWeeks(i).toDateTime(agora), criadas.get(i).getInicio());
		}
	}
	
	@Test
	public void naoDeveriaCriarSessoesSeDataInicioForMaiorQueDataFimEPeriodicidadeForDiaria() {
		//ENTRADAS
		LocalDate amanha = new LocalDate().plusDays(1);
		LocalDate hoje = new LocalDate();
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(amanha, hoje, agora, Periodicidade.DIARIA);
		
		//SAIDAS
		Assert.assertEquals(0, criadas.size());
	}
	
	@Test
	public void naoDeveriaCriarSessoesSeDataInicioForMaiorQueDataFimEPeriodicidadeForSemanal() {
		//ENTRADAS
		LocalDate amanha = new LocalDate().plusDays(1);
		LocalDate hoje = new LocalDate();
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(amanha, hoje, agora, Periodicidade.SEMANAL);
		
		//SAIDAS
		Assert.assertEquals(0, criadas.size());
	}
	
}
