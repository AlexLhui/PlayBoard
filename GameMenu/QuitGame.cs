using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class QuitGame : MonoBehaviour
{
    public GameObject morpionGame;
    public void QuitTheGame() {
        
        GameManager.tuioServer.Disconnect();
        Application.Quit();
    }
}
