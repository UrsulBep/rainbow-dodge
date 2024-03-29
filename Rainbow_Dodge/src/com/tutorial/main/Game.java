package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1550691097823471818L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	
	public static boolean paused = false;
	public int difficulty = 0;
	
	//0 = normal
	//1 = hard
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	
	//Storing game states such as the menu, game, etc
	public enum STATE {
		Menu,
		Select,
		Instructions,
		Shop,
		Game,
		End
	}; //accidently came across this last ";", don't know why this works, but its more convenient so why not
		
	//initially setting game state to the menu state
	public static STATE gameState = STATE.Menu;
	
	public static BufferedImage sprite_sheet;
	
	public Game() {
		
		//load in spritesheet as an image
		BufferedImageLoader loader = new BufferedImageLoader();
				
		sprite_sheet = loader.loadImage("/images/spritesheet.png");
		
		//initializing new instances of classes
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud);
		spawner = new Spawn(handler, hud, this);
		menu = new Menu(this, handler, hud);
		
		//keyboard and mouse listeners
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		//load in audio files
		AudioPlayer.load();
		//play background music, loops when done
		AudioPlayer.getMusic("music").loop();
		
		new Window(WIDTH, HEIGHT, "Rainbow Dodge", this);
		
		r = new Random();
		
		//spawn initial menu particles
		for(int i = 0; i < 20; i++) {
			handler.addObject(new MenuParticle(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.MenuParticle, handler));
		}
		
		//spawning in initial entities when game state is set as game (note: moved to Menu class)
		/*if(gameState == STATE.Game) {
			//spawning the player
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
			
			//spawning initial enemies
			for(int i = 0; i < 1; i++) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
		}*/
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		//double amountOfTicks = 60.0;
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: "+ frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		//when the game state is game, hud and spawner will update
		if (gameState == STATE.Game) {
			//if not paused update everything, if not don't, which results in a pause effect
			if(!paused) {
				handler.tick();
				hud.tick();
				spawner.tick();
				
				//when health drops to 0, aka when you lose, clear enemies and reset health to full, game over screen activate
				if(HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					spawner.setScoreKeep(0);
					gameState = STATE.End;
					handler.clearEnemys();
					//adding Menu particle entities back in
					for(int i = 0; i < 20; i++) {
						handler.addObject(new MenuParticle(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.MenuParticle, handler));
					}
				}
			}
		}
		//Game States where I want the ticks to be updated
		else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Instructions || gameState == STATE.Select ) {
			menu.tick();
			handler.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
				
		if(paused) {
			g.setColor(Color.white);
			g.drawString("PAUSED", 550, 20);
		}
		
		//when the game state is registered as in the game, hud will render
		if(gameState == STATE.Game) {
			hud.render(g);
			handler.render(g);
		}
		//game state shop
		else if(gameState == STATE.Shop) {
			shop.render(g);
			hud.render(g);
		}
		//Game states where I want graphics to render in
		else if (gameState == STATE.Menu || gameState == STATE.Instructions || gameState == STATE.End || gameState == STATE.Select) {
			menu.render(g);
			handler.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp (float var, float min, float max) {
		if(var >= max)
			return var  = max;
		else if (var <= min)
			return var = min;
		else 
			return var;
	}
	
	public static void main(String []args) {
		new Game();
	}
}
