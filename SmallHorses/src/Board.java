import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
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
    int longueur;
    int largeur;
    int largCase;
    int longCase;
    int cases;
    Dice dice;

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
                    Horse horse1 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
                    Horse horse2 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
                    Horse horse3 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
                    Horse horse4 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
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
                    Horse horse1 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
                    Horse horse2 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
                    Horse horse3 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
                    Horse horse4 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
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
                    Horse horse1 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
                    Horse horse2 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
                    Horse horse3 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
                    Horse horse4 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
                    Team teamObj = new Team(couleur.get(a), horse1, horse2, horse3, horse4);
                    team.add(teamObj);
                    teamList.add(couleur.get(a));
                    teamObj.setNumberOfTeam(numberOfTeam);
                    System.out.println("Player 4 initialized");
                }
            }
            else{ //Player 1
                Horse horse1 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
                Horse horse2 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
                Horse horse3 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
                Horse horse4 = new Horse(1, Horse.Etat.STABLE, "SmallHorsesTransparent.png",a);
                Team teamObj = new Team(couleur.get(a),horse1,horse2,horse3,horse4);
                team.add(teamObj);
                teamObj.setNumberOfTeam(numberOfTeam);
                teamList.add(couleur.get(a));
                System.out.println("Player 1 initialized");
            }
        } // Code du dessus attribue des couleurs au hasard aux joueurs

        for(int j = 0; j<numberOfTeam; j++){
            int upperOffset = 118;
            int leftOffset = 958;
            int lineSize = 18;
            if(team.get(j).color == Color.BLUE){
                //Horse chev1 = team.get(j).getHorse1();
                team.get(j).getHorse1().setPosition(1);
                team.get(j).getHorse1().setNumberHorse(1);
                //    team.get(j).getHorse1().setImageok("Little_blue_horse_sized.png");
                team.get(j).getHorse1().getImage().setX(team.get(j).getHorse1().getXPos(1));
                team.get(j).getHorse1().getImage().setY(team.get(j).getHorse1().getYPos(1));
                pane.getChildren().add(team.get(j).getHorse1().getImage());
                team.get(j).getHorse2().setPosition(2);
                team.get(j).getHorse2().setNumberHorse(2);
                team.get(j).getHorse2().setColor(Color.BLUE);
                //    team.get(j).getHorse2().setImageok("Little_blue_horse_sized.png");
                team.get(j).getHorse2().getImage().setX(team.get(j).getHorse2().getXPos(2));
                team.get(j).getHorse2().getImage().setY(team.get(j).getHorse2().getYPos(2));
                pane.getChildren().add(team.get(j).getHorse2().getImage());
                team.get(j).getHorse3().setPosition(3);
                team.get(j).getHorse3().setNumberHorse(3);
                team.get(j).getHorse3().setColor(Color.BLUE);
                //    team.get(j).getHorse3().setImageok("Little_blue_horse_sized.png");
                team.get(j).getHorse3().getImage().setX(team.get(j).getHorse3().getXPos(3));
                team.get(j).getHorse3().getImage().setY(team.get(j).getHorse3().getYPos(3));
                pane.getChildren().add(team.get(j).getHorse3().getImage());
                team.get(j).getHorse4().setPosition(4);
                team.get(j).getHorse4().setNumberHorse(4);
                team.get(j).getHorse4().setColor(Color.BLUE);
                //    team.get(j).getHorse4().setImageok("Little_blue_horse_sized.png");
                team.get(j).getHorse4().getImage().setX(team.get(j).getHorse4().getXPos(4));
                team.get(j).getHorse4().getImage().setY(team.get(j).getHorse4().getYPos(4));
                pane.getChildren().add(team.get(j).getHorse4().getImage());
                System.out.println("Blue");
            }
            else if(team.get(j).color == Color.RED){
                team.get(j).getHorse1().setPosition(5);
                team.get(j).getHorse1().setNumberHorse(1);
                team.get(j).getHorse1().setColor(Color.RED);
                //    team.get(j).getHorse1().setImageok("Little_red_horse_sized.png");
                //ratioX*leftOffset
                team.get(j).getHorse1().getImage().setX(team.get(j).getHorse1().getXPos(5));
                team.get(j).getHorse1().getImage().setY(team.get(j).getHorse1().getYPos(5));
                team.get(j).getHorse1().getImage().setRotate(90);
                pane.getChildren().add(team.get(j).getHorse1().getImage());
                team.get(j).getHorse2().setPosition(6);
                team.get(j).getHorse2().setNumberHorse(2);
                team.get(j).getHorse2().setColor(Color.RED);
                //    team.get(j).getHorse2().setImageok("Little_red_horse_sized.png");
                team.get(j).getHorse2().getImage().setX(team.get(j).getHorse2().getXPos(6));
                team.get(j).getHorse2().getImage().setY(team.get(j).getHorse2().getYPos(6));
                team.get(j).getHorse2().getImage().setRotate(90);
                pane.getChildren().add(team.get(j).getHorse2().getImage());
                team.get(j).getHorse3().setPosition(7);
                team.get(j).getHorse3().setNumberHorse(3);
                team.get(j).getHorse3().setColor(Color.RED);
                //    team.get(j).getHorse3().setImageok("Little_red_horse_sized.png");
                team.get(j).getHorse3().getImage().setX(team.get(j).getHorse3().getXPos(7));
                team.get(j).getHorse3().getImage().setY(team.get(j).getHorse3().getYPos(7));
                team.get(j).getHorse3().getImage().setRotate(90);
                pane.getChildren().add(team.get(j).getHorse3().getImage());
                team.get(j).getHorse4().setPosition(8);
                team.get(j).getHorse4().setNumberHorse(4);
                team.get(j).getHorse4().setColor(Color.RED);
                //    team.get(j).getHorse4().setImageok("Little_red_horse_sized.png");
                team.get(j).getHorse4().getImage().setX(team.get(j).getHorse4().getXPos(8));
                team.get(j).getHorse4().getImage().setY(team.get(j).getHorse4().getYPos(8));
                team.get(j).getHorse4().getImage().setRotate(90);
                pane.getChildren().add(team.get(j).getHorse4().getImage());
                System.out.println("Red");
            }
            else if(team.get(j).color == Color.GREEN){
                team.get(j).getHorse1().setPosition(9);
                team.get(j).getHorse1().setNumberHorse(1);
                team.get(j).getHorse1().setColor(Color.GREEN);
                //    team.get(j).getHorse1().setImageok("Little_green_horse_sized.png");
                team.get(j).getHorse1().getImage().setX(team.get(j).getHorse1().getXPos(9));
                team.get(j).getHorse1().getImage().setY(team.get(j).getHorse1().getYPos(9));
                team.get(j).getHorse1().getImage().setRotate(180);
                pane.getChildren().add(team.get(j).getHorse1().getImage());
                team.get(j).getHorse2().setPosition(10);
                team.get(j).getHorse2().setNumberHorse(2);
                team.get(j).getHorse2().setColor(Color.GREEN);
                //    team.get(j).getHorse2().setImageok("Little_green_horse_sized.png");
                team.get(j).getHorse2().getImage().setX(team.get(j).getHorse2().getXPos(10));
                team.get(j).getHorse2().getImage().setY(team.get(j).getHorse2().getYPos(10));
                team.get(j).getHorse2().getImage().setRotate(180);
                pane.getChildren().add(team.get(j).getHorse2().getImage());
                team.get(j).getHorse3().setPosition(11);
                team.get(j).getHorse3().setNumberHorse(3);
                team.get(j).getHorse3().setColor(Color.GREEN);
                //    team.get(j).getHorse3().setImageok("Little_green_horse_sized.png");
                team.get(j).getHorse3().getImage().setX(team.get(j).getHorse3().getXPos(11));
                team.get(j).getHorse3().getImage().setY(team.get(j).getHorse3().getYPos(11));
                team.get(j).getHorse3().getImage().setRotate(180);
                pane.getChildren().add(team.get(j).getHorse3().getImage());
                team.get(j).getHorse4().setPosition(12);
                team.get(j).getHorse4().setNumberHorse(4);
                team.get(j).getHorse4().setColor(Color.GREEN);
                //    team.get(j).getHorse4().setImageok("Little_green_horse_sized.png");
                team.get(j).getHorse4().getImage().setX(team.get(j).getHorse4().getXPos(12));
                team.get(j).getHorse4().getImage().setY(team.get(j).getHorse4().getYPos(12));
                team.get(j).getHorse4().getImage().setRotate(180);
                pane.getChildren().add(team.get(j).getHorse4().getImage());
                System.out.println("Green");
            }
            else if(team.get(j).color == Color.YELLOW){
                team.get(j).getHorse1().setPosition(13);
                team.get(j).getHorse1().setNumberHorse(1);
                team.get(j).getHorse1().setColor(Color.YELLOW);
                //    team.get(j).getHorse1().setImageok("Little_yellow_horse_sized.png");
                team.get(j).getHorse1().getImage().setX(team.get(j).getHorse1().getXPos(13));
                team.get(j).getHorse1().getImage().setY(team.get(j).getHorse1().getYPos(13));
                team.get(j).getHorse1().getImage().setRotate(-90);
                pane.getChildren().add(team.get(j).getHorse1().getImage());
                team.get(j).getHorse2().setPosition(14);
                team.get(j).getHorse2().setNumberHorse(2);
                team.get(j).getHorse2().setColor(Color.YELLOW);
                //    team.get(j).getHorse2().setImageok("Little_yellow_horse_sized.png");
                team.get(j).getHorse2().getImage().setX(team.get(j).getHorse2().getXPos(14));
                team.get(j).getHorse2().getImage().setY(team.get(j).getHorse2().getYPos(14));
                team.get(j).getHorse2().getImage().setRotate(-90);
                pane.getChildren().add(team.get(j).getHorse2().getImage());
                team.get(j).getHorse3().setPosition(15);
                team.get(j).getHorse3().setNumberHorse(3);
                team.get(j).getHorse3().setColor(Color.YELLOW);
                //    team.get(j).getHorse3().setImageok("Little_yellow_horse_sized.png");
                team.get(j).getHorse3().getImage().setX(team.get(j).getHorse3().getXPos(15));
                team.get(j).getHorse3().getImage().setY(team.get(j).getHorse3().getYPos(15));
                team.get(j).getHorse3().getImage().setRotate(-90);
                pane.getChildren().add(team.get(j).getHorse3().getImage());
                team.get(j).getHorse4().setPosition(16);
                team.get(j).getHorse4().setNumberHorse(4);
                team.get(j).getHorse4().setColor(Color.YELLOW);
                //    team.get(j).getHorse4().setImageok("Little_yellow_horse_sized.png");
                team.get(j).getHorse4().getImage().setX(team.get(j).getHorse4().getXPos(16));
                team.get(j).getHorse4().getImage().setY(team.get(j).getHorse4().getYPos(16));
                team.get(j).getHorse4().getImage().setRotate(-90);
                pane.getChildren().add(team.get(j).getHorse4().getImage());
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


    public void update(int tour){
        //Si tour < nombre d'équipes => on utilise la variable playerTurn1 pour décider qui joue
        int playerTurn1 = (int) (Math.random()*team.get(0).getNumberOfTeam()); // On attribue au hasard le joueur qui commence
        int playerTurn = tour%team.get(0).getNumberOfTeam(); //To get the number of team
        if(dice.notSetDice){
            this.setOnKeyPressed(keyEvent -> { // A mettre dans le update
                String key = keyEvent.getCode().toString();
                if (key.equals("SPACE")) {
                    int res = this.dice.throwDice();
                    System.out.println(res);
                }
            });
            //if res = 6
            //if il n'y a pas de cheval sur la case de départ
            // Je peux choisir de sortir
            // if il existe un cheval de l'équipe sur le plateau
            // Je peux appeler changePosition
            //On attend qu'il rejoue
            //if res < 6
            //if il existe un cheval de l'équipe sur le plateau
            // Je peux appeler changePosition
            //Sinon : passe son tour
        }
        // On attend l'événement : lance de dé de la part du joueur
        /* 2 possibilités :
                il fait 6 => il sort un cheval de l'écurie, il joue ou il ne peut pas jouer, après un 6 le joueur rejoue
                il fait entre 1 et 5 => il joue ou il passe son tour s'il ne peut pas
         */
        // Lorsque l'image bouge, 5 possibilités :
        /*
        Soit le cheval est juste sorti de l'écurie
        Soit le cheval avance comme prévu
        Soit le cheval est bloqué par un autre devant il s'arrête derrière
        Soit il fait un lancé qui arrive juste sur le cheval de devant : il s'arrête derrière si c'est un de son équipe sinon il prend la place et le renvoie à l'écurie du propriétaire
        Soit il arrive dans les cases d'arrivé et ne bouge pas s'il a fait un score trop élevé, avance s'il a fait le bon score ou le score (bloquage du cheval) ou un score inférieur à celui attendu
         */
        // Si un joueur a ses 4 chevaux sur la piste d'arrivé : il gagne (fonction end)
    }
}
/* Idée d'une manière de traiter l'attente de l'appui sur le bouton
jButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent evt) {
        TrucKiVaBien(evt) ; //envoie les infos
        fenetre.dispose() ;  // dégage la fenetre et rend la main au main
        }
        }); */