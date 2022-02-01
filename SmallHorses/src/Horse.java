import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;


public class Horse {

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // getScreenSize() returns the size of the screen in pixels
    double screenWidth = size.getWidth(); // screenWidth will store the width of the screen
    double screenHeight = size.getHeight(); // screenHeight will store the height of the screen

    public final int sizeX = 44;
    public final int sizeY = 44;
    public final double middleX = screenWidth/2;
    public final double middleY = screenHeight/2;
    public final double squareSize = sizeX*screenHeight/600;
    public final double lineSize = 5*screenHeight/600;

    public enum Etat{STABLE, CIRCUIT, ARRIVED}
    public Etat situation;
    private int position;
    private int numberHorse; //From 1 to 4
    private final ImageView image;
    public Color color;

    public int getNumberHorse(){
        return this.numberHorse;
    }
    public int getPosition(){
        return this.position;
    }
    public ImageView getImage(){
        return image;
    }
    public void setNumberHorse(int numbHorse){numberHorse = numbHorse;}

    public void setPosition(int pos){
        position = pos;
    }

    public void setColor(Color couleur){
        color = couleur;
    }

    public void initPosition(Horse chev){
        if(chev.color == Color.BLUE){
            chev.setPosition(17);
            chev.getImage().setX(chev.getXPos(17));
            chev.getImage().setY(chev.getYPos(17));
        }
        else if(chev.color == Color.RED){
            chev.setPosition(27);
            chev.getImage().setX(chev.getXPos(27));
            chev.getImage().setY(chev.getYPos(27));
        }
        else if(chev.color == Color.GREEN){
            chev.setPosition(37);
            chev.getImage().setX(chev.getXPos(37));
            chev.getImage().setY(chev.getYPos(37));
        }
        else if(chev.color == Color.YELLOW){
            chev.setPosition(47);
            chev.getImage().setX(chev.getXPos(47));
            chev.getImage().setY(chev.getYPos(47));
        }
    }

    public void deployHorse(ArrayList<Team> team, Team currentTeam) {
        Color color = currentTeam.color;
        int filledCase = 0;
        int i;
        if (color == Color.YELLOW) {
            for (i = 0; i < 4; i++) {
                if (isFilled(team,13+i)) { //13 is the first position of yellow start position
                    filledCase = 13+i;
                }
            }
            if (filledCase != 0) { //If there is a horse to deploy
                switch (filledCase) {
                    case 13 -> currentTeam.getHorse1().setPosAndImg(currentTeam.getHorse1(), 47);
                    case 14 -> currentTeam.getHorse2().setPosAndImg(currentTeam.getHorse2(), 47);
                    case 15 -> currentTeam.getHorse3().setPosAndImg(currentTeam.getHorse3(), 47);
                    default -> //case 16
                            currentTeam.getHorse4().setPosAndImg(currentTeam.getHorse4(), 47);
                }
            }
        }
        if (color == Color.BLUE) {
            for (i = 0; i < 4; i++) {
                if (isFilled(team,1+i)) { //13 is the first position of yellow start position
                    filledCase = 1+i;
                }
            }
            if (filledCase != 0) { //If there is a horse to deploy
                switch (filledCase) {
                    case 1 -> currentTeam.getHorse1().setPosAndImg(currentTeam.getHorse1(), 17);
                    case 2 -> currentTeam.getHorse2().setPosAndImg(currentTeam.getHorse2(), 17);
                    case 3 -> currentTeam.getHorse3().setPosAndImg(currentTeam.getHorse3(), 17);
                    default -> //case 4
                            currentTeam.getHorse4().setPosAndImg(currentTeam.getHorse4(), 17);
                }
            }
        }
        if (color == Color.GREEN) {
            for (i = 0; i < 4; i++) {
                if (isFilled(team,9+i)) { //13 is the first position of yellow start position
                    filledCase = 9+i;
                }
            }
            if (filledCase != 0) { //If there is a horse to deploy
                switch (filledCase) {
                    case 9 -> currentTeam.getHorse1().setPosAndImg(currentTeam.getHorse1(), 37);
                    case 10 -> currentTeam.getHorse2().setPosAndImg(currentTeam.getHorse2(), 37);
                    case 11 -> currentTeam.getHorse3().setPosAndImg(currentTeam.getHorse3(), 37);
                    default -> //case 12
                            currentTeam.getHorse4().setPosAndImg(currentTeam.getHorse4(), 37);
                }
            }
        }
        if (color == Color.RED) {
            for (i = 0; i < 4; i++) {
                if (isFilled(team,5+i)) { //13 is the first position of yellow start position
                    filledCase = 5+i;
                }
            }
            if (filledCase != 0) { //If there is a horse to deploy
                switch (filledCase) {
                    case 5 -> currentTeam.getHorse1().setPosAndImg(currentTeam.getHorse1(), 27);
                    case 6 -> currentTeam.getHorse2().setPosAndImg(currentTeam.getHorse2(), 27);
                    case 7 -> currentTeam.getHorse3().setPosAndImg(currentTeam.getHorse3(), 27);
                    default -> //case 8
                            currentTeam.getHorse4().setPosAndImg(currentTeam.getHorse4(), 27);
                }
            }
        }
    }

