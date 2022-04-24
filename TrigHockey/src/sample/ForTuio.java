package sample;

import TUIO.*;
import java.util.*;
import javax.swing.*;


public class ForTuio extends JComponent implements TuioListener {

    public Hashtable<Long, TuioObject> objectList = new Hashtable<Long, TuioObject>();
    public ArrayList<Long> keyObjects = new ArrayList<Long>();
    public static ArrayList<TuioObject> objList = new ArrayList<TuioObject>();

    public static ArrayList<Integer> idPlayers = new ArrayList<>();
    private Hashtable<Long, TuioCursor> cursorList = new Hashtable<Long, TuioCursor>();
    private Hashtable<Long, TuioBlob> blobList = new Hashtable<Long, TuioBlob>();

    public static final int table_size = 760; //il faut chagner cette valeur

    public static int width, height;
    private float scale = 1.0f;
    public boolean debug = false;
    public boolean debugObj = false;

    private boolean idInIdPlayers(TuioObject obj)
    {
        int idObj = obj.getSymbolID();
        boolean yes = false;
        for (var id:idPlayers)
        {
            if (idObj == id)
            {
                yes = true;
            }
        }
        return yes;
    }

    private void deleteThatIdInIdPlayers(TuioObject obj)
    {
        int idObj = obj.getSymbolID();
        for (var pos=0;pos<idPlayers.size();pos++)
        {
            if (idObj == idPlayers.get(pos))
            {
                idPlayers.remove(pos);
            }
        }
    }

    public static void deleteInIdPlayers(int id)
    {
        for (var pos=0;pos<idPlayers.size();pos++)
        {
            if (id == idPlayers.get(pos))
            {
                idPlayers.remove(pos);
            }
        }
    }

    public static int posInObjList(int id)  //takes the id of the Tag X Scape object in argument and returns its position in objList, return -1 if the object with the given id is not on the list (on the table)
    {
        int pos = -1;
        int loop = -1;
        for (TuioObject obj : objList)
        {
            loop++;
            if (id == obj.getSymbolID())
            {
                return loop;
            }
        }
        return pos;
    }

    public void setSize(int w, int h) {
        super.setSize(w, h);
        width = w;
        height = h;
        scale = height / (float) ForTuio.table_size;
    }

    public void addTuioObject(TuioObject tobj) {
        objList.add(tobj);
        if (!idInIdPlayers(tobj))
        {
            idPlayers.add(tobj.getSymbolID());
            var player = new Player(tobj.getX()*Main.width,tobj.getY()*Main.height,Main.rayon_manique,"img/joueur_rouge.png",Main.masse_manique,tobj.getSymbolID());
            player.removed= false;
            Main.players.add(player);
            Main.spheres.add(player);
            //player.draw();
            System.out.println("On a créé le joueur ! avec l'ID : "+ tobj.getSymbolID());
        }

        if (debug||debugObj)
        {
            System.out.println("Taille liste " + objList.size());}
            //System.out.println("add obj " + tobj.getSymbolID() + " (" + tobj.getSessionID() + ") " + tobj.getX() + " " + tobj.getY() + " " + tobj.getAngle()); }
    }

    public void updateTuioObject(TuioObject tobj) {

        if (!(objectList.contains(tobj.getSymbolID()))) {
            objectList.put(tobj.getSessionID(), tobj);
            keyObjects.add(tobj.getSessionID());

            //objList.add(tobj);
        }
        Player player = Player.FindPlayerWithObjectId(tobj.getSymbolID(),Main.players);
        if (player != null) {
            player.setOld_pos(player.getPos());
            player.update();
            //System.out.println("on est ici !!!!!");
        }

        if (debug||debugObj)
        { System.out.println("set obj " + tobj.getSymbolID() + " (" + tobj.getSessionID() + ") " + tobj.getX() + " " + tobj.getY() + " " + tobj.getAngle() + " " + tobj.getMotionSpeed() + " " + tobj.getRotationSpeed() + " " + tobj.getMotionAccel() + " " + tobj.getRotationAccel()); }
    }

    public void removeTuioObject(TuioObject tobj) {
        objectList.remove(tobj.getSessionID());
        objList.remove(tobj);
        Player player = Player.FindPlayerWithObjectId(tobj.getSymbolID(),Main.players);
        player.removed = true;
        /*Player player = Player.FindPlayerWithObjectId(tobj.getSymbolID(),Main.players);
        if (player != null) {
            player.disparait();
            Main.players.remove(player);
            player = null;
            deleteThatIdInIdPlayers(tobj);
            System.out.printf("Il a disparu !!!!");
        } */

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


    public void refresh(TuioTime frameTime) {
        repaint();
    }


}
