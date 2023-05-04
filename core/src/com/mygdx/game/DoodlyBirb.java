package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.MainMenuScreen;

public class DoodlyBirb extends Game {
	public SpriteBatch batch;
	public BitmapFont titleFont;
	public BitmapFont mainFont;
	public BitmapFont accentFont;

	@Override
	public void create () {
		titleFont = new BitmapFont(Gdx.files.internal("font/boyrun.fnt"));
		mainFont = new BitmapFont(Gdx.files.internal("font/mainFont.fnt"));
		accentFont = new BitmapFont(Gdx.files.internal("font/coloredFont.fnt"));
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


