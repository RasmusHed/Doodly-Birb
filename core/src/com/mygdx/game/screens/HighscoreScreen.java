package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.HighscoreList;
import com.mygdx.game.DoodlyBirb;
import com.mygdx.game.utils.Settings;
import com.mygdx.game.sprites.Background;

public class HighscoreScreen implements Screen {
    final DoodlyBirb game;
    final Background background;

    OrthographicCamera camera;

    public HighscoreScreen(final DoodlyBirb game) {
        this.game = game;

        background = new Background(0, 0);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(background.getBackgroundImage(), background.getBackgroundPosition().x, background.getBackgroundPosition().y);
        game.titleFont.draw(game.batch, "Highscore:", 30, 460);
        game.mainFont.draw(game.batch, "Tap anywhere to return!", 30, 100);
        int pos = 440;
        for (String scores : HighscoreList.getHighscores()) {
            game.mainFont.draw(game.batch, scores,440, pos);
            pos -= 70;
        }
        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(new MainMenuScreen(game));
        }
        game.batch.end();
    }

    @Override
    public void resize(int i, int i1) {

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
