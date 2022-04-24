package sample;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BlackHole extends Sphere{

    private double m;
    private boolean on=true;


    public BlackHole(double x,double y,double radius,double m){
        super(x,y,radius,"img/trounoir.png");
        this.m=m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public double getM() {
        return m;
    }
    public void disparait(){
        setM(0);
        setOn(false);
        getImageView().setImage(new Image("img/Vide.png"));

    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    @Override
    public void update(double t, BlackHole blackHole, ArrayList<Player> players, BackGround backGround, ArrayList<But> listBut, TrouBlanc trouBlanc,ArrayList<Ball> balls) {
        this.update();
    }
    public void update(){

    }
}
