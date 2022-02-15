import TUIO.*;
import java.util.*;
import javax.swing.*;

public class TestTuio2 extends JComponent implements TuioListener {

    public Hashtable<Long, TuioObject> objectList = new Hashtable<Long, TuioObject>();
    public ArrayList<Long> keyObjects = new ArrayList<Long>();
    public ArrayList<TuioObject> objList = new ArrayList<TuioObject>();

    private Hashtable<Long, TuioCursor> cursorList = new Hashtable<Long, TuioCursor>();
    private Hashtable<Long, TuioBlob> blobList = new Hashtable<Long, TuioBlob>();

    //public static final int finger_size = 18;
    //public static final int object_size = 60;
    public static final int table_size = 760; //il faut chagner cette valeur

    public static int width, height;
    private float scale = 1.0f;
    public boolean debug = false;
    public boolean debugObj = false;

    public void setSize(int w, int h) {
        super.setSize(w, h);
        width = w;
        height = h;
        scale = height / (float) TestTuio2.table_size;
    }

    public void addTuioObject(TuioObject tobj) {
        objectList.put(tobj.getSessionID(), tobj);
        keyObjects.add(tobj.getSessionID());
        objList.add(tobj);

        if (debug||debugObj)
        { System.out.println("add obj " + tobj.getSymbolID() + " (" + tobj.getSessionID() + ") " + tobj.getX() + " " + tobj.getY() + " " + tobj.getAngle()); }
    }

    public void updateTuioObject(TuioObject tobj) {
        if (!(objectList.contains(tobj.getSymbolID()))) {
            objectList.put(tobj.getSessionID(), tobj);
            keyObjects.add(tobj.getSessionID());
            objList.add(tobj);
        }
        if (debug||debugObj)
        { System.out.println("set obj " + tobj.getSymbolID() + " (" + tobj.getSessionID() + ") " + tobj.getX() + " " + tobj.getY() + " " + tobj.getAngle() + " " + tobj.getMotionSpeed() + " " + tobj.getRotationSpeed() + " " + tobj.getMotionAccel() + " " + tobj.getRotationAccel()); }
    }

    public void removeTuioObject(TuioObject tobj) {
        objectList.remove(tobj.getSessionID());

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
