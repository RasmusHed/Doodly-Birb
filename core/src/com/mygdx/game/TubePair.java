package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class TubePair {
    public static final int TUBE_WIDTH = 52;
    public static final int TUBE_HEIGHT = 320;
    private static final int GAP_POSITION_FLUCTUATION = 280;
    private static final int TUBE_GAP = 150;
    private static final int LOWEST_OPENING = 30;
    private Rectangle topTubeHitBox, bottomTubeHitBox;
    private Texture topTubeTexture, bottomTubeTexture;
    private Vector2 topTubePosition, bottomTubePosition;
    private Random rand;

    public TubePair(float x) {
        topTubeTexture = new Texture("topdiscopipev1.png");
        bottomTubeTexture = new Texture("bottomdiscopipev1.png");
        rand = new Random();

        setTubePairPosition(x);
        setHitBoxPosition();
    }

    private void setHitBoxPosition() {
        topTubeHitBox = new Rectangle(getTopTubePosition().x,
                getTopTubePosition().y,
                TUBE_WIDTH, TUBE_HEIGHT);
        bottomTubeHitBox = new Rectangle(getBottomTubePosition().x,
                getBottomTubePosition().y,
                TUBE_WIDTH, TUBE_HEIGHT);
    }

    private void setTubePairPosition(float x) {
        topTubePosition = new Vector2(x, rand.nextInt(GAP_POSITION_FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        bottomTubePosition = new Vector2(x, topTubePosition.y - (TUBE_GAP + TUBE_HEIGHT));
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
        topTubePosition.set(x, rand.nextInt(GAP_POSITION_FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        bottomTubePosition.set(x, topTubePosition.y - TUBE_GAP - bottomTubeTexture.getHeight());
        topTubeHitBox.x = getTopTubePosition().x;
        topTubeHitBox.y = getTopTubePosition().y;
        bottomTubeHitBox.x = getBottomTubePosition().x;
        bottomTubeHitBox.y = getBottomTubePosition().y;
    }

}
