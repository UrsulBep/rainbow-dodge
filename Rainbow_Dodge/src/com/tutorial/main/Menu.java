package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//Button clicks while in the menu
		if(game.gameState == STATE.Menu) {
			//Play button click
			if(mouseOver(mx, my, 235, 100, 150, 64)) {
				game.gameState = STATE.Select;
				//click sound when pressing button
				AudioPlayer.getSound("button_sound").play();
				return;
			}
			//Instructions button click
			if(mouseOver(mx, my, 235, 200, 150, 64)) {
				game.gameState = STATE.Instructions;
				//click sound when pressing button
				AudioPlayer.getSound("button_sound").play();
			}
			//Exit button click
			if(mouseOver(mx, my, 235, 300, 150, 64)) {
				//click sound when pressing button, have to put it above here because system.exit has to come last or it doesn't play sound
				AudioPlayer.getSound("button_sound").play();
				System.exit(1);
			}
		}
		
		if(game.gameState == STATE.Select) {
			//Normal button click
			if(mouseOver(mx, my, 235, 100, 150, 64)) {
				game.gameState = STATE.Game;
				//clear out MenuParticles
				handler.clearEnemys();
				//spawning the player
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler, hud));
				//spawning initial enemies
				for(int i = 0; i < 1; i++) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				
				game.difficulty = 0;
				
				//click sound when pressing button
				AudioPlayer.getSound("button_sound").play();
			}
			//Hard button click
			if(mouseOver(mx, my, 235, 200, 150, 64)) {
				game.gameState = STATE.Game;
				//clear out MenuParticles
				handler.clearEnemys();
				//spawning the player
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler, hud));
				//spawning initial enemies
				for(int i = 0; i < 1; i++) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				
				game.difficulty = 1;
				
				//click sound when pressing button
				AudioPlayer.getSound("button_sound").play();
			}
			//Back button click
			if(mouseOver(mx, my, 235, 300, 150, 64)) {
				//click sound when pressing button, have to put it above here because system.exit has to come last or it doesn't play sound
				AudioPlayer.getSound("button_sound").play();
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		//Mouse input while in game
		else if (game.gameState == STATE.Game && !Game.paused) {
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player) {
					((Player) tempObject).shoot(mx, my);
				}
			}
		}
		
		//Button clicks while in Instructions
		else if (game.gameState == STATE.Instructions) {
			//Back button click
			if(mouseOver(mx, my, 235, 300, 150, 64)) {
				game.gameState = STATE.Menu;
				//click sound when pressing button
				AudioPlayer.getSound("button_sound").play();
			}
		}
		
		//Button clicks on game over screen
		else if (game.gameState == STATE.End) {
			//try again
			if(mouseOver(mx, my, 235, 200, 150, 64)) {
				game.gameState = STATE.Game;
				//reset score and levels back to their base values
				hud.setScore(0);
				hud.setLevel(1);
				//clear out MenuParticles
				handler.clearEnemys();
				//spawning the player
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler, hud));
				
				//spawning initial enemies
				for(int i = 0; i < 1; i++) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				//click sound when pressing button
				AudioPlayer.getSound("button_sound").play();
			}
			//return to Menu
			if(mouseOver(mx, my, 235, 300, 150, 64)) {
				game.gameState = STATE.Menu;
				hud.setScore(0);
				hud.setLevel(1);
				//click sound when pressing button
				AudioPlayer.getSound("button_sound").play();
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	//checks if mouse coords during mousePressed event are within clickable boxes in menu
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		//graphics inside the Menu state
		if(game.gameState == STATE.Menu) {
			Font fntBig = new Font("arial", 1, 50);
			Font fntMedium = new Font("arial", 1, 25);
			Font fntSmall = new Font("arial", 1, 11);
			
			//title
			g.setColor(Color.white);
			g.setFont(fntBig);
			g.drawString("Rainbow Dodge", 110, 50);
			
			//Play button
			g.setColor(Color.white);
			g.setFont(fntBig);
			g.drawRect(235, 100, 150, 64);
			g.drawString("Play", 260, 150);
			
			//Instructions button
			g.setColor(Color.white);
			g.setFont(fntMedium);
			g.drawRect(235, 200, 150, 64);
			g.drawString("Instructions", 240, 243);
			
			//Exit button
			g.setColor(Color.white);
			g.setFont(fntBig);
			g.drawRect(235, 300, 150, 64);
			g.drawString("Exit", 260, 350);
		}
		//graphics inside the Instructions state
		else if(game.gameState == STATE.Instructions) {
			Font fntBig = new Font("arial", 1, 50);
			Font fntMedium = new Font("arial", 1, 25);
			Font fntSmall = new Font("arial", 1, 11);
			
			//Instructions heading
			g.setColor(Color.yellow);
			g.setFont(fntBig);
			g.drawString("Instructions", 175, 50);
			
			//The Controls
			g.setColor(Color.yellow);
			g.setFont(fntMedium);
			g.drawString("Controls:", 10, 80);
			g.setFont(fntSmall);
			g.drawString("Movement:", 10, 100);
			g.drawString("WASD to move", 10, 120);
			g.drawString("Shooting:", 10, 160);
			g.drawString("LEFT CLICK to shoot", 10, 180);
			g.drawString("Shooting enemies will deplete their health,", 10, 200);
			g.drawString("eventually killing them", 10, 220);
			g.drawString("Killing enemies also gives you points", 10, 240);
			g.drawString("Other controls:", 10, 280);
			g.drawString("SPACEBAR to enter and exit Shop", 10, 300);
			g.drawString("P to pause", 10, 320);
			
			
			//Goal of game
			g.setColor(Color.yellow);
			g.setFont(fntMedium);
			g.drawString("How to play:", 340, 80);
			g.setFont(fntSmall);
			g.drawString("Rogue cubes are out to get you!", 340, 100);
			g.drawString("Touching enemies will deplete your health,", 340, 120);
			g.drawString("Avoid them at all costs and survive as long as", 340, 140);
			g.drawString("you can! When your health depletes its game over.", 340, 160);
			g.drawString("Buy Upgrades in the store with points to last longer.", 340, 180);
			g.drawString("Try to rack up a high score!", 340, 200);
			
			//Back button
			g.setColor(Color.yellow);
			g.setFont(fntBig);
			g.drawRect(235, 300, 150, 64);
			g.drawString("Back", 250, 350);
		}
		//graphics in the Game over screen/end state
		else if(game.gameState == STATE.End) {
			Font fntBig = new Font("arial", 1, 50);
			Font fntMedium = new Font("arial", 1, 25);
			Font fntSmall = new Font("arial", 1, 11);
			
			//Game over heading
			g.setColor(Color.red);
			g.setFont(fntBig);
			g.drawString("Game Over", 175, 50);
			
			g.setFont(fntMedium);
			g.drawString("You lose", 200, 100);
			g.drawString("Score: " + hud.getScore(), 200, 140);
			
			//Try again button
			g.setColor(Color.red);
			g.setFont(fntMedium);
			g.drawRect(235, 200, 150, 64);
			g.drawString("Try again?", 248, 240);
			
			//Back/Menu button
			g.setColor(Color.red);
			g.setFont(fntBig);
			g.drawRect(235, 300, 150, 64);
			g.drawString("Menu", 245, 350);
		}
		//DIFFICULTY select screen(from Play)
		else if(game.gameState == STATE.Select) {
			Font fntBig = new Font("arial", 1, 50);
			Font fntBigMedium = new Font("arial", 1, 40);
			Font fntMedium = new Font("arial", 1, 25);
			Font fntSmall = new Font("arial", 1, 11);
			
			//title
			g.setColor(Color.white);
			g.setFont(fntBig);
			g.drawString("SELECT DIFFICULTY", 70, 50);
			
			//Play button
			g.setColor(Color.white);
			g.setFont(fntBigMedium);
			g.drawRect(235, 100, 150, 64);
			g.drawString("Normal", 240, 150);
			
			//Instructions button
			g.setColor(Color.white);
			g.setFont(fntBig);
			g.drawRect(235, 200, 150, 64);
			g.drawString("Hard", 250, 250);
			
			//Exit button
			g.setColor(Color.white);
			g.setFont(fntBig);
			g.drawRect(235, 300, 150, 64);
			g.drawString("Back", 250, 350);
		}
	}

}
