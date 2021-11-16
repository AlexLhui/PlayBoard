public class Horse {

    enum Etat{Stable, Circuit, Arrived}
    Etat situation;
    public int position;
    Team eqcheval;

    public Horse(int position, Etat situation, Team eqcheval){
        this.position = position;
        this.situation = situation;
        this.eqcheval = eqcheval;
    }

}
