package com.example.javafxpokemon;

import javafx.scene.image.Image;

public class Cascoon extends Pokemon{
    private String weight;
    private String height;
    private String attack;
    private String defense;
    private String stamina;
    private String type;
    private String name;
    private Image sprite = new Image("file:\\C:\\Users\\Kenan\\Documents\\DLSU Files\\3rd Term\\LBYCPEI\\JavaFXPokemon\\src\\main\\resources\\Pokemon Images\\CascoonPic.png");
    private Image background = new Image("file:\\C:\\Users\\Kenan\\Documents\\DLSU Files\\3rd Term\\LBYCPEI\\JavaFXPokemon\\src\\main\\resources\\Pokemon Images\\CascoonBackground.jpg");
    public Cascoon(){

    }
    public Cascoon(String name, String weight, String height, String attack, String defense, String stamina, String type){
        this.name=name;
        this.type=type;
        this.stamina=stamina;
        this.attack=attack;
        this.weight=weight;
        this.height=height;
        this.defense=defense;
    }
    public String toString(){
        return "Pokemon "+name+" with the type "+type+" has stats of "+stamina+" "+attack+" "+weight+" "+height+" "+defense+" ";
    }
    @Override
    public String[] getStats() {
        return new String[]{weight, height, attack, defense, stamina};
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Image getBackground() {
        return background;
    }

    @Override
    public Image getSprite() {
        return sprite;
    }
}
