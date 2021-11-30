import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Board extends Scene {

    public final int nbCase = 72;
    private ImageView image;
    ArrayList<Color> couleur;
    ArrayList<Team> team;

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

    public Board(Pane pane, double v, double v1, boolean b, String filename){
        super(pane, v, v1, b);
        Image boardSheet = new Image(filename);
        ImageView board = new ImageView(boardSheet);
        board.setViewport(new Rectangle2D(0,0,600,600));
        pane.getChildren().add(board);
    }

    private static Random numberGenerator = new Random();

    public <T> T randomElement(T[] elements){ // Permet d'obtenir un élément au hasard dans un enum ou tableau
        return elements[numberGenerator.nextInt(elements.length)];
    }

    public void initBoard(int numberOfTeam){
        couleur = new ArrayList<>();
        couleur.add(Color.BLUE);
        couleur.add(Color.RED);
        couleur.add(Color.GREEN);
        couleur.add(Color.YELLOW);

        team = new ArrayList<>();

        for(int i = 0; i<numberOfTeam; i++){
            int a = (int) (Math.random()*3);
            if(i!=0) {
                if(i == 1) {
                    while(team.get(0).color == couleur.get(a)) {
                        a = (int) (Math.random()*3);
                    }
                    Horse horse1 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                    Horse horse2 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                    Horse horse3 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                    Horse horse4 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                    Team teamObj = new Team(couleur.get(a), horse1, horse2, horse3, horse4);
                    team.add(teamObj);
                    teamObj.setNumberOfTeam(numberOfTeam);
                }
                if(i == 2){
                    while((team.get(0).color == couleur.get(a)) && (team.get(1).color == couleur.get(a))){
                        a = (int) (Math.random()*3);
                    }
                    Horse horse1 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                    Horse horse2 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                    Horse horse3 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                    Horse horse4 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                    Team teamObj = new Team(couleur.get(a), horse1, horse2, horse3, horse4);
                    team.add(teamObj);
                    teamObj.setNumberOfTeam(numberOfTeam);
                }
                if(i == 3){
                    while((team.get(0).color == couleur.get(a)) && (team.get(1).color == couleur.get(a)) && (team.get(2).color == couleur.get(a))){
                        a = (int) (Math.random()*3);
                    }
                    Horse horse1 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                    Horse horse2 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                    Horse horse3 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                    Horse horse4 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                    Team teamObj = new Team(couleur.get(a), horse1, horse2, horse3, horse4);
                    team.add(teamObj);
                    teamObj.setNumberOfTeam(numberOfTeam);
                }
            }
            else{
                Horse horse1 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                Horse horse2 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                Horse horse3 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                Horse horse4 = new Horse(1, Horse.Etat.STABLE, "file:SmallHorsesTransparent.png",a);
                Team teamObj = new Team(couleur.get(a),horse1,horse2,horse3,horse4);
                team.add(teamObj);
                teamObj.setNumberOfTeam(numberOfTeam);
            }
        } // Code du dessus attribue des couleurs au hasard aux joueurs

        for(int j = 0; j<numberOfTeam; j++){
            if(team.get(j).color == Color.BLUE){
                team.get(j).getHorse1().setPosition(1);
                team.get(j).getHorse1().setNumberHorse(1);
                team.get(j).getHorse1().setColor(Color.BLUE);
            //    team.get(j).getHorse1().setImageok("Little_blue_horse_sized.png");
                team.get(j).getHorse1().getImage().setX(46);
                team.get(j).getHorse1().getImage().setY(525);
                team.get(j).getHorse2().setPosition(2);
                team.get(j).getHorse2().setNumberHorse(2);
                team.get(j).getHorse2().setColor(Color.BLUE);
            //    team.get(j).getHorse2().setImageok("Little_blue_horse_sized.png");
                team.get(j).getHorse2().getImage().setX(95);
                team.get(j).getHorse2().getImage().setY(525);
                team.get(j).getHorse3().setPosition(3);
                team.get(j).getHorse3().setNumberHorse(3);
                team.get(j).getHorse3().setColor(Color.BLUE);
            //    team.get(j).getHorse3().setImageok("Little_blue_horse_sized.png");
                team.get(j).getHorse3().getImage().setX(95);
                team.get(j).getHorse3().getImage().setY(476);
                team.get(j).getHorse4().setPosition(4);
                team.get(j).getHorse4().setNumberHorse(4);
                team.get(j).getHorse4().setColor(Color.BLUE);
            //    team.get(j).getHorse4().setImageok("Little_blue_horse_sized.png");
                team.get(j).getHorse4().getImage().setX(46);
                team.get(j).getHorse4().getImage().setY(476);
            }
            else if(team.get(j).color == Color.RED){
                team.get(j).getHorse1().setPosition(5);
                team.get(j).getHorse1().setNumberHorse(1);
                team.get(j).getHorse1().setColor(Color.RED);
            //    team.get(j).getHorse1().setImageok("Little_red_horse_sized.png");
                team.get(j).getHorse1().getImage().setX(46);
                team.get(j).getHorse1().getImage().setY(84);
                team.get(j).getHorse2().setPosition(6);
                team.get(j).getHorse2().setNumberHorse(2);
                team.get(j).getHorse2().setColor(Color.RED);
            //    team.get(j).getHorse2().setImageok("Little_red_horse_sized.png");
                team.get(j).getHorse2().getImage().setX(95);
                team.get(j).getHorse2().getImage().setY(84);
                team.get(j).getHorse3().setPosition(7);
                team.get(j).getHorse3().setNumberHorse(3);
                team.get(j).getHorse3().setColor(Color.RED);
            //    team.get(j).getHorse3().setImageok("Little_red_horse_sized.png");
                team.get(j).getHorse3().getImage().setX(95);
                team.get(j).getHorse3().getImage().setY(35);
                team.get(j).getHorse4().setPosition(8);
                team.get(j).getHorse4().setNumberHorse(4);
                team.get(j).getHorse4().setColor(Color.RED);
            //    team.get(j).getHorse4().setImageok("Little_red_horse_sized.png");
                team.get(j).getHorse4().getImage().setX(46);
                team.get(j).getHorse4().getImage().setY(35);
            }
            else if(team.get(j).color == Color.GREEN){
                team.get(j).getHorse1().setPosition(9);
                team.get(j).getHorse1().setNumberHorse(1);
                team.get(j).getHorse1().setColor(Color.GREEN);
            //    team.get(j).getHorse1().setImageok("Little_green_horse_sized.png");
                team.get(j).getHorse1().getImage().setX(487);
                team.get(j).getHorse1().getImage().setY(84);
                team.get(j).getHorse2().setPosition(10);
                team.get(j).getHorse2().setNumberHorse(2);
                team.get(j).getHorse2().setColor(Color.GREEN);
            //    team.get(j).getHorse2().setImageok("Little_green_horse_sized.png");
                team.get(j).getHorse2().getImage().setX(536);
                team.get(j).getHorse2().getImage().setY(84);
                team.get(j).getHorse3().setPosition(11);
                team.get(j).getHorse3().setNumberHorse(3);
                team.get(j).getHorse3().setColor(Color.GREEN);
            //    team.get(j).getHorse3().setImageok("Little_green_horse_sized.png");
                team.get(j).getHorse3().getImage().setX(536);
                team.get(j).getHorse3().getImage().setY(35);
                team.get(j).getHorse4().setPosition(12);
                team.get(j).getHorse4().setNumberHorse(4);
                team.get(j).getHorse4().setColor(Color.GREEN);
            //    team.get(j).getHorse4().setImageok("Little_green_horse_sized.png");
                team.get(j).getHorse4().getImage().setX(487);
                team.get(j).getHorse4().getImage().setY(35);
            }
            else if(team.get(j).color == Color.YELLOW){
                team.get(j).getHorse1().setPosition(13);
                team.get(j).getHorse1().setNumberHorse(1);
                team.get(j).getHorse1().setColor(Color.YELLOW);
            //    team.get(j).getHorse1().setImageok("Little_yellow_horse_sized.png");
                team.get(j).getHorse1().getImage().setX(487);
                team.get(j).getHorse1().getImage().setY(525);
                team.get(j).getHorse2().setPosition(14);
                team.get(j).getHorse2().setNumberHorse(2);
                team.get(j).getHorse2().setColor(Color.YELLOW);
            //    team.get(j).getHorse2().setImageok("Little_yellow_horse_sized.png");
                team.get(j).getHorse2().getImage().setX(536);
                team.get(j).getHorse2().getImage().setY(525);
                team.get(j).getHorse3().setPosition(15);
                team.get(j).getHorse3().setNumberHorse(3);
                team.get(j).getHorse3().setColor(Color.YELLOW);
            //    team.get(j).getHorse3().setImageok("Little_yellow_horse_sized.png");
                team.get(j).getHorse3().getImage().setX(536);
                team.get(j).getHorse3().getImage().setY(476);
                team.get(j).getHorse4().setPosition(16);
                team.get(j).getHorse4().setNumberHorse(4);
                team.get(j).getHorse4().setColor(Color.YELLOW);
            //    team.get(j).getHorse4().setImageok("Little_yellow_horse_sized.png");
                team.get(j).getHorse4().getImage().setX(487);
                team.get(j).getHorse4().getImage().setY(476);
            }
        }
        //Code du dessus attribue les positions initiales aux chevaux et leur numéro de série
    }


    public void update(int tour){

    }
}