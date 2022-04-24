package sample;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static java.lang.Boolean.*;

public class Player extends Sphere{
    private final ArrayList<String> controls;
    public boolean rebound;
    private Vector pos= new Vector(getX(),getY());
    private Vector old_pos=new Vector(0,0);
    private Vector old_pos2=new Vector(0,0);
    private Vector v,a,f;
    private Vector old_v = new Vector(0,0);
    private final double frot=0.2;
    private double m=20;
    private final double frac_contact=10;
    public static int playerCount = 0;
    private int playerIndex = 0;
    public int objectId;
    public static boolean removed;

    public Player(double x,double y,double radius,String fileName,ArrayList<String> controls,double m){
        super(x,y,radius,fileName);
        this.m=m;
        this.controls=controls;
        removed = false;
        playerIndex = playerCount;
        playerCount+=1;
        /*
        controls.get(0) = haut
        controls.get(1) = gauche
        controls.get(2) = bas
        controls.get(3) = droite
         */
        this.rebound=FALSE;
        v=new Vector(0,0);
        a=new Vector(0,0);
        f=new Vector(0,0);
    }
    public Player(double x,double y,double radius,ArrayList<String> controls){
        super(x,y,radius,"img/Vide.png");
        this.controls=controls;
        playerIndex = playerCount;
        playerCount+=1;
        removed = false;
        /*
        controls.get(0) = haut
        controls.get(1) = gauche
        controls.get(2) = bas
        controls.get(3) = droite
         */
        this.rebound=FALSE;
        v=new Vector(0,0);
        a=new Vector(0,0);
        f=new Vector(0,0);
    }
    public Player(double x,double y,double radius,String fileName,double m,int id){
        super(x,y,radius,fileName);
        this.controls = null;
        this.objectId = id;
        this.m=m;
        removed = false;
        playerIndex = playerCount;
        playerCount+=1;
        this.rebound=FALSE;
        v=new Vector(0,0);
        a=new Vector(0,0);
        f=new Vector(0,0);
    }
    public ArrayList<String> getControls() {
        return controls;
    }

    public void push(double x,double y){
        f.setX(x);
        f.setY(y);
    }
    public void stop(){
        f= new Vector(0,0);
    }

    public Vector getA() {
        return a;
    }

    public Vector getV() {
        return v;
    }

    public Vector getPos() {
        return pos;
    }

    public void setPos(Vector pos) {
        this.pos = pos;
    }

    public void setOld_pos(Vector old_pos) {
        this.old_pos = old_pos;
    }

    @Override
    public void update(double t, BlackHole blackHole, ArrayList<Player> players, BackGround backGround, ArrayList<But> listBut, TrouBlanc trouBlanc,ArrayList<Ball> balls){
        this.update();
        for (Ball ball:balls){
            Vector d_player=new Vector(ball.getX()-this.getX(),ball.getY()- this.getY());
            double test=d_player.getNorm()-(ball.getRadius()+ this.getRadius());
            if (test<=0 && this.rebound==FALSE){
                this.rebound=TRUE;
                Vector nd_player =d_player.normalize();
                double prodscalaire=nd_player.scalarProduct(ball.getV());
                Vector term1=nd_player.multi(-2*prodscalaire);
                ball.setV(term1.sum(ball.getV()));
                ball.getV().setNorm(ball.getV().getNorm()/frac_contact);
                Vector player_speed=new Vector(this.getV().getX(), this.getV().getY());
                player_speed=nd_player.multi(player_speed.scalarProduct(nd_player));
                ball.setV(ball.getV().sum(player_speed));

            }
            if (test>0){
                this.rebound=FALSE;
            }
        }
    }

    public void update(){
        old_v=v;
        v=pos.sum(old_pos.multi(-1));
        a=v.sum(old_v.multi(-1));

        int pos = ForTuio.posInObjList(objectId);
        if (pos!=-1)
        {

            float x = ForTuio.objList.get(pos).getX() * (float) Main.width;
            float y = ForTuio.objList.get(pos).getY() * (float) Main.height;
            this.setPos(new Vector(x, y));
            //System.out.println(x + " & " + y + playerCount);
            this.setX(x);
            this.setY(y);
        }

    }

    public static Player FindPlayerWithObjectId(int objectId, ArrayList<Player> playerList)
    {
        for (var player: playerList)
        {
            if (objectId == player.objectId)
            {
                return player;
            }
        }
        return null;
    }

    public void disparait() {
        this.getImageView().setImage(new Image("img/Vide.png"));
    }
}
