package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class RandomEnemy extends GameObject {
	
	private Handler handler;
	private Random r = new Random();
	private Color col;
	private int colShift = 0;
	
	public RandomEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		//initial random velocities (aka direction and speed)
		velX = (r.nextInt(5 - -5) + -5);
		velY = (r.nextInt(5 - -5) + -5);
		
		while(velX == 0 && velY == 0) {
			velX = (r.nextInt(5 - -5) + -5);
			velY = (r.nextInt(5 - -5) + -5);
		}
		
		HEALTH = 5;
		//col is a random always changing color
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() {
		x += velX;
		y += velY;
		//bounce when hits screen boundaries (is also very random for this version)
		if (y <= 0 || y >= Game.HEIGHT - 52) {
			velY *= -1;
			velX = (r.nextInt(5 - -5) + -5);
		}
		if (x <= 0 || x >= Game.WIDTH - 28) {
			velX *= -1;
			velY = (r.nextInt(5 - -5) + -5);
		}
		//trail code
		handler.addObject(new Trail(x, y, ID.Trail, col, 16, 16, 0.05f, handler));
		
		if(colShift == 13) colShift = 0;
		//makes the color shift randomly
		if(colShift == 12) {
			col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		}
		colShift++;
	}

	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
