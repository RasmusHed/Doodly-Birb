package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.Settings;

public class Score implements Comparable<Score>{
    private int score;
    private String playerName;
    private Vector2 scorePosition;

    public Score(float x, float y) {
        this.score = 0;
        this.playerName = "";
        scorePosition = new Vector2(x, y);
    }

    public Score(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Vector2 getScorePosition() {
        return scorePosition;
    }

    public void setScorePosition(Vector2 scorePosition) {
        this.scorePosition = scorePosition;
    }

    public void setScore(float score) {
        this.score = convertScore(score);
    }

    public void setScorePosition(float position) {
        this.scorePosition.x = position;
    }

    public static int convertScore(float score) {
        int newScore = 0;
        newScore = (int) ((score - (Settings.FIRST_TUBE_SPAWN_POSITION + (Settings.TUBE_WIDTH / 2)))
                / (Settings.TUBE_SPACING + Settings.TUBE_WIDTH));
        if (newScore < 0) {
            newScore = 0;
        }
        return newScore;
    }

    @Override
    public int compareTo(Score o) {
        return getScore().compareTo(o.getScore());
    }
}