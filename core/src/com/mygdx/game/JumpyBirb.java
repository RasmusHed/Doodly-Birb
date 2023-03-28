package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JumpyBirb extends Game {
	public SpriteBatch batch;
	public BitmapFont titleFont;
	public BitmapFont scoreFont;

	@Override
	public void create () {
		titleFont = new BitmapFont(Gdx.files.internal("font/coloredFont.fnt"));
		scoreFont = new BitmapFont(Gdx.files.internal("font/boyrun.fnt"));
		batch = new SpriteBatch();

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();

	}
	@Override
	public void dispose () {

	}

}


