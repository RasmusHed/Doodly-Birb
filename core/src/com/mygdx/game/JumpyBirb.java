package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JumpyBirb extends Game {
	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create () {
		font = new BitmapFont();
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


