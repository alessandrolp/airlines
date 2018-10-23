package br.com.codeit.airlines.domain;

public class Comissaria extends Tripulante {
	
	private Comissaria(TipoTripulante tipoTripulante) {
		super(tipoTripulante);
	}
	
	public static Comissaria of() {
		return new Comissaria(TipoTripulante.PASSAGEIRO);
	}

	@Override
	public String getIdentificacao() {
		return "Comissaria";
	}

}
