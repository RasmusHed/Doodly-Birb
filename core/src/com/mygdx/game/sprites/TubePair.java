package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.Settings;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TubePair {
    private Rectangle topTubeHitBox, bottomTubeHitBox;
    private Texture topTubeTexture, topTubeTexture2, bottomTubeTexture, bottomTubeTexture2;
    private Vector2 topTubePosition, bottomTubePosition;
    private Random rand;

    public TubePair(float x) {
        int randTop = ThreadLocalRandom.current().nextInt(5);
        int randBottom = ThreadLocalRandom.current().nextInt(5);
        if (randTop == 0) {
            topTubeTexture = new Texture("tubes/top1_1.png");
            topTubeTexture2 = new Texture("tubes/top1_2.png");
        } else if (randTop == 1) {
            topTubeTexture = new Texture("tubes/top2_1.png");
            topTubeTexture2 = new Texture("tubes/top2_2.png");
        } else if (randTop == 2) {
            topTubeTexture = new Texture("tubes/top3_1.png");
            topTubeTexture2 = new Texture("tubes/top3_2.png");
        } else if (randTop == 3) {
            topTubeTexture = new Texture("tubes/top4_1.png");
            topTubeTexture2 = new Texture("tubes/top4_2.png");
        } else if (randTop == 4) {
            topTubeTexture = new Texture("tubes/top5_1.png");
            topTubeTexture2 = new Texture("tubes/top5_2.png");
        } else if (randTop == 5) {
            topTubeTexture = new Texture("tubes/top6_1.png");
            topTubeTexture2 = new Texture("tubes/top6_2.png");
        }
        if (randBottom == 0) {
            bottomTubeTexture = new Texture("tubes/bottom1_1.png");
            bottomTubeTexture2 = new Texture("tubes/bottom1_2.png");
        } else if (randBottom == 1) {
            bottomTubeTexture = new Texture("tubes/bottom2_1.png");
            bottomTubeTexture2 = new Texture("tubes/bottom2_2.png");
        } else if (randBottom == 2) {
            bottomTubeTexture = new Texture("tubes/bottom3_1.png");
            bottomTubeTexture2 = new Texture("tubes/bottom3_2.png");
        } else if (randBottom == 3) {
            bottomTubeTexture = new Texture("tubes/bottom4_1.png");
            bottomTubeTexture2 = new Texture("tubes/bottom4_2.png");
        } else if (randBottom == 4) {
            bottomTubeTexture = new Texture("tubes/bottom5_1.png");
            bottomTubeTexture2 = new Texture("tubes/bottom5_2.png");
        } else if (randBottom == 5) {
            bottomTubeTexture = new Texture("tubes/bottom6_1.png");
            bottomTubeTexture2 = new Texture("tubes/bottom6_2.png");
        }
        rand = new Random();

        setTubePairPosition(x);
        setHitBoxPosition();
    }

    private void setHitBoxPosition() {
        topTubeHitBox = new Rectangle(getTopTubePosition().x,
                getTopTubePosition().y,
                Settings.TUBE_WIDTH - 50, Settings.TUBE_HEIGHT - 30);
        bottomTubeHitBox = new Rectangle(getBottomTubePosition().x,
                getBottomTubePosition().y,
                Settings.TUBE_WIDTH - 50, Settings.TUBE_HEIGHT - 20);
    }

    private void setTubePairPosition(float x) {
        topTubePosition = new Vector2(x, rand.nextInt(Settings.GAP_POSITION_FLUCTUATION)
                + Settings.TUBE_GAP + Settings.LOWEST_OPENING);
        bottomTubePosition = new Vector2(x, topTubePosition.y - (Settings.TUBE_GAP + Settings.TUBE_HEIGHT));
    }

    public Texture getTopTubeTexture(int tubeFrame) {
        if (tubeFrame == 0) {
            return topTubeTexture;
        } else {
            return topTubeTexture2;
        }
    }

    public Texture getBottomTubeTexture(int tubeFrame) {
        if (tubeFrame == 0) {
            return bottomTubeTexture;
        } else {
            return bottomTubeTexture2;
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
        bottomTubePosition.set(x, topTubePosition.y - Settings.TUBE_GAP - bottomTubeTexture.getHeight());
        topTubeHitBox.x = getTopTubePosition().x;
        topTubeHitBox.y = getTopTubePosition().y;
        bottomTubeHitBox.x = getBottomTubePosition().x;
        bottomTubeHitBox.y = getBottomTubePosition().y;
    }

}
