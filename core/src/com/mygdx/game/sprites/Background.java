package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
    private static Texture backgroundImage;
    private static SpriteBatch spriteBatch;
    private final Vector2 backgroundPosition;


    public Background(float x, float y) {
        spriteBatch = new SpriteBatch();
        backgroundImage = new Texture("paperBackground.png");
        backgroundPosition = new Vector2(x,y);
    }

    public Texture getBackgroundImage() {
        return backgroundImage;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public Vector2 getBackgroundPosition() {
        return backgroundPosition;
    }

    public void setBackgroundPosition(float position) {
        this.backgroundPosition.x = position;
    }
}