    public boolean isFilled(ArrayList<Team> team, int position){
        for (Team j : team) {
            if (j.getHorse1().getPosition() == position) {
                return true;
            } else if (j.getHorse2().getPosition() == position) {
                return true;
            } else if (j.getHorse3().getPosition() == position) {
                return true;
            } else if (j.getHorse4().getPosition() == position) {
                return true;
            }
        }
    return false;
    }

    public Horse getHorseFilled(ArrayList<Team> team, int position){
        for (Team j : team) {
            if (j.getHorse1().getPosition() == position) {
                return j.getHorse1();
            } else if (j.getHorse2().getPosition() == position) {
                return j.getHorse2();
            } else if (j.getHorse3().getPosition() == position) {
                return j.getHorse2();
            } else if (j.getHorse4().getPosition() == position) {
                return j.getHorse4();
            }
        }
        return team.get(1).getHorse1(); //Arbitrary, we will never go to this line
    }

    public void beenEaten(Horse chev){
        if(chev.color == Color.BLUE){
            chev.setPosAndImg(chev, chev.getNumberHorse());
            //Replacer le cheval dans sa case d'origine !!
        }
        else if(chev.color == Color.RED){
            chev.setPosAndImg(chev, chev.getNumberHorse()+4);
        }
        else if(chev.color == Color.GREEN){
            chev.setPosAndImg(chev, chev.getNumberHorse()+8);
        }
        else if(chev.color == Color.YELLOW){
            chev.setPosAndImg(chev, chev.getNumberHorse()+12);
        }
    }

    public Horse(int position, Etat situation, String filename, int N){
        this.position = position;
        this.situation = situation;
        this.image = new ImageView(new Image(filename,4*squareSize,squareSize,true,true));
        this.image.setViewport(new Rectangle2D(N*squareSize, 0,squareSize,squareSize));
    }

    public void setPosAndImg(Horse horse, int pos) {
        double x = getXPos(pos);
        double y = getYPos(pos);
        horse.getImage().setX(x);
        horse.getImage().setY(y);
    }

    public double getXPos(int pos) {
        double x;
        switch(pos) {
            case 1 :
            case 4 :
            case 25 :
            case 26 :
            case 27 :
            case 5 :
            case 8 :
                x = middleX-squareSize/2-5*squareSize-5*lineSize;
                break;
            case 2 :
            case 3 :
            case 24 :
            case 61 :
            case 28 :
            case 6 :
            case 7 :
                x = middleX-squareSize/2-4*squareSize-4*lineSize;
                break;
            case 23 :
            case 62 :
            case 29 :
                x = middleX-squareSize/2-3*squareSize-3*lineSize;
            case 22 :
            case 63 :
            case 30 :
                x = middleX-squareSize/2-2*squareSize-2*lineSize;
                break;
            case 17 :
            case 18 :
            case 19 :
            case 20 :
            case 21 :
            case 64 :
            case 31 :
            case 32 :
            case 33 :
            case 34 :
            case 35 :
                x = middleX-squareSize/2-squareSize-lineSize;
                break;
            case 56 :
            case 57 :
            case 58 :
            case 59 :
            case 60 :
            case 68 :
            case 67 :
            case 66 :
            case 65 :
            case 36 :
                x = middleX-squareSize/2;
                break;
            case 55 :
            case 54 :
            case 53 :
            case 52 :
            case 51 :
            case 72 :
            case 41 :
            case 40 :
            case 39 :
            case 38 :
            case 37 :
                x = middleX-squareSize/2+squareSize+lineSize;
                break;
            case 50 :
            case 71 :
            case 42 :
                x = middleX-squareSize/2+2*squareSize+2*lineSize;
                break;
            case 49 :
            case 70 :
            case 43 :
                x = middleX-squareSize/2+3*squareSize+3*lineSize;
                break;
            case 13 :
            case 16 :
            case 48 :
            case 69 :
            case 44 :
            case 9 :
            case 12 :
                x = middleX-squareSize/2+4*squareSize+4*lineSize;
                break;
            default :
                x = middleX-squareSize/2+5*squareSize+5*lineSize;
                break;
        }
        return x;
    }

    public double getYPos(int pos) {
        return switch (position) {
            case 8, 7, 35, 36, 37, 12, 11 -> middleY - squareSize/2 - 5 * squareSize - 5 * lineSize;
            case 5, 6, 34, 65, 38, 9, 10 -> middleY - squareSize/2 - 4 * squareSize - 4 * lineSize;
            case 33, 66, 39 -> middleY - squareSize/2 - 3 * squareSize - 3 * lineSize;
            case 32, 67, 40 -> middleY - squareSize/2 - 2 * squareSize - 2 * lineSize;
            case 27, 28, 29, 30, 31, 68, 41, 42, 43, 44, 45 -> middleY - squareSize/2 - squareSize - lineSize;
            case 26, 61, 62, 63, 64, 72, 71, 70, 69, 46 -> middleY - squareSize/2;
            case 25, 24, 23, 22, 21, 60, 51, 50, 49, 48, 47 -> middleY - squareSize/2 + squareSize + lineSize;
            case 20, 59, 52 -> middleY - squareSize/2 + 2 * squareSize + 2 * lineSize;
            case 19, 58, 53 -> middleY - squareSize/2 + 3 * squareSize + 3 * lineSize;
            case 4, 3, 18, 57, 54, 16, 15 -> middleY - squareSize/2 + 4 * squareSize + 4 * lineSize;
            default -> middleY - squareSize/2 + 5 * squareSize + 5 * lineSize;
        };
    }
}
