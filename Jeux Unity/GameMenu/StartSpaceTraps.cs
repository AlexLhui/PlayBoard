using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class StartSpaceTraps : MonoBehaviour
{
    public void LunchST()
    {
        GameManager.instance.UpdateGameState(GameManager.GameState.SpaceTrapsNbOfPlayers);
    }
}