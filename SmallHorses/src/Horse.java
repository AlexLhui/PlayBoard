import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Horse {

    private enum Etat{STABLE, CIRCUIT, ARRIVED}
    Etat situation;
    private int position;
    private int numberHorse; //From 1 to 4
    Team teamHorse;
    private ImageView image;
    int sizeX;
    int sizeY;

    public int getNumberHorse(){
        return this.numberHorse;
    }
    public int getPosition(){
        return this.position;
    }
    public ImageView getImage(){
        return image;
    }
    public void setNumberHorse(int numbHorse){numberHorse = numbHorse;}

    public void setPosition(int pos){
        position = pos;
    }



    public void initPosition(Horse chev){
        if(chev.teamHorse.team == Team.teamColor.Blue){
            chev.setPosition(17);
            chev.getImage().setX(245);
            chev.getImage().setY(528);
        }
        else if(chev.teamHorse.team == Team.teamColor.Red){
            chev.setPosition(27);
            chev.getImage().setX(49);
            chev.getImage().setY(234);
        }
        else if(chev.teamHorse.team == Team.teamColor.Green){
            chev.setPosition(37);
            chev.getImage().setX(343);
            chev.getImage().setY(38);
        }
        else if(chev.teamHorse.team == Team.teamColor.Yellow){
            chev.setPosition(47);
            chev.getImage().setX(539);
            chev.getImage().setY(332);
        }
    }
    public void beenEaten(Horse chev){
        if(chev.teamHorse.team == Team.teamColor.Blue){
            chev.setPosition(chev.getNumberHorse());
        }
        else if(chev.teamHorse.team == Team.teamColor.Red){
            chev.setPosition(chev.getNumberHorse()+4);
        }
        else if(chev.teamHorse.team == Team.teamColor.Green){
            chev.setPosition(chev.getNumberHorse()+8);
        }
        else if(chev.teamHorse.team == Team.teamColor.Yellow){
            chev.setPosition(chev.getNumberHorse()+12);
        }
    }

    public Horse(int position, Etat situation, Team eqcheval, String filename){
        this.position = position;
        this.situation = situation;
        this.teamHorse = eqcheval;
        this.image = new ImageView(new Image(filename));
        this.image.setViewport(new Rectangle2D(0, 0, sizeX, sizeY));
    }

}
