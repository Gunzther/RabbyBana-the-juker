package code;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {
	private String data;

	private static final long serialVersionUID = 1L;

	public boolean rigth, left, up, down;
	public int speed = 4;

	public Player(int x, int y) {
		setBounds(x, y, 32, 32);
	}

	public void tick() {
		 
		if (rigth && canMove(x+speed,y)) {
			x += speed;
		}
		if (left && canMove(x-speed,y)) {
			x -= speed;
		}
		if (up && canMove(x, y-speed)) {
			y -= speed;
		}
		if (down && canMove(x, y+speed)) {
			y += speed;
		}
	}
	
	private boolean canMove(int nextX,int nextY){
		Rectangle bounds = new Rectangle(nextX,nextY,width,height);
		Level2 level = Game2.level;
		
		for(int xx = 0;xx < level.tiles.length;xx++){
			for(int yy = 0;yy < level.tiles[0].length;yy++){
				if(level.tiles[xx][yy] != null){
					if(bounds.intersects(level.tiles[xx][yy])){
						return false;
					}
				}
			}
		}
		return true;
	}

	public void render(Graphics g) {
		BotSheet sheet = Game2.playerSheet;
		g.drawImage(sheet.getBot(0, 0), x, y, 32, 32, null);
	}
}
