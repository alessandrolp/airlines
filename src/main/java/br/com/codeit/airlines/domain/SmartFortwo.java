package br.com.codeit.airlines.domain;

public class SmartFortwo {
	
	public static void iniciarTransporte(final Tripulante motorista) {
		Tripulacao tripulacao = RegistradoraTripulacao.getInstance().getTripulacaoPorMotorista(motorista);
		transportar(tripulacao, motorista);
	}
	
	private static void transportar(final Tripulacao tripulacao, final Tripulante motorista) {
		tripulacao.getPassageirosDisponiveis().forEach(passageiro -> {
			transportarPassageiro(motorista, passageiro);
		});
		transportarMotoristaTripulacao(motorista);
	}

	private static void transportarMotoristaTripulacao(Tripulante motoristaTripulacaoAnterior) {
		RegistradoraTripulacao.getInstance().getTripulacaoAindaNaoTransportada().ifPresent(tripulacao -> {
			Tripulante motorista = tripulacao.getMotoristaDisponivel();
			transportarPassageiro(motorista, motoristaTripulacaoAnterior);
			transportar(tripulacao, motorista);
		});
	}

	private static void transportarPassageiro(final Tripulante motorista, final Tripulante passageiro) {
		System.out.println("Motorista: " + motorista.getIdentificacao() + " - Transportando passageiro: " + passageiro.getIdentificacao());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		passageiro.alterarLocalizacaoParaAviao();
		
		if(motorista instanceof Policial && passageiro instanceof Prisioneiro) {
			motorista.alterarLocalizacaoParaAviao();
		}
	}

}
