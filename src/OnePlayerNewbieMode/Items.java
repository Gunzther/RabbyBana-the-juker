package OnePlayerNewbieMode;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Items extends Rectangle {

	private static final long serialVersionUID = 1L;

	public BotSheet itemSheet;

	public Items(int x, int y) {
		setBounds(x + 6, y + 10, 8, 8);
	}

	public void render(Graphics g, int rand) {
		if (rand == 0) {
			itemSheet = new BotSheet("/items/toffy1.png");
			g.drawImage(itemSheet.getBot(0, 0), x, y, 24, 24, null);
		} else if (rand == 1) {
			itemSheet = new BotSheet("/items/toffy2.png");
			g.drawImage(itemSheet.getBot(0, 0), x, y, 24, 24, null);
		} else if (rand == 2) {
			itemSheet = new BotSheet("/items/toffy3.png");
			g.drawImage(itemSheet.getBot(0, 0), x, y, 24, 24, null);
		} else if (rand == 3) {
			itemSheet = new BotSheet("/items/toffy4.png");
			g.drawImage(itemSheet.getBot(0, 0), x, y, 24, 24, null);
		}
	}

}
