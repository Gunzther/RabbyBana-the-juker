package OnePlayerCasualMode;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This class has the TimeMin object constructor and render the 6 styles of minute picture.
 * 1.) black rabbit 01:
 * 2.) black rabbit 00:
 * 3.) black rabbit move left 00:
 * 4.) black rabbit move right 00:
 * in 10 minute left
 * 5.) red rabbit move left 00:
 * 6.) red rabbit move right 00:
 *  
 * @author KameriiJ
 */
public class TimeMin extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	public TimeSheet timeSheet;

	public TimeMin(int x,int y){
		setBounds(x,y,64,64);
	}
	
	public void render(Graphics g,int num){
		if(num == 0) {
			timeSheet = new TimeSheet("/Gtime/t0.png");
			g.drawImage(timeSheet.getBot(0,0),x,y,64,64,null);
		}
		else if(num == 1) {
			timeSheet = new TimeSheet("/Gtime/t1.png");
			g.drawImage(timeSheet.getBot(0,0),x,y,64,64,null);
		}
		else if(num == 2) {
			timeSheet = new TimeSheet("/Gtime/t2.png");
			g.drawImage(timeSheet.getBot(0,0),x,y,64,64,null);
		}
		else if(num == 3) {
			timeSheet = new TimeSheet("/Gtime/t3.png");
			g.drawImage(timeSheet.getBot(0,0),x,y,64,64,null);
		}
		else if(num == 4) {
			timeSheet = new TimeSheet("/Gtime/t4.png");
			g.drawImage(timeSheet.getBot(0,0),x,y,64,64,null);
		}
		else if(num == 5) {
			timeSheet = new TimeSheet("/Gtime/t5.png");
			g.drawImage(timeSheet.getBot(0,0),x,y,64,64,null);
		}
	}

}

