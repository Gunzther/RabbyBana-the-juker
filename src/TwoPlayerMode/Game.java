package TwoPlayerMode;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener {
	
	private static final long serialVersionUID = 1L; 
	private boolean isRunning = false;
	
	public static final int WIDTH = 800,HEIGTH = 640;
	public static final String TITLE = "Chasing-Game-SKE";
	
	private Thread thread;
	
	public static Player1 player1;
	public static Player2 player2;
	public static SmallItem small;
	public static Level level;
	public static BotSheet player1Sheet;
	public static BotSheet player2Sheet;
	
	public Game() {
		Dimension dimension = new Dimension(WIDTH, HEIGTH);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		
		addKeyListener(this);
		player1 = new Player1(Game.WIDTH/2,Game.HEIGTH/2);
		player2 = new Player2(Game.WIDTH/2,Game.HEIGTH/2);
		level = new Level("/map/map_chasing2.png");
		player1Sheet = new BotSheet("/bot/banana2.png");
		player2Sheet = new BotSheet("/bot/tui.png");
	}
	
	public synchronized void start(){
		if(isRunning) return;
		isRunning = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!isRunning) return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void tick(){
		player1.tick();
		player2.tick();
		level.tick();
	}
	
	private void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGTH);
		player1.render(g);
		player2.render(g);
		level.render(g);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[]args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		frame.setTitle(Game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		game.start();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)player1.rigth = true;
		if(e.getKeyCode() == KeyEvent.VK_LEFT)player1.left = true;
		if(e.getKeyCode() == KeyEvent.VK_UP)player1.up = true;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)player1.down = true;
		
		if(e.getKeyCode() == KeyEvent.VK_D)player2.rigth = true;
		if(e.getKeyCode() == KeyEvent.VK_A)player2.left = true;
		if(e.getKeyCode() == KeyEvent.VK_W)player2.up = true;
		if(e.getKeyCode() == KeyEvent.VK_S)player2.down = true;
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)player1.rigth = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT)player1.left = false;
		if(e.getKeyCode() == KeyEvent.VK_UP)player1.up = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)player1.down = false;
		
		if(e.getKeyCode() == KeyEvent.VK_D)player2.rigth = false;
		if(e.getKeyCode() == KeyEvent.VK_A)player2.left = false;
		if(e.getKeyCode() == KeyEvent.VK_W)player2.up = false;
		if(e.getKeyCode() == KeyEvent.VK_S)player2.down = false;
	}
	
	@Override
	public void run() {
		requestFocus();
		int fps = 0;
		double timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double targetTick = 60.0;
		double delta = 0;
		double ns = 1000000000 / targetTick; 
		
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1){
				tick();
				render();
				fps++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000){
				System.out.println(fps);
				fps = 0;
				timer += 1000;
			}
		}
		stop();
		
	}
}
