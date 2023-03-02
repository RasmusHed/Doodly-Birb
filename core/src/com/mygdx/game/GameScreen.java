package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    public static final int FIRST_TUBE_SPAWN_POSITION = 400;
    final JumpyBirb game;
    final Deathscreen death;
    private Birb birb;
    private OrthographicCamera camera;
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 5;
    private TubePair tubePair;
    public Background background;
    private Array<TubePair> tubes;

    public GameScreen(JumpyBirb game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Create the birb
        birb = new Birb(150, 240);

        // Generates five tubes and adds 400px to the start x position
        tubes = new Array<TubePair>();

        // Create the background
        background = new Background(0,0);

        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new TubePair(i * (TUBE_SPACING + TubePair.TUBE_WIDTH) + FIRST_TUBE_SPAWN_POSITION));
        }
        death = new Deathscreen(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        //camera moves to the right in synch (x-position + 1)
        //which makes it look like the tubes move to the left
        camera.position.x = camera.position.x + 1;
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        //draw the background and move it one pixel to the right
        game.batch.draw(background.getBackgroundImage(), background.getBackgroundPosistion().x, background.getBackgroundPosistion().y);
        background.setBackgroundPosistion(background.getBackgroundPosistion().x + 1);

        //draw the birb, set update the rectangle position and move birb one pixel to the right
        game.batch.draw(birb.getTexture(), birb.getPosistion().x, birb.getPosistion().y);
        birb.setBirbRectangle(birb.getPosistion().x, birb.getPosistion().y);
        birb.setXPosistion(birb.getPosistion().x + 1);

        birb.gravity();
        //birb cant move below the screen
        if (birb.getPosistion().y < 5) {
            birb.setYPosistion(5);
        }


        //draw the tubes.
        for (TubePair tubePair : tubes) {
            game.batch.draw(tubePair.getTopTubeTexture(), tubePair.getTopTubePosition().x, tubePair.getTopTubePosition().y);
            game.batch.draw(tubePair.getBottomTubeTexture(), tubePair.getBottomTubePosition().x, tubePair.getBottomTubePosition().y);
            //respawns the tubes
            if (camera.position.x - (camera.viewportWidth / 2) > tubePair.getTopTubePosition().x + tubePair.getTopTubeTexture().getWidth()) {
                tubePair.reposition(tubePair.getTopTubePosition().x + ((TubePair.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            // check if birb overlaps with tubes, in that case call deathscreen
            if (birb.getBirbRectangle().overlaps(tubePair.getBottomTubeHitBox()) || birb.getBirbRectangle().overlaps(tubePair.getTopTubeHitBox())) {
                game.setScreen(new Deathscreen(game));
            }
        }

        //makes the birb jump, but not when it's too far above the screen
        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && birb.getPosistion().y < 470) {
            birb.jump();
        }
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
}
