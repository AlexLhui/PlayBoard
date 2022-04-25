using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TH : MonoBehaviour
{
    public void LunchTH()
    {
        GameManager.instance.UpdateGameState(GameManager.GameState.TrigHockey);
    }
}
