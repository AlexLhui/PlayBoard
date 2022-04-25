using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GoToMenu : MonoBehaviour
{
    public GameObject morpionGame;
    public void BackToMenu()
    {
        //morpionGame.SetActive(false);
        Morpion.morpionState = 8;
       
    }
}
