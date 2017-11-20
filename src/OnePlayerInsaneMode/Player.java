package OnePlayerInsaneMode;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JOptionPane;


public class Player extends Rectangle {

	private static final long serialVersionUID = 1L;

	public boolean rigth, left, up, down;
	public int speed = 4;
	private int timePlayer;
	private int timeSize;
	public int playerSize = 30;

	public Player(int x, int y) {
		setBounds(x, y, 30, 30);
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
		
		Level level = Game.level;
		Bot en = Game.enemy;
		
		for(int i = 0;i < level.item.size();i++){
			if(this.intersects(level.item.get(i))){
				timePlayer = 0;
				if(level.rand == 0) {
						speed = 8;
				}
				else if(level.rand == 1) {
						speed = 2;
				}
				else if(level.rand == 2) {
						en.speedE = 8;
				}
				else if(level.rand == 3) {
						en.speedE = 2;
				}
				level.item.remove(i);
				break;
			}
		}
		
		for(int i = 0;i < level.smallItem.size();i++){
			if(this.intersects(level.smallItem.get(i))){
				timeSize = 0;
				playerSize = 16;
				level.smallItem.remove(i);
				break;
			}
		}
		
			Bot enemy = Game.enemy;
			if(enemy.intersects(this)) {
				//JOptionPane.showMessageDialog(null,"You Lose!!!",null,JOptionPane.PLAIN_MESSAGE);
				//messageLose();
				//playOrQuit();
			}
			if(timePlayer == 60*2) {
				timePlayer = 0;
				speed = 4;
				en.speedE = 6;
			}
			if(timeSize == 60*4) {
				playerSize = 30;
				timeSize = 0;
			}
			timeSize++;
			timePlayer++;
	}
	
	private boolean canMove(int nextX,int nextY){
		Rectangle bounds = new Rectangle(nextX,nextY,width,height);
		Level level = Game.level;
		
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
		BotSheet sheet = Game.playerSheet;
		g.drawImage(sheet.getBot(0, 0), x, y, playerSize, playerSize, null);
	}
}
