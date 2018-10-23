package br.com.codeit.airlines.domain;

public class Policial extends Tripulante  {
	
	private Policial(TipoTripulante tipoTripulante) {
		super(tipoTripulante);
	}

	public static Policial of() {
		return new Policial(TipoTripulante.MOTORISTA);
	}

	@Override
	public String getIdentificacao() {
		return "Policial";
	}

}
