package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Settings;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class TubePair {
    private Rectangle topTubeHitBox, bottomTubeHitBox;
    private Texture topTubeTexture, bottomTubeTexture;
    private Vector2 topTubePosition, bottomTubePosition;
    private Random rand;

    public TubePair(float x) {
        int randTop = ThreadLocalRandom.current().nextInt(6);
        int randBottom = ThreadLocalRandom.current().nextInt(6);
        if (randTop > 3){
            topTubeTexture = new Texture("top1.png");   
        } else {
            topTubeTexture = new Texture("top2.png");
        }
        if (randBottom > 3){
            bottomTubeTexture = new Texture("bottom4.png");   
        } else {
            bottomTubeTexture = new Texture("bottom5.png");
        }
        rand = new Random();

        setTubePairPosition(x);
        setHitBoxPosition();
    }

    private void setHitBoxPosition() {
        topTubeHitBox = new Rectangle(getTopTubePosition().x,
                getTopTubePosition().y,
                Settings.TUBE_WIDTH, Settings.TUBE_HEIGHT);
        bottomTubeHitBox = new Rectangle(getBottomTubePosition().x,
                getBottomTubePosition().y,
                Settings.TUBE_WIDTH, Settings.TUBE_HEIGHT);
    }

    private void setTubePairPosition(float x) {
        topTubePosition = new Vector2(x, rand.nextInt(Settings.GAP_POSITION_FLUCTUATION)
                + Settings.TUBE_GAP + Settings.LOWEST_OPENING);
        bottomTubePosition = new Vector2(x, topTubePosition.y - (Settings.TUBE_GAP + Settings.TUBE_HEIGHT));
    }

    public Texture getTopTubeTexture() {
        return topTubeTexture;
    }

    public Texture getBottomTubeTexture() {
        return bottomTubeTexture;
    }

    public Vector2 getTopTubePosition() {
        return topTubePosition;
    }

    public Vector2 getBottomTubePosition() {
        return bottomTubePosition;
    }

    public Rectangle getTopTubeHitBox() {
        return topTubeHitBox;
    }

    public Rectangle getBottomTubeHitBox() {
        return bottomTubeHitBox;
    }

    public void reposition(float x) {
        topTubePosition.set(x, rand.nextInt(Settings.GAP_POSITION_FLUCTUATION) + Settings.TUBE_GAP + Settings.LOWEST_OPENING);
        bottomTubePosition.set(x, topTubePosition.y - Settings.TUBE_GAP - bottomTubeTexture.getHeight());
        topTubeHitBox.x = getTopTubePosition().x;
        topTubeHitBox.y = getTopTubePosition().y;
        bottomTubeHitBox.x = getBottomTubePosition().x;
        bottomTubeHitBox.y = getBottomTubePosition().y;
    }

}
