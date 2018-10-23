package br.com.codeit.airlines.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.codeit.airlines.domain.RegistradoraTripulacao;
import br.com.codeit.airlines.domain.SmartFortwo;
import br.com.codeit.airlines.domain.Tripulante;
import br.com.codeit.airlines.repository.TripulanteRepository;

@Service
public class SmartwoService {
	
	public void iniciarTransporte(final String identificacao) {
		Tripulante motorista = TripulanteRepository.getInstance().getMotoristaPorIdentificacao(identificacao);
		SmartFortwo.iniciarTransporte(motorista);
	}
	
	public void limparRegistros() {
		RegistradoraTripulacao.getInstance().limparRegistros();
		TripulanteRepository.getInstance().salvarRegistros();
	}
	
	public List<Tripulante> getTripulantes(){
		return TripulanteRepository.getInstance().getTripulantes();
	}

}
