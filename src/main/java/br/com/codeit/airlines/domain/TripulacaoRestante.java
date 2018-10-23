package br.com.codeit.airlines.domain;

import com.google.common.base.Preconditions;

public class TripulacaoRestante extends Tripulacao {
	
	private TripulacaoRestante(Tripulante ... tripulantes) {
		super(tripulantes);
	}
	
	public static TripulacaoRestante of(Policial policial, Prisioneiro prisioneiro) {
		Preconditions.checkNotNull(policial, "Policial é obrigatório");
		Preconditions.checkNotNull(prisioneiro, "Prisioneiro é obrigatório");
		return new TripulacaoRestante(policial, prisioneiro);
	}
	
	@Override
	public boolean passageiroPodeFicarSozinho() {
		return false;
	}

}

