package code;

import java.awt.Graphics;
import java.awt.Rectangle;

public class SmallItems extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	public BotSheet smallItemSheet;
	
	public SmallItems(int x,int y){
		setBounds(x+10,y+8,8,8);
	}
	
	public void render(Graphics g){
			smallItemSheet = new BotSheet("/bot/toffy5.png");
			g.drawImage(smallItemSheet.getBot(0,0),x,y,24,24,null);
	}
}
