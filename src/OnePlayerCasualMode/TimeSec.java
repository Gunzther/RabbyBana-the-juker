package OnePlayerCasualMode;

import java.awt.Graphics;
import java.awt.Rectangle;

public class TimeSec extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	public TimeSheet timeSheet;

	public TimeSec(int x,int y){
		setBounds(x,y,64,64);
	}
	
	public void render(Graphics g,int gameSec){
		String path = "";
			path = String.format("/GtimeSec/m%d.png",30-gameSec);
			timeSheet = new TimeSheet(path);
			g.drawImage(timeSheet.getBot(0,0),x,y,64,64,null);
	}
}

