package com.tutorial.main;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private Game game;
	
	private int scoreKeep = 0;
	private int healSpawns = r.nextInt(3) + 2;
	private int enemyGenerate;
	
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void tick() {
		scoreKeep++;
		if (scoreKeep >= 200) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			//Normal difficulty spawning
			if(game.difficulty == 0) {
				if(hud.getLevel() == 2) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel() == 3) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				}
				else if(hud.getLevel() == 4) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH  - 50), r.nextInt(Game.HEIGHT  - 50), ID.SmartEnemy, handler));
				}
				else if(hud.getLevel() == 5) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH  - 50), r.nextInt(Game.HEIGHT  - 50), ID.FastEnemy, handler));
				}
				else if(hud.getLevel() == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH  - 50), r.nextInt(Game.HEIGHT  - 50), ID.FastEnemy, handler));
				}
				else if(hud.getLevel() == 7) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH  - 50), r.nextInt(Game.HEIGHT  - 50), ID.FastEnemy, handler));
				}
				else if(hud.getLevel() == 10) {
					handler.clearEnemys();
					handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -100, ID.EnemyBoss, handler));
				}
				//continuous spawning after this point
				else if(hud.getLevel() >= 11 && hud.getLevel() % 2 == 0) {
					enemyGenerate = r.nextInt(5);
					System.out.println(enemyGenerate);
					if(enemyGenerate == 0) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					}
					else if(enemyGenerate == 1) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					}
					else if(enemyGenerate == 2) {
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH  - 50), r.nextInt(Game.HEIGHT  - 50), ID.SmartEnemy, handler));
					}
					else if(enemyGenerate == 3) {
						handler.addObject(new RandomEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.RandomEnemy, handler));
					}
					else if(enemyGenerate == 4) {
						handler.addObject(new SuperRandomEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SuperRandomEnemy, handler));
					}
				}
				
				//spawning health pickups every so often
				if (hud.getLevel() % healSpawns == 0) {
					handler.addObject(new HealPickup(r.nextInt(Game.WIDTH  - 50), r.nextInt(Game.HEIGHT  - 50), ID.HealPickup, handler));
					healSpawns = r.nextInt(3) + 2;
				}
				
			}
			//Hard difficulty spawning
			else if(game.difficulty == 1) {
				if(hud.getLevel() == 2) {
					handler.addObject(new RandomEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.RandomEnemy, handler));
				}
				else if(hud.getLevel() == 3) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				}
				else if(hud.getLevel() == 4) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH  - 50), r.nextInt(Game.HEIGHT  - 50), ID.SmartEnemy, handler));
				}
				else if(hud.getLevel() == 5) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH  - 50), r.nextInt(Game.HEIGHT  - 50), ID.FastEnemy, handler));
				}
				else if(hud.getLevel() == 6) {
					handler.addObject(new RandomEnemy(r.nextInt(Game.WIDTH  - 50), r.nextInt(Game.HEIGHT  - 50), ID.RandomEnemy, handler));
				}
				else if(hud.getLevel() == 7) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH  - 50), r.nextInt(Game.HEIGHT  - 50), ID.FastEnemy, handler));
				}
				else if(hud.getLevel() == 8) {
					handler.addObject(new SuperRandomEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SuperRandomEnemy, handler));
				}
				else if(hud.getLevel() == 10) {
					handler.clearEnemys();
					handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -100, ID.EnemyBoss, handler));
				}
				else if(hud.getLevel() == 11) {
					handler.addObject(new SuperRandomEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SuperRandomEnemy, handler));
					handler.addObject(new SuperRandomEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SuperRandomEnemy, handler));
				}
				
				else if(hud.getLevel() >= 12) {
					enemyGenerate = r.nextInt(5);
					System.out.println(enemyGenerate);
					if(enemyGenerate == 0) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					}
					else if(enemyGenerate == 1) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					}
					else if(enemyGenerate == 2) {
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH  - 50), r.nextInt(Game.HEIGHT  - 50), ID.SmartEnemy, handler));
					}
					else if(enemyGenerate == 3) {
						handler.addObject(new RandomEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.RandomEnemy, handler));
					}
					else if(enemyGenerate == 4) {
						handler.addObject(new SuperRandomEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SuperRandomEnemy, handler));
					}
				}
				
			}
		}
	}
	//getters and setters for scoreKeep
	public void setScoreKeep(int scoreKeep) {
		this.scoreKeep = scoreKeep;
	}
	
	public int getScoreKeep() {
		return scoreKeep;
	}
	
}
