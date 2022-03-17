import TUIO.TuioClient;
import TUIO.TuioObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Tuio implements ActionListener {

    TestTuio2 TuioDump;
    TuioClient TuioClient;
    ArrayList<Integer> TuioSymbolList;
    int TuioNumberOfPlayers;

    Timer timer = new Timer(250, this); //250 ms

    @Override
    public void actionPerformed(ActionEvent e) {
        int j = 0;
        if (TuioDump.objList.size() < TuioNumberOfPlayers+1) {
            System.out.println("Poser les tags"); // Ajouter un texte demandant de poser le dé, jouer 1 2 3 4
            for(int i = j; i<TuioDump.objList.size(); i++){
                TuioSymbolList.add(TuioDump.objList.get(i).getSymbolID());
                j += 1;
            }
        }
        else {
            timer.stop();
        }
    }

    public ArrayList<Integer> getTags(TestTuio2 dump, TuioClient client, ArrayList<Integer> symbolList, int numberOfPlayers) {
        TuioDump = dump;
        TuioClient = client;
        TuioSymbolList = symbolList;
        TuioNumberOfPlayers = numberOfPlayers;
        timer.start();
        while (timer.isRunning()) {

        }
        return TuioSymbolList;
    }

}
