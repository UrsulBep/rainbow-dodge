package com.tutorial.main;

import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	//loads in my sound files to be used in the game from the res folder

	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load() {
		
		try {
			soundMap.put("button_sound", new Sound("res/zapsplat_multimedia_button_press_plastic_click_002_36869.wav"));
			soundMap.put("shoot_sound", new Sound("res/scope1_edited.wav"));
			soundMap.put("hit_sound", new Sound("res/HitSound.wav"));
			soundMap.put("pickup_sound", new Sound("res/Plink.wav"));
			soundMap.put("death_sound", new Sound("res/Roblox-death-sound.wav"));
			
			musicMap.put("music", new Music("res/raving-energy-edited-2.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
}
