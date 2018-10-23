package br.com.codeit.airlines.domain;

public class ChefeServicoVoo extends Tripulante {
	
	private ChefeServicoVoo(TipoTripulante tipoTripulante) {
		super(tipoTripulante);
	}
	
	public static ChefeServicoVoo of() {
		return new ChefeServicoVoo(TipoTripulante.MOTORISTA);
	}

	@Override
	public String getIdentificacao() {
		return "Chefe de voo";
	}

}
