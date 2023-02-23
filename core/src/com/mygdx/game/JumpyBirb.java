package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class JumpyBirb extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	/*private Birb birb;
	private OrthographicCamera camera;

	private static final int TUBE_SPACING = 125;
	private static final int TUBE_COUNT = 5;
	private Tube tube;
	private Array<Tube> tubes;*/


	@Override
	public void create () {
		font = new BitmapFont();
		batch = new SpriteBatch();

		this.setScreen(new MainMenuScreen(this));

		/*camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		birb = new Birb(150, 240);

		// Generates five tubes and adds 400 to the start x position
		tubes = new Array<Tube>();
		for(int i = 1; i <= TUBE_COUNT; i++){
			tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH) + 400));
		}*/
	}

	@Override
	public void render () {
		super.render();
		/*ScreenUtils.clear(0, 0, 0.2f, 1);

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


		batch.draw(birb.getTexture(), birb.getPosistion().x, birb.getPosistion().y);
		//draw the tubes.
		for (Tube tube: tubes) {
			batch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
			batch.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
		}
		batch.end();

		//makes the birb jump, but not when its too far above the screen
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && birb.getPosistion().y < 470) {
			birb.jump();
		}

		for(Tube tube : tubes){
			if(camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
				tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
			}
		}*/
	}
	@Override
	public void dispose () {

	}
}


