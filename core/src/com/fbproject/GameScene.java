package com.fbproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class GameScene extends ScreenAdapter {
    FlappyBird game;
    
    private Tails tails;	
    private Array<Pipe> pipes;
    private BitmapFont font;
    
    private Texture bgTexture;
    private static final int POINT = 5; 

    public GameScene(FlappyBird game) {
        this.game = game;

    }

    @Override
    public void show() {
        bgTexture =  new Texture("SonicBackground.png");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Poppins-SemiBoldItalic.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 120;
        font = generator.generateFont(parameter);
        generator.dispose();
		
		tails = new Tails(FlappyBird.WIDTH / 4, FlappyBird.HEIGHT / 2);		
		pipes = new Array<Pipe>();
		for(int i = 1; i <= Pipe.getPipeTotal(); i++) {
			pipes.add(new Pipe(i * (Pipe.getPipeSpacing() + Pipe.getPipeWidth()) + FlappyBird.WIDTH));
		}
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);

		if (!isPlayerCollided()) {			
			drawObject();

			tails.move();
			for(Pipe pipe : pipes) {
				pipe.move();
			}	
		} else {
            game.setScreen(new GameOverScene(game));
        }
    }

    @Override
    public void dispose() {
        tails.despawn();
        for(Pipe pipe : pipes) {
            pipe.despawn();
        }
        font.dispose();
        bgTexture.dispose();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    private void drawObject() {		
		game.batch.begin();		
		game.batch.draw(bgTexture, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
		
		for(Pipe pipe : pipes) {		
			pipe.getsPipeSide(0).draw(game.batch);			
			pipe.getsPipeSide(1).draw(game.batch);
		}		

		tails.getSprite().draw(game.batch);
		
		font.setColor(245/255f, 130/255f, 15/255f, 1);				
		font.draw(game.batch, Integer.toString(TextInterface.score), 50f, FlappyBird.HEIGHT - 50f);        
		
		game.batch.end();
	}

    private boolean isPlayerCollided() {		
		boolean checker = false;		
		
		if(tails.getSprite().getY() <= 0 || tails.getSprite().getY() + tails.getSprite().getHeight() >= FlappyBird.HEIGHT) {
			return true;
		}		
		
		for(Pipe pipe : pipes) {			
			checker = 
			(				
			((tails.getSprite().getY() + Tails.getHitboxReducer() / 2) <= pipe.getsPipeSide(0).getY() + pipe.getsPipeSide(0).getHeight())
			&&
			(				
			((tails.getSprite().getX() + Tails.getHitboxReducer() / 2) <= pipe.getsPipeSide(0).getX() + pipe.getsPipeSide(0).getWidth()) 
			&& 				
			((tails.getSprite().getX() + Tails.getHitboxReducer() / 2)  + (tails.getSprite().getWidth() - Tails.getHitboxReducer()) >= pipe.getsPipeSide(0).getX())
			)
			) 
			|| 
			(				
			((tails.getSprite().getY() + Tails.getHitboxReducer() / 2) + (tails.getSprite().getHeight() - Tails.getHitboxReducer()) >= pipe.getsPipeSide(1).getY())
			&&
			(				
			((tails.getSprite().getX() + Tails.getHitboxReducer() / 2) <= pipe.getsPipeSide(1).getX() + pipe.getsPipeSide(1).getWidth()) 
			&& 				
			((tails.getSprite().getX() + Tails.getHitboxReducer() / 2)  + (tails.getSprite().getWidth() - Tails.getHitboxReducer()) >= pipe.getsPipeSide(1).getX())
			)
			);			
			
			tailsThroughPipe(pipe);		
			
			if(checker) {
				break;
			}
		}	
		
		return checker;
	}
	
	private void tailsThroughPipe(Pipe pipe) {
	if(
			(				
				((tails.getSprite().getY() + Tails.getHitboxReducer() / 2) > pipe.getsPipeSide(0).getY() + pipe.getsPipeSide(0).getHeight())
				&&				
				((tails.getSprite().getY() + Tails.getHitboxReducer() / 2) + (tails.getSprite().getHeight() - Tails.getHitboxReducer()) < pipe.getsPipeSide(1).getY())
			)
			&&
			(				
				MathUtils.isEqual((tails.getSprite().getX() + Tails.getHitboxReducer() / 2), pipe.getsPipeSide(0).getX() + pipe.getsPipeSide(0).getWidth(), 0.5f)
			)
		) {			
			TextInterface.score += POINT;
		}
	}
}
