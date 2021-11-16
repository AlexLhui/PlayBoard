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

    public void addTeam() {
        setNumberOfTeam(getNumberOfTeam()+1);
    }

    public Team (teamColor color) { //Ajouter position 1 2 3 4 en argument
        this.team = color;
        addTeam();
    }

}
