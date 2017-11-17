package code;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Level2 {
	
	public int width;
	public int heigth;
	
	public Tiles[][] tiles;
	
	public Level2(String path){
		
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
					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick(){
			Game2.enemy.tick();
	}
	
	public void render(Graphics g){
		for(int x  = 0;x < width;x++){
			for(int y = 0;y < heigth;y++){
				if(tiles[x][y] != null) tiles[x][y].render(g);
			}
		}
			Game2.enemy.render(g);
	}
}
