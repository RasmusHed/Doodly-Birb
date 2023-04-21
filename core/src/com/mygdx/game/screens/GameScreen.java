package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.*;
import com.mygdx.game.sprites.Birb;
import com.mygdx.game.sprites.TubeBatch;
import com.mygdx.game.sprites.TubePair;
import com.mygdx.game.utils.Settings;

public class GameScreen implements Screen {
    final JumpyBirb game;
    final DeathScreen death;
    final TubeBatch tubes;
    final Score score;
    final Birb birb;
    final OrthographicCamera camera;
    //final Background background;
    private Sound deathSound;
    private float elapsedTime = 0;
    private int tubeFrame;

    public GameScreen(JumpyBirb game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);

        // Create the birb
        birb = new Birb(150, Settings.SCREEN_HEIGHT/2);

        // Start the highscore counter
        score = new Score(Settings.SCREEN_WIDTH/2, Settings.SCREEN_HEIGHT - 80);

        // Generates five tubes and adds 400px to the start x position
        tubes = new TubeBatch();

        // Create the background
        //background = new Background(0, 0);

        deathSound = Gdx.audio.newSound(Gdx.files.internal("birb/deathsound.wav"));
        death = new DeathScreen(game, score);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //camera moves to the right in sync (x-position + 1)
        //which makes it look like the tubes move to the left
        camera.position.x += delta * Settings.DELTATIME;
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        if (Settings.getDELTATIME() == 180) {
            elapsedTime += delta;
        } else if (Settings.getDELTATIME() == 120) {
            elapsedTime += delta * 1.5;
        } else {
            elapsedTime += delta * 2;
        }
        if (elapsedTime >= 2){
            elapsedTime = 0;
        }
        tubeFrame = Math.round(elapsedTime);
        if (tubeFrame >= 2){
            tubeFrame = 0;
        }

        birb.gravity();
        birb.jump();
        birb.cantGoBelowScreen();
        checkBirbHitTubes();

        //draw the background and move it one pixel to the right
        //game.batch.draw(background.getBackgroundImage(), background.getBackgroundPosition().x, background.getBackgroundPosition().y);
        //background.setBackgroundPosition(background.getBackgroundPosition().x += delta * Settings.DELTATIME);

        ScreenUtils.clear(0.9f, 0.9f, 0.9f, 0.7f);

        //draw the tubes
        tubes.spawnTubes(game, tubeFrame);
        tubes.respawnTubesWhenOutOfScreen(camera);

        //write score to gamescreen
        game.titleFont.draw(game.batch, "" + score.getScore(), score.getScorePosition().x, score.getScorePosition().y);
        score.setScorePosition(score.getScorePosition().x += delta * Settings.DELTATIME);

        //draw the birb, set update the rectangle position and move birb one pixel to the right
        game.batch.draw(birb.getTexture(), birb.getPosistion().x, birb.getPosistion().y);
        birb.setBirbRectangle(birb.getPosistion().x, birb.getPosistion().y);
        birb.setXPosistion(birb.getPosistion().x += delta * Settings.DELTATIME);

        score.setScore(birb.getPosistion().x);

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

    private void checkBirbHitTubes() {
        for (TubePair tubePair : tubes.getTubes()) {
            if (birb.getBirbHitBox().overlaps(tubePair.getBottomTubeHitBox()) || birb.getBirbHitBox().overlaps(tubePair.getTopTubeHitBox())) {
                deathSound.play();
                game.setScreen(new DeathScreen(game, score));
            }
        }
    }
}
