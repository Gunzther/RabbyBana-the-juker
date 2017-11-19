package code;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

public class Level2 {
	
	public Random randomItem;
	public int randNum;
	public int rand;
	public int width;
	public int heigth;
	private int time = 0;
	private int timeItem = 0;
	
	public Tiles[][] tiles;

	public List<Items> item;
	public List<SmallItems> smallItem;
	
	public Level2(String path){
		item = new ArrayList<>();
		smallItem = new ArrayList<>();
		
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			this.width = map.getWidth();
			this.heigth = map.getHeight();
			int[] pixels = new int[width*heigth];
			tiles = new Tiles[width][heigth];
			map.getRGB(0, 0, width, heigth, pixels, 0 , width);
			
			for(int xx = 0;xx < width;xx++){
				for(int yy = 0;yy < heigth;yy++){
					int val = pixels[xx + (yy*width)];
					
					if(val == 0xFF000000){ //Tile
						tiles[xx][yy] = new Tiles(xx*32,yy*32);
					}
					
					else if(val == 0xFFFF00FF){ //Player
						Game2.player.x = xx*32;
						Game2.player.y = yy*32;
					}
					
					else if(val == 0xFFFF0000){ //Enemies
						Game2.enemy.x = xx*32;
						Game2.enemy.y = yy*32;
					}
					else if(val == 0xFF00FF00){ //Items
						item.add(new Items(xx*32,yy*32));
					}
					else if(val == 0xFFFEFB00){ //Items
						smallItem.add(new SmallItems(xx*32,yy*32));
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick(){
		Game2.enemy.tick();
		time++;
		timeItem++;
	}
	
	public void render(Graphics g){
		for(int x  = 0;x < width;x++){
			for(int y = 0;y < heigth;y++){
				if(tiles[x][y] != null) tiles[x][y].render(g);
			}
		}
			Game2.enemy.render(g);
			if(timeItem == 1) {
				randomItem = new Random();
				randNum = randomItem.nextInt(13);
				randomItem = new Random();
				rand = randomItem.nextInt(4);
			}
			if(timeItem >= 60*2 && timeItem <= 60*6) {
				item.get(randNum).render(g,rand);
			}
			else if(timeItem == 60*7) timeItem = 0;
			
			if(time >= 60*10 && time <= 60*16) {
				for(int i = 0;i < smallItem.size();i++){
					smallItem.get(i).render(g);
				}
			}
			else if(time == 60*17) time = 0;
	}
}
