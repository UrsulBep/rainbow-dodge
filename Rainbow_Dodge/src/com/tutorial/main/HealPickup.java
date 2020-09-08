package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class HealPickup extends GameObject {
	
	private Handler handler;
	
	private BufferedImage pickup_image;

	public HealPickup(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		pickup_image = ss.grabImage(1, 2, 32, 32);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() {

	}

	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect((int)x, (int)y, 16, 16);
		g.drawImage(pickup_image, (int)x, (int)y, null);
	}

}
