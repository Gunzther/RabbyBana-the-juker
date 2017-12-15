package OnePlayerCasualMode;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import GUI.Difficultyselected1;
import GUI.EndingWinFrame;
import GUI.EndingLoseFrame;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;
import kuusisto.tinysound.internal.ByteList;

/**
 * This Game is chasing game in  insane mode.
 * In this mode, enemy's speed is equal player's speed.
 * A rabbit is the enemy, and a banana is the player.
 * 
 * @author KameriiJ
 */
public class Game extends Canvas implements Runnable,KeyListener {
	
	private static final long serialVersionUID = 1L; 
	private boolean isRunning = false;
	
	public static final int WIDTH = 800,HEIGTH = 640;
	public static final String TITLE = "Chasing-Game-SKE";
	
	private Thread thread;
	
	public static EndingLoseFrame button;
	public static Player player;
	public static Bot enemy;
	public Map map;
	public static SmallItem small;
	public static Level level;
	public static BotSheet enemySheet;
	public static BotSheet playerSheet;
	public static MapSheet mapSheet;
	public static GUI.Difficultyselected1 resultCS;
	public static JFrame frame;
	
	public static Music song;
	public static Music subSong;
	public static kuusisto.tinysound.internal.ByteList soundbyte;
	
	public Game() {
		Dimension dimension = new Dimension(WIDTH, HEIGTH);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		
		addKeyListener(this);
		player = new Player(Game.WIDTH/2,Game.HEIGTH/2);
		enemy = new Bot(Game.WIDTH/2,Game.HEIGTH/2);
		map = new Map(0,0);
		level = new Level("/map/map_chasing2.png");
		enemySheet = new BotSheet("/bot/tui.png");
		playerSheet = new BotSheet("/bot/banana2.png");
		mapSheet = new MapSheet("/map/newMap.png");
		resultCS = new Difficultyselected1();
        frame = new JFrame();
        soundbyte = new ByteList();
	}
	
	public synchronized void start(){
		if(isRunning) return;
		isRunning = true;
		
		thread = new Thread(this);
		thread.start();
		song.play(true); 
	}
	
	public synchronized void stop(){
		soundbyte.clear();
		frame.dispose();
		if(!isRunning) return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void tick(){
		player.tick();
		level.tick();
	}
	
	private void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		map.render(g);
		player.render(g);
		level.render(g);
		g.dispose();
		bs.show();
	}
	
	public static void main() {
		Game game = new Game();
		song = TinySound.loadMusic("/sound/chasing-game.wav");
		frame = new JFrame();
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
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)player.rigth = true;
		if(e.getKeyCode() == KeyEvent.VK_LEFT)player.left = true;
		if(e.getKeyCode() == KeyEvent.VK_UP)player.up = true;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)player.down = true;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)player.rigth = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT)player.left = false;
		if(e.getKeyCode() == KeyEvent.VK_UP)player.up = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)player.down = false;
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
				if(resultCS.getResult() == 1) break;
				else if(resultCS.getResult() == 2)break;
				fps++;
				delta--;
			}
			if(resultCS.getResult() == 1) break;
			else if(resultCS.getResult() == 2)break;
			if(System.currentTimeMillis() - timer >= 1000){
				System.out.println(fps);
				fps = 0;
				timer += 1000;
			}
		}
		 song.stop();
		
		 if(resultCS.getResult() == 1) {
			 new EndingLoseFrame().setVisible(true);
			 frame.dispose();
			 subSong = TinySound.loadMusic("/sound/lose.wav");
			 subSong.play(true); 
		 }
		 
		 else if(resultCS.getResult() == 2) {
			 new EndingWinFrame().setVisible(true);
			 frame.dispose();
			 subSong = TinySound.loadMusic("/sound/win.wav");
			 subSong.play(true); 
		 }
		stop();
	}
}

