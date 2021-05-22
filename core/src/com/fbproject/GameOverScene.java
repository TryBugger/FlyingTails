package com.fbproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;

public class GameOverScene extends ScreenAdapter {
    FlappyBird game;

    private Texture bgTexture;

    TextInterface text;

    private BitmapFont font;
    private GlyphLayout scoreText;
    private GlyphLayout gameOverText;
    
    public GameOverScene(FlappyBird game) {
        this.game  = game;
    }

    @Override
    public void show() {
        bgTexture = new Texture("SonicBackground.png");

        text = new TextInterface();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("anticlimax.regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 60;
        font = generator.generateFont(parameter);
        generator.dispose();

        scoreText = new GlyphLayout(font, Integer.toString(TextInterface.score));
        gameOverText = new GlyphLayout(font, TextInterface.gameOver);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);

        drawObject();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyUp(int keycode) {
                if(keycode == Keys.ENTER) {
                    if(TextInterface.score > text.getHighScore()) {
                        TextInterface.highScore = (TextInterface.score);
                    }
                    TextInterface.score = 0;
                    game.setScreen(new GameScene(game));
                } else if(keycode == Keys.ESCAPE) {
                    Gdx.app.exit();
                }
                
                return true;
            }
        });
    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        font.dispose();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    private void drawObject() {
        game.batch.begin();
        
        game.batch.draw(bgTexture, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);

        font.setColor(242/255f, 133/255f, 24/255f, 1);
        font.draw(game.batch, 
                        TextInterface.gameOver,
                        FlappyBird.WIDTH / 2 - (gameOverText.width / 2), 
                        FlappyBird.HEIGHT / 2 + (gameOverText.height / 2) + 200);
        font.draw(game.batch, 
                        Integer.toString(TextInterface.score), 
                        FlappyBird.WIDTH / 2 - (scoreText.width / 2), 
                        FlappyBird.HEIGHT / 2 + (scoreText.height / 2) + 100);

        System.out.println(font.getXHeight());

        game.batch.end();
    }
}
