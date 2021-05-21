package com.fbproject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class FlappyBird extends Game {
	public static final int HEIGHT = 800; 
	public static final int WIDTH = 480; 
	public static final String TITLE = "Legenda Ekor Terbang";
	
	SpriteBatch batch;	
	ShapeRenderer shapeRenderer;

	TextInterface text;
	
	@Override
	public void create() {		
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		text = new TextInterface();
		setScreen(new GameScene(this));
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		shapeRenderer.dispose();
	}
}