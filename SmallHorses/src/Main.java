import TUIO.TuioClient;
import TUIO.TuioObject;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.animation.AnimationTimer;

import javax.management.timer.Timer;
import javax.swing.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class Main extends Application {
    private int tour = 0;
    public int numberOfPlayers;

    ArrayList<Integer> symbolList = new ArrayList<Integer>();

    public void start(Stage primaryStage) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // getScreenSize() returns the size of the screen in pixels
        int screenWidth = (int) size.getWidth(); // screenWidth will store the width of the screen
        int screenHeight = (int) size.getHeight(); // screenHeight will store the height of the screen
        System.out.println("Width : " + screenWidth + ". Height : " + screenHeight);
        int port = 3333;

        TestTuio2 dump = new TestTuio2();
        TuioClient client = new TuioClient(port);

        client.addTuioListener(dump);
        client.connect();

        Tuio tuio = new Tuio();
        numberOfPlayers = tuio.getNumberOfPlayers();
//        System.out.println("Number of players : " + numberOfPlayers);
        symbolList = tuio.getTags(dump,client,symbolList, numberOfPlayers);
        System.out.println("Symbol list : " + symbolList);


        primaryStage.setTitle("Petits chevaux");
        Group root = new Group();
        BorderPane pane = new BorderPane(root);

//        Button exitButton = new Button(" ",new ImageView(new Image("Little_red_horse_sized.png",100,100,false,false)));
//        exitButton.setOnAction(actionEvent -> System.exit(0));
//        exitButton.setOnTouchPressed(EventHandler.create(new CloseListener(),exitButton,System.exit(0)));
//        pane.getChildren().add(exitButton);

//        Button exitButton=new Button(" ",new ImageView(new Image("StopButton.png",100,100,false,false)));
//        exitButton.getOnTouchPressed();
//        exitButton.setOnAction(actionEvent -> {
//            System.exit(0);
//        });
//        pane.setRight(exitButton);

        //Scene theScene = new Scene(pane, 600, 600,true);
        Board board = new Board(pane,screenWidth,screenHeight,true,"SmallHorsesBoardJavaFx.png",numberOfPlayers, dump, symbolList); //We will have to change number of teams
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
        board.update(tour, player,-1,true, gc, numberOfPlayers, primaryStage, dump, symbolList);


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
