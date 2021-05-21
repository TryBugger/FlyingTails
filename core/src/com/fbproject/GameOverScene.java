package com.fbproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.ScreenAdapter;

public class GameOverScene extends ScreenAdapter {
    FlappyBird game;

    private GlyphLayout scoreText;
    private GlyphLayout gameOverText;
    
    BitmapFont font;
    
    private Texture bgTexture;
    
    public GameOverScene(FlappyBird game) {
        this.game  = game;

        bgTexture = new Texture("SonicBackground.png");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("anticlimax.regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 60;
        font = generator.generateFont(parameter);
        generator.dispose();

        scoreText = new GlyphLayout(font, Integer.toString(game.text.getScore()));
        gameOverText = new GlyphLayout(font, game.text.getGameOver());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);

        drawObject();
    }

    private void drawObject() {
        game.batch.begin();
        
        game.batch.draw(bgTexture, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);

        font.setColor(242/255f, 133/255f, 24/255f, 1);
        font.draw(game.batch, 
                        game.text.getGameOver(),
                        FlappyBird.WIDTH / 2 - (gameOverText.width / 2), 
                        FlappyBird.HEIGHT / 2 + 200 + (gameOverText.height / 2));
        font.draw(game.batch, 
                        Integer.toString(game.text.getScore()), 
                        FlappyBird.WIDTH / 2 - (scoreText.width / 2), 
                        FlappyBird.HEIGHT / 2 + 100 + (scoreText.height / 2));

        System.out.println(font.getXHeight());

        game.batch.end();
    }
}
