package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Highscore {
    private float score;
    private Vector2 scorePosition;

    public Highscore(float x, float y) {
        this.score = 0;
        scorePosition = new Vector2(x, y);
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
        newScore = (int) ((score - (Settings.FIRST_TUBE_SPAWN_POSITION + (Settings.TUBE_WIDTH / 2)))
                / (Settings.TUBE_SPACING + Settings.TUBE_WIDTH));
        if (newScore < 0) {
            newScore = 0;
        }
        return newScore;
    }

    public static void writeHighscore(int score){
        FileHandle file = Gdx.files.local("highscores.txt");
        List<Integer> highscores = addHighscore(score);
        StringBuilder sb = new StringBuilder();
        for (Integer highscore : highscores){
            sb.append(highscore + "\n");
        }
        String highscoreString = sb.toString();
        file.writeString(highscoreString, false);
    }

    private static List<Integer> addHighscore(int score) {
        List<Integer> highscores = new ArrayList<>();
        for (String highscore : getHighscores()){
            highscores.add(Integer.parseInt(highscore));
        }
        highscores.add(score);
        Collections.sort(highscores);
        Collections.reverse(highscores);
        while (highscores.size() > 5){
            highscores.remove(highscores.size()-1);
        }
        return highscores;
    }

    public static String[] getHighscores() {

        FileHandle file = Gdx.files.local("highscores.txt");
        String filetext = file.readString();
        String[] highscores = filetext.split("\n");


        return highscores;
    }

    public static void printHS(){
        String[] highscores = getHighscores();
        for (int i = 0; i < highscores.length; i++) {
            System.out.println(highscores[i]);
        }
    }
}
