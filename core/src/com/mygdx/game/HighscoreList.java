package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighscoreList {
    private List<Score> highscoreList;

    public static boolean isHighscoreBadFormat() {
        boolean badFormat = false;
        if (getHighscores().length == 0) {
            badFormat = true;
        }
        for (String parsedHighscore : getHighscores()) {
            String[] nameAndScore = parsedHighscore.split(": ");
            if (nameAndScore[0].length() != 3) {
                badFormat = true;
            }
            try {
                Integer.parseInt(nameAndScore[1]);
            } catch (NumberFormatException e) {
                System.err.println("Highscore is not a number!");
                badFormat = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Highscores must be a name and a score");
                badFormat = true;
            }
        }
        return badFormat;
    }

    private static List<Score> addHighscore(Score score) {
        List<Score> highscores = new ArrayList<>();
        for (String parsedHighscore : getHighscores()) {
            String[] nameAndScore = parsedHighscore.split(": ");
            Score highscore = new Score(nameAndScore[0], Integer.parseInt(nameAndScore[1]));
            highscores.add(highscore);
        }
        highscores.add(score);
        Collections.sort(highscores);
        Collections.reverse(highscores);
        while (highscores.size() > 5) {
            highscores.remove(highscores.size() - 1);
        }
        return highscores;
    }

    public static boolean isScoreOnHighscoreList(Score score) {
        boolean isScoreOnList = false;
        for (String parsedHighscore : getHighscores()) {
            String[] nameAndScore = parsedHighscore.split(": ");
            int highscore = Integer.parseInt(nameAndScore[1]);
            if (score.getScore() > highscore) {
                isScoreOnList = true;
            }
        }
        return isScoreOnList;
    }

    public static void createHighscoreFile() {
        FileHandle file = Gdx.files.local("highscores.txt");
        file.writeString("---: 0", false);
        for (int i = 0; i < 4; i++) {
            file.writeString("\n---: 0", true);
        }
    }

    public static void writeHighscore(Score score) {
        FileHandle file = Gdx.files.local("highscores.txt");
        List<Score> highscores = addHighscore(score);
        StringBuilder sb = new StringBuilder();
        for (Score highscore : highscores) {
            sb.append(highscore.getPlayerName() + ": " + highscore.getScore() + "\n");
        }
        String highscoreString = sb.toString();
        file.writeString(highscoreString, false);
    }

    public static String[] getHighscores() {

        FileHandle file = Gdx.files.local("highscores.txt");
        String filetext = file.readString();
        String[] highscores = filetext.split("\n");


        return highscores;
    }
}