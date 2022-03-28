import TUIO.TuioClient;
import TUIO.TuioObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Tuio implements ActionListener {

    public TestTuio2 TuioDump;
    public TuioClient TuioClient;
    public ArrayList<Integer> TuioSymbolList;
    public int TuioNumberOfPlayers;
    public boolean numberOfPlayersChosen = false;

    JFrame frame = new JFrame("Choose the number of players");

    Timer timer = new Timer(250, this); //250 ms

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!numberOfPlayersChosen) {
            try {
                String cmd = e.getActionCommand();
                switch (cmd) {
                    case "2 players" -> {
                        TuioNumberOfPlayers = 2;
                        frame.dispose();
                        numberOfPlayersChosen = true;
                        timer.stop();
                    }
                    case "3 players" -> {
                        TuioNumberOfPlayers = 3;
                        frame.dispose();
                        numberOfPlayersChosen = true;
                        timer.stop();
                    }
                    case "4 players" -> {
                        TuioNumberOfPlayers = 4;
                        frame.dispose();
                        numberOfPlayersChosen = true;
                        timer.stop();
                    }
                    default -> {
                    }
                }
            }
            catch (Exception exception) {
//                System.out.println("Error");
            }
        }
        else {
            int j = 0;
            if (TuioSymbolList.size() < TuioNumberOfPlayers + 1) {
                System.out.println("Poser les tags"); // Ajouter un texte demandant de poser le dé, jouer 1 2 3 4
                for (int i = j; i < TuioDump.objList.size(); i++) {
                    if (!TuioSymbolList.contains((int) TuioDump.objList.get(i).getSessionID())) {
                        TuioSymbolList.add((int) TuioDump.objList.get(i).getSessionID());
                        System.out.println((int) TuioDump.objList.get(i).getSessionID());
                        j += 1;
                    }
                }
            } else {
                timer.stop();
            }
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

    public int getNumberOfPlayers() {
//        frame = new JFrame("Choose the number of players");
        frame.setVisible(true);
        frame.setSize(500,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        JButton button2 = new JButton("2 players");
        panel.add(button2);
        button2.addActionListener(this);

        JButton button3 = new JButton("3 players");
        panel.add(button3);
        button3.addActionListener (this);

        JButton button4 = new JButton("4 players");
        panel.add(button4);
        button4.addActionListener(this);

        timer.start();

        while (timer.isRunning()) {

        }
        return TuioNumberOfPlayers;
    }

}
