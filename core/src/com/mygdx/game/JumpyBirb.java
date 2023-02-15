package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class JumpyBirb extends ApplicationAdapter {
	//private Texture birbImage;
	//private Rectangle birb;
	private Birb birb;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private static final int TUBE_SPACING = 125;
	private static final int TUBE_COUNT = 4;
	private Tube tube;
	private Array<Tube> tubes;


	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		birb = new Birb(150, 240);

		tubes = new Array<Tube>();
		for(int i = 1; i <= TUBE_COUNT; i++){
			tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
		}

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.position.x = camera.position.x + 1;
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		if (birb.getPosistion().y > 5) {
			birb.gravity();
		}
		batch.draw(birb.getTexture(), birb.getPosistion().x, birb.getPosistion().y);
		//draw the tubes.
		for (Tube tube: tubes) {
			batch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
			batch.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
		}
		batch.end();

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && birb.getPosistion().y < 470) {
			birb.jump();
		}

		for(Tube tube : tubes){
			if(camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
				tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
			}

		}


	}
	@Override
	public void dispose () {

	}
}


