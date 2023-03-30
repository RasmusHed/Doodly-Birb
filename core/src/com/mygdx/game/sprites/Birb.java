package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Settings;


public class Birb {

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
                Settings.BIRB_HITBOX_WIDTH,
                Settings.BIRB_HITBOX_HEIGHT);
    }

    public void gravity() {
        velocity.add(0, Settings.GRAVITY, 0);
        posistion.add(0, velocity.y, 0);
    }

    public void jump() {
        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && getPosistion().y < 470) {
            velocity.y = Settings.JUMP;
            velocity.scl(0.4f);
        }
    }

    public void cantGoBelowScreen() {
        if (getPosistion().y < 5) {
            setYPosistion(5);
        }
    }
}

