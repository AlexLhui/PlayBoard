package sample;

import TUIO.TuioClient;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Insets;


import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

import static java.awt.Font.*;
import static javafx.scene.text.Font.font;

import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import TUIO.*;

/*
    Original Idea : Adrien LENOIR, Yohan ISMAËL
    Written By : Yohan ISMAËL
    Meteorite By : Adrien LENOIR
    Disigned by : Adrien lENOIR
 */

public class Main extends Application {

    private final double pushing_value=40;
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final double width = screenSize.getWidth();
    public static final double height = screenSize.getHeight();
    private static final double unpeu=0;
    public final double scene_sizeX=width-unpeu;
    public static final double scene_sizeY=height-unpeu;
    public static boolean isConnected = false;


    public static final double rayon_fond=scene_sizeY/2;
    public static final double rayon_manique=0.1*rayon_fond;
    public static final double rayon_balle=rayon_manique*3/5;
    public final double rayon_trounoir=rayon_fond/8;

    public static final double masse_fond=rayon_fond;
    public static final double masse_manique=0.05*masse_fond;
    public final double masse_balle=masse_manique*3/5;
    public final double masse_trounoir=masse_fond;

    public int nbJoueurs = 0;
    public int nbJoueursMax=12;
    public int nbBalls = 0;
    public int nbBallsMax= nbJoueursMax/2;

    public final int taille_police=(int) rayon_fond/15;
    public final int taille_bouton=(int) (rayon_fond/2.5);

    private boolean blackholeOn=false;
    private MyMusic myMusic;

    public static ArrayList<Player> players=new ArrayList<>();

