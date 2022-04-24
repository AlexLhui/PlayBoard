package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public abstract class Sphere {
    private double x;
    private double y;
    private final double radius;
    private Image image;
    private ImageView imageView;

    public Sphere(double x,double y,double radius,String fileName){
        this.x=x;
        this.y=y;
        this.radius=radius;
        this.image=new Image(fileName,2*radius,2*radius,false,false);
        this.imageView=new ImageView(this.image);
    }
    public void draw(){
        imageView.setX(getX()-getRadius());
        imageView.setY(getY()-getRadius());
    }
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public Image getImage() {
        return image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public abstract void update(double t, BlackHole blackHole, ArrayList<Player> players, BackGround backGround, ArrayList<But> listBut, TrouBlanc trouBlanc,ArrayList<Ball> balls);
}
