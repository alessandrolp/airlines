package br.com.codeit.airlines.domain;

public class Prisioneiro extends Tripulante {
	
	private Prisioneiro(TipoTripulante tipoTripulante) {
		super(tipoTripulante);
	}

	public static Prisioneiro of() {
		return new Prisioneiro(TipoTripulante.PASSAGEIRO);
	}

	@Override
	public String getIdentificacao() {
		return "Prisioneiro";
	}

}
