import java.util.Random;

public class Board {

    public final int nbCase = 72;

    /*
     * Squares 1 to 4 : Blue stable
     * Squares 5 to 8 : Red stable
     * Squares 9 to 12 : Green stable
     * Squares 13 to 16 : Yellow stable
     * Squares 17 to 56 : Game
     * Squares 57 to 60 : Final arrival for blue team
     * Squares 61 to 64 : Final arrival for red team
     * Squares 65 to 68 : Final arrival for green team
     * Squares 70 to 72 : Final arrival for yellow team
     */

    private static Random numberGenerator = new Random();

    public <T> T randomElement(T[] elements){ // Permet d'obtenir un élément au hasard dans un enum ou tableau
        return elements[numberGenerator.nextInt(elements.length)];
    }

    public void initBoard(int numberOfTeam){
        Team.teamColor[] tabCol = Team.teamColor.values();
        Team[] team = new Team[numberOfTeam-1];
        for(int i = 0; i<numberOfTeam; i++){
            Team.teamColor randColor = randomElement(tabCol);
            if(i==1){
                while(randColor == team[i-1].team){
                    randColor = randomElement(tabCol);
                }
            }
            if(i==2){
                while((randColor == team[i-1].team) || (randColor == team[i-2].team)){
                    randColor = randomElement(tabCol);
                }
            }
            if(i==3){
                while((randColor == team[i-1].team) || (randColor == team[i-2].team) || (randColor == team[i-3].team)){
                    randColor = randomElement(tabCol);
                }
            }
            team[i] = new Team(randColor);
            team[i].setNumberOfTeam(numberOfTeam);
        } // Code du dessus attribue des couleurs au hasard aux joueurs
        //Manque initialisation des chevaux de team

    }
}