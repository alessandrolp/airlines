package br.com.codeit.airlines.domain;

public class Oficial extends Tripulante {
	
	private Oficial(TipoTripulante tipoTripulante) {
		super(tipoTripulante);
	}
	
	public static Oficial of() {
		return new Oficial(TipoTripulante.PASSAGEIRO);
	}

	@Override
	public String getIdentificacao() {
		return "Oficial";
	}

}
