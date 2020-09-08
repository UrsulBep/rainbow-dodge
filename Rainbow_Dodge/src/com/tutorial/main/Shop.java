package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.tutorial.main.Game.STATE;

public class Shop extends MouseAdapter {
	
	Handler handler;
	HUD hud;
	//prices
	private int[] cost = {500, 500, 1000, 250};
	
	public Shop(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial", 0, 48));
		g.drawString("SHOP", Game.WIDTH/2-76, 50);
		
		//upgrade health button(1)
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Upgrade Health", 110, 120);
		g.drawString("Cost: " + cost[0], 110, 140);
		g.drawRect(100, 100, 100, 80);
		
		//upgrade speed button(2)
		g.drawString("Upgrade Speed", 260, 120);
		g.drawString("Current Speed: " + (handler.spd - 3), 255, 140);
		g.drawString("Cost: " + cost[1], 260, 160);
		g.drawRect(250, 100, 100, 80);
				
		//Refill health button(3)
		g.drawString("Refill Health", 420, 120);
		g.drawString("Cost: " + cost[2], 410, 140);
		g.drawRect(400, 100, 100, 80);
		
		//upgrade bullet speed button(4)
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Upgrade bullet", 110, 220);
		g.drawString("speed", 110, 240);
		g.drawString("Current Speed: " + (handler.bullet_spd - 4), 105, 260);
		g.drawString("Cost: " + cost[3], 110, 280);
		g.drawRect(100, 200, 100, 90);
		
		g.drawString("SCORE: " + hud.getScore(), Game.WIDTH/2-50, 300);
		g.drawString("Press Space to exit", Game.WIDTH/2-50, 330);
		
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Shop) {
			//upgrade health button(1)
			if(mouseOver(mx , my, 100, 100, 100, 80)) {
				if(hud.getScore() >= cost[0]) {
					AudioPlayer.getSound("button_sound").play();
					hud.setScore(hud.getScore() - cost[0]);
					cost[0] += 750;
					hud.bounds += 20;
					HUD.HEALTH += hud.bounds / 2;
				}
			}
			//upgrade speed button(2)
			if(mouseOver(mx , my, 250, 100, 100, 80)) {
				if(hud.getScore() >= cost[1]) {
					AudioPlayer.getSound("button_sound").play();
					hud.setScore(hud.getScore() - cost[1]);
					cost[1] += 750;
					handler.spd++;
				}
			}
			//refill health button(3)
			if(mouseOver(mx , my, 400, 100, 100, 80)) {
				if(hud.getScore() >= cost[2]) {
					AudioPlayer.getSound("button_sound").play();
					hud.setScore(hud.getScore() - cost[2]);
					HUD.HEALTH = (100 + (hud.bounds/2));
				}
			}
			//upgrade bullet speed button(4)
			if(mouseOver(mx , my, 100, 200, 100, 80)) {
				if(hud.getScore() >= cost[3]) {
					AudioPlayer.getSound("button_sound").play();
					hud.setScore(hud.getScore() - cost[3]);
					cost[3] += 500;
					handler.bullet_spd++;
				}
			}
		}
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
			else return false;
		}
		else return false;
	}

}
