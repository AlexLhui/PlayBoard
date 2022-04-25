using System;
using System.Threading;
using System.Text;
using System.Collections;
using System.Collections.Generic;
using TUIOsharp;
using TUIOsharp.DataProcessors;
using TUIOsharp.Entities;
using OSCsharp.Data;
using OSCsharp.Net;
using OSCsharp.Utils;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class AddingPlayers : MonoBehaviour
{
    // This code is specific for the morpion game, it needs to be changed for another game !


    public Text instructions;
    //private int objCount;
    public Dictionary<string, TuioObject> playerPawns = new Dictionary<string, TuioObject>();
    int player; // state : 0 if no ClassId were saved, 1 when the ClassId for the tag for Gamma is saved, 2 when the ClassId for the tag for Ophe2782 is saved
    void Start()
    {
        player = 0;
       // objCount = GameManager.instance.SizeListObj();
    }
	
	void Update() //if soup  ��
    {
        if (GameManager.nbObj == 1 && player == 0)
        {
            player++;
            GameManager.GammaId = GameManager.listObj[0].ClassId;
        }
        if (GameManager.nbObj == 1 && player == 1)
        {
            GameManager.OpheId = GameManager.listObj[0].ClassId;
            if (GameManager.OpheId!= GameManager.GammaId)
            {
                player++;

            }
            else
            {
                instructions.text = "Gamma has been added !\nPlease place another Tag to add Ophe2782 !";
            }
        }
        else if (player == 0 && GameManager.nbObj == 0)
        {
            instructions.text = "Place one object on the table to add Gamma's GameTag !";
        }
        else if (player == 1 && GameManager.nbObj == 0)
        {
            instructions.text = "Place one object on the table to add Ophe2782's GameTag !";
        }
        else if (player < 2 && GameManager.nbObj > 1)
        {
            instructions.text = "Too much ! One at a time !";
        }
        else if(player == 2 && GameManager.nbObj != 0)
        {
            instructions.text = "Ophe2782 has been added !\nPlease remove all objects of the table !";
        }
        else if (player == 2 && GameManager.nbObj == 0)
        {
            GameManager.instance.UpdateGameState(GameManager.GameState.Morpion); 
        }
    }
}