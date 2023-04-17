package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Settings;

import java.util.Random;
import java.util.Set;

public class TubePair {
    private Rectangle topTubeHitBox, bottomTubeHitBox;
    private Texture topTubeTexture1, topTubeTexture2, topTubeTexture3, topTubeTexture4, topTubeTexture5, topTubeTexture6, topTubeTexture7;
    private Texture bottomTubeTexture1, bottomTubeTexture2, bottomTubeTexture3, bottomTubeTexture4, bottomTubeTexture5, bottomTubeTexture6, bottomTubeTexture7;
    private Vector2 topTubePosition, bottomTubePosition;
    private Random rand, randTop, randBottom;

    public TubePair(float x) {
        topTubeTexture1 = new Texture("pipes/top1correct-removebg-preview.png");
        topTubeTexture2 = new Texture("pipes/top2correct-removebg-preview.png");
        topTubeTexture3 = new Texture("pipes/top3correct-removebg-preview.png");
        topTubeTexture4 = new Texture("pipes/top4correct-removebg-preview.png");
        topTubeTexture5 = new Texture("pipes/top5correct-removebg-preview.png");
        topTubeTexture6 = new Texture("pipes/top6correct-removebg-preview.png");
        topTubeTexture7 = new Texture("pipes/top7correct-removebg-preview.png");
        bottomTubeTexture1 = new Texture("pipes/bottom1correct-removebg-preview.png");
        bottomTubeTexture2 = new Texture("pipes/bottom2correct-removebg-preview.png");
        bottomTubeTexture3 = new Texture("pipes/bottom3correct-removebg-preview.png");
        bottomTubeTexture4 = new Texture("pipes/bottom4correct-removebg-preview.png");
        bottomTubeTexture5 = new Texture("pipes/bottom5correct-removebg-preview.png");
        bottomTubeTexture6 = new Texture("pipes/bottom6correct-removebg-preview.png");
        bottomTubeTexture7 = new Texture("pipes/bottom7correct-removebg-preview.png");
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
        rand = new Random();
        int randomPipe = rand.nextInt(7);
        if (randomPipe == 0) {
            return topTubeTexture1;
        } else if (randomPipe == 1) {
            return topTubeTexture2;
        } else if (randomPipe == 2) {
            return topTubeTexture3;
        } else if (randomPipe == 3) {
            return topTubeTexture4;
        } else if (randomPipe == 4) {
            return topTubeTexture5;
        } else if (randomPipe == 5) {
            return topTubeTexture6;
        } else {
            return topTubeTexture7;
        }
    }

    public Texture getBottomTubeTexture() {
        rand = new Random();
        int randomPipe = rand.nextInt(7);
        if (randomPipe == 0) {
            return bottomTubeTexture1;
        } else if (randomPipe == 1) {
            return bottomTubeTexture2;
        } else if (randomPipe == 2) {
            return bottomTubeTexture3;
        } else if (randomPipe == 3) {
            return bottomTubeTexture4;
        } else if (randomPipe == 4) {
            return bottomTubeTexture5;
        } else if (randomPipe == 5) {
            return bottomTubeTexture6;
        } else {
            return bottomTubeTexture7;
        }
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
        bottomTubePosition.set(x, topTubePosition.y - Settings.TUBE_GAP - bottomTubeTexture1.getHeight());
        topTubeHitBox.x = getTopTubePosition().x;
        topTubeHitBox.y = getTopTubePosition().y;
        bottomTubeHitBox.x = getBottomTubePosition().x;
        bottomTubeHitBox.y = getBottomTubePosition().y;
    }
}
