package OnePlayerNewbieMode;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

public class Level {
	
	public Random randomItem;
	public int randNum;
	public int rand;
	public int width;
	public int heigth;
	private int time = 0;
	private int timeItem = 0;
	
	public Tiles[][] tiles;

	public List<Items> item;
	public List<SmallItem> smallItem;
	public int[] tileColor = {0xFF00FF00,0xFF00FF01,0xFF00FF02,0xFF00FF03,0xFF00FF04,0xFF00FF05,0xFF00FF06,0xFF00FF07,0xFF00FF08,0xFF00FF09,0xFF00FF10,0xFF00FF11};
	
	public int[] pixels;
	public int val;
	
	public Level(String path){
		item = new ArrayList<>();
		smallItem = new ArrayList<>();
		
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			this.width = map.getWidth();
			this.heigth = map.getHeight();
			pixels = new int[width*heigth];
			tiles = new Tiles[width][heigth];
			map.getRGB(0, 0, width, heigth, pixels, 0 , width);
			
			for(int xx = 0;xx < width;xx++){
				for(int yy = 0;yy < heigth;yy++){
					val = pixels[xx + (yy*width)];
					
					if(val == 0xFF000000){ //Tile
						tiles[xx][yy] = new Tiles(xx*32,yy*32);
					}
					
					else if(val == 0xFFFF00FF){ //Player
						Game.player.x = xx*32;
						Game.player.y = yy*32;
					}
					
					else if(val == 0xFFFF0000){ //Enemies
						Game.enemy.x = xx*32;
						Game.enemy.y = yy*32;
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick(){
		Game.enemy.tick();
		time++;
		timeItem++;
	}
	
	public void render(Graphics g){
		for(int x  = 0;x < width;x++){
			for(int y = 0;y < heigth;y++){
				if(tiles[x][y] != null) tiles[x][y].render(g);
			}
		}
			Game.enemy.render(g);
			if(timeItem == 1) {
				randomItem = new Random();
				randNum = randomItem.nextInt(12);
				randomItem = new Random();
				rand = randomItem.nextInt(4);
			}
			
			if(timeItem == 60*2) {
				for(int xx = 0;xx < width;xx++){
					for(int yy = 0;yy < heigth;yy++){
						val = pixels[xx + (yy*width)];
						if(val == tileColor[randNum]) {// Items
							item.add(new Items(xx*32,yy*32));
						}
					}
				}
			}
			
			if(timeItem >= 60*2 && timeItem <= 60*6) {
				if(item.size() != 0) {
					item.get(0).render(g,rand);
				}
			}
			
			else if(timeItem == 60*7) {
				if(item.size() == 1) {
					item.remove(0);
				}
				timeItem = 0;
			}
			
			if(time == 60*10) {
				for(int xx = 0;xx < width;xx++){
					for(int yy = 0;yy < heigth;yy++){
						val = pixels[xx + (yy*width)];
						if(val == 0xFFFFFF00){ //toSmall Items
							smallItem.add(new SmallItem(xx*32,yy*32));
						}
					}
				}
			}
			
			if(time >= 60*10 && time <= 60*16) {
				for(int i = 0;i < smallItem.size();i++){
					smallItem.get(i).render(g);
				}
			}
			
			else if(time == 60*17) {
				if(smallItem.size() == 1) {
					smallItem.remove(0);
				}
				time = 0;
			}
	}
}
