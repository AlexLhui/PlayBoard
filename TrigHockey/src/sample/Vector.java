package sample;

public class Vector {
    private double x;
    private double y;
    private double angle0;
    private double norm;

    public Vector(double x,double y){
        this.x=x;
        this.y=y;
        this.angle0=Math.atan2(y,x);
        this.norm=Math.sqrt(x*x+y*y);
    }

    public void setX(double x) {
        this.x = x;
        this.angle0=Math.atan2(y,x);
        this.norm=Math.sqrt(x*x+y*y);
    }
    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
        this.angle0=Math.atan2(y,x);
        this.norm=Math.sqrt(x*x+y*y);
    }
    public double getY() {
        return y;
    }

    public void setAngle0(double angle0) {
        this.angle0=angle0;
        this.x=this.norm*Math.cos(angle0);
        this.y=this.norm*Math.sin(angle0);
    }
    public double getAngle0() {
        return angle0;
    }

    public void setNorm(double norm){
        this.x=x*norm/this.norm;
        this.y=y*norm/this.norm;
        this.norm=norm;
    }
    public double getNorm() {
        return norm;
    }

    public Vector normalize(){
        return new Vector(x/norm,y/norm);
    }

    public Vector sum(Vector vector){
        return new Vector(x+vector.getX(),y+vector.getY());
    }
    public Vector multi(double lambda){
        return new Vector(x*lambda,y*lambda);
    }
    public double scalarProduct(Vector vector){
        return x*vector.getX()+y*vector.getY();
    }
    public double relativeAngle(Vector vector){
        return angle0-vector.getAngle0();
    }
    public String toString(){
        return "x: "+x+"\ty: "+y+"\tnorm: "+norm+"\tangle0: "+angle0;
    }

}
