package com.tutorial.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sprite;
	
	public SpriteSheet(BufferedImage ss) {
		this.sprite = ss;
	}
	
	public BufferedImage grabImage(int column, int row, int width, int height) {
		BufferedImage img = sprite.getSubimage((column * 32) - 32, (row * 32) - 32, width, height);
		return img;
	}
	
}
