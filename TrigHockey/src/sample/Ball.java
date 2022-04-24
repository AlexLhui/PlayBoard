package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.Boolean.*;

import sample.Main.*;

public class Ball extends Sphere{

    private final double G = 1;
    private double m ;

    private Vector pos=new Vector(this.getX(),this.getY());
    private Vector v,a,f;




    private final double v_max=25;
    private final double beaucoup_trop_loin=1000;

    private boolean goal=false;


/*
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double widht = screenSize.getWidth();
    private double height = screenSize.getHeight();
    private final double unpeu=100;
    public final double scene_sizeX=widht-unpeu;
    public final double scene_sizeY=height-unpeu;


 */
    private boolean bouncing_exte=FALSE;

    public Ball(double  x, double y,double radius,double m){
        super(x,y,radius,"img/MeteoriteUnPeuDeFeu.png");
        this.m=m;
        this.v=new Vector(0,0);
        this.a=new Vector(0,0);
    }

    public Vector getV() {
        return v;
    }

    public void setV(Vector v) {
        this.v = v;
    }

    @Override
    public void update(double t, BlackHole blackHole, ArrayList<Player> players, BackGround backGround,ArrayList<But> listBut,TrouBlanc trouBlanc,ArrayList<Ball> balls) {
        update(t,blackHole, players,backGround, listBut,trouBlanc);
    }

    public void update(double t, BlackHole blackHole, ArrayList<Player> players, BackGround backGround,ArrayList<But> listBut,TrouBlanc trouBlanc) {

        //gravité
        Vector d_blackhole=new Vector(blackHole.getX()-pos.getX(), blackHole.getY()-pos.getY());
        double F=(G*m*blackHole.getM())/(d_blackhole.getNorm()* d_blackhole.getNorm());
        a = new Vector(F*Math.cos(d_blackhole.getAngle0()),F*Math.sin(d_blackhole.getAngle0()));


        //rebond sphère extérieur
        Vector d_background=new Vector(this.getX()- backGround.getX(),this.getY()- backGround.getY());

        if (d_background.getNorm()>=backGround.getRadius()-this.getRadius() && !bouncing_exte){
            Vector nd_backgrond=d_background.normalize();
            double prodscalaire=nd_backgrond.scalarProduct(v);
            Vector term1=nd_backgrond.multi(-2*prodscalaire);
            v=term1.sum(v);
            bouncing_exte=TRUE;

            for (But but:listBut){
                if (but.isItAGoal(this,backGround)){
                    but.goal();
                    goal=true;
                }
            }
        }
        if (!(d_background.getNorm()>=backGround.getRadius()-this.getRadius())){
            bouncing_exte=FALSE;
        }

        //équation horaires
        if (v.getNorm()>v_max){
            v.setNorm(v_max);
        }
        v=v.sum(a);
        pos=pos.sum(v);
        if (((Math.abs(pos.getX()-blackHole.getX())<blackHole.getRadius() && Math.abs(pos.getY()-blackHole.getY())<blackHole.getRadius()) && blackHole.isOn())||goal){
            pos.setX(trouBlanc.getPos_respawn().getX());
            pos.setY(trouBlanc.getPos_respawn().getY());
            v.setX(trouBlanc.getV_respawn().getX());
            v.setY(trouBlanc.getV_respawn().getY());
            goal=false;
        }
        this.setX(pos.getX());
        this.setY(pos.getY());

    }

}
