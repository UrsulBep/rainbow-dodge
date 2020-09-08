package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PlayerBullet extends GameObject {
	
	private Handler handler;
	private HUD hud;
	
	private BufferedImage bullet_image;

	public PlayerBullet(int x, int y, double velX, double velY, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		
		this.velX = (float) (velX * handler.bullet_spd);
		this.velY = (float) (velY * handler.bullet_spd);
		this.handler = handler;
		this.hud = hud;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		bullet_image = ss.grabImage(2, 2, 12, 12);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		//remove bullet code on screen boundaries
		if(x <= 0 || x >= Game.WIDTH || y <= 0 || y >= Game.HEIGHT) {
			handler.removeObject(this);
		}
		 //trail code
		 handler.addObject(new TrailSprites(x, y, ID.Trail, bullet_image, 32, 32, 0.1f, handler));
		
		collision();
	}
	
	//collision code for the bullet
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			//bullet collides with enemy
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy ||  tempObject.getId() == ID.RandomEnemy || tempObject.getId() == ID.SuperRandomEnemy || tempObject.getId() == ID.EnemyBoss || tempObject.getId() == ID.EnemyBossBullet) { //tempObject is now BasicEnemy/FastEnemy/etc
				if(getBounds().intersects(tempObject.getBounds())) {
					//subtract health from enemy
					AudioPlayer.getSound("hit_sound").play();
					tempObject.HEALTH --;
					handler.removeObject(this);
					//kill enemies at 0 health
					if(tempObject.HEALTH <= 0) {
						if(tempObject.getId() != ID.EnemyBossBullet) {
						AudioPlayer.getSound("death_sound").play();
						hud.setScore(hud.getScore() + 250);
						}
						handler.removeObject(tempObject);
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(bullet_image,(int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 12, 12);
	}
	
	

}
