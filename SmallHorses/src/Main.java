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
        Scene theScene = new Scene(pane, 600, 600,true);
        primaryStage.setScene(theScene);
        primaryStage.show();

      /*  Image horseSheet = new Image("Little_blue_horse.png");
        ImageView horse = new ImageView(horseSheet);
        horse.setViewport(new Rectangle2D(0,0,480,480));
        horse.setX(100);
        horse.setY(100);
        pane.getChildren().add(horse); */

        Board board = new Board("SmallHorsesBoardJavaFx.png");
        board.initBoard(2);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
