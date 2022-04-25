using System;
using System.Collections.Generic;
using TUIOsharp.Entities;
using UnityEngine;
using UnityEngine.UI;

public class SSAddPlayer : MonoBehaviour
{
    // This code is specific for the SpaceSpeed game, it needs to be changed for another game !
    #region Variables
    public Text instructions;
    //public Text[] debugText;
    private int[] _nbTagsOnPoints = {0,0,0,0}; //indicates the number of tags on one point, it must be 1,1,1,1 so that we can set the ids to the player
    private float[] _blueGiantsXPos = {239.0f, 959.0f, 1679.0f, 959.0f};
    private float[] _blueGiantsYPos = {539.0f, 269.0f, 539.0f, 809.0f};
    #endregion
	
	void Update() //if soup  ��
    {
        for (int p = 0; p < 4; p++)
        {
            _nbTagsOnPoints[p] = 0;
        }
        if (GameManager.nbObj == 4)
        {
            for (int i = 0; i < 4; i++)
            {
                float distance;
                int player = -1;
                var obj = GameManager.listObj[i];
                for (int k = 0; k < 4; k++)
                {
                    distance = GameManager.Distance(obj.X * 1920, (1 - obj.Y) * 1080 ,
                        _blueGiantsXPos[k], _blueGiantsYPos[k]);
                    
                    if (distance < 120.0f)
                    {
                        player = k;
                        
                    }
                    else
                    {
                        String debugString = "ClassId :" + obj.ClassId + "\n Position x : " + obj.X * 1920 + "\n Position y :" +
                                             (1 - obj.Y) * 1080 + "\n Angle :" + (obj.Angle * (180f / Math.PI)) +
                                             "\n nombre d'objets dans la liste :" + GameManager.nbObj;
                        //debugText[i].text = debugString;
                    }
                }

                if (player != -1)
                {
                    _nbTagsOnPoints[player]++;
                    String okString = "Player " + player + " is ready ! \nHe has the Id : " + obj.ClassId + " and the position in objList : " + i;
                    //debugText[i].text = okString; 
                }

            }

            bool isGood = true; //is if each blue planet has exactly one tag on it, is false otherwise
            for (int j = 0; j < 4; j++)
            {
                if (_nbTagsOnPoints[j] != 1)
                {
                    isGood = false;
                }
                _nbTagsOnPoints[j] = 0;
            }

            if (isGood) //if each blue planet has exactly one tag on it
            {
                for (int i = 0; i < 4; i++)
                {
                    for (int k = 0; k < 4; k++)
                    {
                        var obj = GameManager.listObj[i];
                        float distance = GameManager.Distance(obj.X * 1920, (1 - obj.Y) * 1080 ,
                            _blueGiantsXPos[k], _blueGiantsYPos[k]);
                        if (distance < 120.0f)
                        {
                            GameManager.instance.speedSpaceIds[k] = GameManager.listObj[i].ClassId; 
                        }
                    }
                }
                GameManager.instance.UpdateGameState(GameManager.GameState.SpaceSpeed); 
                instructions.text = " is Good is true. " + "\n speedSpaceIds : " +
                                    GameManager.instance.speedSpaceIds[0] + " | " +
                                    GameManager.instance.speedSpaceIds[1]
                                    + " | " + GameManager.instance.speedSpaceIds[2] + " | " +
                                    GameManager.instance.speedSpaceIds[3] + "\n listObj Class Ids : " +
                                    GameManager.listObj[0].ClassId + " | "
                                    + GameManager.listObj[1].ClassId + " | " + GameManager.listObj[2].ClassId + " | " +
                                    GameManager.listObj[3].ClassId;
            }
            else
            {
                String monString = "Please place only and exactly one Tag per blue giant star !";
                instructions.text = monString;
            }
        }
        else
        {
            String monString = "Each player should place his Tag on his blue giant star.\n Put exactly the 4 Tags needed on the table !";
            instructions.text = monString;
        }
    }
}