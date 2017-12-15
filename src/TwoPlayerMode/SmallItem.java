package TwoPlayerMode;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This class has the SmallItem object constructor.
 * This item will make player or enemy(rabbit) smaller(from 30*30 to 16*16). 
 * @author KameriiJ
 */
public class SmallItem extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	public BotSheet smallItemSheet;
	
	public SmallItem(int x,int y){
		setBounds(x+10,y+8,8,8);
	}
	
	public void render(Graphics g){
			smallItemSheet = new BotSheet("/items/toffy5.png");
			g.drawImage(smallItemSheet.getBot(0,0),x,y,24,24,null);
	}
}
