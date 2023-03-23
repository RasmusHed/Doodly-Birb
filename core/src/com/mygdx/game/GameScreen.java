package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen implements Screen {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;
    final JumpyBirb game;
    final Deathscreen death;
    final TubeBatch tubes;
    final Highscore score;
    final Birb birb;
    final OrthographicCamera camera;
    final Background background;

    public GameScreen(JumpyBirb game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        // Create the birb
        birb = new Birb(150, SCREEN_HEIGHT/2);

        // Start the highscore counter
        score = new Highscore(SCREEN_WIDTH/2, SCREEN_HEIGHT - 80);

        // Generates five tubes and adds 400px to the start x position
        tubes = new TubeBatch();

        // Create the background
        background = new Background(0, 0);

        death = new Deathscreen(game, score);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //camera moves to the right in synch (x-position + 1)
        //which makes it look like the tubes move to the left
        camera.position.x = camera.position.x + 1;
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        birb.gravity();
        birb.jump();
        birb.cantGoBelowScreen();
        checkBirbHitTubes();

        //draw the background and move it one pixel to the right
        game.batch.draw(background.getBackgroundImage(), background.getBackgroundPosition().x, background.getBackgroundPosition().y);
        background.setBackgroundPosition(background.getBackgroundPosition().x + 1);

        //draw the tubes
        tubes.spawnTubes(game);
        tubes.respawnTubesWhenOutOfScreen(camera);

        //write score to gamescreen
        game.font.draw(game.batch, "Your score: " + score.getScore(), score.getScorePosition().x, score.getScorePosition().y);
        score.setScorePosition(score.getScorePosition().x + 1);

        //draw the birb, set update the rectangle position and move birb one pixel to the right
        game.batch.draw(birb.getTexture(), birb.getPosistion().x, birb.getPosistion().y);
        birb.setBirbRectangle(birb.getPosistion().x, birb.getPosistion().y);
        birb.setXPosistion(birb.getPosistion().x + 1);

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
                game.setScreen(new Deathscreen(game, score));
            }
        }
    }
}
