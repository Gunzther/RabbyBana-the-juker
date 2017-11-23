package OnePlayerNewbieMode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MapSheet {

	private BufferedImage sheet;

	public MapSheet(String path) {
		try {
			sheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error");
		}
	}

	public BufferedImage getMap(int i, int j) {
		return sheet.getSubimage(i, j, 400, 320);
	}
}
