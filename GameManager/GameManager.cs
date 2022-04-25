using System;
using System.Threading;
using System.Text;
using System.Collections;
using System.Collections.Generic;
using TUIOsharp;
using TUIOsharp.DataProcessors;
using TUIOsharp.Entities;
using OSCsharp.Data;
using OSCsharp.Net;
using OSCsharp.Utils;
using UnityEngine;
using UnityEngine.UI;
using System.Diagnostics;


public class GameManager : MonoBehaviour
{
	public static GameManager instance;

    #region Variables SpeedSpace MiniGame
    private int[] _speedSpaceIds = new int[4];

    public int[] speedSpaceIds
    {
	    get
	    {
		    return _speedSpaceIds;
	    }
	    set
	    {
		    _speedSpaceIds = value;
	    }
    }
	
	public static int firstDraw = -1;
	#endregion

	void Awake() // The GameManager class is a Singleton
	{
		if (instance == null)
		{
			instance = this;
		}
	ConnectTuio(); 
		
	}

	

    #region Variables
    private static float resolutionX = 1920.0f;
	private static float resolutionY = 1080.0f;
	private static bool invertX = false;
	private static bool invertY = false;
	public static bool degs = true;
	public static TuioServer tuioServer = new TuioServer(3333);
	public bool open = true; //appuyer sur le bouton permet d'ouvrir ou fermer le tuioserver
	public static GameState State = GameState.GameMenu;
	public static int nbObj = 0;
	public static int GammaId;
	public static int OpheId;
	public static GameObject go;
	
	public static List<TuioObject> listObj = new List<TuioObject>();
	public List<GameObject> scenes;
	#endregion

	#region For the Scenes
	public enum GameState // Names of the different scenes of the game
	{
		Menu,
		GameMenu,
		AddPlayer,
		Morpion,
		SSAddPlayers,
		SpaceSpeed,
		SpaceTrapsNbOfPlayers,
		SpaceTraps,
		TrigHockey,
		Scene_2,
		Scene_1,
		Scene0,
		Scene1,
		Scene2
	}
	void Start()
	{
		UpdateGameState(State); //State initialized with GameMenu
	}
	
	private void OpenScene(GameState state) // Open the scene given in argument and close the others
	{
		foreach (GameObject scene in scenes)
		{
			if (scene.tag == state.ToString() && scene.tag != "GameManager")
			{
				scene.SetActive(true);
			}
			else
			{
				scene.SetActive(false);
			}

		}
	}

	public void UpdateGameState(GameState newState) // doit être appelé d'un script lorsque l'on souhaite changer de scène
	{
		State = newState;

		switch (newState)
		{
			case GameState.GameMenu:
				OpenScene(GameState.GameMenu);
				break;
			case GameState.AddPlayer:
				OpenScene(GameState.AddPlayer);
				break;
			case GameState.Morpion:
				OpenScene(GameState.Morpion);
				break;
			case GameState.SSAddPlayers:
				OpenScene(GameState.SSAddPlayers);
				break;
			case GameState.SpaceSpeed:
				OpenScene(GameState.SpaceSpeed);
				break;
			case GameState.SpaceTrapsNbOfPlayers:
				OpenScene(GameState.SpaceTrapsNbOfPlayers);
				break;
			case GameState.SpaceTraps:
				OpenScene(GameState.SpaceTraps);
				break;
			case GameState.TrigHockey:
				OpenScene(GameState.TrigHockey);
				break;
			case GameState.Menu:
				OpenScene(GameState.Menu);
				break;
			case GameState.Scene_2:
				OpenScene(GameState.Scene_2);
				break;
			case GameState.Scene_1:
				OpenScene(GameState.Scene_1);
				break;
			case GameState.Scene0:
				OpenScene(GameState.Scene0);
				break;
			case GameState.Scene1:
				break;
			case GameState.Scene2:
				break;
			default:
				break;
		}

		//OnGameStateChanged?.Invoke(newState);
	}
	

	#endregion
	
	
	#region Tools
	private static bool CheckIfClassIdIn(int classId1)   // This method checks if the given id has a corresponding object in the list listObj
	{
		bool flag = false;
		foreach (TuioObject obj in listObj)
		{
			if (obj.ClassId == classId1)
			{
				flag = true;
			}
		}
		return flag;
	}

	public static int GivePosOfClassId(int classId1)   // THIS FUNCTION GIVES THE POSITION OF THE OBJECT IN THE LIST
	{
		int pos = -1;
		int position = -1;
		foreach (TuioObject obj in listObj)
		{
			pos++;
			if (classId1 == obj.ClassId)
			{
				position = pos;
			}
		}
		return position; // = -1 if the id is not in the list, otherwise it is equal to 
	}


	public static int indexOfObjectWith(int id)
	{
	int index = -1;
	int compt = -1;
	foreach (TuioObject obj in listObj) 
	{
		compt++;
		if (obj.ClassId==id)
		{
			index = compt++;
		}
	}
	return index;
	}
	
