package sample;


import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class But extends Sphere{

    public final double angle_but_deg=30.308;
    public final double angle_but_rad=angle_but_deg*Math.PI/180;

    public double angle_deg;
    public double angle_rad=angle_deg*Math.PI/180;

    private double eps=1e-5;

    private final ImageView imageView=new ImageView(this.getImage());
    private final int nbBut;
    public int nbBut_Pris=0;
    public static int nbBut_Pris_tot=0;




    public But(double x, double y, double radius,int nbBut) {
        super(x,y,radius,"img/BUTS_2.png");
        this.nbBut=nbBut;

    }
    public void setAngle(double angle_deg){
        this.angle_deg=angle_deg;
        angle_rad=angle_deg*Math.PI/180;
    }


    public void draw() {
        imageView.setX(getX());
        imageView.setY(getY());
        imageView.setRotate(angle_deg);
    }

    @Override
    public String toString() {
        return getX()+"  "+getY();
    }

    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public void update(double t, BlackHole blackHole, ArrayList<Player> players, BackGround backGround, ArrayList<But> listBut, TrouBlanc trouBlanc,ArrayList<Ball> balls) {
        this.update();
    }
    public void update(){

    }

    public double getAngle_but_rad() {
        return angle_but_rad;
    }

    public double getAngle_rad() {
        return angle_rad;
    }

    public int getNbBut() {
        return nbBut;
    }

    public int getNbBut_Pris() {
        return nbBut_Pris;
    }

    public void setNbBut_Pris(int nbBut_Pris) {
        this.nbBut_Pris = nbBut_Pris;
    }
    public boolean isItAGoal(Ball ball,BackGround backGround){
        Vector pos=new Vector(ball.getX()-backGround.getX(),ball.getY()-backGround.getY());
        double le_test=this.getAngle_rad()-pos.getAngle0();

        while (le_test+eps>=0) {
            le_test -= 2*Math.PI;
        }
        while (le_test<0){
            le_test+=2*Math.PI;
        }
        return le_test < angle_but_rad / 2 || 2 * Math.PI - le_test < angle_but_rad / 2;
    }
    public void goal(){
        this.nbBut_Pris++;
        nbBut_Pris_tot++;
    }
}
