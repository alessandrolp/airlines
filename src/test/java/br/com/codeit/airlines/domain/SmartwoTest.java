package br.com.codeit.airlines.domain;

import org.junit.Before;
import org.junit.Test;

public class SmartwoTest {
	
	private Piloto piloto;
	private Oficial oficialUm;
	private Oficial oficialDois;
	
	private ChefeServicoVoo chefeServicoVoo;
	private Comissaria comissariaUm;
	private Comissaria comissariaDois;
	
	private Policial policial;
	private Prisioneiro prisioneiro;
	
	@Before
	public void setup() {
		RegistradoraTripulacao.getInstance().limparRegistros();
		
		piloto = Piloto.of();
		oficialUm = Oficial.of();
		oficialDois = Oficial.of();
		
		chefeServicoVoo = ChefeServicoVoo.of();
		comissariaUm = Comissaria.of();
		comissariaDois = Comissaria.of();
		
		policial = Policial.of();
		prisioneiro = Prisioneiro.of();
		
		TripulacaoRestante.of(policial, prisioneiro);
		TripulacaoCabine.of(chefeServicoVoo, comissariaUm, comissariaDois);
		TripulacaoTecnica.of(piloto, oficialUm, oficialDois);
	}
	
	@Test
	public void deveTransportarComSucessoMotoristaInicialPiloto() {
		SmartFortwo.iniciarTransporte(piloto);
	}
	
	@Test
	public void deveTransportarComSucessoMotoristaInicialChefeVoo() {
		SmartFortwo.iniciarTransporte(chefeServicoVoo);
	}
	
	@Test(expected = RuntimeException.class)
	public void deveFalharTransportarComoMotoristaInicialPolicial() {
		SmartFortwo.iniciarTransporte(policial);
	}
	
	@Test(expected = RuntimeException.class)
	public void deveFalharTransportarComoMotoristaInicialComissaria() {
		SmartFortwo.iniciarTransporte(comissariaUm);
	}
	
	@Test(expected = RuntimeException.class)
	public void deveFalharTransportarComoMotoristaInicialOficial() {
		SmartFortwo.iniciarTransporte(oficialUm);
	}
	
	@Test(expected = RuntimeException.class)
	public void deveFalharTransportarComoMotoristaInicialPrisioneiro() {
		SmartFortwo.iniciarTransporte(prisioneiro);
	}

}
