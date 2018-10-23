package br.com.codeit.airlines.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.codeit.airlines.domain.ChefeServicoVoo;
import br.com.codeit.airlines.domain.Comissaria;
import br.com.codeit.airlines.domain.Oficial;
import br.com.codeit.airlines.domain.Piloto;
import br.com.codeit.airlines.domain.Policial;
import br.com.codeit.airlines.domain.Prisioneiro;
import br.com.codeit.airlines.domain.TripulacaoCabine;
import br.com.codeit.airlines.domain.TripulacaoRestante;
import br.com.codeit.airlines.domain.TripulacaoTecnica;
import br.com.codeit.airlines.domain.Tripulante;

public class TripulanteRepository {
	
	private static TripulanteRepository INSTANCE;
	
	private List<Tripulante> tripulantes = new ArrayList<>();
	
	private TripulanteRepository() {}
	
	public static TripulanteRepository getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new TripulanteRepository();
		}
		return INSTANCE;
	}
	
	public void salvarRegistros() {
		tripulantes = new ArrayList<>();
		
		final Piloto piloto = Piloto.of();
		final Oficial oficialUm = Oficial.of();
		final Oficial oficialDois = Oficial.of();
		
		final ChefeServicoVoo chefeServicoVoo = ChefeServicoVoo.of();
		final Comissaria comissariaUm = Comissaria.of();
		final Comissaria comissariaDois = Comissaria.of();
		
		final Policial policial = Policial.of();
		final Prisioneiro prisioneiro = Prisioneiro.of();
		
		TripulacaoRestante.of(policial, prisioneiro);
		TripulacaoCabine.of(chefeServicoVoo, comissariaUm, comissariaDois);
		TripulacaoTecnica.of(piloto, oficialUm, oficialDois);
		
		tripulantes.addAll(Arrays.asList(piloto, oficialUm, oficialDois, chefeServicoVoo, comissariaUm,comissariaDois, policial, prisioneiro));
	}
	
	public List<Tripulante> getTripulantes(){
		return Collections.unmodifiableList(this.tripulantes);
	}
	
	public Tripulante getMotoristaPorIdentificacao(final String identificacao) {
		return tripulantes.stream().filter(tripulante -> tripulante.getIdentificacao().equals(identificacao))
				.findAny()
				.orElseThrow(() -> new RuntimeException("Identificação do tripulante não encontrado"));
	}
	
}
