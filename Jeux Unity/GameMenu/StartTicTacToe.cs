using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class StartTicTacToe : MonoBehaviour
{
    public void LunchMorpion()
    {
        Morpion.morpionState = 0;
        GameManager.instance.UpdateGameState(GameManager.GameState.AddPlayer);
    }
}
