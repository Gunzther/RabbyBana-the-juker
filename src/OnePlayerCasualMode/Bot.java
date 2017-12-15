package OnePlayerCasualMode;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Bot class build a rabbit and control its direction to run toward to the player(banana).
 * @author KameriiJ
 */
public class Bot extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	private int random = 0,smart = 1,find_path = 2;
	private int state = random;
	private int right = 0,left = 1,up = 2,down = 3;
	private int dir = -1;
	private String lastDir = "";
	
	private int time = 0;
	private int targetTime = 60*1;
	
	public Random randomGen;
	public int speedE = 4;
	public int enemySize = 32;
	public int timeSizeEn = 0;

	public Bot(int x ,int y){
		randomGen = new Random();
		setBounds(x,y,30,30);
		dir = randomGen.nextInt(4);
	}
	
	/**
	 * This method manage the bot direction.
	 * 1.) Random state - to random the direction in every 7 seconds.
	 * 2.) Smart state - to make bot follow the player.
	 * 3.) Find path state - to find the new direction when bot can't move through the wall to the player
	 *     (dodge the wall).
	 */
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
			 if(x < Game.player.x) {
				 if(canMove(x+speedE, y)) {
					 x+=speedE;
					 move = true;
					 lastDir = "right";
				 }
			 }
			 if(x > Game.player.x) { 
				 if(canMove(x-speedE, y)) { 
					 x-=speedE;
					 move = true;
					 lastDir = "left";
				 }
			 }
			 if(y < Game.player.y) {
				 if(canMove(x, y+speedE)) { 
					 y+=speedE;
					 move = true;
					 lastDir = "down";
				 }
			 }
			 if(y > Game.player.y) { 
				 if(canMove(x, y-speedE)) { 
					 y-=speedE;
					 move = true;
					 lastDir = "up";
				 }
			 }
			if(x == Game.player.x && y == Game.player.y) move = true;
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
		
		Level level = Game.level;
		
		for(int i = 0;i < level.smallItem.size();i++){
			if(this.intersects(level.smallItem.get(i))){
				timeSizeEn = 0;
				enemySize = 16;
				level.smallItem.remove(i);
				break;
			}
		}
		if(timeSizeEn == 60*4) {
			enemySize = 32;
			timeSizeEn = 0;
		}
		timeSizeEn++;
	}
	
	/**
	 * This void method will find the new direction when bot can't move through the wall to the player
	 */
	public void findPath() {
		if(lastDir.equals("right")) {
			if(y < Game.player.y) {
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
			if(y < Game.player.y) {
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
			if(x < Game.player.x) {
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
			if(x < Game.player.x) {
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
	
	/**
	 * This void method check the random direction(right left up or down) that can move or not.
	 * If the bot can't move, It'll random the new direction.
	 */
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
	
	/**
	 * This method check the direction(right left up or down) that can move or not.
	 */
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

	public void render(Graphics g){
		g.drawImage(Game.enemySheet.getBot(0,0),x,y,enemySize,enemySize,null);
	}
	
}

