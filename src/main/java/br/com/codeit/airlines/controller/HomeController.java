package br.com.codeit.airlines.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.codeit.airlines.domain.Tripulante;
import br.com.codeit.airlines.service.SmartwoService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

@Component
public class HomeController implements Initializable, Observer {

	@FXML
	private Button btnReiniciar;

	@FXML
	private ToggleButton btnPolicialAviao;

	@FXML
	private ToggleButton btnPilotoAviao;

	@FXML
	private ToggleButton btnComissariaDoisAviao;

	@FXML
	private ToggleButton btnOficialUmAviao;

	@FXML
	private ToggleButton btnComissariaUmAviao;

	@FXML
	private ToggleButton btnPrisioneiroAviao;

	@FXML
	private Button btnIniciar;

	@FXML
	private ToggleButton btnOficialDoisAviao;

	@FXML
	private ToggleGroup grupoTripulantes;

	@FXML
	private ToggleButton btnChefeVooAviao;

	@FXML
	private Label labelInformacao;

	@Autowired
	private SmartwoService smartwoService;

	private Map<String, ToggleButton> map = new HashMap<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnReiniciar.setDisable(true);
		esconderInformacoesLadoAviao();
	}

	@FXML
	private void onClickBtnIniciar(ActionEvent event) {
		adicionarMapInformacoesLadoAviao();
		smartwoService.getTripulantes().forEach(t -> t.addObserver(this));
		RadioButton selecionado = (RadioButton) grupoTripulantes.getSelectedToggle();

		final Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				smartwoService.iniciarTransporte(selecionado.getText());
				return null;
			}
		};
		
		task.setOnSucceeded(e -> {
			labelInformacao.setText("Transporte concluído com sucesso");
		});
		
		task.setOnFailed(e -> {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Airline");
			alert.setHeaderText(e.getSource().getException().getMessage());
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == ButtonType.OK) {
				acoesReiniciar();
			}
		});
		new Thread(task).start();

		btnIniciar.setDisable(true);
		btnReiniciar.setDisable(false);
	}

	@FXML
	private void onClickBtnReiniciar(ActionEvent event) {
		acoesReiniciar();
	}

	@Override
	public void update(Observable observable, Object arg) {
		if (observable instanceof Tripulante) {
			Tripulante tripulante = (Tripulante) observable;
			final StringBuilder sb = new StringBuilder(tripulante.getIdentificacao());
			ToggleButton toggleButton = map.get(sb.toString());
			if (toggleButton == null) {
				if (map.get(sb.append("Um").toString()) != null) {
					toggleButton = map.get(sb.toString());
				} else {
					toggleButton = map.get(sb.replace(sb.indexOf("Um"), sb.length(), "Dois").toString());
				}
			}
			toggleButton.setVisible(true);
			map.remove(sb.toString());
		}
	}
	
	private void acoesReiniciar() {
		smartwoService.limparRegistros();
		btnIniciar.setDisable(false);
		btnReiniciar.setDisable(true);
		esconderInformacoesLadoAviao();
		labelInformacao.setText("Selecione um motorista para Smartwo e clique em iniciar.");
	}

	private void esconderInformacoesLadoAviao() {
		btnPilotoAviao.setVisible(false);
		btnOficialUmAviao.setVisible(false);
		btnOficialDoisAviao.setVisible(false);
		btnChefeVooAviao.setVisible(false);
		btnComissariaUmAviao.setVisible(false);
		btnComissariaDoisAviao.setVisible(false);
		btnPolicialAviao.setVisible(false);
		btnPrisioneiroAviao.setVisible(false);
	}

	private void adicionarMapInformacoesLadoAviao() {
		map.put("Piloto", btnPilotoAviao);
		map.put("OficialUm", btnOficialUmAviao);
		map.put("OficialDois", btnOficialDoisAviao);
		map.put("Chefe de voo", btnChefeVooAviao);
		map.put("ComissariaUm", btnComissariaUmAviao);
		map.put("ComissariaDois", btnComissariaDoisAviao);
		map.put("Policial", btnPolicialAviao);
		map.put("Prisioneiro", btnPrisioneiroAviao);
	}
}
