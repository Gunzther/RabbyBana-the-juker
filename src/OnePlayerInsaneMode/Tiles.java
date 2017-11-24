package OnePlayerInsaneMode;

import java.awt.Rectangle;

public class Tiles extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	public BotSheet mapTile;
	
	public Tiles(int x,int y){
		setBounds(x,y,32,32);
	}
}

