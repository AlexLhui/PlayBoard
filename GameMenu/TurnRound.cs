using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TurnRound : MonoBehaviour
    {
        public GameObject[] buttons;
 
        private float RotateSpeed = 0.4f;
        private float Radius = 450f;
        private float tim = 0;
 
        private Vector2 _centre = new Vector2(0,0);
        private float _angle = 0;
 
        private void Start()
        {
            // _centre = transform.position;
        }
 
        private void Update()
        {
           // tim += 1000*Time.deltaTime;
            _angle -= RotateSpeed * Time.deltaTime;
 
            
            for (int i=0;i<buttons.Length;i++)
            {
                var offset = new Vector2(Mathf.Sin(_angle+i*Mathf.PI/4), Mathf.Cos(_angle+i*Mathf.PI/4)) * Radius;
                buttons[i].transform.position =  _centre + offset;
            }
            //button.transform.eulerAngles -= new Vector3(0, 0,  50*RotateSpeed/Radius);
        }
  
  
 
    }

