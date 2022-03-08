import TUIO.TuioClient;
import TUIO.TuioObject;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.animation.AnimationTimer;
import javax.swing.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


import java.awt.*;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class Main extends Application{
    private int tour = 0;
    private int numberOfPlayers = 2;
    ArrayList<TuioObject> tuioList= new ArrayList<TuioObject>();
    ArrayList<Integer> symbolList = new ArrayList<Integer>();

    public void start(Stage primaryStage) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // getScreenSize() returns the size of the screen in pixels
        int screenWidth = (int) size.getWidth(); // screenWidth will store the width of the screen
        int screenHeight = (int) size.getHeight(); // screenHeight will store the height of the screen
        System.out.println("Width : " + screenWidth + "Height : " + screenHeight);
        int port = 3333;

        TestTuio2 dump = new TestTuio2();
        TuioClient client = new TuioClient(port);

        client.addTuioListener(dump);
        client.connect();

        //Veuillez poser le tag du dé
        if(dump.objList.size() == 1){
            tuioList.add(dump.objList.get(0));
            symbolList.add(tuioList.get(0).getSymbolID());
        }
        int i = 0;
        while(i != numberOfPlayers){
            // Veuillez ajouter le tag du joueur n
            if (symbolList.size() != numberOfPlayers) {
                if (dump.objList.size() == 2) {
                    tuioList.add(dump.objList.get(1));
                    symbolList.add(tuioList.get(1).getSymbolID());
                    System.out.println(dump.objList.get(1).getSymbolID());
                    i+= 1;
                } // Veuillez retirer le tag du joueur n (sauf si c'est le dernier joueur)
            }
        }

        primaryStage.setTitle("Petits chevaux");
        Group root = new Group();
        Pane pane = new Pane(root);
        //Scene theScene = new Scene(pane, 600, 600,true);
        Board board = new Board(pane,screenWidth,screenHeight,true,"SmallHorsesBoardJavaFx.png",numberOfPlayers); //We will have to change number of teams
        primaryStage.setScene(board);
        primaryStage.setFullScreen(true);
        primaryStage.show();

       /* final Canvas canvas = new Canvas(pane.getWidth(), pane.getHeight());
        pane.getChildren().add(canvas);
        GraphicsContext gc= canvas.getGraphicsContext2D();
        gc.fillText(board.affichage, 200, 30);*/

        final javafx.scene.canvas.Canvas canvas = new Canvas(pane.getWidth(), pane.getHeight());
        pane.getChildren().add(canvas);
        GraphicsContext gc= canvas.getGraphicsContext2D();

        int player = 1; //Random 1-4
        board.update(tour, player,-1,true, gc, numberOfPlayers);


//        while (!board.isGameFinished()) {
//            board.update(tour, player);
//            tour +=1;
//            player = player%4 + 1;
//        }

    }

    public static void main(String[] args) {
        launch(args);

    }
}
