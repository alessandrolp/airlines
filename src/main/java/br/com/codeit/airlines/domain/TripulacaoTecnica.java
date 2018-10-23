package br.com.codeit.airlines.domain;

import com.google.common.base.Preconditions;

public class TripulacaoTecnica extends Tripulacao {
	
	private TripulacaoTecnica(Tripulante ... tripulantes) {
		super(tripulantes);
	}

	public static TripulacaoTecnica of(Piloto piloto, Oficial oficialUm, Oficial oficialDois) {
		Preconditions.checkNotNull(piloto, "Piloto é obrigatório");
		Preconditions.checkNotNull(oficialUm, "Oficial é obrigatório");
		Preconditions.checkNotNull(oficialDois, "Oficial é obrigatório");
		return new TripulacaoTecnica(piloto, oficialUm, oficialDois);
	}

	@Override
	public boolean passageiroPodeFicarSozinho() {
		return true;
	}

}
