using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Leave : MonoBehaviour
{
    public void QuitTheGame() {
        GameManager.tuioServer.Disconnect();
        Application.Quit();
    }
}



