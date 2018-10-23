package br.com.codeit.airlines.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RegistradoraTripulacao {
	
	private static RegistradoraTripulacao INSTANCE;
	
	private List<Tripulacao> tripulacoes = new ArrayList<>();
	
	private RegistradoraTripulacao() {}
	
	public static RegistradoraTripulacao getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new RegistradoraTripulacao();
		}
		return INSTANCE;
	}

	public void registrar(Tripulacao tripulacao) {
		this.tripulacoes.add(tripulacao);
	}
	
	public void limparRegistros() {
		this.tripulacoes.clear();
	}
	
	public Tripulacao getTripulacaoPorMotorista(final Tripulante motorista) {
		if(motorista instanceof Policial) {
			throw new RuntimeException("Policial não pode deixar prisioneiro sozinho com o restante dos tripulantes.");
		}
		if(motorista.isMotorista()) {
			return tripulacoes.stream().filter(tripulacao -> tripulacao.getMotoristaDisponivel().equals(motorista)).findAny().get();
		}
		throw new RuntimeException("Motorista selecionado (" + motorista.getIdentificacao() +") não tem permissão para dirigir SmartFortwo");
	}
	
	public Optional<Tripulacao> getTripulacaoAindaNaoTransportada() {
		List<Tripulacao> tripulacoesDisponiveis = getTripulacoesDisponives();
		
		Optional<Tripulacao> tripulacaoOpt = tripulacoesDisponiveis.stream()
				.filter(Tripulacao::passageiroPodeFicarSozinho)
				.findAny();
		
		if(tripulacaoOpt.isPresent()) {
			return tripulacaoOpt;
		} else if (!tripulacoesDisponiveis.isEmpty()){
			return Optional.of(tripulacoesDisponiveis.get(0));
		} else {
			return Optional.empty();
		}
	}
	
	private List<Tripulacao> getTripulacoesDisponives(){
		return tripulacoes.stream().filter(Tripulacao::temPassageirosDisponiveis).collect(Collectors.toList());
	}

}
