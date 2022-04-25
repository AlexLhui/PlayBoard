using UnityEngine;

namespace Tourniquet
//This code allows the buttons on the menu to move in circle
{
    public class Tourniquet : MonoBehaviour
    {
        public GameObject button;
 
        private float RotateSpeed = 0.3f;
        private float Radius = 500f;
        private float tim = 0;
 
        private Vector2 _centre = new Vector2(0,0);
        private float _angle = 0;
 
        private void Start()
        {
           // _centre = transform.position;
        }
 
        private void Update()
        {
            tim += 1000*Time.deltaTime;
            _angle -= RotateSpeed * Time.deltaTime;
 
            var offset = new Vector2(Mathf.Sin(_angle), Mathf.Cos(_angle)) * Radius;
            button.transform.position = _centre + offset;
            button.transform.eulerAngles -= new Vector3(0, 0,  50*RotateSpeed/Radius);
        }
  
  
 
    }
}
