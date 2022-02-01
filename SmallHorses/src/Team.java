import javafx.scene.paint.Color;

public class Team {

    private int numberOfTeam = 0;
    public int player;
    private Horse horse1;
    private Horse horse2;
    private Horse horse3;
    private Horse horse4;
    Color color;
    private int positionDepart;

    public int getPositionDepart(){return positionDepart;}

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
        if(color == Color.BLUE){
            this.positionDepart = 17;
        }
        else if(color == Color.RED){
            this.positionDepart = 27;
        }
        else if(color == Color.GREEN){
            this.positionDepart = 37;
        }
        else if(color == Color.YELLOW){
            this.positionDepart = 47;
        }
    }

}
