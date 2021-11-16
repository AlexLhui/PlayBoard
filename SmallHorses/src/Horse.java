public class Horse {

    private enum Etat{STABLE, CIRCUIT, ARRIVED}
    Etat situation;
    private int position;
    private int numberHorse; //From 1 to 4
    Team teamHorse;

    public int getNumberHorse(){
        return this.numberHorse;
    }

    public void setPosition(int pos){
        position = pos;
    }

    public void initPosition(Horse chev){
        if(chev.teamHorse.team == Team.teamColor.Blue){
            chev.setPosition(17);
        }
        else if(chev.teamHorse.team == Team.teamColor.Red){
            chev.setPosition(27);
        }
        else if(chev.teamHorse.team == Team.teamColor.Green){
            chev.setPosition(37);
        }
        else if(chev.teamHorse.team == Team.teamColor.Yellow){
            chev.setPosition(47);
        }
    }
    public void beingEaten(Horse chev){
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

    public Horse(int position, Etat situation, Team eqcheval){
        this.position = position;
        this.situation = situation;
        this.teamHorse = eqcheval;
    }

}
