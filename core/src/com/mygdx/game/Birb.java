package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class Birb {
    private static final float GRAVITY = -0.234f;
    private static final float JUMP = 8;
    private static final int HITBOX_HEIGHT = 45;
    private static final int HITBOX_WIDTH = 45;

    private Rectangle birbHitBox;
    private Texture birb;
    private Vector3 posistion;
    private Vector3 velocity;


    public Birb(int x, int y) {
        posistion = new Vector3(x, y, 0);
        velocity = new Vector3(10, 0, 0);
        birb = new Texture("birbIcon.png");

        setHitBox();
    }

    public Texture getTexture() {
        return birb;
    }

    public Vector3 getPosistion() {
        return posistion;
    }

    public Rectangle getBirbHitBox() {
        return birbHitBox;
    }

    public void setYPosistion(float posistion) {
        this.posistion.y = posistion;
    }

    public void setXPosistion(float posistion) {
        this.posistion.x = posistion;
    }


    public void setBirbRectangle(float x, float y) {
        this.birbHitBox.x = x;
        this.birbHitBox.y = y;
    }

    private void setHitBox() {
        birbHitBox = new Rectangle(getPosistion().x,
                getPosistion().y,
                HITBOX_WIDTH,
                HITBOX_HEIGHT);
    }

    public void gravity() {
        velocity.add(0, GRAVITY, 0);
        posistion.add(0, velocity.y, 0);
    }

    public void jump() {
        velocity.y = 15;
        velocity.scl(0.4f);
    }
}

