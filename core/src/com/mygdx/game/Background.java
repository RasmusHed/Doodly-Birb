package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
    private static Texture backgroundImage;
    private static SpriteBatch spriteBatch;
    private Vector2 backgroundPosistion;


    public Background(float x, float y) {
        spriteBatch = new SpriteBatch();
        backgroundImage = new Texture("firstBackground.png");
        backgroundPosistion = new Vector2(x,y);
    }

    public Texture getBackgroundImage() {
        return backgroundImage;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public Vector2 getBackgroundPosistion() {
        return backgroundPosistion;
    }

    public void setBackgroundPosistion(float posistion) {
        this.backgroundPosistion.x = posistion;
    }
}
