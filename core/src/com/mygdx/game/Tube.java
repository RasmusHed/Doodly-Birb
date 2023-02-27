package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 52;
    public Texture getTopTubeTexture() {
        return topTubeTexture;
    }

    public Texture getBottomTubeTexture() {
        return bottomTubeTexture;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }
    //How far the gap can move
    private static final int FLUCTUATION = 280;
    private static final int TUBE_GAP = 150;
    private static final int LOWEST_OPENING = 30;
    private Rectangle topTubeBox;
    private Rectangle bottomTubeBox;
    private Texture topTubeTexture, bottomTubeTexture;
    private Vector2 posTopTube, posBotTube;
    private Random rand;
    public Tube(float x){
        topTubeTexture = new Texture("toptube.png");
        bottomTubeTexture = new Texture("bottomtube.png");
        rand = new Random();


        posTopTube = new Vector2(x,rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x,posTopTube.y - TUBE_GAP - bottomTubeTexture.getHeight());

        topTubeBox = new Rectangle();
        bottomTubeBox = new Rectangle();

        topTubeBox.x = getPosTopTube().x;
        topTubeBox.y = getPosTopTube().y;
        topTubeBox.width = 52;
        topTubeBox.height = 320;

        bottomTubeBox.x = getPosBotTube().x;
        bottomTubeBox.y = getPosBotTube().y;
        bottomTubeBox.width = 52;
        bottomTubeBox.height = 320;
        }
        public void reposition(float x){
            posTopTube.set(x,rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
            posBotTube.set(x,posTopTube.y - TUBE_GAP - bottomTubeTexture.getHeight());
            topTubeBox.x = getPosTopTube().x;
            topTubeBox.y = getPosTopTube().y;
            bottomTubeBox.x = getPosBotTube().x;
            bottomTubeBox.y = getPosBotTube().y;
    }

    public Rectangle getTopTubeBox() {
        return topTubeBox;
    }

    public Rectangle getBottomTubeBox() {
        return bottomTubeBox;
    }
}
