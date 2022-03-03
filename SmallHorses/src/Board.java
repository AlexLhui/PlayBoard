import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Board extends Scene {

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // getScreenSize() returns the size of the screen in pixels
    int screenWidth = (int)size.getWidth(); // screenWidth will store the width of the screen
    int screenHeight = (int)size.getHeight(); // screenHeight will store the height of the screen

    public final int nbCase = 72;
    private ImageView image;
    ArrayList<Color> couleur;
    ArrayList<Team> team;
    ArrayList<Color> teamList;
    private boolean gameFinished = false;
    Dice dice;
    public String affichage;

    /*
     * Cases 1 à 4 : Blue stable
     * Cases 5 à 8 : Red stable
     * Cases 9 à 12 : Green stable
     * Cases 13 à 16 : Yellow stable
     * Cases 17 à 56 : Game
     * Cases 57 à 60 : Final arrival for blue team
     * Cases 61 à 64 : Final arrival for red team
     * Cases 65 à 68 : Final arrival for green team
     * Cases 70 à 72 : Final arrival for yellow team
     */

    public boolean isGameFinished() {
        return gameFinished;
    }

//    public int largTolarCase(int largeur){
//        this.largCase = (largeur-106)/11;
//        return largCase;
//    }
//
//    public int longTolongCase(int longueur){
//        this.longCase = (longueur-106)/11;
//        return longCase;
//    }

    public Board(Pane pane, double width, double height, boolean b, String filename, int numberOfTeam){
        super(pane, width, height, b);
        Image boardSheet = new Image(filename,height,height,true,true);
        ImageView board = new ImageView(boardSheet);
        board.setViewport(new Rectangle2D(0,0,height,height));
        board.setX((width-height)/2);
        board.setY(0);
        pane.getChildren().add(board);

        couleur = new ArrayList<>();
        couleur.add(Color.BLUE);
        couleur.add(Color.RED);
        couleur.add(Color.GREEN);
        couleur.add(Color.YELLOW);

        team = new ArrayList<>();
        teamList = new ArrayList<>();

        this.dice = new Dice();
        pane.getChildren().add(this.dice.getImage());
        int squareSize = 44 * screenHeight / 600;
        this.dice.getImage().setX((int)(width/2- squareSize/2));
        this.dice.getImage().setY((int)(height/2- squareSize/2));

        for(int i = 0; i<numberOfTeam; i++){
            int a = (int) (Math.random()*4);
            if(i!=0) {
                if(i == 1) { //Player 2
                    a = (int) (Math.random()*4);
                    while (teamList.contains(couleur.get(a))) {
                        a = (int) (Math.random()*4);
                    }
                    Horse horse1 = new Horse(1, Horse.Etat.STABLE, "SmallHorses1Transparent.png",a);
                    Horse horse2 = new Horse(1, Horse.Etat.STABLE, "SmallHorses2Transparent.png",a);
                    Horse horse3 = new Horse(1, Horse.Etat.STABLE, "SmallHorses3Transparent.png",a);
                    Horse horse4 = new Horse(1, Horse.Etat.STABLE, "SmallHorses4Transparent.png",a);
                    Team teamObj = new Team(couleur.get(a), horse1, horse2, horse3, horse4);
                    team.add(teamObj);
                    teamList.add(couleur.get(a));
                    teamObj.setNumberOfTeam(numberOfTeam);
                    System.out.println("Player 2 initialized");
                }
                if(i == 2){ //Player 3
                    a = (int) (Math.random()*4);
                    while (teamList.contains(couleur.get(a))) {
                        a = (int) (Math.random()*4);
                    }
                    Horse horse1 = new Horse(1, Horse.Etat.STABLE, "SmallHorses1Transparent.png",a);
                    Horse horse2 = new Horse(1, Horse.Etat.STABLE, "SmallHorses2Transparent.png",a);
                    Horse horse3 = new Horse(1, Horse.Etat.STABLE, "SmallHorses3Transparent.png",a);
                    Horse horse4 = new Horse(1, Horse.Etat.STABLE, "SmallHorses4Transparent.png",a);
                    Team teamObj = new Team(couleur.get(a), horse1, horse2, horse3, horse4);
                    team.add(teamObj);
                    teamList.add(couleur.get(a));
                    teamObj.setNumberOfTeam(numberOfTeam);
                    System.out.println("Player 3 initialized");
                }
                if(i == 3){ //Player 4
                    a = (int) (Math.random()*4);
                    while (teamList.contains(couleur.get(a))) {
                        a = (int) (Math.random()*4);
                    }
                    Horse horse1 = new Horse(1, Horse.Etat.STABLE, "SmallHorses1Transparent.png",a);
                    Horse horse2 = new Horse(1, Horse.Etat.STABLE, "SmallHorses2Transparent.png",a);
                    Horse horse3 = new Horse(1, Horse.Etat.STABLE, "SmallHorses3Transparent.png",a);
                    Horse horse4 = new Horse(1, Horse.Etat.STABLE, "SmallHorses4Transparent.png",a);
                    Team teamObj = new Team(couleur.get(a), horse1, horse2, horse3, horse4);
                    team.add(teamObj);
                    teamList.add(couleur.get(a));
                    teamObj.setNumberOfTeam(numberOfTeam);
                    System.out.println("Player 4 initialized");
                }
            }
            else{ //Player 1
                Horse horse1 = new Horse(1, Horse.Etat.STABLE, "SmallHorses1Transparent.png",a);
                Horse horse2 = new Horse(1, Horse.Etat.STABLE, "SmallHorses2Transparent.png",a);
                Horse horse3 = new Horse(1, Horse.Etat.STABLE, "SmallHorses3Transparent.png",a);
                Horse horse4 = new Horse(1, Horse.Etat.STABLE, "SmallHorses4Transparent.png",a);
                Team teamObj = new Team(couleur.get(a),horse1,horse2,horse3,horse4);
                team.add(teamObj);
                teamObj.setNumberOfTeam(numberOfTeam);
                teamList.add(couleur.get(a));
                System.out.println("Player 1 initialized");
            }
        } // Code du dessus attribue des couleurs au hasard aux joueurs

        for(int j = 0; j<numberOfTeam; j++){
            Horse chev1 = team.get(j).getHorse1();
            Horse chev2 = team.get(j).getHorse2();
            Horse chev3 = team.get(j).getHorse3();
            Horse chev4 = team.get(j).getHorse4();
            if(team.get(j).color == Color.BLUE){
                chev1.setPosition(1);
                chev1.setNumberHorse(1);
                chev1.setColor(Color.BLUE);
                //    team.get(j).getHorse1().setImageok("Little_blue_horse_sized.png");
                chev1.getImage().setX(chev1.getXPos(1));
                chev1.getImage().setY(chev1.getYPos(1));
                pane.getChildren().add(chev1.getImage());
                chev2.setPosition(2);
                chev2.setNumberHorse(2);
                chev2.setColor(Color.BLUE);
                //    chev2.setImageok("Little_blue_horse_sized.png");
                chev2.getImage().setX(chev2.getXPos(2));
                chev2.getImage().setY(chev2.getYPos(2));
                pane.getChildren().add(chev2.getImage());
                chev3.setPosition(3);
                chev3.setNumberHorse(3);
                chev3.setColor(Color.BLUE);
                //    chev3.setImageok("Little_blue_horse_sized.png");
                chev3.getImage().setX(chev3.getXPos(3));
                chev3.getImage().setY(chev3.getYPos(3));
                pane.getChildren().add(chev3.getImage());
                chev4.setPosition(4);
                chev4.setNumberHorse(4);
                chev4.setColor(Color.BLUE);
                //    chev4.setImageok("Little_blue_horse_sized.png");
                chev4.getImage().setX(chev4.getXPos(4));
                chev4.getImage().setY(chev4.getYPos(4));
                pane.getChildren().add(chev4.getImage());
                System.out.println("Blue");
            }
            else if(team.get(j).color == Color.RED){
                chev1.setPosition(5);
                chev1.setNumberHorse(1);
                chev1.setColor(Color.RED);
                //    chev1.setImageok("Little_red_horse_sized.png");
                //ratioX*leftOffset
                chev1.getImage().setX(chev1.getXPos(5));
                chev1.getImage().setY(chev1.getYPos(5));
                chev1.getImage().setRotate(90);
                pane.getChildren().add(chev1.getImage());
                chev2.setPosition(6);
                chev2.setNumberHorse(2);
                chev2.setColor(Color.RED);
                //    chev2.setImageok("Little_red_horse_sized.png");
                chev2.getImage().setX(chev2.getXPos(6));
                chev2.getImage().setY(chev2.getYPos(6));
                chev2.getImage().setRotate(90);
                pane.getChildren().add(chev2.getImage());
                chev3.setPosition(7);
                chev3.setNumberHorse(3);
                chev3.setColor(Color.RED);
                //    chev3.setImageok("Little_red_horse_sized.png");
                chev3.getImage().setX(chev3.getXPos(7));
                chev3.getImage().setY(chev3.getYPos(7));
                chev3.getImage().setRotate(90);
                pane.getChildren().add(chev3.getImage());
                chev4.setPosition(8);
                chev4.setNumberHorse(4);
                chev4.setColor(Color.RED);
                //    chev4.setImageok("Little_red_horse_sized.png");
                chev4.getImage().setX(chev4.getXPos(8));
                chev4.getImage().setY(chev4.getYPos(8));
                chev4.getImage().setRotate(90);
                pane.getChildren().add(chev4.getImage());
                System.out.println("Red");
            }
            else if(team.get(j).color == Color.GREEN){
                chev1.setPosition(9);
                chev1.setNumberHorse(1);
                chev1.setColor(Color.GREEN);
                //    chev1.setImageok("Little_green_horse_sized.png");
                chev1.getImage().setX(chev1.getXPos(9));
                chev1.getImage().setY(chev1.getYPos(9));
                chev1.getImage().setRotate(180);
                pane.getChildren().add(chev1.getImage());
                chev2.setPosition(10);
                chev2.setNumberHorse(2);
                chev2.setColor(Color.GREEN);
                //    chev2.setImageok("Little_green_horse_sized.png");
                chev2.getImage().setX(chev2.getXPos(10));
                chev2.getImage().setY(chev2.getYPos(10));
                chev2.getImage().setRotate(180);
                pane.getChildren().add(chev2.getImage());
                chev3.setPosition(11);
                chev3.setNumberHorse(3);
                chev3.setColor(Color.GREEN);
                //    chev3.setImageok("Little_green_horse_sized.png");
                chev3.getImage().setX(chev3.getXPos(11));
                chev3.getImage().setY(chev3.getYPos(11));
                chev3.getImage().setRotate(180);
                pane.getChildren().add(chev3.getImage());
                chev4.setPosition(12);
                chev4.setNumberHorse(4);
                chev4.setColor(Color.GREEN);
                //    chev4.setImageok("Little_green_horse_sized.png");
                chev4.getImage().setX(chev4.getXPos(12));
                chev4.getImage().setY(chev4.getYPos(12));
                chev4.getImage().setRotate(180);
                pane.getChildren().add(chev4.getImage());
                System.out.println("Green");
            }
            else if(team.get(j).color == Color.YELLOW){
                chev1.setPosition(13);
                chev1.setNumberHorse(1);
                chev1.setColor(Color.YELLOW);
                //    chev1.setImageok("Little_yellow_horse_sized.png");
                chev1.getImage().setX(chev1.getXPos(13));
                chev1.getImage().setY(chev1.getYPos(13));
                chev1.getImage().setRotate(-90);
                pane.getChildren().add(chev1.getImage());
                chev2.setPosition(14);
                chev2.setNumberHorse(2);
                chev2.setColor(Color.YELLOW);
                //    chev2.setImageok("Little_yellow_horse_sized.png");
                chev2.getImage().setX(chev2.getXPos(14));
                chev2.getImage().setY(chev2.getYPos(14));
                chev2.getImage().setRotate(-90);
                pane.getChildren().add(chev2.getImage());
                chev3.setPosition(15);
                chev3.setNumberHorse(3);
                chev3.setColor(Color.YELLOW);
                //    chev3.setImageok("Little_yellow_horse_sized.png");
                chev3.getImage().setX(chev3.getXPos(15));
                chev3.getImage().setY(chev3.getYPos(15));
                chev3.getImage().setRotate(-90);
                pane.getChildren().add(chev3.getImage());
                chev4.setPosition(16);
                chev4.setNumberHorse(4);
                chev4.setColor(Color.YELLOW);
                //    chev4.setImageok("Little_yellow_horse_sized.png");
                chev4.getImage().setX(chev4.getXPos(16));
                chev4.getImage().setY(chev4.getYPos(16));
                chev4.getImage().setRotate(-90);
                pane.getChildren().add(chev4.getImage());
                System.out.println("Yellow");
            }
        }
        //Code du dessus attribue les positions initiales aux chevaux et leur numéro de série

        this.setOnKeyPressed(keyEvent -> { // A mettre dans le update
            String key = keyEvent.getCode().toString();
            if (key.equals("SPACE")) {
                int res = this.dice.throwDice();
                System.out.println(res);
            }
        });
    }

    private static Random numberGenerator = new Random();

    public <T> T randomElement(T[] elements){ // Permet d'obtenir un élément au hasard dans un enum ou tableau
        return elements[numberGenerator.nextInt(elements.length)];
    }

    public void changePosition(int res, Horse horse, boolean newMove){//Fonction isFilled pour savoir si une case est occupée par un cheval
        if (res != 0 ){
            int pos = horse.getPosition();
            switch(pos) {
                case 1 :
                case 2 :
                case 3 :
                case 4 :
                    if (res == 6) {
                        if (horse.isFilled(team,17)) {
                            Horse disturbingHorse = horse.getHorseFilled(team,17);
                            if (disturbingHorse.color == horse.color) { //If the player already has a horse on start case, nothing happens
                                changePosition(0,horse,false);
                            }
                            else {
                                disturbingHorse.beenEaten(team, disturbingHorse);
                                horse.setPosition(17);
                                horse.situation = Horse.Etat.CIRCUIT;
                                changePosition(0,horse,false);
                                System.out.println("Horse " + disturbingHorse.numberHorse + disturbingHorse.getColor() + " has been eaten by horse " + horse.numberHorse + horse.getColor() + ".");
                            }
                        }
                        else {
                            horse.setPosition(17);
                            horse.situation = Horse.Etat.CIRCUIT;
                            changePosition(0,horse,false);
                        }
                    }
                    break;
                case 5 :
                case 6 :
                case 7 :
                case 8 :
                    if (res == 6) {
                        if (horse.isFilled(team,27)) {
                            Horse disturbingHorse = horse.getHorseFilled(team,27);
                            if (disturbingHorse.color == horse.color) { //If the player already has a horse on start case, nothing happens
                                changePosition(0,horse,false);
                            }
                            else {
                                disturbingHorse.beenEaten(team, disturbingHorse);
                                horse.setPosition(27);
                                horse.situation = Horse.Etat.CIRCUIT;
                                changePosition(0,horse,false);
                                System.out.println("Horse " + disturbingHorse.numberHorse + disturbingHorse.getColor() + " has been eaten by horse " + horse.numberHorse + horse.getColor() + ".");
                            }
                        }
                        else {
                            horse.setPosition(27);
                            horse.situation = Horse.Etat.CIRCUIT;
                            changePosition(0,horse,false);
                        }
                    }
                    break;
                case 9 :
                case 10 :
                case 11 :
                case 12 :
                    if (res == 6) {
                        if (horse.isFilled(team,37)) {
                            Horse disturbingHorse = horse.getHorseFilled(team,37);
                            if (disturbingHorse.color == horse.color) { //If the player already has a horse on start case, nothing happens
                                changePosition(0,horse,false);
                            }
                            else {
                                disturbingHorse.beenEaten(team, disturbingHorse);
                                horse.setPosition(37);
                                horse.situation = Horse.Etat.CIRCUIT;
                                changePosition(0,horse,false);
                                System.out.println("Horse " + disturbingHorse.numberHorse + disturbingHorse.getColor() + " has been eaten by horse " + horse.numberHorse + horse.getColor() + ".");
                            }
                        }
                        else {
                            horse.setPosition(37);
                            horse.situation = Horse.Etat.CIRCUIT;
                            changePosition(0,horse,false);
                        }
                    }
                    break;
                case 13 :
                case 14 :
                case 15 :
                case 16 :
                    if (res == 6) {
                        if (horse.isFilled(team,47)) { //Returns true if there is any horse on the 47th case
                            Horse disturbingHorse = horse.getHorseFilled(team,47);
                            if (disturbingHorse.color == horse.color) { //If the player already has a horse on start case, nothing happens
                                changePosition(0,horse,false);
                            }
                            else {
                                disturbingHorse.beenEaten(team, disturbingHorse);
                                horse.setPosition(47);
                                horse.situation = Horse.Etat.CIRCUIT;
                                changePosition(0,horse,false);
                                System.out.println("Horse " + disturbingHorse.numberHorse + disturbingHorse.getColor() + " has been eaten by horse " + horse.numberHorse + horse.getColor() + ".");
                            }
                        }
                        else {
                            horse.setPosition(47);
                            horse.situation = Horse.Etat.CIRCUIT;
                            changePosition(0,horse,false);
                        }
                    }
                    break;
                case 56 :
                    if (horse.color == Color.BLUE && newMove && !horse.isFilled(team,57) && (res == 1)) { //Check if blue horse can go to case 57
                        horse.setPosition(57);
                        changePosition(0, horse,false);
                    }
                    else {
                        if (res == 1) {
                            if (horse.color == Color.BLUE) {
                                changePosition(0, horse, false);
                            } else {
                                if (horse.isFilled(team, 17)) { //Returns true if there is any horse on the 17th case
                                    Horse disturbingHorse = horse.getHorseFilled(team, 17);
                                    if (disturbingHorse.color == horse.color) { //If the player already has a horse on start case, nothing happens
                                        changePosition(0, horse, false);
                                    } else {
                                        disturbingHorse.beenEaten(team, disturbingHorse);
                                        horse.setPosition(17);
                                        horse.situation = Horse.Etat.CIRCUIT;
                                        changePosition(0, horse, false);
                                        System.out.println("Horse " + disturbingHorse.numberHorse + disturbingHorse.getColor() + " has been eaten by horse " + horse.numberHorse + horse.getColor() + ".");
                                    }
                                }
                            }
                        }
                        else {
                            horse.setPosition(17);
                            changePosition(res-1,horse,false);
                        }
                    }
                    break;
                case 26 :
                    if (horse.color == Color.RED && newMove && !horse.isFilled(team,61) && res == 1) { //Check if blue horse can go to case 57
                        horse.setPosition(61);
                        changePosition(0, horse,false);
                    }
                    else {
                        if (res == 1) {
                            if (horse.color == Color.RED) {
                                changePosition(0, horse, false);
                            } else {
                                if (horse.isFilled(team, 27)) { //Returns true if there is any horse on the 17th case
                                    Horse disturbingHorse = horse.getHorseFilled(team, 27);
                                    if (disturbingHorse.color == horse.color) { //If the player already has a horse on start case, nothing happens
                                        changePosition(0, horse, false);
                                    } else {
                                        disturbingHorse.beenEaten(team, disturbingHorse);
                                        horse.setPosition(27);
                                        horse.situation = Horse.Etat.CIRCUIT;
                                        changePosition(0, horse, false);
                                        System.out.println("Horse " + disturbingHorse.numberHorse + disturbingHorse.getColor() + " has been eaten by horse " + horse.numberHorse + horse.getColor() + ".");
                                    }
                                }
                            }
                        }
                        else {
                            horse.setPosition(27);
                            changePosition(res-1,horse,false);
                        }
                    }
                    break;
                case 36 :
                    if (horse.color == Color.GREEN && newMove && !horse.isFilled(team,65) && res == 1) { //Check if blue horse can go to case 57
                        horse.setPosition(65);
                        changePosition(0, horse,false);
                    }
                    else {
                        if (res == 1) {
                            if (horse.color == Color.GREEN) {
                                changePosition(0, horse, false);
                            } else {
                                if (horse.isFilled(team, 37)) { //Returns true if there is any horse on the 17th case
                                    Horse disturbingHorse = horse.getHorseFilled(team, 37);
                                    if (disturbingHorse.color == horse.color) { //If the player already has a horse on start case, nothing happens
                                        changePosition(0, horse, false);
                                    } else {
                                        disturbingHorse.beenEaten(team, disturbingHorse);
                                        horse.setPosition(37);
                                        horse.situation = Horse.Etat.CIRCUIT;
                                        changePosition(0, horse, false);
                                        System.out.println("Horse " + disturbingHorse.numberHorse + disturbingHorse.getColor() + " has been eaten by horse " + horse.numberHorse + horse.getColor() + ".");
                                    }
                                }
                            }
                        }
                        else {
                            horse.setPosition(37);
                            changePosition(res-1,horse,false);
                        }
                    }
                    break;
                case 46 :
                    if (horse.color == Color.YELLOW && newMove && !horse.isFilled(team,69) && res == 1) { //Check if blue horse can go to case 57
                        horse.setPosition(69);
                        changePosition(0, horse,false);
                    }
                    else {
                        if (res == 1) {
                            if (horse.color == Color.YELLOW) {
                                changePosition(0, horse, false);
                            } else {
                                if (horse.isFilled(team, 47)) { //Returns true if there is any horse on the 17th case
                                    Horse disturbingHorse = horse.getHorseFilled(team, 47);
                                    if (disturbingHorse.color == horse.color) { //If the player already has a horse on start case, nothing happens
                                        changePosition(0, horse, false);
                                    } else {
                                        disturbingHorse.beenEaten(team, disturbingHorse);
                                        horse.setPosition(47);
                                        horse.situation = Horse.Etat.CIRCUIT;
                                        changePosition(0, horse, false);
                                        System.out.println("Horse " + disturbingHorse.numberHorse + disturbingHorse.getColor() + " has been eaten by horse " + horse.numberHorse + horse.getColor() + ".");
                                    }
                                }
                            }
                        }
                        else {
                            horse.setPosition(47);
                            changePosition(res-1,horse,false);
                        }
                    }
                    break;
                case 17 :
                case 18 :
                case 19 :
                case 20 :
                case 21 :
                case 22 :
                case 23 :
                case 24 :
                case 25 :
                case 27 :
                case 28 :
                case 29 :
                case 30 :
                case 31 :
                case 32 :
                case 33 :
                case 34 :
                case 35 :
                case 37 :
                case 38 :
                case 39 :
                case 40 :
                case 41 :
                case 42 :
                case 43 :
                case 44 :
                case 45 :
                case 47 :
                case 48 :
                case 49 :
                case 50 :
                case 51 :
                case 52 :
                case 53 :
                case 54 :
                case 55 : //General case
                    if (res == 1 && horse.isFilled(team,pos+1)) { //If there is a horse on the next case :
                        System.out.println("Next case is occupied.");
                        Horse disturbingHorse = horse.getHorseFilled(team,pos+1);
                        if (disturbingHorse.color == horse.color) {
                            changePosition(0,horse,false);
                        }
                        else {
                            disturbingHorse.beenEaten(team, disturbingHorse);
                            horse.setPosition(pos+1);
                            changePosition(0,horse,false);
                            System.out.println("Horse " + disturbingHorse.numberHorse + disturbingHorse.getColor() + " has been eaten by horse " + horse.numberHorse + horse.getColor() + ".");
                        }
                    }
                    else {
                        horse.setPosition(pos + 1);
                        changePosition(res - 1, horse,false);
                    }
                    break;
                case 57 :
                    if (res == 2 && newMove && !horse.isFilled(team,58)) {
                        horse.setPosition(58);
                        changePosition(0, horse,false);
                    }
                    break;
                case 58 :
                    if (res == 3 && newMove && !horse.isFilled(team,59)) {
                        horse.setPosition(59);
                        changePosition(0, horse,false);
                    }
                    break;
                case 59 :
                    if (res == 4 && newMove && !horse.isFilled(team,60)) {
                        horse.setPosition(60);
                        changePosition(0, horse,false);
                    }
                    break;
                case 61 :
                    if (res == 2 && newMove && !horse.isFilled(team,62)) {
                        horse.setPosition(62);
                        changePosition(0, horse,false);
                    }
                    break;
                case 62 :
                    if (res == 3 && newMove && !horse.isFilled(team,63)) {
                        horse.setPosition(63);
                        changePosition(0, horse,false);
                    }
                    break;
                case 63 :
                    if (res == 4 && newMove && !horse.isFilled(team,64)) {
                        horse.setPosition(64);
                        changePosition(0, horse,false);
                    }
                    break;
                case 65 :
                    if (res == 2 && newMove && !horse.isFilled(team,66)) {
                        horse.setPosition(66);
                        changePosition(0, horse,false);
                    }
                    break;
                case 66 :
                    if (res == 3 && newMove && !horse.isFilled(team,67)) {
                        horse.setPosition(67);
                        changePosition(0, horse,false);
                    }
                    break;
                case 67 :
                    if (res == 4 && newMove && !horse.isFilled(team,68)) {
                        horse.setPosition(68);
                        changePosition(0, horse,false);
                    }
                    break;
                case 69 :
                    if (res == 2 && newMove && !horse.isFilled(team,70)) {
                        horse.setPosition(70);
                        changePosition(0, horse,false);
                    }
                    break;
                case 70 :
                    if (res == 3 && newMove && !horse.isFilled(team,71)) {
                        horse.setPosition(71);
                        changePosition(0, horse,false);
                    }
                    break;
                case 71 :
                    if (res == 4 && newMove && !horse.isFilled(team,72)) {
                        horse.setPosition(72);
                        changePosition(0, horse,false);
                    }
                    break;
                default : //For case ..
                    break;
            }
        }
        else {
            horse.setPosAndImg(horse, horse.getPosition());
        }
    }

    private void associateAction(int tour, int player, int res, GraphicsContext gc, int numberOfPlayers) {
        if (!team.get(player).getHorse1().isOneHorseMovable(team,player,res)) {
            System.out.println("Player " + team.get(player).toString() + " did not move because no move was possible.");
            update(tour + 1, (player + 1) % numberOfPlayers, -1, true, gc, numberOfPlayers);
        }
        else {
            this.setOnKeyPressed(keyEvent1 -> { //To check which horse is going to be moved
                String key1 = keyEvent1.getCode().toString();
                if (key1.equals("DIGIT1") && team.get(player).getHorse1().isHorseMovable(team, team.get(player).getHorse1(), res)) {
                    changePosition(res, team.get(player).getHorse1(),true);
                    if (res == 6) {
                        update(tour + 1, player, -1, true, gc, numberOfPlayers);
                    }
                    else {
                        update(tour + 1, (player + 1) % numberOfPlayers, -1, true, gc, numberOfPlayers);
                    }
                    System.out.println("Player " + team.get(player).toString() + " moved horse 1.");
                } else if (key1.equals("DIGIT2") && team.get(player).getHorse2().isHorseMovable(team, team.get(player).getHorse2(), res)) {
                    changePosition(res, team.get(player).getHorse2(),true);
                    if (res == 6) {
                        update(tour + 1, player, -1, true, gc, numberOfPlayers);
                    }
                    else {
                        update(tour + 1, (player + 1) % numberOfPlayers, -1, true, gc, numberOfPlayers);
                    }
                    System.out.println("Player " + team.get(player).toString() + " moved horse 2.");
                } else if (key1.equals("DIGIT3") && team.get(player).getHorse3().isHorseMovable(team, team.get(player).getHorse3(), res)) {
                    changePosition(res, team.get(player).getHorse3(),true);
                    if (res == 6) {
                        update(tour + 1, player, -1, true, gc, numberOfPlayers);
                    }
                    else {
                        update(tour + 1, (player + 1) % numberOfPlayers, -1, true, gc, numberOfPlayers);
                    }
                    System.out.println("Player " + team.get(player).toString() + " moved horse 3.");
                } else if (key1.equals("DIGIT4") && team.get(player).getHorse4().isHorseMovable(team, team.get(player).getHorse4(), res)) {
                    changePosition(res, team.get(player).getHorse4(),true);
                    if (res == 6) {
                        update(tour + 1, player, -1, true, gc, numberOfPlayers);
                    }
                    else {
                        update(tour + 1, (player + 1) % numberOfPlayers, -1, true, gc, numberOfPlayers);
                    }
                    System.out.println("Player " + team.get(player).toString() + " moved horse 4.");
                } else {
                    boolean goodKeyPressed = key1.equals("DIGIT1") || key1.equals("DIGIT2") || key1.equals("DIGIT3") || key1.equals("DIGIT4");
                    if (goodKeyPressed && team.get(player).getHorse1().isOneHorseMovable(team, player, res)) {
                        System.out.println("Player " + team.get(player).toString() + " did not move because the move was not possible, but another one is.");
                        update(tour, player, res, false, gc, numberOfPlayers);
                    } else if (goodKeyPressed && !team.get(player).getHorse1().isOneHorseMovable(team, player, res)) {
                        System.out.println("Player " + team.get(player).toString() + " did not move because the move was not possible, and no other move is.");
                        update(tour, (player + 1) % numberOfPlayers, -1, true, gc, numberOfPlayers);
                    } else {
                        update(tour, player, res, false, gc, numberOfPlayers);
                    }
                }
            });
        }
        System.out.println(" ");
    }

    public void update(int tour, int player, int prevRes, boolean hasPlayed, GraphicsContext gc, int numberOfPlayers){
        //Si tour < nombre d'équipes => on utilise la variable playerTurn1 pour décider qui joue
        if (hasPlayed) {
            this.setOnKeyPressed(keyEvent -> {
                String key = keyEvent.getCode().toString();
                gc.clearRect(0, 0, 150, 10);
                if (key.equals("SPACE")) { //To throw the dice
                    int res = this.dice.throwDice();
                    System.out.println("Player " + team.get(player).toString() + " got a " + res + ".");
                    associateAction(tour, player, res, gc, numberOfPlayers);
                    affichage = "Player "+team.get(player).toString() +" has done "+res;
                }
                else { //If space is not pressed
                    update(tour, player, prevRes,true, gc, numberOfPlayers); //Player has to throw the dice
                    affichage = "Player "+team.get(player).toString() +" must play";
                }
                //gc.setFill(Color.BLACK);
                gc.clearRect(0,0,1000,1000);
                gc.fillText(affichage, 200, 30);
            });
        }
        else { //if (!hasPlayed)
            associateAction(tour, player, prevRes, gc, numberOfPlayers);
        }
    }

}