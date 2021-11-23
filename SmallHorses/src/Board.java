import javafx.scene.image.ImageView;

import java.util.Random;

public class Board {

    public final int nbCase = 72;
    private ImageView image;

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
        for(int j = 0; j<numberOfTeam; j++){
            if(team[j].team == Team.teamColor.Blue){
                team[j].getHorse1().setPosition(1);
                team[j].getHorse1().setNumberHorse(1);
                team[j].getHorse1().getImage().setX(46);
                team[j].getHorse1().getImage().setY(525);
                team[j].getHorse2().setPosition(2);
                team[j].getHorse2().setNumberHorse(2);
                team[j].getHorse2().getImage().setX(95);
                team[j].getHorse2().getImage().setY(525);
                team[j].getHorse3().setPosition(3);
                team[j].getHorse3().setNumberHorse(3);
                team[j].getHorse3().getImage().setX(95);
                team[j].getHorse3().getImage().setY(476);
                team[j].getHorse4().setPosition(4);
                team[j].getHorse4().setNumberHorse(4);
                team[j].getHorse4().getImage().setX(46);
                team[j].getHorse4().getImage().setY(476);
            }
            else if(team[j].team == Team.teamColor.Red){
                team[j].getHorse1().setPosition(5);
                team[j].getHorse1().setNumberHorse(1);
                team[j].getHorse1().getImage().setX(46);
                team[j].getHorse1().getImage().setY(84);
                team[j].getHorse2().setPosition(6);
                team[j].getHorse2().setNumberHorse(2);
                team[j].getHorse2().getImage().setX(95);
                team[j].getHorse2().getImage().setY(84);
                team[j].getHorse3().setPosition(7);
                team[j].getHorse3().setNumberHorse(3);
                team[j].getHorse3().getImage().setX(95);
                team[j].getHorse3().getImage().setY(35);
                team[j].getHorse4().setPosition(8);
                team[j].getHorse4().setNumberHorse(4);
                team[j].getHorse4().getImage().setX(46);
                team[j].getHorse4().getImage().setY(35);
            }
            else if(team[j].team == Team.teamColor.Green){
                team[j].getHorse1().setPosition(9);
                team[j].getHorse1().setNumberHorse(1);
                team[j].getHorse1().getImage().setX(487);
                team[j].getHorse1().getImage().setY(84);
                team[j].getHorse2().setPosition(10);
                team[j].getHorse2().setNumberHorse(2);
                team[j].getHorse2().getImage().setX(536);
                team[j].getHorse2().getImage().setY(84);
                team[j].getHorse3().setPosition(11);
                team[j].getHorse3().setNumberHorse(3);
                team[j].getHorse3().getImage().setX(536);
                team[j].getHorse3().getImage().setY(35);
                team[j].getHorse4().setPosition(12);
                team[j].getHorse4().setNumberHorse(4);
                team[j].getHorse4().getImage().setX(487);
                team[j].getHorse4().getImage().setY(35);
            }
            else if(team[j].team == Team.teamColor.Yellow){
                team[j].getHorse1().setPosition(13);
                team[j].getHorse1().setNumberHorse(1);
                team[j].getHorse1().getImage().setX(487);
                team[j].getHorse1().getImage().setY(525);
                team[j].getHorse2().setPosition(14);
                team[j].getHorse2().setNumberHorse(2);
                team[j].getHorse2().getImage().setX(536);
                team[j].getHorse2().getImage().setY(525);
                team[j].getHorse3().setPosition(15);
                team[j].getHorse3().setNumberHorse(3);
                team[j].getHorse3().getImage().setX(536);
                team[j].getHorse3().getImage().setY(476);
                team[j].getHorse4().setPosition(16);
                team[j].getHorse4().setNumberHorse(4);
                team[j].getHorse4().getImage().setX(487);
                team[j].getHorse4().getImage().setY(476);
            }
        }
        //Code du dessus attribue les positions initiales aux chevaux et leur numéro de série
    }

    public void update(int tour){

    }
}