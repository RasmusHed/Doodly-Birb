package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.JumpyBirb;
import com.mygdx.game.Score;
import com.mygdx.game.screens.DeathScreen;
import com.mygdx.game.utils.Settings;

import java.util.Random;


public class Birb {
    private Rectangle birbHitBox;
    private final Texture birb;
    private final Texture jumpingBirb;
    private final Texture deadBirb;
    private Vector3 posistion;
    private Vector3 velocity;
    private final Sound jumpSound, jumpSound1, jumpSound2, jumpSound3, jumpSound4;
    private Random random;
    private final Sound deathSound;
    private float lastPos = 700;

    public Birb(int x, int y) {
        posistion = new Vector3(x, y, 0);
        velocity = new Vector3(10, 0, 0);
        birb = new Texture("birb/birb.png");
        jumpingBirb = new Texture("birb/birb_2.png");
        deadBirb = new Texture("birb/birb_dead.png");
        deathSound = Gdx.audio.newSound(Gdx.files.internal("birb/deathsound.wav"));
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("birb/sound.wav"));
        jumpSound1 = Gdx.audio.newSound(Gdx.files.internal("birb/sound(1).wav"));
        jumpSound2 = Gdx.audio.newSound(Gdx.files.internal("birb/sound(2).wav"));
        jumpSound3 = Gdx.audio.newSound(Gdx.files.internal("birb/sound(3).wav"));
        jumpSound4 = Gdx.audio.newSound(Gdx.files.internal("birb/sound(4).wav"));

        setHitBox();
    }

    public Texture getTexture(boolean dead) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            lastPos = getPosition().y;
        }
        if (dead) {
            return deadBirb;
        } else if (lastPos < getPosition().y - 10) {
            return jumpingBirb;
        } else {
            return birb;
        }
    }

    public Vector3 getPosition() {
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
        birbHitBox = new Rectangle(getPosition().x,
                getPosition().y,
                Settings.BIRB_HITBOX_WIDTH,
                Settings.BIRB_HITBOX_HEIGHT);
    }

    public void gravity() {
        velocity.add(0, Settings.GRAVITY, 0);
        posistion.add(0, velocity.y, 0);
    }

    public void jump() {
        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && getPosition().y < 470) {
            jumpingSound();
            velocity.y = Settings.JUMP;
            velocity.scl(0.4f);
        }
    }

    private void jumpingSound() {
        random = new Random();
        int randomSound = random.nextInt(5);
        if (randomSound == 0) {
            jumpSound.play();
        } else if (randomSound == 1) {
            jumpSound1.play();
        } else if (randomSound == 2) {
            jumpSound2.play();
        } else if (randomSound == 3) {
            jumpSound3.play();
        } else {
            jumpSound4.play();
        }
    }

    public void deathAnimation(JumpyBirb game, Score score) {
        deathSound.play();
        if (getPosition().y < 6) {
            game.setScreen(new DeathScreen(game, score));
        }
    }

    public void cantGoBelowScreen() {
        if (getPosition().y < 5) {
            setYPosistion(5);
        }
    }
}

