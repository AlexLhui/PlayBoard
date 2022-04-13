import TUIO.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class TestTuio2 extends JComponent implements TuioListener, ActionListener {

    public Hashtable<Long, TuioObject> objectList = new Hashtable<Long, TuioObject>();
    public ArrayList<Long> keyObjects = new ArrayList<Long>();
    public ArrayList<TuioObject> objList = new ArrayList<TuioObject>();

    private Hashtable<Long, TuioCursor> cursorList = new Hashtable<Long, TuioCursor>();
    private Hashtable<Long, TuioBlob> blobList = new Hashtable<Long, TuioBlob>();

    //public static final int finger_size = 18;
    //public static final int object_size = 60;
    public static final int table_size = 760; //il faut chagner cette valeur

    public Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // getScreenSize() returns the size of the screen in pixels
    public double screenWidth = size.getWidth(); // screenWidth will store the width of the screen
    public double screenHeight = size.getHeight(); // screenHeight will store the height of the screen

    public ArrayList<TuioObject> otherObjects = new ArrayList<TuioObject>();

    public static int width, height;
    private float scale = 1.0f;
    public boolean debug = false;
    public boolean debugObj = false;

    public boolean boardInitialised = false;
    public Board board;
    public int tour;
    public int player;
    public int prevRes;
    public boolean previousHasPlayed;
    public GraphicsContext gc;
    public int numberOfPlayers;
    public Stage primaryStage;
    public TestTuio2 dump;
    public ArrayList<Integer> symbolList;
    public Dice dice;

    public boolean gameStop = false;

    Timer timer = new Timer(100,this);
    Timer timerStop = new Timer(1000, this);
    public int endTime = 0;

    public void setSize(int w, int h) {
        super.setSize(w, h);
        width = w;
        height = h;
        scale = height / (float) TestTuio2.table_size;
    }

    public void addTuioObject(TuioObject tobj) {
        int objListSize;
        try {objListSize = dump.objList.size();}
        catch (Exception ignored) {objListSize = 0;}
        int symbListSize;
        try {symbListSize = symbolList.size();}
        catch (Exception ignored) {symbListSize = 0;}
        if (objListSize < numberOfPlayers + 1 && symbListSize < numberOfPlayers + 1) { //If not all tags have been placed once
            objectList.put(tobj.getSessionID(), tobj);
            keyObjects.add(tobj.getSessionID());
            objList.add(tobj);
        }
        else if (objListSize < numberOfPlayers + 1 && symbListSize == numberOfPlayers + 1) { //If a tag has been removed
            int symb = tobj.getSymbolID();
            if (symbolList.contains(symb)) { // Be sure the tag was here before
                int index = symbolList.indexOf(symb);
                try {dump.objList.add(index,tobj);}
                catch (Exception ignored) {dump.objList.add(tobj);}
            }
        }
        else {
            otherObjects.add(tobj);
        }
        if (debug||debugObj)
        { System.out.println("add obj " + tobj.getSymbolID() + " (" + tobj.getSessionID() + ") " + tobj.getX() + " " + tobj.getY() + " " + tobj.getAngle()); }
    }

    public void updateTuioObject(TuioObject tobj) {
        if (!(objectList.contains(tobj.getSymbolID()))) {
            objectList.put(tobj.getSessionID(), tobj);
            keyObjects.add(tobj.getSessionID());
        }
        if (debug||debugObj)
        { System.out.println("set obj " + tobj.getSymbolID() + " (" + tobj.getSessionID() + ") " + tobj.getX() + " " + tobj.getY() + " " + tobj.getAngle() + " " + tobj.getMotionSpeed() + " " + tobj.getRotationSpeed() + " " + tobj.getMotionAccel() + " " + tobj.getRotationAccel()); }
    }

    public void removeTuioObject(TuioObject tobj) {
        if (tobj.getSymbolID() == 0) {
            dice.oldHashCode = tobj.hashCode();
        }
        objectList.remove(tobj.getSessionID());
        objList.remove(tobj);

        if (otherObjects.contains(tobj)) {
            otherObjects.remove(tobj);
        }

        if (debug||debugObj)
        { System.out.println("del obj " + tobj.getSymbolID() + " (" + tobj.getSessionID() + ")"); }
    }

    public void addTuioCursor(TuioCursor tcur) {

        cursorList.put(tcur.getSessionID(), tcur);

        if (debug) {
            System.out.println("add cur " + tcur.getCursorID() + " (" + tcur.getSessionID() + ") " + tcur.getX() + " " + tcur.getY());
        }
    }

    public void updateTuioCursor(TuioCursor tcur) {

        if (debug) {
            System.out.println("set cur " + tcur.getCursorID() + " (" + tcur.getSessionID() + ") " + tcur.getX() + " " + tcur.getY() + " " + tcur.getMotionSpeed() + " " + tcur.getMotionAccel()); }
    }

    public void removeTuioCursor(TuioCursor tcur) {

        cursorList.remove(tcur.getSessionID());

        if (debug)
        {System.out.println("del cur " + tcur.getCursorID() + " (" + tcur.getSessionID() + ")");}
    }

    public void addTuioBlob(TuioBlob tblb) {
        blobList.put(tblb.getSessionID(), tblb);

        if (debug)
        {System.out.println("add blb " + tblb.getBlobID() + " (" + tblb.getSessionID() + ") " + tblb.getX() + " " + tblb.getY() + " " + tblb.getAngle());}
    }

    public void updateTuioBlob(TuioBlob tblb) {

        if (debug)
        {System.out.println("set blb " + tblb.getBlobID() + " (" + tblb.getSessionID() + ") " + tblb.getX() + " " + tblb.getY() + " " + tblb.getAngle() + " " + tblb.getMotionSpeed() + " " + tblb.getRotationSpeed() + " " + tblb.getMotionAccel() + " " + tblb.getRotationAccel());}
    }

    public void removeTuioBlob(TuioBlob tblb) {
        blobList.remove(tblb.getSessionID());

        if (debug)
        {System.out.println("del blb " + tblb.getBlobID() + " (" + tblb.getSessionID() + ")");}
    }

    public void setVariables(Board board, int tour, int player, int prevRes, boolean previousHasPlayed, GraphicsContext gc, int numberOfPlayers, Stage primaryStage, TestTuio2 dump, ArrayList<Integer> symbolList, Dice dice) {
        this.boardInitialised = true;
        this.dice = dice;
        this.board = board;
        this.tour = tour;
        this.player = player;
        this.prevRes = prevRes;
        this.previousHasPlayed = previousHasPlayed;
        this.gc = gc;
        this.numberOfPlayers = numberOfPlayers;
        this.primaryStage = primaryStage;
        this.dump = dump;
        this.symbolList = symbolList;
        dice.dump = dump;
    }

    public void refresh(TuioTime frameTime) {
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (boardInitialised) {
            try {
                checkStopGame();
                board.update(tour, player, prevRes, previousHasPlayed, gc, numberOfPlayers, primaryStage, dump, symbolList);
            }
            catch (Exception ignored) {
                System.out.println("Tag issue : replace the tags or at least the one currently in use.");
            }
        }
        if (gameStop) {
            if (!timerStop.isRunning()) {
                timer.stop(); //Stop main timer
                timerStop.start(); //Start new timer
                JFrame endFrame = new JFrame("End game");
                JPanel endPanel = new JPanel();
                JLabel endLabel = new JLabel("Thanks for playing !");
                endLabel.setFont(new Font("Verdana",1,50));
                endPanel.add(endLabel);
                endFrame.add(endPanel);
                endFrame.setSize((int) screenWidth, (int) screenHeight);
                endFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                endFrame.setUndecorated(true);
                endFrame.setVisible(true);
            }
            else {
                endTime +=1;
                if (endTime == 5) { //After 5 seconds of end time
                    System.exit(0);
                }
            }
        }
    }

    public void checkStopGame() {
        try {
            for (TuioObject obj : otherObjects) {
                if ((screenWidth / 2 - 50) <= screenWidth * obj.getX() && screenWidth * obj.getX() <= (screenWidth / 2 + 50) && (screenHeight / 2 - 50) <= screenHeight * obj.getY() && screenHeight * obj.getY() <= (screenHeight / 2 + 50)) {
                    gameStop = true;
                }
            }
        }
        catch (Exception ignored) {}
    }

    public boolean isTagPlaced(int symbol) {
        for (TuioObject obj : dump.objList) {
            if (obj.getSymbolID() == symbol) {
                return true;
            }
        }
        return false;
    }

    public boolean areAllTagsPlaced() {
        return dump.objList.size() == symbolList.size();
    }

    public ArrayList<Integer> getTagsOnTable() {
        ArrayList<Integer> tags = new ArrayList<Integer>();
        for (TuioObject obj : objList) {
            tags.add(obj.getSymbolID());
        }
        return tags;
    }
}
