package OnePlayerNewbieMode;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BotSheet {
	
	private BufferedImage sheet;
	public BotSheet(String path){
		try {
			sheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error");
		}
	}
	
	public BufferedImage getBot(int xx,int yy){
		return sheet.getSubimage(xx, yy,xx+16,yy+16);
	}
}
