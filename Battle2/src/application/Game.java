package application;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Game {
	public static int gamemode=999;
	public static int difficulty=0;
	public static int Money=3000;
	public static int maxDistance=0;
	public static int Distance=0;

	public static TreeMap<Integer,Job> jobs=new TreeMap<Integer,Job>();

	public static List<Battler> actors=new ArrayList<Battler>(4);
	public static List<Battler> enemies=new ArrayList<Battler>();
	public static List<Battler> battlers=new ArrayList<Battler>();

	public static int mapID=0;
	public static List<MapArea> maps=new ArrayList<MapArea>();
	public static TreeMap<String,Integer> mapsID=new TreeMap<String,Integer>();

}
