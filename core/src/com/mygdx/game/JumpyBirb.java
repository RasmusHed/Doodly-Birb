package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class JumpyBirb extends Game {
    private Birb birb;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 5;
    private Tube tube;
    private Array<Tube> tubes;


    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        birb = new Birb(150, 240);

        // Generates five tubes and adds 400 to the start x position
        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH) + 400));
        }

    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        //camera and birb moves to the right in synch (x-position + 1)
        //which makes it look like the tubes move to the left
        camera.position.x = camera.position.x + 1;
        birb.setXPosistion(birb.getPosistion().x + 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        birb.gravity();

        //birb cant move below the screen
        if (birb.getPosistion().y < 5) {
            birb.setYPosistion(5);
        }

        //draw the birb and update the rectangle position
        batch.draw(birb.getTexture(), birb.getPosistion().x, birb.getPosistion().y);
        birb.setBirbRectangle(birb.getPosistion().x, birb.getPosistion().y);

        //draw the tubes.
        for (Tube tube : tubes) {
            batch.draw(tube.getTopTubeTexture(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            batch.draw(tube.getBottomTubeTexture(), tube.getPosBotTube().x, tube.getPosBotTube().y);

            if (birb.getBirbRectangle().overlaps(tube.getBottomTubeBox()) || birb.getBirbRectangle().overlaps(tube.getTopTubeBox())) {
                birb.jump();
            }
        }
        batch.end();

        //makes the birb jump, but not when it's too far above the screen
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && birb.getPosistion().y < 470) {
            birb.jump();
        }
        //respawns the tubes
        for (Tube tube : tubes) {
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTubeTexture().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
        }

    }

    @Override
    public void dispose() {

    }
}


