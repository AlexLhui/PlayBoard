import javafx.scene.paint.Color;

public class Team {

    private int numberOfTeam = 0;
    public int player;
    private Horse horse1;
    private Horse horse2;
    private Horse horse3;
    private Horse horse4;
    Color color;

    public int getNumberOfTeam() {
        return numberOfTeam;
    }

    public void setNumberOfTeam(int nb) {
        numberOfTeam = nb;
    }

    public Horse getHorse1(){ return horse1;}
    public Horse getHorse2(){ return horse2;}
    public Horse getHorse3(){ return horse3;}
    public Horse getHorse4(){ return horse4;}

    public void addTeam() {
        setNumberOfTeam(getNumberOfTeam()+1);
    }

    public Team (Color color, Horse horse1, Horse horse2, Horse horse3, Horse horse4) {
        this.color = color;
        addTeam();
        this.horse1 = horse1;
        this.horse2 = horse2;
        this.horse3 = horse3;
        this.horse4 = horse4;
    }

    public void changePosition(int res, int position){
        position = res + position;
        // fonction renvoie les coordonnées en fonction de la position
        // setX et setY de l'image
    }

}
