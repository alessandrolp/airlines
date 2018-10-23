package br.com.codeit.airlines.domain;

import com.google.common.base.Preconditions;

public class TripulacaoCabine extends Tripulacao {

	private TripulacaoCabine(Tripulante ... tripulantes) {
		super(tripulantes);
	}
	
	public static TripulacaoCabine of(ChefeServicoVoo chefeServicoVoo, Comissaria comissariaUm, Comissaria comissariaDois) {
		Preconditions.checkNotNull(chefeServicoVoo, "Chefe serviço de voo é obrigatório");
		Preconditions.checkNotNull(comissariaUm, "Comissária é obrigatório");
		Preconditions.checkNotNull(comissariaUm, "Comissária é obrigatório");
		return new TripulacaoCabine(chefeServicoVoo, comissariaUm, comissariaDois);
	}
	
	@Override
	public boolean passageiroPodeFicarSozinho() {
		return true;
	}

}
