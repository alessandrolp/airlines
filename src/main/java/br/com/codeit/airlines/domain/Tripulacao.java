package br.com.codeit.airlines.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Tripulacao {
	
	private List<Tripulante> tripulantes = new ArrayList<>();
	
	protected Tripulacao(Tripulante ... tripulantes) {
		this.tripulantes.addAll(Arrays.asList(tripulantes));
		RegistradoraTripulacao.getInstance().registrar(this);
	}

	public abstract boolean passageiroPodeFicarSozinho();
	
	public List<Tripulante> getPassageirosDisponiveis(){
		return tripulantes.stream()
				.filter(Tripulante::isTipoPassageiroELocalizacaoTerminal)
				.collect(Collectors.toList());
	}
	
	public boolean temPassageirosDisponiveis() {
		return getPassageirosDisponiveis().size() > 0;
	}
	
	public Tripulante getMotoristaDisponivel() {
		return tripulantes.stream()
				.filter(Tripulante::isTipoMotoristaELocalizacaoTerminal)
				.findAny()
				.orElseThrow(() -> new RuntimeException("Motorista não encontrado, ja esta no avião"));
	}

}
