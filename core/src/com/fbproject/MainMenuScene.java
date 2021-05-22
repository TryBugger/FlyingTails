package com.fbproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;

public class MainMenuScene extends ScreenAdapter {
    FlappyBird game;

    private Texture bgTexture;
    private Texture tailsTexture;
    private Sprite tailsImage;
    
    private BitmapFont font;
    private GlyphLayout titleFont;

    public MainMenuScene(FlappyBird game) {
        this.game = game;
    }

    @Override
    public void show() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("DigitaltsOrange.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 80;
        font = generator.generateFont(parameter);
        generator.dispose();
        titleFont = new GlyphLayout(font, 
                                    TextInterface.titleText,
                                    new Color(242/255f, 133/255f, 24/255f, 1f),
                                    220f,
                                    Align.center,
                                    true);

        bgTexture = new Texture("jungleBackground.jpg");

        tailsTexture = new Texture("TailsMenu.png");
        tailsImage = new Sprite(tailsTexture);
        tailsImage.setSize(150 * (822/822), 150 * (972/822));
    }

    @Override
    public void render(float delta) {
        drawObject();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyUp(int keycode) {
                if(keycode == Keys.ENTER) {
                    game.setScreen(new GameScene(game));
                }
                return true;
            }
        });
    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        tailsTexture.dispose();
        font.dispose();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    private void drawObject() {
        game.batch.begin();

        game.batch.draw(bgTexture, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);

        font.setColor(new Color(242/255f, 133/255f, 24/255f, 1f));
        font.draw(game.batch, 
                    TextInterface.titleText, 
                    (FlappyBird.WIDTH / 2) - (titleFont.width / 2), 
                    (FlappyBird.HEIGHT / 2) + (titleFont.height / 2) + 200,
                    220f,
                    Align.center,
                    true);


        tailsImage.setPosition((FlappyBird.WIDTH / 2) - (tailsImage.getWidth() / 2),
                (FlappyBird.HEIGHT / 2) - (tailsImage.getHeight() / 2) - 150);
        tailsImage.draw(game.batch);

        game.batch.end();
    }
}
