using System;
using System.Collections;
using System.Collections.Generic;
using TUIOsharp.Entities;
using UnityEngine;
using UnityEngine.UI;

public class SaveMorpion : MonoBehaviour
{
    public GameObject GPawn;
    public GameObject OPawn;
    public Text debug;
    public Text debugZone;
    public Text grilleText;
    public Text gameResult;
    private int morpionState = 0; //0 new game | 1 player one turn | 2 player two turn | 3 player one won | 4 player two won | 5 five draw | 6 player want to start a new game, remove objects

    public GameObject[] GPawns = new GameObject[5];
    public GameObject[] OPawns = new GameObject[5];
    private static int[] gameBoard;
 
    
    public void Update()
    {
        switch (morpionState)
        {
            case 0:
                newGame();
                break;
            case 1:
                morpionState = PlayerTurn(1);
                int i = grillTest();
                switch (i)
                {
                    case 0:
                        morpionState = 5; //draw
                        break;
                    case 1: //player 1 win
                        morpionState = 3;
                        break;
                    case 2: // player 2 win
                        morpionState = 4; 
                        break;
                    case -1: //the game continue
                        break;
                }
                break;
            case 2:
                morpionState = PlayerTurn(2);
                int j = grillTest();
                switch (j)
                {
                    case 0:
                        morpionState = 5; //draw
                        break;
                    case 1:
                        morpionState = 3;
                        
                        break;
                    case 2:
                        morpionState = 4;
                       
                        break;
                    case -1:
                        break;
                }
                break;
            case 3:
                gameResult.text = "Gamma won !";
                
                break;
            case 4:
                gameResult.text = "Ophe2782 won !";
                break;
            case 5:
                gameResult.text = "Draw !";
                break;
            
        }
    }

    // Start is called before the first frame update
    private void newGame()
    {
        gameBoard = new int[9] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        morpionState = 1;
        gameResult.text = "";
    }
        
    private void initPawns() //places all pawns and setactive = false
    {

    }

    public void PlacePawn()
    { 
        GPawn = (GameObject)Instantiate(GPawn);
        GPawn.transform.position = new Vector3(400f, 757, 0f);
    }

    public void PlacePawn1()
    {
        if (GameManager.nbObj != 0)
        {
            int position = GameManager.GivePosOfClassId(GameManager.GammaId);
            if (position != -1)
            {
                TuioObject obj = GameManager.listObj[position];
                GPawn = (GameObject)Instantiate(GPawn);
                GPawn.transform.position = new Vector3(obj.GetX() * 1920, (1 - obj.GetY()) * 1080, 0f);
                String monString = "Gamma ClassId :" + obj.ClassId + "\n Position x : " + obj.X * 1920 + "\n Position y :" + (1 - obj.Y) * 1080 + "\n Angle :" + obj.Angle + "\n nombre d'objets dans la liste :" + GameManager.nbObj;
                debug.text = monString;
                int z = whichCase(obj);
                String zone = "Zone : " + z;
                debugZone.text = zone;
            }
        }
        //GameObject GPawn1; 
        /*if (GameManager.nbObj!=0) {
            TuioObject obj = GameManager.listObj[0];
            //private GameObject GPawn1 = (GameObject)Instantiate(GPawn1);
            //GPawn = Instantiate(Resources.Load("GPawn", typeof(GameObject))) as GameObject;
            GPawn = (GameObject)Instantiate(GPawn);
            GPawn.transform.position = new Vector3(obj.GetX()*1920, (1-obj.GetY())*1080, 0f);
            String monString = "ClassId :" + obj.ClassId + "\n Position x : " + obj.X*1920 + "\n Position y :" + (1-obj.Y)*1080 + "\n Angle :"+obj.Angle + "\n nombre d'objets dans la liste :"+ GameManager.nbObj;
            debug.text = monString;
            int z = whichCase(obj);
            String zone = "Zone : " + z;
            debugZone.text = zone;
        }
         */
        
    }

    private int PlayerTurn(int player)
    {
        if (GameManager.nbObj == 1) //if there is no object on the table we don't do the next step
        {
            bool completed = false;
            //while (!completed)
            //{
            int index = -1;
            if (player == 1) 
            {
                index = GameManager.indexOfObjectWith(GameManager.GammaId);
            }
            else
            {
                index = GameManager.indexOfObjectWith(GameManager.OpheId);
            }
                
            if (index != -1) // equality if the object is neither in the list or on the table
            {
                if (player == 1)
                {
                        
                    TuioObject obj = GameManager.listObj[index];
                    int zone = whichCase(obj);
                    debug.text = "Player 1";
                    if (zone != -1)
                    {
                        debug.text = "Zone != -1";
                        if (gameBoard[zone - 1] == 0)
                        {
                            float x = giveXCoordonates(zone);
                            float y = giveYCoordonates(zone);
                            GPawn = (GameObject)Instantiate(GPawn);
                            GPawn.transform.position = new Vector3(x, y, 0f);
                            gameBoard[zone - 1] = 1;
                            player = 2;
                            String monString = "Gamma ClassId :" + obj.ClassId + "\n Position x : " + obj.X * 1920 + "\n Position y :" + (1 - obj.Y) * 1080 + "\n Angle :" + obj.Angle + "\n nombre d'objets dans la liste :" + GameManager.nbObj;
                            debug.text = monString;
                            String z = "Zone : " + zone;
                            debugZone.text = z;
                            grilleText.text = gameBoard[0] + " | " + gameBoard[1] + " | " + gameBoard[2] + " | " + "\n" + gameBoard[3] + " | " + gameBoard[4] + " | " + gameBoard[5] + " | " + "\n" + gameBoard[6] + " | " + gameBoard[7] + " | " + gameBoard[8];

                        }
                    }
                }
                else
                {
                TuioObject obj = GameManager.listObj[index];
                int zone = whichCase(obj);
                    if (zone != -1)
                    {
                        if (gameBoard[zone - 1] == 0)
                        {
                            float x = giveXCoordonates(zone);
                            float y = giveYCoordonates(zone);
                            OPawn = (GameObject)Instantiate(OPawn);
                            OPawn.transform.position = new Vector3(x, y, 0f);
                            gameBoard[zone - 1] = -1;
                            player = 1;
                            grilleText.text = gameBoard[0] + " | " + gameBoard[1] + " | " + gameBoard[2] + " | " + "\n" + gameBoard[3] + " | " + gameBoard[4] + " | " + gameBoard[5] + " | " + "\n" + gameBoard[6] + " | " + gameBoard[7] + " | " + gameBoard[8];
                        }
                    }
                }
            }
            //}
        }
        return player;
    }
        
