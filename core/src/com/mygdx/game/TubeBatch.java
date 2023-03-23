package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;

public class TubeBatch {
    public static final int FIRST_TUBE_SPAWN_POSITION = 400;
    public static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 5;
    private Array<TubePair> tubes;

    public TubeBatch() {
        tubes = new Array<>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new TubePair(i * (TUBE_SPACING + TubePair.TUBE_WIDTH) + FIRST_TUBE_SPAWN_POSITION));
        }
    }
    public Array<TubePair> getTubes() {
        return tubes;
    }


    public void spawnTubes(JumpyBirb game) {
        for (TubePair tubePair : getTubes()) {
            game.batch.draw(tubePair.getTopTubeTexture(), tubePair.getTopTubePosition().x, tubePair.getTopTubePosition().y);
            game.batch.draw(tubePair.getBottomTubeTexture(), tubePair.getBottomTubePosition().x, tubePair.getBottomTubePosition().y);
        }
    }

    public void respawnTubesWhenOutOfScreen(OrthographicCamera camera) {
        for (TubePair tubePair : getTubes()) {
            if (camera.position.x - (camera.viewportWidth / 2) > tubePair.getTopTubePosition().x + TubePair.TUBE_WIDTH) {
                tubePair.reposition(tubePair.getTopTubePosition().x + ((TubePair.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
        }
    }
}
