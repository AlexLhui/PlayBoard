package sample;


import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BackGround extends Sphere{


    public BackGround(double x, double y, double radius){
        super(x,y,radius,"img/background.png");
    }

    @Override
    public void update(double t, BlackHole blackHole, ArrayList<Player> players, BackGround backGround, ArrayList<But> listBut, TrouBlanc trouBlanc,ArrayList<Ball> balls) {
        this.update();
    }
    public void update(){

    }
}
