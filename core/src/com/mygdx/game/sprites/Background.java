package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
    private static Texture backgroundImage;
    private static Texture backgroundImageFront;
    private static Texture backgroundImageBack;
    private final Vector2 backgroundPosition;
    private final Vector2 backgroundImageFrontPosition;
    private final Vector2 backgroundImageBackPosition;


    public Background(float x, float y) {
        backgroundImage = new Texture("background/paperBackground.png");
        backgroundImageFront = new Texture("background/paper_background_front.png");
        backgroundImageBack = new Texture("background/paper_background.jpg");
        backgroundPosition = new Vector2(x,y);
        backgroundImageFrontPosition = new Vector2(x,y);
        backgroundImageBackPosition = new Vector2(x,y);
    }

    public Vector2 getBackgroundImageFrontPosition() {
        return backgroundImageFrontPosition;
    }

    public Vector2 getBackgroundImageBackPosition() {
        return backgroundImageBackPosition;
    }

    public Texture getBackgroundImageFront() {
        return backgroundImageFront;
    }

    public void setBackgroundImageFrontPosition(float position) {
        this.backgroundImageFrontPosition.x = position;
    }
    public void setBackgroundImageBackPosition(float position) {
        this.backgroundImageFrontPosition.x = position;
    }

    public Texture getBackgroundImageBack() {
        return backgroundImageBack;
    }

    public Texture getBackgroundImage() {
        return backgroundImage;
    }

    public Vector2 getBackgroundPosition() {
        return backgroundPosition;
    }

}
