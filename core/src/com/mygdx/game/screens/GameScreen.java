package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.*;
import com.mygdx.game.sprites.Background;
import com.mygdx.game.sprites.Birb;
import com.mygdx.game.sprites.TubeBatch;
import com.mygdx.game.sprites.TubePair;

public class GameScreen implements Screen {
    final JumpyBirb game;
    final Deathscreen death;
    final TubeBatch tubes;
    final Score score;
    final Birb birb;
    final OrthographicCamera camera;
    final Background background;
    private Sound deathSound;

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
        background = new Background(0, 0);

        deathSound = Gdx.audio.newSound(Gdx.files.internal("birb/deathsound.wav"));
        death = new Deathscreen(game, score);
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

        birb.gravity();
        birb.jump();
        birb.cantGoBelowScreen();
        checkBirbHitTubes();

        //draw the background and move it one pixel to the right
        game.batch.draw(background.getBackgroundImage(), background.getBackgroundPosition().x, background.getBackgroundPosition().y);
        background.setBackgroundPosition(background.getBackgroundPosition().x += delta * Settings.DELTATIME);

        //draw the tubes
        tubes.spawnTubes(game);
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
                game.setScreen(new Deathscreen(game, score));
            }
        }
    }
}
