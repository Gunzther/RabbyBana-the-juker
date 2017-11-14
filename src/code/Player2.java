package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

public class Player2 extends Rectangle {
	private String data;

	private static final long serialVersionUID = 1L;

	public boolean rigth, left, up, down;
	public int speed = 4;

	public Player2(int x, int y) {
		setBounds(x, y, 32, 32);
	}

	public void tick() {
		
		if (rigth) {
			x += speed;
		}
		if (left) {
			x -= speed;
		}
		if (up) {
			y -= speed;
		}
		if (down) {
			y += speed;
		}
		 
//		if (rigth && canMove(x+speed,y)) {
//			x += speed;
//		}
//		if (left && canMove(x-speed,y)) {
//			x -= speed;
//		}
//		if (up && canMove(x, y-speed)) {
//			y -= speed;
//		}
//		if (down && canMove(x, y+speed)) {
//			y += speed;
//		}
	}
	
//	private boolean canMove(int nextX,int nextY){
//		Rectangle bounds = new Rectangle(nextX,nextY,width,height);
//		Level level = Game.level;
//		
//		for(int xx = 0;xx < level.tiles.length;xx++){
//			for(int yy = 0;yy < level.tiles[0].length;yy++){
//				if(level.tiles[xx][yy] != null){
//					if(bounds.intersects(level.tiles[xx][yy])){
//						return false;
//					}
//				}
//			}
//		}
//		return true;
//	}

	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect(x, y, width, height);
//		BotSheet sheet = Game.botSheet;
//		g.drawImage(sheet.getBot(0, 0), x, y, 32, 32, null);
	}
}
