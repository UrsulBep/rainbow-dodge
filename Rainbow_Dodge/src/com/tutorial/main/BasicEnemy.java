package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject {
	
	private Handler handler;
	
	private BufferedImage enemy_image;

	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		enemy_image = ss.grabImage(2, 1, 16, 16);
		
		velX = 5;
		velY = 5;
		
		HEALTH = 3;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() {
		x += velX;
		y += velY;
		//bounce when hits screen boundaries
		if (y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 28) velX *= -1;
		//trail code
		handler.addObject(new TrailSprites(x, y, ID.Trail, enemy_image, 16, 16, 0.05f, handler));
	}

	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect((int)x, (int)y, 16, 16);
		g.drawImage(enemy_image, (int)x, (int)y, null);
	}

}
