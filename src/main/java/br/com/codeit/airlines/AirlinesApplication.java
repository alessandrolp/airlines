package br.com.codeit.airlines;

import java.util.Optional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.codeit.airlines.repository.TripulanteRepository;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

@SpringBootApplication
@EnableAutoConfiguration
public class AirlinesApplication extends Application {
	
	public static ConfigurableApplicationContext applicationContext;

	@Override
	public void init() throws Exception {
		applicationContext = new SpringApplicationBuilder(AirlinesApplication.class).headless(false).web(true).run(new String[] {});
		applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent rootNode = FXMLLoaderFactory.load("templates/FXMLhome.fxml");
		primaryStage.setScene(new Scene(rootNode));
		primaryStage.centerOnScreen();
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Airline");
			alert.setHeaderText("Deseja realmente sair?");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == ButtonType.OK) {
				primaryStage.close();
			}
		});
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		applicationContext.close();
	}

	public static void main(String[] args) {
		TripulanteRepository.getInstance().salvarRegistros();
		launch(args);
	}
}