    private int whichCase(TuioObject obj) // given a TuioObject it returns the number of the case corresponding to the place of the object on the morpion board
    {
        int zone = -1;
        // obj.GetX()*1920, (1-obj.GetY())*1080
        float x = obj.X*1920;
        float y = (1 - obj.Y) * 1080;
        int line;
        int column;
        float x1 = 731.0f;
        float x2 = 1290.0f;
        float y1 = 328.0f;
        float y2 = 655.0f;
        

        if (x<x1)
        {
            column = 1;
        }
        else if (x>x1&&x<x2)
        {
            column = 2;
        }
        else
        {
            column = 3;
        }

        if (y<y1)
        {
            line = 1;
        }
        else if (y>y1&&y<y2)
        {
            line = 2;
        }
        else
        {
            line = 3;
        }

        switch (line, column)
        {
            case (1, 1):
                zone = 7;
                break;
            case (1, 2):
                zone = 8;
                break;
            case (1, 3):
                zone = 9;
                break;
            case (2, 1):
                zone = 4;
                break;
            case (2, 2):
                zone = 5;
                break;
            case (2, 3):
                zone = 6;
                break;
            case (3, 1):
                zone = 1;
                break;
            case (3, 2):
                zone = 2;
                break;
            case (3, 3):
                zone = 3;
                break;
        }
        return zone;
    }

    private float giveXCoordonates(int zone)
    {
        float x = -1;
        switch (zone)
        {
            case 1:
            case 4:
            case 7:
                x = 365; // 588
                break;
            case 2:
            case 5:
            case 8:
                x = 995;  //1010
                break;
            case 3:
            case 6:
            case 9:
                x = 1590;  //1620
                break;
        }
        return x;
    }

    private float giveYCoordonates(int zone)
    {
        float y = -1;
        switch (zone)
        {
            case 1:
            case 2:
            case 3:
                y = 767; //887       //193
                break;
            case 4:
            case 5:
            case 6:
                y = 465; //589     //491
                break;
            case 7:
            case 8:
            case 9:
                y = 168; //274     //806
                break;
        }
        return y;
    }


    public int grillTest()
    {
        int pW = -1; //playerWinning variable : it  will be equal to 0 for equality, 1 when P1 win, 2 when p2 win, -1 if the game is still going
        int sum = 0;


        for (int j = 0; j < 7; j += 3) //v�rifie les lignes
        {
            sum = 0;
            for (int i = 0; i < 3; i++)
            {
                sum += gameBoard[j + i];

                if (sum == 3)
                {
                    pW = 1;

                }
                else if (sum == -3)

                {
                    pW = 2;

                }
            }
        }

        sum = 0;
            for (int j = 0; j < 3; j++) //v�rifie les colonnes
            {
                sum = 0;
                for (int i = 0; i < 7; i += 3)
                {
                    sum += gameBoard[j + i];

                    if (sum == 3)
                    {
                        pW = 1;

                    }
                    else if(sum == -3)

                      {
                        pW = 2;

                    }
                }
            }

         sum = 0;
         if (gameBoard[0] + gameBoard[4] + gameBoard[8] == 3)
         {
             pW = 1;
         }
         
         if (gameBoard[0] + gameBoard[4] + gameBoard[8] == -3)
         {
             pW = 2;
         }
         
         if (gameBoard[2] + gameBoard[4] + gameBoard[6] == 3)
         {
             pW = 1;
         }
         
         if (gameBoard[2] + gameBoard[4] + gameBoard[6] == -3)
         {
             pW = 2;
         }

        bool isEmptyCase = false; //isEmptyCase -> if one case in the grid is not occupied it is equal to true
        for (int i = 0; i < 9; i++)
        {
            if (gameBoard[i] == 0)
            {
                isEmptyCase = true;
            }

        }

        if (!isEmptyCase && pW == -1)
        {
            pW = 0;
        }

        return pW; // equals 0 for equality, 1 for victory of player 1, 2 of player 2 and -1 if the game is not over yet

     }

 }
    



