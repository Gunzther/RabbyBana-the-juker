package OnePlayerCasualMode;

import java.awt.Rectangle;

/**
 * This class has the Tiles object constructor.
 * This object is the wall in the map.
 * @author KameriiJ
 */
public class Tiles extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	public BotSheet mapTile;
	
	public Tiles(int x,int y){
		setBounds(x,y,32,32);
	}
}

