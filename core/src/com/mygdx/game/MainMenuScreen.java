package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    final JumpyBirb game;
    final Background background;

    OrthographicCamera camera;

    public MainMenuScreen(final JumpyBirb game) {
        this.game = game;

        background = new Background(0, 0);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(background.getBackgroundImage(), background.getBackgroundPosition().x, background.getBackgroundPosition().y);
        game.titleFont.draw(game.batch, "JUMPY BIRB", 100, 390);
        game.mainFont.draw(game.batch, "Options", 270, 270);
        game.mainFont.draw(game.batch, "Quit", 270, 190);
        game.accentFont.draw(game.batch, "Tap anywhere to begin!", 100, 70);
        game.batch.end();

        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
