package TwoPlayerMode;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This class has the Map object constructor and render the map that load from MapSheet class(size 800*640).
 * @author KameriiJ
 */
public class Map extends Rectangle{
	
	private static final long serialVersionUID = 1L;

	public Map(int x ,int y){
		setBounds(x,y,800,640);
	}
	
	public void render(Graphics g){
		g.drawImage(Game.mapSheet.getMap(0,0),x,y,800,640,null);
	}
}

