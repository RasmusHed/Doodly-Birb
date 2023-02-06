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
import com.badlogic.gdx.utils.ScreenUtils;

public class JumpyBirb extends ApplicationAdapter {
	//private Texture birbImage;
	//private Rectangle birb;
	private Birb birb;
	private OrthographicCamera camera;
	private SpriteBatch batch;


	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		birb = new Birb(150, 240);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		birb.gravity();

		if (birb.getPosistion().y < 5) {
			birb.setYPosistion(5);
		}
		batch.draw(birb.getTexture(), birb.getPosistion().x, birb.getPosistion().y);
		batch.end();

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && birb.getPosistion().y < 470) {
			birb.jump();
		}

	}
	@Override
	public void dispose () {

	}
}


