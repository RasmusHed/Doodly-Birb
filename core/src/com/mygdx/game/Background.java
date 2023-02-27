package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private static Texture backgroundImage;
    private static SpriteBatch spriteBatch;


    public Background(float x) {
        spriteBatch = new SpriteBatch();
        backgroundImage = new Texture("firstBackground.png");
    }

    public Texture getBackgroundImage() {
        return backgroundImage;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }
}
