public class Team {

    private int numberOfTeam = 0;
    enum teamColor{Yellow, Blue, Green, Red}
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
