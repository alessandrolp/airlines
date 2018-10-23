package br.com.codeit.airlines.domain;

import java.util.Observable;

public abstract class Tripulante extends Observable {
	
	private TipoTripulante tipoTripulante;
	
	private Localizacao localizacao = Localizacao.TERMINAL;
	
	protected Tripulante(TipoTripulante tipoTripulante) {
		this.tipoTripulante = tipoTripulante;
	}

	public abstract String getIdentificacao();
	
	public boolean isTipoMotoristaELocalizacaoTerminal() {
		return isMotorista() && isLocalizacaoTerminal();
	}
	
	public boolean isTipoPassageiroELocalizacaoTerminal() {
		return isPassageiro() && isLocalizacaoTerminal();
	}
	
	public void alterarLocalizacaoParaAviao() {
		this.localizacao = Localizacao.AVIAO;
		setChanged();
		notifyObservers();
	}

	public boolean isMotorista() {
		return this.tipoTripulante.equals(TipoTripulante.MOTORISTA);
	}
	
	private boolean isPassageiro() {
		return this.tipoTripulante.equals(TipoTripulante.PASSAGEIRO);
	}
	
	private boolean isLocalizacaoTerminal() {
		return this.localizacao.equals(Localizacao.TERMINAL);
	}
	
}
