using System;
using System.Collections;
using System.Collections.Generic;
using TUIOsharp.Entities;
using UnityEngine;
using UnityEngine.UI;

public class HowManyPlayers : MonoBehaviour
{
    //This code is for choosing the number of players for the SpaceTraps game
    #region Variables
    public GameObject DiceLigne;
    public Text results;
    public static int numberOfPlayers = 4;
    private int state = 0; // 0 is for dice state, 1 for choosing the number of player state and 2 for waiting the number of tag
    #endregion
    private int WhatIsTheNumberOfPlayer(float angle2)
    {
        int p = 4;
        if (angle2 < 72)
        {
            p = 2;
        }
        else if (angle2 >= 72 && angle2 < 144)
        {
            p = 3;
        }
        else if (angle2 >= 144 && angle2 < 216)
        {
            p = 4;
        }
        else if (angle2 >= 216 && angle2 < 288)
        {
            p = 5;
        }
        else
        {
            p = 6;
        }

        return p;
    }

    private void Awake()
    {
        DiceScript.diceId = -1;
    }

    private void Update()
    {

        if (state == 0)
        {
            results.text = "Please place the dice object.";
            // dice = false;
        }
        else if (state == 1)
        {
            results.text = "Now select the number of player by rotating an object and pressing the next button.";
            if (GameManager.listObj.Count == 0) return;
            var angle = GameManager.listObj[0].Angle;
            angle = angle * 360 / (2 * (float) Math.PI);
            angle = Math.Abs(angle);
            var angle2 = -angle;

            DiceLigne.transform.eulerAngles = new Vector3(
                DiceLigne.transform.eulerAngles.x,
                DiceLigne.transform.eulerAngles.y,
                angle2
            );

            numberOfPlayers = WhatIsTheNumberOfPlayer(angle);
            results.text = "Number Of Player : " + numberOfPlayers.ToString();
        }
        else
        {
            results.text = "Place all the player objects on the table.";
        }
    }

    public void StartTheGame()
    {
        GameManager.instance.UpdateGameState(GameManager.GameState.SpaceTraps);
    }
}
