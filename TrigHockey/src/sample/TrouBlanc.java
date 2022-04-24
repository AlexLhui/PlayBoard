package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TrouBlanc extends Sphere{
    private Vector pos_respawn,v_respawn;

    public TrouBlanc(double x, double y, double radius){
        super(x,y,radius,"img/troublanc.png");
        pos_respawn=new Vector(0,0);
        v_respawn=new Vector(0,0);
    }
    @Override
    public void update(double t, BlackHole blackHole, ArrayList<Player> players, BackGround backGround, ArrayList<But> listBut, TrouBlanc trouBlanc,ArrayList<Ball> balls){
        trouBlanc.update(t,blackHole,backGround);
    }

    public void update(double t,BlackHole blackHole,BackGround backGround){
        pos_respawn.setX(blackHole.getRadius()*5*Math.cos(2*t)+backGround.getX());
        pos_respawn.setY(blackHole.getRadius()*5*Math.sin(2*t)+backGround.getY());
        v_respawn.setX(-10*Math.sin(2*t));
        v_respawn.setY(10*Math.cos(2*t));

        this.setX(pos_respawn.getX());
        this.setY(pos_respawn.getY());
    }

    public Vector getPos_respawn() {
        return pos_respawn;
    }

    public Vector getV_respawn() {
        return v_respawn;
    }
}
