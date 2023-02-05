package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class JumpyBirb extends ApplicationAdapter {

	private Texture birbImage;
	private Rectangle birb;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	@Override
	public void create () {
		birbImage = new Texture(Gdx.files.internal("birbIcon.png"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		birb = new Rectangle();
		birb.x = 200;
		birb.y = 20;
		birb.width = 64;
		birb.height = 64;

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(birbImage, birb.x, birb.y);
		batch.end();
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) birb.y += 2500 * Gdx.graphics.getDeltaTime();
		birb.y -= 90 * Gdx.graphics.getDeltaTime();
	}
	
	@Override
	public void dispose () {

	}
}
