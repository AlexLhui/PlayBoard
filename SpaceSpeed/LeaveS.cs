using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LeaveS : MonoBehaviour
{
    public void QuitTheGameS() {
        GameManager.tuioServer.Disconnect();
        Application.Quit();
    }
}



