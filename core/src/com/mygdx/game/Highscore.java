package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Highscore {
    private float score;
    private Vector2 scorePosition;

    public Highscore(float x, float y) {
        this.score = 0;
        scorePosition = new Vector2(x,y);
    }

    public int getScore() {

        return convertScore(score);
    }

    public Vector2 getScorePosition() {
        return scorePosition;
    }

    public void setScorePosition(Vector2 scorePosition) {
        this.scorePosition = scorePosition;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setScorePosition(float position) {
        this.scorePosition.x = position;
    }

    private int convertScore(float score) {
        int newScore = 0;
        newScore = (int)((score - (Settings.FIRST_TUBE_SPAWN_POSITION + (Settings.TUBE_WIDTH/2)))
                / (Settings.TUBE_SPACING + Settings.TUBE_WIDTH));
        if (newScore < 0) { newScore = 0; }
        return newScore;
    }

}
