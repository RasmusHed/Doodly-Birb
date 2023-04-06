package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Highscore;
import com.mygdx.game.JumpyBirb;
import com.mygdx.game.MyInputProcessor;
import com.mygdx.game.screens.GameScreen;

public class Deathscreen implements Screen {
    final JumpyBirb game;
    final Highscore score;
    String highscore;
    private static final int RETURN_X = 100;
    private static final int RETURN_Y = 150;
    OrthographicCamera camera;
    private boolean input;
    private float elapsedTime = 0;
    private MyInputProcessor inputProcessor;
    private String name = "";

    public Deathscreen(final JumpyBirb game, final Highscore score) {
        this.game = game;
        this.score = score;

        String[] highscoreArray = Highscore.getHighscores();
        highscore = highscoreArray[0];
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        inputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        elapsedTime += delta;
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.titleFont.draw(game.batch, "Highscore: " + name, 100, 400);
        game.mainFont.draw(game.batch, "Your score: " + score.getScore(), 100, 300);
        game.mainFont.draw(game.batch, "Press space to play again", 100, 240);
        game.mainFont.draw(game.batch, "Return to main screen", RETURN_X, RETURN_Y);
        if (!input && elapsedTime > 0.1 && Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            String lastKey = inputProcessor.getLastKeyPressed();
            name += lastKey;
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                input = true;
            }
        }
        game.batch.end();

        if (Gdx.input.justTouched() && Gdx.input.getX() >= RETURN_X && Gdx.input.getX() <= RETURN_X + 620 && Gdx.input.getY() >= RETURN_Y + 190 && Gdx.input.getY() <= RETURN_Y + 240) {
            game.setScreen(new MainMenuScreen(game));
        } else if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
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
