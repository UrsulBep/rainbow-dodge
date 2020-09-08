package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyBoss extends GameObject {
	
	private Handler handler;
	private BufferedImage enemy_image;
	Random r = new Random();
	
	private int timer = 60;
	private int timer2 = 50;

	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		enemy_image = ss.grabImage(1, 3, 64, 64);
		
		velX = 0;
		velY = 2;
		
		HEALTH = 30;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 64, 64);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(timer <= 0) {
			velY = 0;
			timer2--;
		}
		else
			timer--;
		if (timer2 <= 0) {
			if(velX == 0) {
				velX = 2;
			}
			if(velX > 0) {
				velX += 0.005f;
			}
			else if(velX < 0) {
				velX -= 0.005f;
			}
			
			velX = Game.clamp(velX, -10, 10);
			
			//bullet code
			int spawn = r.nextInt(10);
			if(spawn == 0) {
				handler.addObject(new EnemyBossBullet((int)x + 24, (int)y + 48, ID.EnemyBossBullet, handler));
			}
		}
		//bounce when hits screen boundaries
		//if (y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 78) velX *= -1;
		
		//trail code
		handler.addObject(new TrailSprites(x, y, ID.Trail, enemy_image, 64, 64, 0.08f, handler));
	}

	public void render(Graphics g) {
		g.drawImage(enemy_image, (int)x, (int)y, null);
	}

}
