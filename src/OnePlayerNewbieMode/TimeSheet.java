package OnePlayerNewbieMode;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TimeSheet {
	
	private BufferedImage sheet;
	public TimeSheet(String path){
		try {
			sheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error");
		}
	}
	
	public BufferedImage getBot(int xx,int yy){
		return sheet.getSubimage(xx, yy,xx+32,yy+32);
	}
}

