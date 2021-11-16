public class Team {

    private int numberOfTeam = 0;
    enum teamColor{Yellow, Blue, Green, Red} //Blue case 17 Red case 28 Green case 39 Yellow case 50
    public teamColor team;

    public int getNumberOfTeam() {
        return numberOfTeam;
    }

    public void setNumberOfTeam(int nb) {
        numberOfTeam = nb;
    }

    public void addTeam() {
        setNumberOfTeam(getNumberOfTeam()+1);
    }

    public Team (teamColor color) {
        this.team = color;
        addTeam();
    }

}
