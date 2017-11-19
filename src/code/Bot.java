package code;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bot extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	private int random = 0,smart = 1,find_path = 2;
	private int state = random;
	private int right = 0,left = 1,up = 2,down = 3;
	private int dir = -1;
	private String lastDir = "";
	
	public Random randomGen;
	
	private int time = 0;
	private int targetTime = 60*1;
	
	private int speedE = 2;

	public Bot(int x ,int y){
		randomGen = new Random();
		setBounds(x,y,32,32);
		dir = randomGen.nextInt(4);
	}
	
	public void tick(){
		if(state == random){
			checkMove();
			time++;
			if(time == targetTime) {
				state = smart;
				time = 0;
			}
		}
		
		else if(state == smart){ //follow the player
			boolean move = false;
			 if(x < Game2.player.x) { 
				 if(canMove(x+speedE, y)) {
					 x+=speedE;
					 move = true;
					 lastDir = "right";
				 }
			 }
			 if(x > Game2.player.x) { 
				 if(canMove(x-speedE, y)) { 
					 x-=speedE;
					 move = true;
					 lastDir = "left";
				 }
			 }
			 if(y < Game2.player.y) {
				 if(canMove(x, y+speedE)) { 
					 y+=speedE;
					 move = true;
					 lastDir = "down";
				 }
			 }
			 if(y > Game2.player.y) { 
				 if(canMove(x, y-speedE)) { 
					 y-=speedE;
					 move = true;
					 lastDir = "up";
				 }
			 }
			if(x == Game2.player.x && y == Game2.player.y) move = true;
			if(!move) {
				state = find_path;
			}
			time++;
			if(time == 60*7) {
				state = random;
				time = 0;
			}
		}
		else if(state == find_path) {
			findPath();
		}
	}
	
	public void findPath() {
		if(lastDir.equals("right")) {
			if(y < Game2.player.y) {
				if(canMove(x, y+speedE)) {
					y+=speedE;
					state = smart;
				}
			}else {
				if(canMove(x, y-speedE)) {
					y-=speedE;
					state = smart;
				}
			}
			if(canMove(x+speedE, y)) x+=speedE;
		}
		
		else if(lastDir.equals("left")) {
			if(y < Game2.player.y) {
				if(canMove(x, y+speedE)) {
					y+=speedE;
					state = smart;
				}
			}else {
				if(canMove(x, y-speedE)) {
					y-=speedE;
					state = smart;
				}
			}
			if(canMove(x-speedE, y)) x-=speedE;
		}
		
		else if(lastDir.equals("up")) {
			if(x < Game2.player.x) {
				if(canMove(x+speedE, y)) {
					x+=speedE;
					state = smart;
				}
			}else {
				if(canMove(x-speedE, y)) {
					x-=speedE;
					state = smart;
				}
			}
			if(canMove(x, y-speedE)) y-=speedE;
		}
		
		else if(lastDir.equals("down")) {
			if(x < Game2.player.x) {
				if(canMove(x+speedE, y)) {
					x+=speedE;
					state = smart;
				}
			}else {
				if(canMove(x-speedE, y)) {
					x-=speedE;
					state = smart;
				}
				
			}
			if(canMove(x, y+speedE)) y+=speedE;
		}
	
		time++;
		if(time == 60*7) {
			state = random;
			time = 0;
		}
	}
	
	public void checkMove(){
		if(dir == right){
			if(canMove(x+speedE,y)){
				x+=speedE;
			}
			else{
				dir = randomGen.nextInt(4);
			}
		}
		else if(dir == left){
			if(canMove(x-speedE,y)){
				x-=speedE;
			}
			else{
				dir = randomGen.nextInt(4);
			}
		}
		else if(dir == up){
			if(canMove(x,y-speedE)){
				y-=speedE;
			}
			else{
				dir = randomGen.nextInt(4);
			}
		}
		else if(dir == down){
			if(canMove(x,y+speedE)){
				y+=speedE;
			}
			else{
				dir = randomGen.nextInt(4);
			}
		}
		else{
			dir = randomGen.nextInt(4);
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

	public void render(Graphics g){
		g.drawImage(Game2.enemySheet.getBot(0,0),x,y,32,32,null);
	}
	
}
