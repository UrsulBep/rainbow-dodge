package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject {
	
	Random r = new Random();
	Handler handler;
	HUD hud;
	
	private BufferedImage player_image;

	public Player(int x, int y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
				
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		player_image = ss.grabImage(1, 1, 32, 32);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32 ,32);
	}

	public void tick() {
		x += velX;
		y += velY;
		//prevents player from leaving screen boundaries
		 x = Game.clamp(x, 0, Game.WIDTH - 46);
		 y = Game.clamp(y, 0, Game.HEIGHT - 70);
		 //trail code
		 handler.addObject(new TrailSprites(x, y, ID.Trail, player_image, 32, 32, 0.05f, handler));
		 
		 collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			//collide with enemy
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy ||  tempObject.getId() == ID.RandomEnemy || tempObject.getId() == ID.SuperRandomEnemy || tempObject.getId() == ID.EnemyBoss || tempObject.getId() == ID.EnemyBossBullet) { //tempObject is now BasicEnemy/FastEnemy/etc
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
					HUD.HEALTH -= 2;
				}
			}
			//collide with healPickup's
			if(tempObject.getId() == ID.HealPickup) {
				if(getBounds().intersects(tempObject.getBounds())) {
					AudioPlayer.getSound("pickup_sound").play();
					HUD.HEALTH += 25;
					handler.removeObject(tempObject);
				}
			}
		}
	}
	
	//calculating bullet direction then adding a bullet object
	public void shoot(int mx, int my) {
		double distance = Math.sqrt(( Math.pow((mx - (x + 16)), 2)) + (Math.pow((my - y), 2)));
		double bulletVelX = (mx - (x + 16)) / distance;
		//if(mx - x < 0) bulletVelX *= -1;
		double bulletVelY = (my - (y + 16)) / distance;
		//if(my - y < 0) bulletVelY *= -1;
		handler.addObject(new PlayerBullet((int)x + 12, (int)y + 12, bulletVelX, bulletVelY, ID.PlayerBullet, handler , hud));
		AudioPlayer.getSound("shoot_sound").play();
	}

	public void render(Graphics g) {
		//g.setColor(Color.white);
		//g.fillRect((int)x, (int)y , 32,  32);
		g.drawImage(player_image, (int)x, (int)y, null);
	}

	
	
}
