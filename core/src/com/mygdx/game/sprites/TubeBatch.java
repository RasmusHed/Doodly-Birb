package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.JumpyBirb;
import com.mygdx.game.Settings;

public class TubeBatch {
    private Array<TubePair> tubes;

    public TubeBatch() {
        tubes = new Array<>();
        for (int i = 1; i <= Settings.TUBE_COUNT; i++) {
            tubes.add(new TubePair(i * (Settings.TUBE_SPACING + Settings.TUBE_WIDTH) + Settings.FIRST_TUBE_SPAWN_POSITION));
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
            if (camera.position.x - (camera.viewportWidth / 2) > tubePair.getTopTubePosition().x + Settings.TUBE_WIDTH) {
                tubePair.reposition(tubePair.getTopTubePosition().x + ((Settings.TUBE_WIDTH + Settings.TUBE_SPACING) * Settings.TUBE_COUNT));
            }
        }
    }
}