	public static float Distance(float x_1, float y_1, float x_2, float y_2)
	{
		return (float)Math.Sqrt(Math.Pow(y_2 - y_1, 2.0f) + Math.Pow(x_2 - x_1, 2.0f));
	}
	#endregion

	#region TUIO related
	private void ConnectTuio()
	{
		//tuioServer.Disconnect();
		if (!open)
		{
			tuioServer.Disconnect();
			Application.Quit();
		} //si open est false on déconnecte le tuioServer et on ferme l'application, ce code ne doit pas être éxécuté deux fois

		if (open)
		{
			tuioServer.Connect();
		} //si open est true on connecte le tuioServer

		// Ajoute les événements liés aux objets
		ObjectProcessor objectProcessor = new ObjectProcessor();
		objectProcessor.ObjectAdded += OnObjectAdded;
		objectProcessor.ObjectUpdated += OnObjectUpdated;
		objectProcessor.ObjectRemoved += OnObjectRemoved;

		//tuioServer.AddDataProcessor(cursorProcessor);
		tuioServer.AddDataObjectProcessor(objectProcessor);

		// on ne pourra plus connecter le tuioserver seulement le déconnecter
		open = false;

	}
	
private static void OnObjectAdded(object sender, TuioObjectEventArgs e)
	{
		var entity = e.Object;
		lock (tuioServer)
		{
			// If the id of the object is not already in listObj we add it	
			if (!CheckIfClassIdIn(entity.ClassId))
			{
				listObj.Add(entity);
				nbObj++;
				
				if (GameState.SpaceSpeed == State)
				{
					Player.objAddedOnTable(entity.ClassId);
				}
				else if (GameState.TrigHockey == State)
				{
					var id = entity.ClassId;
					if (!THPlayer.IsThere(id)) THPlayer.playerIds.Add(id);
				}
				
			}
			
		}
	}

	private static void OnObjectUpdated(object sender, TuioObjectEventArgs e)
	{
		var entity = e.Object;
		lock (tuioServer)
		{
			var x = invertX ? resolutionX * (1 - entity.X) : resolutionX * entity.X;
			var y = invertY ? resolutionX * (1 - entity.Y) : resolutionX * entity.Y;
			var angle = degs ? (entity.Angle * (180f / Math.PI)) : entity.Angle;
			
		}
	}

	private static void OnObjectRemoved(object sender, TuioObjectEventArgs e)
	{
		var entity = e.Object;
			
		lock (tuioServer)
		{
			
			if (CheckIfClassIdIn(entity.ClassId)) // we check that the removed object is really in the list listObj then we remove it
			{
				if (GameState.SpaceSpeed == State)
				{
					Player.totemRetired(entity.ClassId);
				}
			
				listObj.Remove(entity);
				if (nbObj > 1) { nbObj -= 1; }
				else if (nbObj==1) { nbObj = 0; } // idk why this does not always work
			}
		}
		
	}




	private static void OnCursorAdded(object sender, TuioCursorEventArgs e)
	{
		var entity = e.Cursor;
		lock (tuioServer)
		{
			var x = invertX ? resolutionX * (1 - entity.X) : resolutionX * entity.X;
			var y = invertY ? resolutionX * (1 - entity.Y) : resolutionX * entity.Y;
			//AJOUTER LE CURSOR DANS LA LISTE listCur
		}
	}

	private static void OnCursorUpdated(object sender, TuioCursorEventArgs e)
	{
		var entity = e.Cursor;
		lock (tuioServer)
		{
			var x = invertX ? resolutionX * (1 - entity.X) : resolutionX * entity.X;
			var y = invertY ? resolutionX * (1 - entity.Y) : resolutionX * entity.Y;

		}
	}

	private static void OnCursorRemoved(object sender, TuioCursorEventArgs e)
	{
		var entity = e.Cursor;
		lock (tuioServer)
		{
			//enlever le cursor de la liste ?
		}
	}

	private static void OscErrorOccuredHandler(object sender, ExceptionEventArgs exceptionEventArgs)
	{
		//Console.WriteLine(string.Format("Error {0}", exceptionEventArgs.ToString()));
		//Debug.Log("Error " + exceptionEventArgs.ToString());
	}

	private static void OscMessageReceivedHandler(object sender, OscMessageReceivedEventArgs oscMessageReceivedEventArgs)
	{
		OscMessage msg = oscMessageReceivedEventArgs.Message;

		StringBuilder data = new StringBuilder();
		for (int i = 0; i < msg.Data.Count; i++)
		{
			data.AppendFormat(" {0}", msg.Data[i]);
		}

		//Console.WriteLine(string.Format("{0}{1}{2}", msg.Address, msg.TypeTag, data.ToString()));
		//Debug.Log(msg.Address + msg.TypeTag + data.ToString());
	}
	

	#endregion
	
}