    private final Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, taille_police);

    public static ArrayList<Sphere> spheres=new ArrayList<>();

    public static Group root;
    public static BorderPane pane;
    /*
    public Main() throws FileNotFoundException {
    }
    //private final Font font = Font.loadFont(new FileInputStream(new File("src/font/Texaz.ttf")),taille_police);
                                              ^
    On peut avoir une police custom comme ça  |
    Faut changer la taille de la police quand m^me pour être bien
    */





    @Override
    public void start(Stage primaryStage) {

        int port = 3333;
        if (!isConnected)
        {
            ForTuio dump = new ForTuio();
            TuioClient client = new TuioClient(port);
            client.addTuioListener(dump);

            isConnected = true;
            client.connect();
            System.out.println(" we are here ");
        }
        ChoiceBox<String> butChoiceBox= new ChoiceBox<>();
        for (int i=0;i<=nbJoueursMax;i++){
            String aled =""+i;
            butChoiceBox.getItems().add(aled);
            butChoiceBox.setOnAction(actionEvent -> nbJoueurs=Integer.parseInt(butChoiceBox.getValue()));
        }
        String strNbButs="Nb Buts:";
        butChoiceBox.setTranslateX((int) (2*strNbButs.length()/3)*taille_police);
        butChoiceBox.setTranslateY( (int) (taille_police/4));
        Label butLabel = new Label(strNbButs);
        butLabel.setTextFill(Color.WHITE);
        butLabel.setFont(font);
        Group butGroup = new Group(butChoiceBox,butLabel);





        ChoiceBox<String> ballChoiceBox= new ChoiceBox<>();
        for (int i=0;i<=nbBallsMax;i++){
            String aled =""+i;
            ballChoiceBox.getItems().add(aled);
            ballChoiceBox.setOnAction(actionEvent -> nbBalls=Integer.parseInt(ballChoiceBox.getValue()));
        }
        String strNbBalls="Nb Balls:";
        ballChoiceBox.setTranslateX((int) (2*strNbBalls.length()/3)*taille_police);
        ballChoiceBox.setTranslateY((int)(taille_police/4));
        Label ballLabel = new Label(strNbBalls);
        ballLabel.setTextFill(Color.WHITE);
        ballLabel.setFont(font);
        Group ballGroup = new Group(ballChoiceBox,ballLabel);
        ballGroup.setTranslateY((int)(taille_police*3/2));



        CheckBox blackHoleCheckBox= new CheckBox("BlackHole");
        blackHoleCheckBox.setTextFill(Color.WHITE);
        blackHoleCheckBox.setFont(font);
        blackHoleCheckBox.setOnAction(actionEvent -> blackholeOn=blackHoleCheckBox.isSelected());
        blackHoleCheckBox.setTranslateY(taille_police*3);



        Group fullGroup = new Group(ballGroup,butGroup,blackHoleCheckBox);







        root=new Group();
        pane=new BorderPane(root);
        Scene scene=new Scene(pane,scene_sizeX,scene_sizeY);

        primaryStage.setFullScreen(true);

        primaryStage.setTitle("TRIGHOCKEY !!! ");
        primaryStage.setScene(scene);
        primaryStage.show();

        //La musique (marche pas trop)
        //myMusic=new MyMusic();
        //myMusic.play();

        // Fond de l'espace
        Image trueBackGround= new Image("img/espace.jfif",scene_sizeX,scene_sizeY,false,false);
        ImageView trueBackGroundView= new ImageView(trueBackGround);
        trueBackGroundView.setX(0);
        trueBackGroundView.setY(0);
        pane.getChildren().add(trueBackGroundView);

        // Pour l'affichage ( temporaire )
        Canvas canvas=new Canvas(scene_sizeX,scene_sizeY);
        GraphicsContext gc= canvas.getGraphicsContext2D();
        pane.getChildren().add(canvas);
        // Cercle noir délimitant la zone de jeu
        BackGround backGround=new BackGround(scene_sizeX/2,scene_sizeY/2,rayon_fond);
        // trou noir
        BlackHole blackHole=new BlackHole(scene_sizeX/2,scene_sizeY/2,rayon_trounoir,masse_trounoir);
        if (!blackholeOn){
            blackHole.disparait();
        }
        blackholeOn=false;
        //trou blanc
        TrouBlanc trouBlanc=new TrouBlanc(backGround.getX(), backGround.getY()+250, rayon_balle);



        // Début : Construction des joueurs
        /*
        ArrayList<String> controlPlayer1=new ArrayList<>();
        controlPlayer1.add("Z");
        controlPlayer1.add("Q");
        controlPlayer1.add("S");
        controlPlayer1.add("D");
        Player player1=new Player(50,50,rayon_manique,"img/joueur.png",controlPlayer1,masse_manique);

        ArrayList<String> controlPlayer2=new ArrayList<>();
        controlPlayer2.add("I");
        controlPlayer2.add("J");
        controlPlayer2.add("K");
        controlPlayer2.add("L");
        Player player2=new Player(scene_sizeX-50,scene_sizeY-50,rayon_manique,"img/joueur_rouge.png",controlPlayer2,masse_manique);

        ArrayList<String> controlPlayer3=new ArrayList<>();
        controlPlayer3.add("R");
        controlPlayer3.add("A");
        controlPlayer3.add("C");
        controlPlayer3.add("B");
        Player Barbicha=new Player(scene_sizeX-50,scene_sizeY-50,rayon_manique,"img/joueur_barbicha.png",controlPlayer3,masse_manique);
        */ // Construction d'un joueur lorsque l'on pose un objet sur la table
        // Fin : Construction des joueurs


        //Début : les buts
        ArrayList<But> listBut =new ArrayList<>();

        for (int i = 0; i < nbJoueurs; i++) {
            listBut.add(new But(backGround.getX() - backGround.getRadius(), backGround.getY() - backGround.getRadius(), backGround.getRadius(),i+1));
            listBut.get(i).setAngle(Math.abs(i * 360 / nbJoueurs));
            listBut.get(i).draw();
            pane.getChildren().add(listBut.get(i).getImageView());
        }
        //Fin : les buts
        //Début : création des balles initiales
        ArrayList<Ball> balls=new ArrayList<>();
        for (int i = 0; i < nbBalls; i++) {
            balls.add(new Ball(blackHole.getX()+250*Math.cos(2*i*Math.PI/nbBalls),blackHole.getY()+250*Math.sin(2*i*Math.PI/nbBalls),rayon_balle,masse_balle));
            balls.get(i).setV(new Vector(-5*Math.sin(2*i*Math.PI/nbBalls),5*Math.cos(2*i*Math.PI/nbBalls)));
        }
        //Fin : création des balles initiales

        //Début : On regroupe les "Joueurs" entre eux
        //ArrayList<Player> players=new ArrayList<>();
        /*
        players.add(player1);
        players.add(player2);
        players.add(Barbicha);
         */ //Le regroupement se fait lorsque l'on pose un objet sur la table
        //Fin : On regroupe les "Joueurs" entre eux

        //Début : On regroupe les "Spheres" entre elles
        //public static ArrayList<Sphere> spheres=new ArrayList<>();
        spheres.add(backGround);
        if (!balls.isEmpty()){
            spheres.addAll(balls);
        }
        spheres.add(blackHole);
        spheres.add(trouBlanc);
        if (!players.isEmpty()) {
            spheres.addAll(players);
            int l = players.size();
            ArrayList<Integer> toRemove = new ArrayList<Integer>();
            for (int i = 0;i<l;i++)
            {
                if(players.get(i).removed)
                {
                    if (players.get(i) != null) {
                        ForTuio.deleteInIdPlayers(players.get(i).objectId);
                        players.get(i).disparait();
                        //Main.players.remove(players.get(i));
                        toRemove.add(i);
                        //players.remove(i);
                        System.out.printf("Il a disparu !!!!");
                    }
                }
            }
                players.removeAll(toRemove);

        }
        //Fin : On regroupe les "Spheres" entre elles

        for (Sphere sphere:spheres){
            pane.getChildren().add(sphere.getImageView()); // c'est pour afficher les premières boules
        }

        // Début : gestion Input clavier
        ArrayList<String> input = new ArrayList<>();
        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code))
                        input.add(code);
                });
        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });


        // Fin : gestion input clavier
        Button button=new Button(" ",new ImageView(new Image("img/GO.png",taille_bouton,taille_bouton,false,false)));
        button.setOnAction(actionEvent -> {
            spheres.clear();
            But.nbBut_Pris_tot=0;
            start(primaryStage);
            primaryStage.setFullScreen(true);
        });
        //pane.setLeft(menuBar); // Faut mettre le menu à la fin
        pane.setRight(button);
        pane.setLeft(fullGroup);



        gc.setFill(Color.WHITE);
        Point2D pos_affichage= new Point2D(5,taille_police+150);
        //gc.setFont(font(taille_police));
        gc.setFont(font);

        final long startNanoTime = System.nanoTime();
        AnimationTimer timer=new AnimationTimer() {
            @Override
            public void handle(long time) {
                double t = (time - startNanoTime) / 1000000000.0;// t est en seconde
                // Début : Controle des joueurs avec le clavier
                int p = 0;
                /*for (Player player:players){
                    player.setOld_pos(player.getPos());
                    player.update();
                } */
                // Fin : Controle des joueurs avec le clavier

                // Press Y for fun
                if (input.contains("Y")){
                        balls.add(new Ball(trouBlanc.getX(),trouBlanc.getY(),rayon_balle,masse_balle));
                        spheres.add(balls.get(balls.size()-1));
                        balls.get(balls.size()-1).setV(trouBlanc.getV_respawn());
                        pane.getChildren().add(balls.get(balls.size()-1).getImageView());
                }


                for (Sphere sphere:spheres){
                    sphere.draw();
                    sphere.update(t,blackHole,players,backGround,listBut,trouBlanc,balls);
                }


                StringBuilder affichage= new StringBuilder();
                if (!listBut.isEmpty()) {
                    affichage.append("NOMBRE DE BUT(S) PRIS\n");
                    for (But but : listBut) {
                        int pourcentage = (But.nbBut_Pris_tot!=0)?(100 * but.nbBut_Pris / But.nbBut_Pris_tot):(0); // opérateur tertiaire omg
                        affichage
                                .append("But n°")
                                .append(but.getNbBut())
                                .append(" : ")
                                .append(but.nbBut_Pris)
                                .append("\t")
                                .append(pourcentage)
                                .append("%\n");
                    }
                    affichage
                            .append("Buts totaux : ")
                            .append(But.nbBut_Pris_tot)
                            .append("\n");
                }else{
                    affichage.append("Pas de buts :(\n");
                }



                gc.clearRect(0,0,pane.getWidth(),pane.getHeight());
                gc.fillText(affichage.toString(),pos_affichage.getX(),pos_affichage.getY());


            }
        };timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
