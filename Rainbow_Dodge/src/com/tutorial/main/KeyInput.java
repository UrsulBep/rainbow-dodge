package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tutorial.main.Game.STATE;


public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	private boolean uP = false;
	private boolean dP = false;
	private boolean lP = false;
	private boolean rP = false;
	private boolean spaceDown = false;
	
	Game game;
		
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
	}
	


	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				//key events for player 1
				
				if (key == KeyEvent.VK_W) {
					uP = true; 
					tempObject.setVelY(-handler.spd);
				
				}
				if (key == KeyEvent.VK_S) {
					dP = true;
					tempObject.setVelY(handler.spd);
				}
				if (key == KeyEvent.VK_A) {
					lP = true;
					tempObject.setVelX(-handler.spd);
				}
				if (key == KeyEvent.VK_D) {
					rP = true;
					tempObject.setVelX(handler.spd);
				}
				if(key == KeyEvent.VK_SPACE) {
					spaceDown = true;
				}
				
			}
			//pause key
			if(Game.gameState == STATE.Game) {
				if(key == KeyEvent.VK_P) {
					Game.paused = !Game.paused;
					key = -1;
				}
			}
			//shop key
			if(key == KeyEvent.VK_SPACE) {
				if(Game.gameState == STATE.Game) {
					Game.gameState = STATE.Shop;
					key = -1;
				}
				else if (Game.gameState == STATE.Shop) {
					Game.gameState = STATE.Game;
					key = -1;
				}
			}
			//exit key
			if (key == KeyEvent.VK_ESCAPE) System.exit(1);
			
		}
		//System.out.println(key);
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				//key events for player 1
				
				if (key == KeyEvent.VK_W) {
					uP = false; 
					//if (dP) tempObject.setVelY(5);
						//else tempObject.setVelY(0);
				
				}
				if (key == KeyEvent.VK_S) {
					dP = false;
					//if (uP) tempObject.setVelY(-5);
						//else tempObject.setVelY(0);
				}
				if (key == KeyEvent.VK_A) {
					lP = false;
					//if (rP) tempObject.setVelX(5);
						//else tempObject.setVelX(0);
				}
				if (key == KeyEvent.VK_D) {
					rP = false;
					//if (lP) tempObject.setVelX(-5);
						//else tempObject.setVelX(0);
				}
				if (key == KeyEvent.VK_SPACE) {
					spaceDown = false;
				}
				
				//vertical movement sticky fix
				if (!uP && !dP) tempObject.setVelY(0);
				//horizontal movement sticky fix
				if (!lP && !rP) tempObject.setVelX(0);
			}
			
				
			
		}
		
		//System.out.println(key);
		
	}
	
}
