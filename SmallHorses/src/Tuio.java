import TUIO.TuioClient;
import TUIO.TuioObject;
import javafx.scene.canvas.GraphicsContext;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class Tuio implements ActionListener {

    public TestTuio2 TuioDump;
    public TuioClient TuioClient;
    public ArrayList<Integer> TuioSymbolList;
    public int TuioNumberOfPlayers;
    public boolean numberOfPlayersChosen = false;
    public boolean tagsAdded = false;

    public Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // getScreenSize() returns the size of the screen in pixels
    public int screenWidth = (int) size.getWidth(); // screenWidth will store the width of the screen
    public int screenHeight = (int) size.getHeight(); // screenHeight will store the height of the screen

    public JFrame framePlayers = new JFrame("Choose the number of players");
    public JFrame frameTags = new JFrame("Place the tags");

    JLabel jLabelTag = new JLabel("Place the dice");

    public Timer timer = new Timer(100, this); //250 ms

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!numberOfPlayersChosen) {
            try {
                String cmd = e.getActionCommand();
                switch (cmd) {
                    case "2 players" -> {
                        TuioNumberOfPlayers = 2;
                        framePlayers.dispose();
                        numberOfPlayersChosen = true;
                        timer.stop();
                    }
                    case "3 players" -> {
                        TuioNumberOfPlayers = 3;
                        framePlayers.dispose();
                        numberOfPlayersChosen = true;
                        timer.stop();
                    }
                    case "4 players" -> {
                        TuioNumberOfPlayers = 4;
                        framePlayers.dispose();
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
        else { //Add the tags
            int j = 0;
            if (TuioSymbolList.size() < TuioNumberOfPlayers + 1) {
//                System.out.println("Poser les tags"); // Ajouter un texte demandant de poser le dé, jouer 1 2 3 4
                for (int i = j; i < TuioDump.objList.size(); i++) {
                    if (!TuioSymbolList.contains((int) TuioDump.objList.get(i).getSymbolID())) { //If object is not in the list
                        TuioSymbolList.add((int) TuioDump.objList.get(i).getSymbolID());
                        System.out.println((int) TuioDump.objList.get(i).getSymbolID());
                        j += 1;
                    }
                }
                switch (TuioSymbolList.size()) {
                    case 1 -> jLabelTag.setText("Place tag of player 1");
                    case 2 -> jLabelTag.setText("Place tag of player 2");
                    case 3 -> jLabelTag.setText("Place tag of player 3");
                    case 4 -> jLabelTag.setText("Place tag of player 4");
                    case 5 -> jLabelTag.setText("Let's go !");
                }
            } else {
                timer.stop();
                tagsAdded = true;
                frameTags.dispose();
            }
        }
    }

    public void getTags(TestTuio2 dump, TuioClient client, ArrayList<Integer> symbolList, int numberOfPlayers) {
        if (!timer.isRunning()) {
            TuioDump = dump;
            TuioClient = client;
            TuioSymbolList = symbolList;
            TuioNumberOfPlayers = numberOfPlayers;
            timer.start();
            //Nice interaction to get players to lay tags ?
        }
        else {
            if (!frameTags.isVisible()) {
                JPanel panel = new JPanel(new GridLayout(2,1)); // Add close button !!

                frameTags.setLayout(new GridBagLayout());
                JLabel jlabel = new JLabel("Place the tags");
                jlabel.setFont(new Font("Verdana",1,30));
                jlabel.setVerticalAlignment(SwingConstants.CENTER);
                panel.add(jlabel);

                jLabelTag.setText("Place the dice first");
                jLabelTag.setFont(new Font("Verdana",1,20));
                jlabel.setVerticalAlignment(SwingConstants.CENTER);
                panel.add(jLabelTag);

                frameTags.add(panel);
                frameTags.setSize(screenWidth, screenHeight);
                frameTags.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frameTags.setUndecorated(true);
                frameTags.setVisible(true);
            }
        }
    }

    public void getNumberOfPlayers() {
        if (!timer.isRunning()) {
            timer.start();
        }
        else {
            if (!framePlayers.isVisible()) {
                JPanel panelPlayers = new JPanel(new GridLayout(1,3)); // Add close button !!

//                JPanel panelInfo = new JPanel();
//                JLabel jlabel = new JLabel("Please remove all the tags before choosing the number of players");
//                jlabel.setFont(new Font("Verdana",1,20));
//                jlabel.setVerticalAlignment(SwingConstants.CENTER);
//                panelInfo.add(jlabel);

                framePlayers.add(panelPlayers);
//                framePlayers.add(panelInfo);

                JButton button2 = new JButton();
                button2.setActionCommand("2 players");
                try {
                    Image img2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("2players.png")));
                    button2.setBackground(Color.WHITE);
                    button2.setIcon(new ImageIcon(img2));
                }
                catch (Exception ignored) {
//                    System.out.println("Error");
                }
                panelPlayers.add(button2);
                button2.addActionListener(this);

                JButton button3 = new JButton();
                button3.setActionCommand("3 players");
                try {
                    Image img3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("3players.png")));
                    button3.setBackground(Color.WHITE);
                    button3.setIcon(new ImageIcon(img3));
                }
                catch (Exception ignored) {
//                    System.out.println("Error");
                }
                panelPlayers.add(button3);
                button3.addActionListener(this);

                JButton button4 = new JButton();
                button4.setActionCommand("4 players");
                try {
                    Image img4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("4players.png")));
                    button4.setBackground(Color.WHITE);
                    button4.setIcon(new ImageIcon(img4));
                }
                catch (Exception ignored) {
//                    System.out.println("Error");
                }
                panelPlayers.add(button4);
                button4.addActionListener(this);

                framePlayers.setSize(screenWidth, screenHeight);
                framePlayers.setExtendedState(JFrame.MAXIMIZED_BOTH);
                framePlayers.setUndecorated(true);
                framePlayers.setVisible(true);
                framePlayers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
    }

}
