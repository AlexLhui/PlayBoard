package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MyMusic {
    private static MediaPlayer mp;
    private static boolean playing=false;
    public MyMusic(){
        File f = new File("src/sound/outer_space.mp3");
        Media m = new Media(f.toURI().toString());
        mp = new MediaPlayer(m);
        mp.setVolume(0.1);
    }

    public void play(){
        if (!playing) {
            mp.setCycleCount(MediaPlayer.INDEFINITE);
            mp.setOnEndOfMedia(() -> mp.seek(Duration.ZERO));
            mp.setOnReady(() -> mp.play());
            playing=true;
        }
    }
}
