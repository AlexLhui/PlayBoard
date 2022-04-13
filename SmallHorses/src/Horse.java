import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.geom.Rectangle2D;
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
    public int numberHorse; //From 1 to 4
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
                return j.getHorse3();
            } else if (j.getHorse4().getPosition() == position) {
                return j.getHorse4();
            }
        }
        return team.get(1).getHorse1(); //Arbitrary, we will never go to this line
    }

    public boolean isHorseMovable(ArrayList<Team> team, Horse horse, int res) {
        int currentPos = horse.getPosition();
        switch(currentPos) {
            case 1 :
            case 2 :
            case 3 :
            case 4 :
                if (res == 6) {
                    if (horse.isFilled(team, 17)) {
                        Horse disturbingHorse = getHorseFilled(team, 17);
                        return (disturbingHorse.color != horse.color);
                    } else {
                        return true;
                    }
                }
                else {
                    return false;
                }
            case 5 :
            case 6 :
            case 7 :
            case 8 :
                if (res == 6) {
                    if (horse.isFilled(team, 27)) {
                        Horse disturbingHorse = getHorseFilled(team, 27);
                        return (disturbingHorse.color != horse.color);
                    } else {
                        return true;
                    }
                }
                else {
                    return false;
                }
            case 9 :
            case 10 :
            case 11 :
            case 12 :
                if (res == 6) {
                    if (horse.isFilled(team, 37)) {
                        Horse disturbingHorse = getHorseFilled(team, 37);
                        return (disturbingHorse.color != horse.color);
                    } else {
                        return true;
                    }
                }
                else {
                    return false;
                }
            case 13 :
            case 14 :
            case 15 :
            case 16 :
                if (res == 6) {
                    if (horse.isFilled(team, 47)) {
                        Horse disturbingHorse = getHorseFilled(team, 47);
                        return (disturbingHorse.color != horse.color);
                    } else {
                        return true;
                    }
                }
                else {
                    return false;
                }
            case 17 :
            case 18 :
            case 19 :
            case 20 :
            case 21 :
            case 22 :
            case 23 :
            case 24 :
            case 25 :
            case 27 :
            case 28 :
            case 29 :
            case 30 :
            case 31 :
            case 32 :
            case 33 :
            case 34 :
            case 35 :
            case 37 :
            case 38 :
            case 39 :
            case 40 :
            case 41 :
            case 42 :
            case 43 :
            case 44 :
            case 45 :
            case 47 :
            case 48 :
            case 49 :
            case 50 :
            case 51 :
            case 52 :
            case 53 :
            case 54 :
            case 55 :
                return secondaryFunction(team,horse,res); //Not good to have a switch in a switch
//                switch (res) {
//                    case 6 :
//                        if (isHorseGoingTooFar(horse,6)) {
//                            isHorseMovable(team,horse,5);
//                        }
//                        else {
//                            if (isFilled(team, futurePos)) {
//                                Horse disturbingHorse = getHorseFilled(team, futurePos);
//                                if (disturbingHorse.color == horse.color) {
//                                    isHorseMovable(team, horse, 5);
//                                } else {
//                                    return true;
//                                }
//                            } else {
//                                return true;
//                            }
//                        }
//                        break;
//                    case 5 :
//                        if (isHorseGoingTooFar(horse,5)) {
//                            isHorseMovable(team,horse,4);
//                        }
//                        else {
//                            if (isFilled(team, futurePos)) {
//                                Horse disturbingHorse = getHorseFilled(team, futurePos);
//                                if (disturbingHorse.color == horse.color) {
//                                    isHorseMovable(team, horse, 4);
//                                } else {
//                                    return true;
//                                }
//                            } else {
//                                return true;
//                            }
//                        }
//                        break;
//                    case 4 :
//                        if (isHorseGoingTooFar(horse,4)) {
//                            isHorseMovable(team,horse,3);
//                        }
//                        else {
//                            if (isFilled(team, futurePos)) {
//                                Horse disturbingHorse = getHorseFilled(team, futurePos);
//                                if (disturbingHorse.color == horse.color) {
//                                    isHorseMovable(team, horse, 3);
//                                } else {
//                                    return true;
//                                }
//                            } else {
//                                return true;
//                            }
//                        }
//                        break;
//                    case 3 :
//                        if (isHorseGoingTooFar(horse,3)) {
//                            isHorseMovable(team,horse,2);
//                        }
//                        else {
//                            if (isFilled(team, futurePos)) {
//                                Horse disturbingHorse = getHorseFilled(team, futurePos);
//                                if (disturbingHorse.color == horse.color) {
//                                    isHorseMovable(team, horse, 2);
//                                } else {
//                                    return true;
//                                }
//                            } else {
//                                return true;
//                            }
//                        }
//                        break;
//                    case 2 :
//                        if (isHorseGoingTooFar(horse,2)) {
//                            isHorseMovable(team,horse,1);
//                        }
//                        else {
//                            if (isFilled(team, futurePos)) {
//                                Horse disturbingHorse = getHorseFilled(team, futurePos);
//                                if (disturbingHorse.color == horse.color) {
//                                    isHorseMovable(team, horse, 1);
//                                } else {
//                                    return true;
//                                }
//                            } else {
//                                return true;
//                            }
//                        }
//                        break;
//                    case 1 :
//                        if (isFilled(team,futurePos)) {
//                            Horse disturbingHorse = getHorseFilled(team,futurePos);
//                            if (disturbingHorse.color == horse.color) {
//                                System.out.println("isHorseMovable : Horse " + horse.numberHorse + " is not movable. I RETURN FALSE !");
//                                return false;
//                            }
//                            else {
//                                System.out.println("isHorseMovable : Horse  " + horse.numberHorse + " is movable. I RETURN TRUE !");
//                                return true;
//                            }
//                        }
//                        else {
//                            return true;
//                        }
//                    default :
//                        break;
//                }
            case 56 : //If the blue horse wants to move to the blue cases
                if (horse.color == Color.BLUE) {
                    if (isFilled(team,57)) {
                        return false;
                    }
                    else {
                        return (res == 1); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 57 :
                if (horse.color == Color.BLUE) {
                    if (isFilled(team,58)) {
                        return false;
                    }
                    else {
                        return (res == 2); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 58 :
                if (horse.color == Color.BLUE) {
                    if (isFilled(team,59)) {
                        return false;
                    }
                    else {
                        return (res == 3); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 59 :
                if (horse.color == Color.BLUE) {
                    if (isFilled(team,60)) {
                        return false;
                    }
                    else {
                        return (res == 4); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 26 :
                if (horse.color == Color.RED) {
                    if (isFilled(team,61)) {
                        return false;
                    }
                    else {
                        return (res == 1); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 61 :
                if (horse.color == Color.RED) {
                    if (isFilled(team,62)) {
                        return false;
                    }
                    else {
                        return (res == 2); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 62 :
                if (horse.color == Color.RED) {
                    if (isFilled(team,63)) {
                        return false;
                    }
                    else {
                        return (res == 3); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 63 :
                if (horse.color == Color.RED) {
                    if (isFilled(team,64)) {
                        return false;
                    }
                    else {
                        return (res == 4); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 36 :
                if (horse.color == Color.GREEN) {
                    if (isFilled(team,65)) {
                        return false;
                    }
                    else {
                        return (res == 1); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 65 :
                if (horse.color == Color.GREEN) {
                    if (isFilled(team,66)) {
                        return false;
                    }
                    else {
                        return (res == 2); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 66 :
                if (horse.color == Color.GREEN) {
                    if (isFilled(team,67)) {
                        return false;
                    }
                    else {
                        return (res == 3); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 67 :
                if (horse.color == Color.GREEN) {
                    if (isFilled(team,68)) {
                        return false;
                    }
                    else {
                        return (res == 4); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 46 :
                if (horse.color == Color.YELLOW) {
                    if (isFilled(team,69)) {
                        return false;
                    }
                    else {
                        return (res == 1); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 69 :
                if (horse.color == Color.YELLOW) {
                    if (isFilled(team,70)) {
                        return false;
                    }
                    else {
                        return (res == 2); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 70 :
                if (horse.color == Color.YELLOW) {
                    if (isFilled(team,71)) {
                        return false;
                    }
                    else {
                        return (res == 3); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 71 :
                if (horse.color == Color.YELLOW) {
                    if (isFilled(team,72)) {
                        return false;
                    }
                    else {
                        return (res == 4); //The player has to do the score he is facing
                    }
                }
                else {
                    return true;
                }
            case 60 :
            case 64 :
            case 68 :
            case 72 :
                return false;
        }
        return true;
    }

    public boolean switchCase6(ArrayList<Team> team, Horse horse) {
        int futurePos = getFuturePos(horse,6);
        if (isHorseGoingTooFar(horse, 6)) {
            return switchCase5(team, horse);
        }
        else {
            if (isFilled(team, futurePos)) {
                Horse disturbingHorse = getHorseFilled(team, futurePos);
                if (disturbingHorse.color == horse.color) {
                    return switchCase5(team, horse);
                }
                else {
                    return true;
                }
            }
            else {
                return true;
            }
        }
    }

    public boolean switchCase5(ArrayList<Team> team, Horse horse) {
        int futurePos = getFuturePos(horse,5);
        if (isHorseGoingTooFar(horse, 5)) {
            return switchCase4(team, horse);
        }
        else {
            if (isFilled(team, futurePos)) {
                Horse disturbingHorse = getHorseFilled(team, futurePos);
                if (disturbingHorse.color == horse.color) {
                    return switchCase4(team, horse);
                }
                else {
                    return true;
                }
            } else {
                return true;
            }
        }
    }

    public boolean switchCase4(ArrayList<Team> team, Horse horse){
        int futurePos = getFuturePos(horse,4);
        if (isHorseGoingTooFar(horse, 4)) {
            return switchCase3(team, horse);
        }
        else {
            if (isFilled(team, futurePos)) {
                Horse disturbingHorse = getHorseFilled(team, futurePos);
                if (disturbingHorse.color == horse.color) {
                    return switchCase3(team, horse);
                }
                else {
                    return true;
                }
            }
            else {
                return true;
            }
        }
    }

    public boolean switchCase3(ArrayList<Team> team, Horse horse){
        int futurePos = getFuturePos(horse,3);
        if (isHorseGoingTooFar(horse, 3)) {
            return switchCase2(team, horse);
        }
        else {
            if (isFilled(team, futurePos)) {
                Horse disturbingHorse = getHorseFilled(team, futurePos);
                if (disturbingHorse.color == horse.color) {
                    return switchCase2(team, horse);
                }
                else {
                    return true;
                }
            }
            else {
                return true;
            }
        }
    }

    public boolean switchCase2(ArrayList<Team> team, Horse horse) {
        int futurePos = getFuturePos(horse,2);
        if (isHorseGoingTooFar(horse, 2)) {
            return switchCase1(team, horse);
        }
        else {
            if (isFilled(team, futurePos)) {
                Horse disturbingHorse = getHorseFilled(team, futurePos);
                if (disturbingHorse.color == horse.color) {
                    return switchCase1(team, horse);
                }
                else {
                    return true;
                }
            }
            else {
                return true;
            }
        }
    }

    public boolean switchCase1(ArrayList<Team> team, Horse horse) {
        int futurePos = getFuturePos(horse, 1);
        if (isFilled(team, futurePos)) {
            Horse disturbingHorse = getHorseFilled(team, futurePos);
            if (disturbingHorse.color == horse.color) {
                return secondaryFunction(team, horse, 0);
            }
            else {
                return true;
            }
        }
        else {
            return true;
        }
    }

    public int getFuturePos(Horse horse, int res) {
        int currentPos = horse.getPosition();
        int futurePos = currentPos+res;
        if (futurePos > 56) {
            futurePos = futurePos - 40;
        }
        return futurePos;
    }

    public boolean secondaryFunction(ArrayList<Team> team, Horse horse, int res) {
        switch (res) {
            case 6 -> {
                return switchCase6(team, horse);
            }
//                System.out.println("Case 6");
//                if (isHorseGoingTooFar(horse, 6)) {
//                    System.out.println("Horse is going too far with 6.");
//                    secondaryFunction(team, horse, 5);
//                }
//                else {
//                    if (isFilled(team, futurePos)) {
//                        System.out.println("Is filled");
//                        Horse disturbingHorse = getHorseFilled(team, futurePos);
//                        if (disturbingHorse.color == horse.color) {
//                            System.out.println("Here 1");
//                            secondaryFunction(team, horse, 5);
//                        }
//                        else {
//                            System.out.println("Here 2");
//                            return true;
//                        }
//                    }
//                    else {
//                        System.out.println("Here 3");
//                        return true;
//                    }
//                }
//                break;
            case 5 -> {
                return switchCase5(team, horse);
            }
//                System.out.println("Case 5");
//                if (isHorseGoingTooFar(horse, 5)) {
//                    System.out.println("Horse is going too far with 5.");
//                    secondaryFunction(team, horse, 4);
//                }
//                else {
//                    if (isFilled(team, futurePos)) {
//                        System.out.println("Is filled");
//                        Horse disturbingHorse = getHorseFilled(team, futurePos);
//                        if (disturbingHorse.color == horse.color) {
//                            System.out.println("Here 1");
//                            secondaryFunction(team, horse, 4);
//                        } else {
//                            System.out.println("Here 2");
//                            return true;
//                        }
//                    } else {
//                        System.out.println("Here 3");
//                        return true;
//                    }
//                }
//                break;
            case 4 -> {
                return switchCase4(team, horse);
            }
//                System.out.println("Case 4");
//                if (isHorseGoingTooFar(horse, 4)) {
//                    System.out.println("Horse is going too far with 4.");
//                    secondaryFunction(team, horse, 3);
//                }
//                else {
//                    if (isFilled(team, futurePos)) {
//                        System.out.println("Is filled");
//                        Horse disturbingHorse = getHorseFilled(team, futurePos);
//                        if (disturbingHorse.color == horse.color) {
//                            System.out.println("Here 1");
//                            secondaryFunction(team, horse, 3);
//                        }
//                        else {
//                            System.out.println("Here 2");
//                            return true;
//                        }
//                    } else {
//                        System.out.println("Here 3");
//                        return true;
//                    }
//                }
//                break;
            case 3 -> {
                return switchCase3(team, horse);
            }
//                System.out.println("Case 3");
//                if (isHorseGoingTooFar(horse, 3)) {
//                    System.out.println("Horse is going too far with 3.");
//                    secondaryFunction(team, horse, 2);
//                }
//                else {
//                    if (isFilled(team, futurePos)) {
//                        System.out.println("Is filled");
//                        Horse disturbingHorse = getHorseFilled(team, futurePos);
//                        if (disturbingHorse.color == horse.color) {
//                            System.out.println("Here 1");
//                            secondaryFunction(team, horse, 2);
//                        }
//                        else {
//                            System.out.println("Here 2");
//                            return true;
//                        }
//                    }
//                    else {
//                        System.out.println("Here 3");
//                        return true;
//                    }
//                }
//                break;
            case 2 -> {
                return switchCase2(team, horse);
            }
//                System.out.println("Case 2");
//                if (isHorseGoingTooFar(horse, 2)) {
//                    System.out.println("Horse is going too far with 2.");
//                    secondaryFunction(team, horse, 1);
//                }
//                else {
//                    if (isFilled(team, futurePos)) {
//                        System.out.println("Is filled");
//                        Horse disturbingHorse = getHorseFilled(team, futurePos);
//                        if (disturbingHorse.color == horse.color) {
//                            System.out.println("Here 1");
//                            secondaryFunction(team, horse, 1);
//                        }
//                        else {
//                            System.out.println("Here 2");
//                            return true;
//                        }
//                    }
//                    else {
//                        System.out.println("Here 3");
//                        return true;
//                    }
//                }
//                break;
            case 1 -> {
                return switchCase1(team, horse);
            }
//                System.out.println("Case 1");
//                if (isFilled(team, futurePos)) {
//                    System.out.println("Is filled");
//                    Horse disturbingHorse = getHorseFilled(team, futurePos);
//                    if (disturbingHorse.color == horse.color) {
//                        System.out.println("isHorseMovable : Horse " + horse.numberHorse + " is not movable. I RETURN FALSE !");
//                        secondaryFunction(team, horse, 0);
//                    }
//                    else {
//                        System.out.println("isHorseMovable : Horse  " + horse.numberHorse + " is movable. I RETURN TRUE !");
//                        return true;
//                    }
//                }
//                else {
//                    System.out.println("ELSE ?");
//                    return true;
//                }
//                break;
            default -> {
                return false; //case 0
            }
        }
    }

    public boolean isHorseGoingTooFar(Horse horse, int res) { //Horse has almost arrived
        if (Color.BLUE.equals(horse.color) && horse.position >= 51 && horse.position <= 56) {
            return horse.getPosition() + res > 56;
        } else if (Color.RED.equals(horse.color) && horse.position >=21 && horse.position <= 26) {
            return horse.getPosition() + res > 26;
        } else if (Color.GREEN.equals(horse.color) && horse.position >=31 && horse.position <= 36) {
            return horse.getPosition() + res > 36;
        } else if (Color.YELLOW.equals(horse.color) && horse.position >=41 && horse.position <= 46) {
            return horse.getPosition() + res > 46;
        }
        return false;
    }

    public boolean isOneHorseMovable(ArrayList<Team> team, int player, int res) {
        boolean out1 = isHorseMovable(team,team.get(player).getHorse1(),res);
        boolean out2 = isHorseMovable(team,team.get(player).getHorse2(),res);
        boolean out3 = isHorseMovable(team,team.get(player).getHorse3(),res);
        boolean out4 = isHorseMovable(team,team.get(player).getHorse4(),res);
        return (out1 || out2 || out3 || out4);
    }

    public void beenEaten(ArrayList<Team> team, Horse chev){
        if(chev.color == Color.BLUE){
            int freePos = freeHomeCase(team,0);
            chev.setPosAndImg(chev, freePos);
            chev.situation = Etat.STABLE;
        }
        else if(chev.color == Color.RED){
            int freePos = freeHomeCase(team,4);
            chev.setPosAndImg(chev, freePos);
            chev.situation = Etat.STABLE;
        }
        else if(chev.color == Color.GREEN){
            int freePos = freeHomeCase(team,8);
            chev.setPosAndImg(chev, freePos);
            chev.situation = Etat.STABLE;
        }
        else if(chev.color == Color.YELLOW){
            int freePos = freeHomeCase(team,12);
            chev.setPosAndImg(chev, freePos);
            chev.situation = Etat.STABLE;
        }
    }

    public Horse(int position, Etat situation, String filename, int N){
        this.position = position;
        this.situation = situation;
        this.image = new ImageView(new Image(filename,4*squareSize,squareSize,true,true));
        this.image.setViewport(new javafx.geometry.Rectangle2D(N*squareSize, 0,squareSize,squareSize));
    }


    public int freeHomeCase(ArrayList<Team> team, int offset) {
        for (int i = 1; i <= 4; i++) {
            if (!isFilled(team,offset+i)) {
                return (offset+i);
            }
        }
        return 0;
    }

    public void setPosAndImg(Horse horse, int pos) {
        horse.setPosition(pos);
        double x = getXPos(pos);
        double y = getYPos(pos);
        horse.getImage().setX(x);
        horse.getImage().setY(y);
    }

    public double getXPos(int pos) {
        return switch (pos) {
            case 1, 4, 25, 26, 27, 5, 8 -> middleX - squareSize / 2 - 5 * squareSize - 5 * lineSize;
            case 2, 3, 24, 61, 28, 6, 7 -> middleX - squareSize / 2 - 4 * squareSize - 4 * lineSize;
            case 23, 62, 29 -> middleX - squareSize / 2 - 3 * squareSize - 3 * lineSize;
            case 22, 63, 30 -> middleX - squareSize / 2 - 2 * squareSize - 2 * lineSize;
            case 17, 18, 19, 20, 21, 64, 31, 32, 33, 34, 35 -> middleX - squareSize / 2 - squareSize - lineSize;
            case 56, 57, 58, 59, 60, 68, 67, 66, 65, 36 -> middleX - squareSize / 2;
            case 55, 54, 53, 52, 51, 72, 41, 40, 39, 38, 37 -> middleX - squareSize / 2 + squareSize + lineSize;
            case 50, 71, 42 -> middleX - squareSize / 2 + 2 * squareSize + 2 * lineSize;
            case 49, 70, 43 -> middleX - squareSize / 2 + 3 * squareSize + 3 * lineSize;
            case 13, 16, 48, 69, 44, 9, 12 -> middleX - squareSize / 2 + 4 * squareSize + 4 * lineSize;
            default -> middleX - squareSize / 2 + 5 * squareSize + 5 * lineSize;
        };
    }

    public double getYPos(int pos) {
        return switch (pos) {
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

    public String getColor() {
        if (color == Color.RED) {
            return "red";
        }
        else if (color == Color.BLUE) {
            return "blue";
        }
        else if (color == Color.YELLOW) {
            return "yellow";
        }
        else if (color == Color.GREEN) {
            return "green";
        }
        return "IDC";
    }

    public int coordToPos(double x, double y){
        int a = 1;
        double x_0 = getXPos(a)+squareSize/2;
        double y_0 = getYPos(a)+squareSize/2;
        System.out.println("X0 : " + x_0 + ", Y0 : " + y_0);
        double d = Math.sqrt((x*x-x_0*x_0)*(x*x-x_0*x_0) + (y*y-y_0*y_0)*(y*y-y_0*y_0));
        int pos = 1;
        for(int i = 2; i<=72; i++){
            double x_1 = getXPos(i)+squareSize/2;
            double y_1 = getYPos(i)+squareSize/2;
            double disti = Math.sqrt((x*x-x_1*x_1)*(x*x-x_1*x_1) + (y*y-y_1*y_1)*(y*y-y_1*y_1));
            if(disti<d){
                d = disti;
                pos = i;
            }
        }
//        if (d > 50000) { //Arbitrary distance
//            return 0;
//        }
//        else {
        return pos;
//        }
    }
}
