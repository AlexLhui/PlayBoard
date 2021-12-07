import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.animation.AnimationTimer;
import javax.swing.*;

import static javafx.application.Application.launch;

public class Main extends Application{

    public void start(Stage primaryStage){
        primaryStage.setTitle("Petits chevaux");
        Group root = new Group();
        Pane pane = new Pane(root);
        //Scene theScene = new Scene(pane, 600, 600,true);
        Board board = new Board(pane,600,600,true,"SmallHorsesBoardJavaFx.png",3); //We will have to change number of teams
        primaryStage.setScene(board);
        primaryStage.show();

        int tour = 0;
        board.update(tour);

//        while (!board.isGameFinished()) {
//            board.update(tour);
//            tour +=1;
//        }

    }

    public static void main(String[] args) {
        launch(args);

    }
}
