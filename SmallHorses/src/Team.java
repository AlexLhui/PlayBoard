public class Team {

    private int numberOfTeam = 0;
    enum teamColor{Yellow, Blue, Green, Red} //Blue case 17 Red case 28 Green case 39 Yellow case 50
    public teamColor team;
    private Horse horse1;
    private Horse horse2;
    private Horse horse3;
    private Horse horse4;

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

    public Team (teamColor color) {
        this.team = color;
        addTeam();
    }

}
