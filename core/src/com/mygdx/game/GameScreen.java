package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final JumpyBirb game;
    private Birb birb;
    private OrthographicCamera camera;
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 5;
    private Tube tube;
    private Array<Tube> tubes;

    public GameScreen(JumpyBirb game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        birb = new Birb(150, 240);

        // Generates five tubes and adds 400px to the start x position
        tubes = new Array<Tube>();
        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH) + 400));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        //camera and birb moves to the right in synch (x-position + 1)
        //which makes it look like the tubes move to the left
        camera.position.x = camera.position.x + 1;
        birb.setXPosistion(birb.getPosistion().x + 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        birb.gravity();

        //birb cant move below the screen
        if (birb.getPosistion().y < 5) {
            birb.setYPosistion(5);
        }

        //draw the birb and update the rectangle position
        game.batch.draw(birb.getTexture(), birb.getPosistion().x, birb.getPosistion().y);
        birb.setBirbRectangle(birb.getPosistion().x, birb.getPosistion().y);

        //draw the tubes.
        for (Tube tube : tubes) {
            game.batch.draw(tube.getTopTubeTexture(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            game.batch.draw(tube.getBottomTubeTexture(), tube.getPosBotTube().x, tube.getPosBotTube().y);

            if (birb.getBirbRectangle().overlaps(tube.getBottomTubeBox()) || birb.getBirbRectangle().overlaps(tube.getTopTubeBox())) {
                birb.jump();
            }
        }
        game.batch.end();

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
