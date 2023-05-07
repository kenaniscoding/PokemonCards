package com.example.javafxpokemon;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.random.RandomGenerator;

public class HelloController {
    @FXML
    public TextField removePokemonIndex;
    @FXML
    public TextField searchPokemon;
    @FXML
    public Label pokemonNameNotFound;
    private int i=0;
    private RandomGenerator rgen = RandomGenerator.getDefault(); //getDefault and not getInstance
    private PokemonCards pokemonDeck;
    public HelloController(){
        pokemonDeck = new PokemonCards();
    }

    public void removePokemon(ActionEvent e) throws IOException {
        System.out.println("Remove One Pokemon");
        HelloApplication app = new HelloApplication();
        app.changeScene("removepokemon.fxml");
        int num;
        for (int x=0; x<pokemonDeck.getSize();x++){
            num=x+1;
            System.out.println("["+num+"]");
            System.out.println(pokemonDeck.getPokemon(x).getName());
        }
    }
    public void removePokemonV2(ActionEvent e)throws IOException{
        String pokemonIndex = removePokemonIndex.getText();
        pokemonDeck.remove(Integer.parseInt(pokemonIndex)-1);
        System.out.println(pokemonIndex);
        HelloApplication app = new HelloApplication();
        app.changeScene("pokemonMenuStage.fxml");
    }
    public void viewOnePokemon(ActionEvent e) throws IOException {
        System.out.println("View One Pokemon");
        int random = rgen.nextInt(0, pokemonDeck.getSize() - 1);
        System.out.println("FROM HELLOCONTROLLER -> "+pokemonDeck.getPokemon(random));
        showPokemon(pokemonDeck.getPokemon(random));
    }
    public void slideShow(ActionEvent e) {
        System.out.println("View Slide Show Pokemon");
        //TODO: PAUSE TRANSITION
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            showPokemon(pokemonDeck.getPokemon(i++));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                if (i>=pokemonDeck.getSize()-1){
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 0,2000);
    }


    public void searchPokemon(ActionEvent e) throws IOException {
        System.out.println("Search for Pokemon");
        HelloApplication app = new HelloApplication();
        app.changeScene("searchpokemon.fxml");
        //TODO: MAKE THE FXML FILE NAME TO BE THE POKEMON NAME (Example: Mewtwo)
    }
    public void searchPokemonV2(ActionEvent e) throws Exception{
        String name = searchPokemon.getText();
        int index = pokemonDeck.searchPokemon(name);
        if (index != -1){
            System.out.println("Pokemon "+  name + " is found!");
            showPokemon(pokemonDeck.getPokemon(index));
        }
        else {
            pokemonNameNotFound.setText("Sorry:  "+  name + " is not found in the database!");
            System.out.println("Sorry:  "+  name + " is not found in the database!");
        }
    }
    public void exitProgram(ActionEvent e){
        Platform.exit();
    }
    public void menuProgram(ActionEvent e) throws IOException {
        String menu = "pokemonMenuStage.fxml"; //<---- LIKE THIS BUT pokemonDeck.getName(index)
        HelloApplication app = new HelloApplication();
        app.changeScene(menu);
    }
    //TODO DELETE AND PUT IN CANVAS CLASS IF IT DOESN'T WORK
    public void showPokemon(Pokemon pokemon) throws IOException {
        System.out.println("showPokemon");
        if(pokemon.getType().contains("/")){
            showDoubleType(pokemon);
        } else{
            showSingleType(pokemon);
        }
    }
    private void showDoubleType(Pokemon pokemon) throws IOException { //TODO: DOUBLE TYPE
        HelloApplication app = new HelloApplication();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DoubleType.fxml"));
        app.getStage().getScene().setRoot(loader.load());
        PersonController controller = loader.getController();
        controller.setName(pokemon.getName());
        controller.setWeight("Weight: "+pokemon.getStats()[0]);
        controller.setHeight("Height: "+pokemon.getStats()[1]);
        String[] newType = new String[0];
        for (int i = 0; i < 3; i++) {
            newType = pokemon.getType().split("/");
        }
        controller.setType(newType[0]);
        controller.setDoubleType(newType[1]);
        controller.setSprite(pokemon.getSprite());
        controller.setBackground(pokemon.getBackground());
        controller.setRecAttack(pokemon.getStats()[2]); //attack, defense, stamina
        controller.setRecDefense(pokemon.getStats()[3]);
        controller.setRecStamina(pokemon.getStats()[4]);
    }
    private void showSingleType(Pokemon pokemon) throws IOException {
        HelloApplication app = new HelloApplication();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SingleType.fxml"));
        app.getStage().getScene().setRoot(loader.load());
        PersonController controller = loader.getController();
        controller.setName(pokemon.getName());
        controller.setWeight("Weight: "+pokemon.getStats()[0]);
        controller.setHeight("Height: "+pokemon.getStats()[1]);
        controller.setType(pokemon.getType());
        controller.setSprite(pokemon.getSprite());
        controller.setBackground(pokemon.getBackground());
        controller.setRecAttack(pokemon.getStats()[2]); //attack, defense, stamina
        controller.setRecDefense(pokemon.getStats()[3]);
        controller.setRecStamina(pokemon.getStats()[4]);
    }
}