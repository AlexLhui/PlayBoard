using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ActivateShowTimes : MonoBehaviour
{
    public GameObject button;

    public void ShowTimes()
    {
        if (false == button.activeInHierarchy)
        {
            button.SetActive(true);
        }
        else
        {
            button.SetActive(false);
        }
    }

}
