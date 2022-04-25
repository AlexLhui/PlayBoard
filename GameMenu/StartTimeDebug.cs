using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class StartTimeDebug : MonoBehaviour
{
    public void StartTimeStuff()
    {
        GameManager.instance.UpdateGameState(GameManager.GameState.SSAddPlayers);
    }
}
