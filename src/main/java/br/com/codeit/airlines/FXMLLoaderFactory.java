package br.com.codeit.airlines;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class FXMLLoaderFactory {
	
	public static Parent load(final String url) {
        try {
        	FXMLLoader loader = new FXMLLoader(FXMLLoaderFactory.class.getClassLoader().getResource(url));
    		loader.setControllerFactory(AirlinesApplication.applicationContext::getBean);
    		return loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar view: " + url, e);
        }
    }

}
