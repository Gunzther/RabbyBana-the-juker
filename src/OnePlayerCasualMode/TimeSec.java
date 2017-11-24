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
		if(gameSec == 0) {
			timeSheet = new TimeSheet("/GtimeSec/m0-0.png");
			g.drawImage(timeSheet.getBot(0,0),x,y,64,64,null);
		}
		else {
			path = String.format("/GtimeSec/m%d.png",60-gameSec);
			timeSheet = new TimeSheet(path);
			g.drawImage(timeSheet.getBot(0,0),x,y,64,64,null);
		}
	}
}

