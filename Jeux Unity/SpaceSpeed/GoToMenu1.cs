using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GoToMenu1 : MonoBehaviour
{
    public void GoingToMenu()
    {
        GameManager.instance.UpdateGameState(GameManager.GameState.GameMenu);
    }
}
