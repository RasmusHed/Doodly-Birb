package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.HighscoreList;
import com.mygdx.game.JumpyBirb;
import com.mygdx.game.MyInputProcessor;
import com.mygdx.game.Score;
import com.mygdx.game.sprites.Background;

public class DeathScreen implements Screen {
    private static final int RETURN_X = 100;
    private static final int RETURN_Y = 150;
    final JumpyBirb game;
    final Score score;
    final Background background;
    String highscore;
    OrthographicCamera camera;
    private MyInputProcessor inputProcessor;


    public DeathScreen(final JumpyBirb game, final Score score) {
        this.game = game;
        this.score = score;
        background = new Background(0, 0);

        String[] highscoreArray = HighscoreList.getHighscores();
        highscore = highscoreArray[0].split(": ")[1];
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
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background.getBackgroundImage(), background.getBackgroundPosition().x, background.getBackgroundPosition().y);
        if (HighscoreList.isScoreOnHighscoreList(score)) {
            game.mainFont.draw(game.batch, "You're on the scoreboard!", 25, 460);
            game.mainFont.draw(game.batch, "Your score: " + score.getScore(), 25, 360);
            game.mainFont.draw(game.batch, "Enter your name: " + inputProcessor.getName(), 25, 300);
        } else {
            game.titleFont.draw(game.batch, "Highscore: " + highscore, 25, 460);
            game.mainFont.draw(game.batch, "Your score: " + score.getScore(), 25, 360);
        }
        game.mainFont.draw(game.batch, "Press anywhere/space", 25, 200);
        game.mainFont.draw(game.batch, "to play again", 25, 140);
        game.mainFont.draw(game.batch, "Press enter for main screen", 25, 80);
        inputProcessor.writePlayerName(score);

        game.batch.end();


        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            HighscoreList.writeHighscore(score);
            game.setScreen(new MainMenuScreen(game));
        } else if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            HighscoreList.writeHighscore(score);
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