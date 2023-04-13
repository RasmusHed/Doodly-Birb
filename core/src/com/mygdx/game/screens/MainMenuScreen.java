package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.HighscoreList;
import com.mygdx.game.TopFiveScores;
import com.mygdx.game.sprites.Background;
import com.mygdx.game.JumpyBirb;
import com.mygdx.game.Settings;

public class MainMenuScreen implements Screen {
    private static final int HIGHSCORE_X = 270;
    private static final int HIGHSCORE_Y = 310;
    private static final int OPTIONS_X = 270;
    private static final int OPTIONS_Y = 230;
    private static final int QUIT_X = 270;
    private static final int QUIT_Y = 150;
    final JumpyBirb game;
    final Background background;

    OrthographicCamera camera;

    public MainMenuScreen(final JumpyBirb game) {
        this.game = game;

        background = new Background(0, 0);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        HighscoreList.createHighscoreFile();
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
        game.titleFont.draw(game.batch, "JUMPY BIRB", 100, 420);
        game.mainFont.draw(game.batch, "Highscore", HIGHSCORE_X, HIGHSCORE_Y);
        game.mainFont.draw(game.batch, "Options", OPTIONS_X, OPTIONS_Y);
        game.mainFont.draw(game.batch, "Quit", QUIT_X, QUIT_Y);
        game.accentFont.draw(game.batch, "Tap anywhere to begin!", 100, 70);
        manuOptions(game);
        game.batch.end();

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

    public static void manuOptions(final JumpyBirb game) {
        // Highscore button
        if (Gdx.input.justTouched() && Gdx.input.getX() >= HIGHSCORE_X && Gdx.input.getX() <= HIGHSCORE_X + 260 && Gdx.input.getY() >= HIGHSCORE_Y - 140 && Gdx.input.getY() <= HIGHSCORE_Y - 90) {
            game.setScreen(new TopFiveScores(game));
        }
        // Options button
        else if (Gdx.input.justTouched() && Gdx.input.getX() >= OPTIONS_X && Gdx.input.getX() <= OPTIONS_X + 210 && Gdx.input.getY() >= OPTIONS_Y + 30 && Gdx.input.getY() <= OPTIONS_Y + 70) {
            game.setScreen(new OptionsScreen(game));
        }
        // Quit button
        else if (Gdx.input.justTouched() && Gdx.input.getX() >= QUIT_X && Gdx.input.getX() <= QUIT_X + 120 && Gdx.input.getY() >= QUIT_Y + 190 && Gdx.input.getY() <= QUIT_Y + 240) {
            Gdx.app.exit();
        }
        else if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game));
        }
    }
}
