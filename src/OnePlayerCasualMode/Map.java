package OnePlayerCasualMode;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Map extends Rectangle{
	public Map(int x ,int y){
		setBounds(x,y,800,640);
	}
	
	public void render(Graphics g){
		g.drawImage(Game.mapSheet.getMap(0,0),x,y,800,640,null);
	}
}

