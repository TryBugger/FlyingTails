package com.fbproject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends Game {
	public static final int HEIGHT = 800; 
	public static final int WIDTH = 480; 
	public static final String TITLE = TextInterface.titleText;
	
	public SpriteBatch batch;	
	
	@Override
	public void create() {		
		batch = new SpriteBatch();
		setScreen(new MainMenuScene(this));
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
}