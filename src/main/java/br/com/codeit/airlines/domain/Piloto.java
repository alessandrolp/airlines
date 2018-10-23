package br.com.codeit.airlines.domain;

public class Piloto extends Tripulante {
	
	private Piloto(TipoTripulante tipoTripulante) {
		super(tipoTripulante);
	}
	
	public static Piloto of() {
		return new Piloto(TipoTripulante.MOTORISTA);
	}

	@Override
	public String getIdentificacao() {
		return "Piloto";
	}
	
	
}
