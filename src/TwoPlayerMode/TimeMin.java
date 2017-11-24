package TwoPlayerMode;

import java.awt.Graphics;
import java.awt.Rectangle;

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

