package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public int spd = 4;
	public int bullet_spd = 5;
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		try {
			for(int i = 0; i < object.size(); i++) {
				GameObject tempObject = object.get(i);
			
				tempObject.render(g);
			}
		}catch(Exception e){}
	}
	
	public void clearEnemys() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() != ID.Player) {
				removeObject(tempObject);
				i--; 
			}
			else if (Game.gameState == Game.STATE.End) {
				removeObject(tempObject);
				i--;
			}
			//note that every time i remove an object from the LinkedList, it actually repositions all the other objects in the list backwards by 1, so i-- is to fix that
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
}
