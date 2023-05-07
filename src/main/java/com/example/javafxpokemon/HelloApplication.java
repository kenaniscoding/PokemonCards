package com.example.javafxpokemon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage window;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        window.isResizable();
        window.initStyle(StageStyle.DECORATED);
        window.setTitle("Pokemon Card Collection!!!");
        window.getIcons().add(new Image("PokemonLogo.png"));
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("pokemonMenuStage.fxml"));
        scene = new Scene(fxml.load(),600, 500);
        window.setScene(scene);
        window.show();
    }
    public void changeScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        window.getScene().setRoot(fxmlLoader.load());
    }
    public Stage getStage(){
        return window;
    }


    public static void main(String[] args) {
        launch();
    }
}