package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Settings;

import java.util.Random;


public class Birb {

    private Rectangle birbHitBox;
    private Texture birb;
    private Vector3 posistion;
    private Vector3 velocity;
    private Sound jumpSound;
    private Sound jumpSound1;
    private Sound jumpSound2;
    private Sound jumpSound3;
    private Sound jumpSound4;
    private Random random;


    public Birb(int x, int y) {
        posistion = new Vector3(x, y, 0);
        velocity = new Vector3(10, 0, 0);
        birb = new Texture("birb.png");
        jumpSound= Gdx.audio.newSound(Gdx.files.internal("sound.wav"));
        jumpSound1= Gdx.audio.newSound(Gdx.files.internal("sound(1).wav"));
        jumpSound2= Gdx.audio.newSound(Gdx.files.internal("sound(2).wav"));
        jumpSound3= Gdx.audio.newSound(Gdx.files.internal("sound(3).wav"));
        jumpSound4= Gdx.audio.newSound(Gdx.files.internal("sound(4).wav"));

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
        random = new Random();
        int randomSound = random.nextInt(4);
        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && getPosistion().y < 470) {
            if (randomSound == 0){
                jumpSound.play();
            } else if (randomSound == 1) {
                jumpSound1.play();
            } else if (randomSound == 2) {
                jumpSound2.play();
            } else if (randomSound == 3) {
                jumpSound3.play();
            } else if (randomSound == 4) {
                jumpSound4.play();
            }
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

